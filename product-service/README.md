# SpringBoot Price Service

Servicio REST para consultar precios aplicables de productos según fecha, marca y prioridad de tarifas.

## Tabla de Contenidos

* Descripción
* Características
* Arquitectura
* Tecnologías
* Instalación
* Uso
* Documentación API
* Testing
* Base de Datos
* Configuración
* Autor

## Descripción

Servicio REST desarrollado en Spring Boot que simula un sistema de precios para una cadena de comercio electrónico. El servicio determina el precio final de un producto considerando:

* Fecha de aplicación (rango de vigencia)
* Identificador de producto
* Identificador de marca/cadena (brand)
* Prioridad de tarifa (la de mayor prioridad prevalece)

El sistema selecciona automáticamente la tarifa con mayor prioridad entre todas las tarifas activas en el rango de fechas solicitado.

## Características

* API RESTful con versionado (/api/v1)
* Arquitectura Hexagonal (Ports & Adapters)
* Base de datos H2 en memoria (fácil testing)
* Documentación interactiva con Swagger/OpenAPI
* Cobertura de tests completa
* Optimización de consultas con índices compuestos
* Validación de parámetros de entrada
* DTOs para desacoplar API de dominio

## Arquitectura

El proyecto implementa Arquitectura Hexagonal:


src
├── main
│   ├── java
│   │   └── com.example.products
│   │       ├── application                          CAPA DE APLICACIÓN
│   │       │   ├── dto
│   │       │   │   ├── ProductRequest.java
│   │       │   │   └── ProductResponse.java
│   │       │   └── ProductsService.java
│   │       │
│   │       ├── domain                               CAPA DE DOMINIO (NÚCLEO PURO)
│   │       │   ├── exception
│   │       │   │   └── ProductException.java
│   │       │   ├── model
│   │       │   │   └── Product.java                 Entidad de Dominio (sin anotaciones JPA)
│   │       │   └── repository
│   │       │       └── ProductRepository.java      Interfaz/Puerto (trabaja con Product)
│   │       │
│   │       ├── infrastructure                       CAPA DE INFRAESTRUCTURA
│   │       │   ├── config                          
│   │       │   │   └── DataInitializer.java       (Carga datos iniciales)
│   │       │   ├── controller
│   │       │   │   ├── ApiErrorResponse.java
│   │       │   │   ├── ProductAPI.java
│   │       │   │   └── ProductController.java  
│   │       │   ├── handler                          
│   │       │   │   └── GlobalExceptionHandler.java
│   │       │   └── persistence
│   │       │       ├── entity                      ENTIDADES JPA
│   │       │       │   └── ProductEntity.java      
│   │       │       ├── mapper                     
│   │       │       │   └── ProductMapper.java
│   │       │       ├── ProductJpaRepository.java  
│   │       │       └── ProductRepositoryImpl.java 
│   │       │
│   │       └── ProductsApplication.java
│   │
│   └── resources
│       ├── application.yml
│       ├── application-dev.yml
│       └── application-test.yml
│
└── test
    └── java
        └── com.example.products
            ├── application
            │   └── ProductsServiceTest.java
            ├── infrastructure
            │   └── persistence
            │       ├──  mapper
            │       │     └── ProductMapperTest.java
            │       └── ProductRepositoryImplTest.java
            │       
            └── ProductControllerTest.java

## Tecnologías

| Tecnología        | Versión | Proposito                |
|-------------------|---------|--------------------------|
| Java              | 21      | Lenguaje Base            |
| Spring boot       | 3.4.3   | Framework principal      |
| Spring Data JPA   | 3.4.3   | Persistencia             |
| H2 Database       | 2.3.232 | Base de datos en memoria |
| Lombok            | 1.18.36 | Reducción de boilerplate |
| JUnit 5           | 5.2     | Testing Unitario         |
| SpringDoc OpenAPI | 2.8.5   | Documentación API        |
| Maven             | 3.14.0  | Gestión de dependencias  |

## Instalación
1. Clona el repositorio:

git clone https://github.com/darjav95dev/springboot-price-service.git
cd springboot-price-service
cd product-service

2. Compila el proyecto

mvn clean install

3. Ejecuta la aplicación

mvn spring-boot:run -Dspring-boot.run.profiles=dev

4. Verificar que está funcionando

curl http://localhost:8080/actuator/health

## Uso

**Endpoint Principal**

GET http://localhost:8080/api/v1/products/{productId}/brands/{brandId}/price?date={date}

**Parámetros**

