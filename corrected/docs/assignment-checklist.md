# Assignment Checklist - Implemented in this project

This project was customized from the trainer's whiteboard checklist.

## Required items

- [x] Request + response logging filter
- [x] JPA auditing
- [x] Redis cache
- [x] Multipart file API
- [x] `@Transactional`
  - [x] `rollbackFor`
  - [x] `noRollbackFor`
  - [x] `readOnly`
  - [x] `timeout`
- [x] Swagger / OpenAPI documentation
- [x] Spring Boot Actuator
- [x] Spring Batch API for creating multiple employees at once
- [x] `@Embeddable` + `@Embedded`
- [x] `orphanRemoval` mapping
- [x] JPA relationship mappings
- [x] Centralized REST exception handling

## Added practical improvements

- Employee leave balance validation and deduction on approval.
- Separate department assignment API.
- Employee document metadata table (`employee_documents`).
- File-size limits and safe UUID-based file names.
- Password/token masking in request logs.
- Pagination and search retained from the original project.

## Important local services

1. MySQL: configured for port `3307` in `application.properties`.
2. Redis: expected on `localhost:6379`.
3. Spring Batch creates its metadata tables automatically with `spring.batch.jdbc.initialize-schema=always`.

## Main new endpoints

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/batch/employees` | Create multiple employees using Spring Batch |
| POST | `/api/v3/employee` | Multipart employee + optional document |
| POST | `/api/v3/employee/{employeeId}/document` | Upload employee document |
| GET | `/api/v3/employee/{employeeId}/document` | List employee documents |
| POST | `/api/transactions/rollback-demo` | Demonstrate rollback |
| POST | `/api/transactions/no-rollback-demo` | Demonstrate `noRollbackFor` |
| POST | `/department/assign` | Assign a department to an employee |
| GET | `/swagger-ui.html` | Swagger UI |
| GET | `/v3/api-docs` | OpenAPI JSON |
| GET | `/actuator/health` | Health |
| GET | `/actuator/metrics` | Metrics |
