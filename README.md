# Employee Management System

A backend-based **Employee Management System** developed using **Spring Boot** and **Spring Data JPA**.

The project provides REST APIs for employee management, department assignment, leave management, employee document upload, Redis caching, JPA auditing, batch employee creation, exception handling, Swagger/OpenAPI documentation, and Spring Boot Actuator monitoring.

---

## 🚀 Features

### Employee Management
- Create employee
- Get all employees
- Get employee by ID
- Search employees
- Update employee
- Delete employee
- Find employee by email/mobile number
- Pagination and sorting
- Employee and address relationship mapping

### Department Management
- Create department
- Get department details
- Assign department to employee
- Employee-Department relationship using `@ManyToOne`

### Leave Management
- Apply for leave
- Check leave balance before applying
- Leave request is created with `PENDING` status when balance is available
- Leave approval/rejection
- Automatically deduct leave balance after approval
- Support for:
  - Sick Leave
  - Casual Leave
  - Earned Leave
  - Unpaid Leave

### Redis Caching
- Redis-based employee caching
- `@Cacheable` used for frequently accessed employee data
- Reduces unnecessary database queries
- Configurable cache expiration time

### JPA Auditing
Automatically tracks:
- `createdAt`
- `updatedAt`
- `createdBy`
- `updatedBy`

Implemented using:
- `@CreatedDate`
- `@LastModifiedDate`
- `@CreatedBy`
- `@LastModifiedBy`
- `AuditorAware`

### Multipart File Upload
- Upload employee profile/document files
- Store uploaded employee document metadata
- Multipart request support using `MultipartFile`

### Spring Batch
Supports bulk employee creation using:

- JSON batch API
- CSV batch API

Spring Batch provides:
- Item Reader
- Item Processor
- Item Writer
- Chunk processing
- Duplicate employee validation
- Job execution tracking

### Transaction Management
Demonstrates Spring transaction features:

- `@Transactional`
- Rollback
- `noRollbackFor`
- `readOnly`
- Transaction timeout

### Entity Relationships
The project demonstrates different JPA relationships:

- `@OneToOne`
- `@OneToMany`
- `@ManyToOne`
- `@Embedded`
- `@Embeddable`
- `orphanRemoval`

### Exception Handling
Centralized exception handling using:

- `@RestControllerAdvice`
- `@ExceptionHandler`
- Custom exceptions
- Validation error handling

### Swagger / OpenAPI
Interactive API documentation is available using Swagger UI.

### Spring Boot Actuator
Provides application monitoring endpoints such as:

- Health
- Info
- Metrics
- Beans
- Mappings
- Loggers

---

# 🛠️ Technologies Used

| Technology | Version / Usage |
|---|---|
| Java | 21 |
| Spring Boot | 4.0.8 |
| Spring Framework | 7.x |
| Spring Data JPA | Database persistence |
| Hibernate | ORM |
| MySQL | Database |
| Redis | Caching |
| Spring Batch | Bulk processing |
| Spring Validation | Request validation |
| Swagger / OpenAPI | API documentation |
| Spring Boot Actuator | Monitoring |
| Maven | Build tool |
| Lombok | Boilerplate reduction |
| IntelliJ IDEA | Development IDE |

---

# 📁 Project Structure

```text
Employee_Management_Advance
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.tyss.restdemo
│   │   │       │
│   │   │       ├── batch
│   │   │       ├── config
│   │   │       ├── controller
│   │   │       ├── dto
│   │   │       ├── entity
│   │   │       ├── exception
│   │   │       ├── repository
│   │   │       ├── service
│   │   │       └── util
│   │   │
│   │   └── resources
│   │       └── application.properties
│   │
│   └── test
│
├── .gitignore
├── pom.xml
└── README.md