| Parámetro | Tipo     | Ubicación | Descripción           | Ejemplo             |
|-----------|----------|-----------|-----------------------|---------------------|
| productId | Integer  | Path      | ID del producto       | 35455               |
| brandId   | Integer  | Path      | ID de la marca/cadena | 1                   |
| date      | DateTime | Query     | Fecha de consulta     | 2020-06-14T10:00:00 |

**Ejemplo de Llamada**

GET http://localhost:8080/api/v1/products/35455/brands/1/price?date=2020-06-14T10:00:00

**Respuesta Exitosa (200 OK)**

{
  "productId": 35455,
  "brandId": 1,
  "priceList": 2,
  "startDate": "2020-06-14T15:00:00",
  "endDate": "2020-06-14T18:30:00",
  "price": 25.45,
  "currency": "EUR"
}

**Respuesta de Error (404 Not Found)**

{
  "timestamp": "2024-06-01T12:00:00",
  "status": 404,
  "error": "Product Not Found",
  "message": "No applicable price found for product 35455 from brand 1 on date 2024-06-01T12:00:00"
}

## Documentación API

**Documentación Interactiva**

Accede a la documentación completa con Swagger UI:

http://localhost:8080/swagger-ui/index.html

## Testing

**Ejecutar todos los tests**

mvn test

**Ejecutar con cobertura**

mvn clean verify

El reporte de cobertura estará disponible en:

target/jacoco-report/index.html

**Verificar formato**

mvn spotless:check

**Verificar vulnerabilidades**

mvn org.owasp:dependency-check-maven:check

**Ver árbol de dependencias**

mvn dependency:tree

**Suite de Tests**

El proyecto incluye tests completos en 3 niveles:

### Tests de Integración (Controller)

Tests parametrizados - Validación de precios esperados:

| Test   | Fecha/Hora       | Producto | Brand | Price List | Precio Esperado |
|--------|------------------|----------|-------|------------|-----------------|
| Test 1 | 2020-06-14 10:00 | 35455    | 1     | 1          | 35.50 EUR       |
| Test 2 | 2020-06-14 16:00 | 35455    | 1     | 1          | 25.45 EUR       |
| Test 3 | 2020-06-14 21:00 | 35455    | 1     | 1          | 35.50 EUR       |
| Test 4 | 2020-06-15 10:00 | 35455    | 1     | 1          | 30.50 EUR       |
| Test 5 | 2020-06-16 21:00 | 35455    | 1     | 1          | 38.95 EUR       |

Test de casos negativos 

* Producto no encontrado (ID 99999)
* Marca no encontrada (Brand ID 999)
* Parámetro de fecha faltante (400 Bad Request)
* Formato de fecha inválido (400 Bad Request)
* ID de producto negativo

### Tests Unitarios (Service Layer)

Tests aislados de la lógica de negocio sin dependencias externas.

### Tests de Unitarios (Repository Layer)

Validación de queries JPA y mapeo de entidades.

## Base de Datos

**H2 Console**

* URL: `http://localhost:8080/h2-console`
* Driver Class: `org.h2.Driver`
* JDBC URL: `jdbc:h2:mem:testdb`
* User Name: `sa`
* Password: (dejar en blanco)

### Esquema de la Tabla Prices

CREATE TABLE PRICES (
ID BIGINT AUTO_INCREMENT PRIMARY KEY,
BRAND_ID INT NOT NULL,
START_DATE TIMESTAMP NOT NULL,
END_DATE TIMESTAMP NOT NULL,
PRICE_LIST INT,
PRODUCT_ID INT NOT NULL,
PRIORITY INT NOT NULL,
PRICE DECIMAL(10,2) NOT NULL,
CURR VARCHAR(3)
);

--Índice compuesto para optimización de queries

CREATE INDEX idx_product_brand_dates_priority
ON PRICES(PRODUCT_ID, BRAND_ID, START_DATE, END_DATE, PRIORITY);

**Datos de Ejemplo**

El servicio inicializa automáticamente con datos de prueba:

INSERT INTO PRICES VALUES
(1, 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR'),
(2, 1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR'),
(3, 1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR'),
(4, 1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR');

## Configuración

Las configuraciones principales se encuentran en `src/main/resources/application-dev.properties`:

**Puerto del servidor**
server.port=8080
**Configuración**
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
**Configuración JPA**
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
**Configuración de logging**
logging.level.org.springframework=INFO
logging.level.com.example=DEBUG

**Perfiles Disponibles**

* dev - Desarrollo (logs detallados, H2 console habilitada)
* test - Testing (configuración optimizada para tests)

## Autor
Darío Javier Díaz Caballero

GitHub: @darjav95dev
LinkedIn: https://www.linkedin.com/in/darjav95dev/

