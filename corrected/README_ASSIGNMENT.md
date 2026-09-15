# EMS - Assignment Customized Project

This version is the corrected assignment build based on the existing `com.tyss.restdemo` project.

## Assignment features included

- Request + response logging filter using `ContentCachingRequestWrapper` and `ContentCachingResponseWrapper`
- JPA auditing (`createdDate`, `lastModifiedDate`, `createdBy`, `lastModifiedBy`)
- Redis caching
- Multipart employee/document upload
- Transaction demonstrations:
  - `rollbackFor`
  - `noRollbackFor`
  - `timeout`
  - `readOnly`
- Swagger / OpenAPI
- Actuator
- Spring Batch API for bulk employee creation
- `@Embeddable` + `@Embedded` through `EmployeeProfile`
- `EmployeeProfileDto` for request/response mapping
- `orphanRemoval` relationships

## Transaction demo APIs

- `POST /api/transactions/rollback-demo?departmentName=HR`
- `POST /api/transactions/no-rollback-demo?departmentName=Finance`
- `GET /api/transactions/read-only-demo`

The read-only API calls a service method annotated with:

`@Transactional(readOnly = true)`

## Bulk employee API

The Spring Batch controller exposes the separate bulk creation API. See `EmployeeBatchController` for the exact request shape.

## Important

Update `src/main/resources/application.properties` with your local MySQL/Redis settings if required before running.
