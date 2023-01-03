# REST API for Ecommerce Website.
An E-commerce REST API built over a span of 1 week. The API can perform all the CRUD operation with customer validation at every step.

## Tech Stack

* Java
* Spring Framework
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Swagger

## Modules

* Login module
* User Module
* Admin Module
* Customer Module
* Product Module
* Order Module
* Payment Module
* Cart Module
* Feedback Module
* Seller Module


## Features

* CRUD Operation for user.
* CRUD Operation for product.
* CRUD Operation for order.
* CRUD Operation for cart.
* CRUD Operation for address.

## Installation & Run

* Before running the API server, you should update the database config inside the [application.properties](https://github.com/Afzhal-ahmed-s/E-commerece/blob/main/E-commerce/src/main/resources/application.properties) file. 
* Update the port number, username and password as per your local database config.

```
    server.port=8888
    spring.datasource.url=jdbc:mysql://localhost:3306/vaccinescheduler;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root
```

## API Root Endpoint

`https://localhost:8888/`

`http://localhost:8888/swagger-ui/`
