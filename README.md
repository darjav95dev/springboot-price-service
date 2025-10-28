# SpringBoot Price Service

Servicio REST desarrollado en Spring Boot para consultar el precio aplicable de un producto en una fecha determinada, según la tarifa vigente y la prioridad de aplicación.

## Descripción

Este servicio simula una base de datos de precios (`PRICES`) de una cadena de comercio electrónico. A través de un endpoint REST, permite consultar el precio final de un producto en función de:

- Fecha de aplicación
- Identificador de producto
- Identificador de cadena (brand)

La lógica del servicio selecciona la tarifa con mayor prioridad dentro del rango de fechas aplicable.

## Tecnologías utilizadas
- Java 21
- Spring Boot
- H2 Database (en memoria)
- JPA / Hibernate
- Maven
- JUnit / Spring Test

## Cómo ejecutar el proyecto
1. Clona el repositorio:

git clone https://github.com/darjav95dev/springboot-price-service.git
cd springboot-price-service

2. Compila y ejecuta la aplicación con Maven:
   mvn spring-boot:run

3. La aplicación estará disponible en `GET http://localhost:8080/api/productos`.

4. Parametros de entrada:
    - `applicationDate`: Fecha y hora para la que se desea consultar el precio (formato: `yyyy-MM-dd'T'HH:mm:ss`).
    - `productId`: Identificador del producto.
    - `brandId`: Identificador de la cadena (brand).

5. Ejemplo de llamada al endpoint:

GET http://localhost:8080/api/prices?applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1

6. Ejemplo de respuesta

{
"productId": 35455,
"brandId": 1,
"priceList": 2,
"startDate": "2020-06-14T15:00:00",
"endDate": "2020-06-14T18:30:00",
"price": 25.45,
"currency": "EUR"
}

7. Acceso a la base de datos H2 (opcional):
    - URL: `http://localhost:8080/h2-console`
    - Driver Class: `org.h2.Driver`
    - JDBC URL: `jdbc:h2:mem:testdb`
    - User Name: `sa`
    - Password: (dejar en blanco)

8. Pruebas unitarias:
    - Ejecuta las pruebas con Maven:
    - mvn test
    - Las pruebas verifican la lógica de selección de precios según los criterios especificados.
    - Los casos de prueba incluyen diferentes fechas y productos para asegurar la correcta aplicación de las tarifas:

-          Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
-          Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)


## 📄 Documentación API

La documentación de la API está disponible en Swagger UI en la siguiente URL:
http://localhost:8080/swagger-ui/index.html#/