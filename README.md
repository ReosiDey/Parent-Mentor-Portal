# Parent-Mentor-Portal
Tech Stack: React.js, Spring Boot, MySQL, JWT, Microservices. Developed a parent-mentor portal with role-based authentication, attendance tracking, and parent-student mapping. Built a scalable microservices architecture using API Gateway and Eureka. Automated attendance reporting via secure REST APIs, improving communication and transparency.


# Description
The Parent Mentor Portal is a full-stack web application developed to improve communication between parents, mentors, and educational institutions. The platform allows mentors to manage student attendance, parents to monitor their child's academic activities, and administrators to efficiently manage users through a secure and scalable system.

The project follows a microservices architecture using Spring Boot and React, making it easier to scale, maintain, and extend in the future.

## Features

- Secure login using JWT Authentication
- Role-based access for Admin, Mentor, Parent, and Student
- Parent-Student mapping for easy monitoring
- Attendance management system
- REST APIs for communication between services
- API Gateway for centralized routing
- Eureka Service Discovery for microservice registration
- MySQL database for data storage
- Responsive React-based user interface
  
## Tech Stack

### Frontend
- React.js
- Vite
- HTML
- CSS
- JavaScript

### Backend
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT Authentication
- REST APIs

### Database
- MySQL

### Architecture
- Microservices
- API Gateway
- Eureka Service Discovery

### Tools
- Git
- GitHub
- Maven
- Postman
- VS Code
- IntelliJ IDEA

## Project Structure

```
Parent-Mentor-Portal
│
├── parent-frontend
├── api-gateway
├── auth-service
├── eureka-server
├── parent-service
├── mentor-service
├── attendance-service
└── README.md

```

## How It Works

1. Users log in securely using JWT-based authentication.
2. Requests are routed through the API Gateway.
3. Each microservice handles a specific responsibility such as authentication, attendance, or parent management.
4. Services communicate through REST APIs and are registered using Eureka.
5. Data is stored securely in a MySQL database.

## Getting Started

### Clone the repository

```bash
git clone https://github.com/ReosiDey/Parent-Mentor-Portal.git
```

### Run the backend services

```bash
mvn clean install
mvn spring-boot:run
```

### Run the frontend

```bash
cd parent-frontend
npm install
npm run dev
```

## About the Project

This project was built to gain hands-on experience with full-stack development and microservices. It demonstrates concepts such as authentication, role-based authorization, RESTful APIs, service discovery, API Gateway, and scalable backend architecture while providing a practical solution for academic communication.

## Author

**Reosi Dey**
