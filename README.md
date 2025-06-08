# Task Manager API

A task management RESTful API built with Spring Boot, PostgreSQL, Lombok and MapStruct.

## Features

- User authentication with Spring Security
- Token-based authentication
- Task creation, assignment, and status updates
- Commenting system per task
- PostgreSQL integration for persistence
- Clean architecture with MapStruct for DTO mapping

## Technologies

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- MapStruct

## 🛠️ Setup and Running Locally

1. **Clone the repository**

   
2. **Create PostgreSQL database and user**

Example using psql terminal:

`CREATE DATABASE task_manager;
CREATE USER task_user WITH ENCRYPTED PASSWORD 'task_password';
GRANT ALL PRIVILEGES ON DATABASE task_manager TO task_user;`

3. **Update application.properties:**

`spring.datasource.url=jdbc:postgresql://localhost:5432/task_manager
spring.datasource.username=task_user
spring.datasource.password=task_password
spring.datasource.driver-class-name=org.postgresql.Driver`

`spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect`

`spring.jpa.show-sql=true
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE`


4. **Run the application:**

Use your IDE (e.g. IntelliJ) to run the `TaskManagerApplication` main class, or from terminal:

`./gradlew bootRun`

5. **Access the API:**

By default, the application runs on:

`http://localhost:8080`

6. **Testing**
Run all tests with:

`./gradlew test
`