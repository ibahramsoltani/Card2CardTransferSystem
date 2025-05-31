# Card2CardTransferSystem

A simple Card-to-Card money transfer system built with Spring Boot, secured via JWT, and powered by RabbitMQ for transaction processing. Designed for secure, scalable, and extensible bank-like transfer operations.

---

## Features

✅ JWT-based user authentication  
✅ Card-to-card transfers using RabbitMQ queues  
✅ In-memory H2 database for development/testing  
✅ RESTful APIs for authentication and transfers  
✅ Transfer limit enforcement per card  
✅ Stateless Spring Security configuration

---

## Technologies Used

- Java 17  
- Spring Boot  
- Spring Security (JWT)  
- Spring Data JPA  
- RabbitMQ  
- H2 Database  
- Maven

---

## Requirements

Make sure you have the following installed:

- Java 17+
- Maven 3.5.0
- RabbitMQ (running locally at `localhost:5672`)

