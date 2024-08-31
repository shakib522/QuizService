# Quiz Service

This repository contains the `quiz-service`, a microservice that handles the management of quizzes within the application ecosystem. It is part of a microservices architecture, integrating with the `question-service` through inter-service communication.

## Overview

The `quiz-service` is responsible for creating, updating, retrieving, and deleting quizzes. It communicates with the `question-service` using OpenFeign for fetching questions associated with quizzes. The service is registered with Eureka Server for service discovery and uses Spring Data JPA for database operations with PostgreSQL.

## Other Repository


## Postman API Collection
[Quiz Craft.postman_collection.json](https://github.com/user-attachments/files/16823586/Quiz.Craft.postman_collection.json)



## Technologies Used

- **OpenFeign**: For declarative REST client communication between microservices.
- **API Gateway**: Serves as the single entry point for routing requests to the microservices.
- **Eureka Client**: Allows the service to register with the Eureka Server for service discovery.
- **Eureka Server**: A service registry used for service discovery.
- **PostgreSQL**: A relational database used to store quiz data.
- **Spring Data JPA**: A part of the Spring Data family that allows for easy integration and management of database operations.

## Prerequisites

- Java 17 or later
- Maven 3.6 or later
- PostgreSQL 13 or later
- Spring Boot 3.x
- Docker (optional, for containerization)

## Getting Started

### Clone the Repository

```bash
git clone <repository-url>
cd quiz-service
```

### Configuration

1. **Database Configuration**:
   Update the `application.yml` or `application.properties` file with the PostgreSQL connection details.

   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://localhost:5432/quizdb
       username: your-username
       password: your-password
     jpa:
       hibernate:
         ddl-auto: update
       show-sql: true
       database-platform: org.hibernate.dialect.PostgreSQLDialect
   ```

2. **Eureka Client Configuration**:
   Ensure the service registers with the Eureka Server by setting the Eureka client properties.

   ```yaml
   eureka:
     client:
       service-url:
         defaultZone: http://localhost:8761/eureka/
   ```

### Running the Quiz Service

To start the `quiz-service`, run the following command from the root directory:

```bash
mvn spring-boot:run
```

Alternatively, package the application as a JAR and run it:

```bash
mvn clean package
java -jar target/quiz-service-0.0.1-SNAPSHOT.jar
```

### Accessing the API

Once the service is up and running, you can access the API at:

```
http://localhost:<port>/api/quizzes
```

The default port is typically configured in the `application.yml` or `application.properties` file.

### API Endpoints

## Inter-Service Communication

The `quiz-service` uses OpenFeign to communicate with the `question-service` for retrieving and managing questions associated with quizzes. Ensure that the `question-service` is also registered with the Eureka Server.

## Docker Support

### Build Docker Image

```bash
docker build -t quiz-service .
```

### Run Docker Container

```bash
docker run -d -p <port>:<port> --name quiz-service quiz-service
```

Ensure that PostgreSQL and Eureka Server are running and accessible to the Docker container.

## Troubleshooting

- **Service Not Registering with Eureka**: Check the Eureka Server URL and ensure it's accessible. Verify the Eureka Client configuration in the `application.yml` file.
- **Database Connection Issues**: Verify PostgreSQL is running, and the connection details in the configuration file are correct.
- **Inter-Service Communication Failures**: Ensure that both services are registered with Eureka and reachable through the correct routes defined in the API Gateway.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For any questions or issues, please contact shakib at shakibhasann522@gmail.com
