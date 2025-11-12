# 🏷️ SpringBoot Price Service

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Coverage](https://img.shields.io/badge/Coverage-92%25-brightgreen.svg)]()

> Servicio REST para consultar precios aplicables de productos según fecha, marca y prioridad de tarifas.

---

## 📋 Tabla de Contenidos

- [Descripción](#-descripción)
- [Características](#-características)
- [Arquitectura](#-arquitectura)
- [Tecnologías](#️-tecnologías)
- [Instalación](#-instalación)
- [Uso](#-uso)
- [Documentación API](#-documentación-api)
- [Testing](#-testing)
- [Base de Datos](#-base-de-datos)
- [Configuración](#️-configuración)
- [Autor](#-autor)

---

## 🎯 Descripción

Servicio REST desarrollado en **Spring Boot** que simula un sistema de precios para una cadena de comercio electrónico. El servicio determina el precio final de un producto considerando:

- 📅 **Fecha de aplicación** (rango de vigencia)
- 🏷️ **Identificador de producto**
- 🏢 **Identificador de marca/cadena** (brand)
- ⭐ **Prioridad de tarifa** (la de mayor prioridad prevalece)

El sistema selecciona automáticamente la tarifa con **mayor prioridad** entre todas las tarifas activas en el rango de fechas solicitado.

---

## ✨ Características

- ✅ API RESTful con versionado (`/api/v1`)
- 🏗️ Arquitectura Hexagonal (Ports & Adapters)
- 💾 Base de datos H2 en memoria (fácil testing)
- 📝 Documentación interactiva con Swagger/OpenAPI
- 🧪 Cobertura de tests completa (+90%)
- 🚀 Optimización de consultas con índices compuestos
- 🔒 Validación de parámetros de entrada
- 📦 DTOs para desacoplar API de dominio
- 🎨 Manejo global de errores con `@ControllerAdvice`

---

## 🏗️ Arquitectura

El proyecto implementa **Arquitectura Hexagonal** con clara separación de capas:

```
src/
├── main/
│   ├── java/
│   │   └── com.example.products/
│   │       │
│   │       ├── 📂 application/                    ⬅️ CAPA DE APLICACIÓN
│   │       │   ├── dto/
│   │       │   │   ├── ProductRequest.java
│   │       │   │   └── ProductResponse.java
│   │       │   └── ProductsService.java
│   │       │
│   │       ├── 📂 domain/                         ⬅️ CAPA DE DOMINIO (NÚCLEO PURO)
│   │       │   ├── exception/
│   │       │   │   └── ProductException.java
│   │       │   ├── model/
│   │       │   │   └── Product.java               (sin anotaciones JPA)
│   │       │   └── repository/
│   │       │       └── ProductRepository.java     (Interfaz/Puerto)
│   │       │
│   │       ├── 📂 infrastructure/                 ⬅️ CAPA DE INFRAESTRUCTURA
│   │       │   ├── config/
│   │       │   │   └── DataInitializer.java
│   │       │   ├── controller/
│   │       │   │   ├── ApiErrorResponse.java
│   │       │   │   ├── ProductAPI.java
│   │       │   │   └── ProductController.java
│   │       │   ├── handler/
│   │       │   │   └── GlobalExceptionHandler.java
│   │       │   └── persistence/
│   │       │       ├── entity/
│   │       │       │   └── ProductEntity.java     (Entidad JPA)
│   │       │       ├── mapper/
│   │       │       │   └── ProductMapper.java
│   │       │       ├── ProductJpaRepository.java
│   │       │       └── ProductRepositoryImpl.java (Adaptador)
│   │       │
│   │       └── ProductsApplication.java
│   │
│   └── resources/
│       ├── application.yml
│       ├── application-dev.yml
│       └── application-test.yml
│
└── test/
    └── java/
        └── com.example.products/
            ├── application/
            │   └── ProductsServiceTest.java
            ├── infrastructure/
            │   └── persistence/
            │       ├── mapper/
            │       │   └── ProductMapperTest.java
            │       └── ProductRepositoryImplTest.java
            └── ProductControllerTest.java
```

### Flujo de Datos

```
Controller → Service → Repository (interfaz) → RepositoryImpl → JPA → Database
    ↓           ↓            ↓                        ↓             ↓
  DTOs      Domain       Domain                   Entity        Entity
           Objects      Objects                   + Mapper
```

---

## 🛠️ Tecnologías

| Tecnología | Versión | Propósito |
|-----------|---------|-----------|
| Java | 21 | Lenguaje base |
| Spring Boot | 3.4.3 | Framework principal |
| Spring Data JPA | 3.4.3 | Persistencia |
| H2 Database | 2.3.232 | Base de datos en memoria |
| Lombok | 1.18.36 | Reducción de boilerplate |
| JUnit 5 | 5.10.2 | Testing unitario |
| SpringDoc OpenAPI | 2.8.5 | Documentación API |
| Maven | 3.14.0 | Gestión de dependencias |
| JaCoCo | 0.8.12 | Cobertura de código |

---

## 📥 Instalación

### Prerrequisitos

- ☕ Java 21 o superior
- 📦 Maven 3.8+
- 🔧 Git

### Pasos

1. **Clonar el repositorio**

```bash
git clone https://github.com/darjav95dev/springboot-price-service.git
cd springboot-price-service/product-service
```

2. **Compilar el proyecto**

```bash
mvn clean install
```

3. **Ejecutar la aplicación**

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

4. **Verificar que está funcionando**

```bash
curl http://localhost:8080/actuator/health
```

> 💡 La aplicación estará disponible en `http://localhost:8080`

---

## 💻 Uso

### Endpoint Principal

```http
GET /api/v1/products/{productId}/brands/{brandId}/price?date={date}
```

### Parámetros

| Parámetro | Tipo | Ubicación | Descripción | Ejemplo |
|-----------|------|-----------|-------------|---------|
| `productId` | Integer | Path | ID del producto | `35455` |
| `brandId` | Integer | Path | ID de la marca/cadena | `1` |
| `date` | DateTime | Query | Fecha de consulta (ISO-8601) | `2020-06-14T10:00:00` |

### Ejemplo de Llamada

```bash
curl -X GET "http://localhost:8080/api/v1/products/35455/brands/1/price?date=2020-06-14T10:00:00"
```

### Respuesta Exitosa (200 OK)

```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 2,
  "startDate": "2020-06-14T15:00:00",
  "endDate": "2020-06-14T18:30:00",
  "price": 25.45,
  "currency": "EUR"
}
```

### Respuesta de Error (404 Not Found)

```json
{
  "timestamp": "2024-06-01T12:00:00",
  "status": 404,
  "error": "Product Not Found",
  "message": "No applicable price found for product 35455 from brand 1 on date 2024-06-01T12:00:00"
}
```

---

## 📚 Documentación API

### Swagger UI

Accede a la documentación interactiva completa:

🔗 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

### OpenAPI Specification

Descarga la especificación en formato JSON:

🔗 **[http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)**

---

## 🧪 Testing

### Ejecutar Tests

```bash
# Todos los tests
mvn test

# Con cobertura de código
mvn clean verify

# Ver reporte de cobertura
# Abrir: target/jacoco-report/index.html
```

### Verificaciones de Calidad

```bash
# Verificar formato de código
mvn spotless:check

# Verificar vulnerabilidades de seguridad
mvn org.owasp:dependency-check-maven:check

# Ver árbol de dependencias
mvn dependency:tree
```

### Suite de Tests

El proyecto incluye **tests completos en 3 niveles**:

#### 🔵 Tests de Integración (Controller)

**Tests parametrizados** - Validación de precios esperados:

| Test | Fecha/Hora | Producto | Brand | Price List | Precio Esperado |
|------|-----------|----------|-------|-----------|-----------------|
| Test 1 | 2020-06-14 10:00 | 35455 | 1 | 1 | 35.50 EUR |
| Test 2 | 2020-06-14 16:00 | 35455 | 1 | 2 | 25.45 EUR |
| Test 3 | 2020-06-14 21:00 | 35455 | 1 | 1 | 35.50 EUR |
| Test 4 | 2020-06-15 10:00 | 35455 | 1 | 3 | 30.50 EUR |
| Test 5 | 2020-06-16 21:00 | 35455 | 1 | 4 | 38.95 EUR |

**Tests de casos negativos:**

- ❌ Producto no encontrado (ID 99999)
- ❌ Marca no encontrada (Brand ID 999)
- ❌ Parámetro de fecha faltante (400 Bad Request)
- ❌ Formato de fecha inválido (400 Bad Request)
- ❌ ID de producto negativo

#### 🟢 Tests Unitarios (Service Layer)

Tests aislados de la lógica de negocio sin dependencias externas.

#### 🟡 Tests Unitarios (Repository Layer)

Validación de queries JPA y mapeo de entidades (`ProductMapper`, `ProductRepositoryImpl`).

### Cobertura de Código

- **Controller Layer:** 100%
- **Service Layer:** 95%+
- **Repository Layer:** 90%+
- **Overall Coverage:** 92%+

---

## 💾 Base de Datos

### H2 Console

Accede a la consola web de H2 para inspeccionar datos:

🔗 **[http://localhost:8080/h2-console](http://localhost:8080/h2-console)**

**Credenciales:**

| Campo | Valor |
|-------|-------|
| Driver Class | `org.h2.Driver` |
| JDBC URL | `jdbc:h2:mem:testdb` |
| Username | `sa` |
| Password | _(dejar en blanco)_ |

### Esquema de la Tabla

```sql
CREATE TABLE PRODUCTS (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    BRAND_ID INT NOT NULL,
    START_DATE TIMESTAMP NOT NULL,
    END_DATE TIMESTAMP NOT NULL,
    PRICE_LIST INT,
    PRODUCT_ID INT NOT NULL,
    PRIORITY INT NOT NULL,
    PRICE DECIMAL(10,2) NOT NULL,
    CURRENCY VARCHAR(3)
);

-- Índice compuesto para optimización de queries
CREATE INDEX idx_product_brand_dates_priority 
ON PRODUCTS(PRODUCT_ID, BRAND_ID, START_DATE, END_DATE, PRIORITY);
```

### Datos de Ejemplo

El servicio inicializa automáticamente con datos de prueba:

```sql
INSERT INTO PRODUCTS VALUES
(1, 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, 35455, 0, 35.50, 'EUR'),
(2, 1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, 35455, 1, 25.45, 'EUR'),
(3, 1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, 35455, 1, 30.50, 'EUR'),
(4, 1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, 35455, 1, 38.95, 'EUR');
```

---

## ⚙️ Configuración

### Archivo de Configuración

Las configuraciones principales se encuentran en `src/main/resources/application-dev.yml`:

```yaml
server:
  port: 8080

spring:
  application:
    name: product-service
  
  h2:
    console:
      enabled: true
      path: /h2-console
  
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password:
  
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: false

logging:
  level:
    org.springframework: INFO
    com.example.products: DEBUG
```

### Perfiles Disponibles

| Perfil | Descripción | Uso |
|--------|-------------|-----|
| `dev` | Desarrollo | Logs detallados, H2 console habilitada |
| `test` | Testing | Configuración optimizada para tests |

**Cambiar de perfil:**

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=test
```

---

## 🎯 Principios de Diseño

Este proyecto implementa:

- ✅ **SOLID Principles**
    - Single Responsibility
    - Open/Closed
    - Liskov Substitution
    - Interface Segregation
    - Dependency Inversion

- ✅ **Clean Architecture**
    - Arquitectura Hexagonal
    - Separation of Concerns
    - Dependency Rule

- ✅ **Best Practices**
    - RESTful API design
    - DTOs para desacoplar capas
    - Repository Pattern
    - Mapper Pattern
    - Exception Handling con `@ControllerAdvice`

---

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo [LICENSE](LICENSE) para más detalles.

---

## 👤 Autor

**Darío Javier Díaz Caballero**

- 🐙 GitHub: [@darjav95dev](https://github.com/darjav95dev)
- 💼 LinkedIn: [darjav95dev](https://www.linkedin.com/in/darjav95dev/)
- 📧 Email: dario95dev@gmail.com

---

<p align="center">
  <a href="#-springboot-price-service">⬆️ Volver arriba</a>
</p>