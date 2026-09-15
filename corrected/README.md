# Spring Boot REST API — Practical 1 (S1 Reference Baseline)

A clean, production-minded, standard Spring Boot REST API for Employee Management demonstrating layered backend architecture.

---

## 1. Project Overview & Architecture

This application demonstrates the complete end-to-end data flow of a modern Spring Boot REST API:

```text
                 HTTP Client
                     │
                     ▼
              REST Controller (@RestController)
              • Thin HTTP layer
              • URL mapping & validation
                     │
                     ▼
                 Request DTO (EmployeeRequest)
                     │
                     ▼
               Service Layer (EmployeeService)
              • Business logic & rules
              • SLF4J logging
                     │
                     ▼
                 Entity Model (Employee)
                     │
                     ▼
           Repository Layer (EmployeeRepository)
              • Spring Data JPA abstraction
                     │
                     ▼
             Hibernate ORM & JDBC
                     │
                     ▼
             Relational Database (MySQL)
```

---

## 2. Technology Stack

* **Language**: Java 17 / 21
* **Framework**: Spring Boot (Spring Web, Spring Data JPA, Jakarta Bean Validation)
* **ORM / Persistence**: Hibernate through Spring Data JPA
* **Database**: MySQL Relational Database
* **Utilities**: Lombok (Selective annotations: `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@Builder`)
* **Logging**: SLF4J via Lombok `@Slf4J`
* **Build Tool**: Maven

---

## 3. Configuration & Database Setup

Configuration is externalized in `src/main/resources/application.properties`.

### Database Configuration: MySQL Database
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/emsdb?createDatabaseIfNotExist=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=root

# Hibernate / JPA Settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

## 4. How to Build & Run

### Build and Run Unit Tests
```bash
./mvnw clean test
```

### Run Application
```bash
./mvnw spring-boot:run
```
The server starts on port `8080` by default and connects to your local MySQL database.

---

## 5. API Endpoints Specification

| Method | Endpoint | Description | Status Code |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/hello` | Hello World greeting endpoint | `200 OK` |
| **GET** | `/api/employees` | Fetch all employees | `200 OK` |
| **GET** | `/api/employees/{id}` | Fetch employee by ID | `200 OK` / `404 Not Found` |
| **GET** | `/api/employees/email/{email}` | Fetch employee by email | `200 OK` / `404 Not Found` |
| **POST** | `/api/employees` | Create new employee | `201 Created` / `400 Bad Request` / `409 Conflict` |
| **PUT** | `/api/employees/{id}` | Update existing employee | `200 OK` / `400 Bad Request` / `404 Not Found` / `409 Conflict` |
| **DELETE** | `/api/employees/{id}` | Delete employee by ID | `204 No Content` / `404 Not Found` |

---

## 6. Sample HTTP Requests & Responses

### 1. Create Employee (`POST /api/employees`)
**Request Body**:
```json
{
  "name": "Alice Smith",
  "email": "alice@example.com",
  "mobileNo": "9876543210",
  "department": "Engineering",
  "salary": 85000.00
}
```
**Response (`201 Created`)**:
```json
{
  "id": 1,
  "name": "Alice Smith",
  "email": "alice@example.com",
  "mobileNo": "9876543210",
  "department": "Engineering",
  "salary": 85000.00
}
```

### 2. Validation Error (`POST /api/employees`)
**Request Body**:
```json
{
  "name": "",
  "email": "invalid-email",
  "mobileNo": "123",
  "salary": -500.00
}
```
**Response (`400 Bad Request`)**:
```json
{
  "name": "Name is required",
  "email": "Invalid email format",
  "mobileNo": "Mobile number must be between 10 and 15 digits",
  "salary": "Salary must be greater than zero"
}
```

### 3. Duplicate Email Error (`POST /api/employees`)
**Response (`409 Conflict`)**:
```text
Employee with email 'alice@example.com' already exists
```

---

## 7. Key Architectural Decisions

1. **Thin Controller Design**: Controllers handle only HTTP routing, request validation (`@Valid`), and status code responses. All business checks and mapping logic live in `EmployeeServiceImpl`.
2. **Constructor Dependency Injection**: Dependencies (`EmployeeRepository`) are injected via constructor to support immutability, testability, and explicit declaration.
3. **DTO Separation & Manual Mapping**: DTOs (`EmployeeRequest`, `EmployeeResponse`) encapsulate the API boundary, keeping sensitive persistence fields (like passwords) protected. Manual conversion via `EmployeeMapper` avoids MapStruct overhead for S1.
4. **Selective Lombok Usage**: `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`, and `@Builder` are used selectively. `@Data` is avoided on JPA entities to prevent issues with Hibernate lazy loading proxies and circular references.
5. **S3 Transaction Boundary Deferral**: Declarative transaction management (`@Transactional`) is intentionally omitted in S1 and deferred to S3.
