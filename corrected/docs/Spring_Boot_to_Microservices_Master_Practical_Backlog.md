# Spring Boot to Microservices — Master Practical Backlog

**Version:** 1.0  
**Purpose:** Training roadmap, sprint backlog, implementation tracker, and AI-agent execution contract  
**Scope:** Spring Boot REST development through production-ready foundations and into Microservices

---

## 1. Purpose

This is the **single master backlog** for the Spring Boot practical implementation track. It is intended for trainer planning, student sequencing, AI-agent implementation/validation, sprint acceptance, and preventing premature introduction of advanced topics.

### Learning progression

```text
Core REST API
      ↓
Production REST API
      ↓
Production Persistence
      ↓
Testing & Quality
      ↓
API Operations
      ↓
Security
      ↓
Service Communication
      ↓
Microservices Foundation
      ↓
Microservices Infrastructure
      ↓
Resilience & Observability
      ↓
Event-Driven Microservices
      ↓
Production Deployment
```

---

## 2. Master Progress Tracker

| Sprint | Practical | Primary Goal | Status |
|---|---|---|---|
| S1 | Core REST API | Build first Spring Boot REST application | ✅ Completed |
| S2 | Production REST API | Make CRUD API production-minded | ⬜ Next |
| S3 | Production Persistence | Handle real JPA/Hibernate scenarios correctly | ⬜ Planned |
| S4 | Testing & Quality | Make the application testable | ⬜ Planned |
| S5 | API Operations | Document and observe the API | ⬜ Planned |
| S6 | API Security | Secure REST APIs | ⬜ Planned |
| S7 | Service Communication | Prepare for distributed applications | ⬜ Planned |
| S8 | Microservices Foundation | Move from monolith to services | ⬜ Planned |
| S9 | Microservices Infrastructure | Add gateway, discovery and configuration | ⬜ Planned |
| S10 | Resilience & Observability | Handle distributed-system failures and visibility | ⬜ Planned |
| S11 | Event-Driven Microservices | Introduce asynchronous communication | ⬜ Planned |
| S12 | Production Deployment | Package and deploy services | ⬜ Planned |

### Status legend

`⬜ Backlog` · `🟡 In Progress` · `🔵 Implemented` · `🟣 Code Review` · `🟠 Tested` · `🟢 Accepted` · `✅ Completed`

---

## 3. Sprint Execution Lifecycle

Every sprint follows:

```text
BACKLOG
   ↓
SPRINT PLANNING
   ↓
IN PROGRESS
   ↓
IMPLEMENTED
   ↓
CODE REVIEW
   ↓
TESTED
   ↓
STANDARD COMPLIANCE
   ↓
ACCEPTED
```

A topic is not complete merely because code exists. It must build, run, meet the requirement, follow standards, handle relevant scenarios, and remain understandable and maintainable.

---

## 4. Sprint Tracking Template

| ID | Requirement | Status | Code | Test | Standard | Notes |
|---|---|---|---|---|---|---|
| Sx-01 | Requirement | ⬜ | ⬜ | ⬜ | ⬜ | |
| Sx-02 | Requirement | ⬜ | ⬜ | ⬜ | ⬜ | |

---

# 5. S1 — Core REST API

**Status:** ✅ Completed

## Objective

Build a small Employee Management REST API using Spring Boot.

## Backlog

| ID | Topic |
|---|---|
| S1-01 | Spring Boot project setup |
| S1-02 | Maven/dependency management |
| S1-03 | API / Web API / REST fundamentals |
| S1-04 | HTTP methods, status codes, headers and body |
| S1-05 | `@RestController` |
| S1-06 | Request mapping |
| S1-07 | CRUD REST endpoints |
| S1-08 | Controller → Service → Repository architecture |
| S1-09 | Constructor dependency injection |
| S1-10 | Request/Response DTOs |
| S1-11 | Entity design |
| S1-12 | Spring Data JPA |
| S1-13 | Hibernate/database integration |
| S1-14 | Basic validation |
| S1-15 | Basic exception handling |
| S1-16 | SLF4J logging |
| S1-17 | External configuration |
| S1-18 | Git/GitHub + README |
| S1-19 | Basic transaction awareness |

## Core API

```text
GET    /api/employees
GET    /api/employees/{id}
POST   /api/employees
PUT    /api/employees/{id}
DELETE /api/employees/{id}
GET    /api/hello
```

## Target mental model

```text
Client
  ↓
HTTP Request
  ↓
@RestController
  ↓
DTO
  ↓
Service
  ↓
Entity
  ↓
Repository
  ↓
Spring Data JPA
  ↓
Hibernate
  ↓
JDBC
  ↓
Database
```

## Exit criteria

- [ ] Build passes
- [ ] Application starts
- [ ] Database connectivity works
- [ ] GET/POST/PUT/DELETE work
- [ ] Validation works
- [ ] Basic error scenarios work
- [ ] Logging works
- [ ] Git repository is clean
- [ ] README exists

---

# 6. S2 — Production REST API

**Status:** ⬜ Next

## Objective

Make the S1 Employee API closer to real enterprise REST development.

## Backlog

| ID | Topic |
|---|---|
| S2-01 | Custom application exceptions |
| S2-02 | `@RestControllerAdvice` |
| S2-03 | `@ExceptionHandler` |
| S2-04 | Standard success and error response |
| S2-05 | Centralized validation-error handling |
| S2-06 | Advanced Bean Validation |
| S2-07 | PUT vs PATCH |
| S2-08 | Pagination |
| S2-09 | Sorting |
| S2-10 | Basic filtering |
| S2-11 | API response consistency |
| S2-12 | Thin-controller refactoring |

### Required scenarios

```text
Valid request
Invalid request
Resource not found
Invalid ID
Validation failure
Unexpected server failure
```

---

# 7. S3 — Production Persistence

**Status:** ⬜ Planned

## Objective

Understand realistic JPA/Hibernate behavior rather than treating persistence as a black box.

## Backlog

| ID | Topic |
|---|---|
| S3-01 | Entity relationships |
| S3-02 | `@OneToMany` |
| S3-03 | `@ManyToOne` |
| S3-04 | Relationship ownership |
| S3-05 | LAZY loading |
| S3-06 | EAGER loading |
| S3-07 | N+1 query problem |
| S3-08 | Fetch optimization awareness |
| S3-09 | Transaction boundaries |
| S3-10 | `@Transactional` |
| S3-11 | Commit / rollback |
| S3-12 | Read-only transaction awareness |
| S3-13 | Optimistic locking |
| S3-14 | Concurrent update scenarios |

```text
Spring Data JPA
      ↓
Hibernate
      ↓
JDBC
      ↓
Database
```

---

# 8. S4 — Testing & Quality

**Status:** ⬜ Planned

## Objective

Make the application testable and establish confidence before distributed systems.

## Backlog

| ID | Topic |
|---|---|
| S4-01 | JUnit |
| S4-02 | Test structure |
| S4-03 | Service unit tests |
| S4-04 | Mockito |
| S4-05 | Mock repository |
| S4-06 | Controller/API tests |
| S4-07 | Request/response assertions |
| S4-08 | Positive and negative scenarios |
| S4-09 | Integration testing |
| S4-10 | Test database strategy awareness |

### Core distinction

```text
Unit Test
→ isolated component

Integration Test
→ multiple real application components working together
```

---

# 9. S5 — API Operations

**Status:** ⬜ Planned

## Objective

Make the API documented, observable and easier to operate.

## Backlog

| ID | Topic |
|---|---|
| S5-01 | OpenAPI |
| S5-02 | Swagger UI |
| S5-03 | API documentation |
| S5-04 | Spring Boot Actuator |
| S5-05 | Health endpoint |
| S5-06 | Readiness/liveness awareness |
| S5-07 | Metrics awareness |
| S5-08 | Log levels |
| S5-09 | Request/correlation ID awareness |
| S5-10 | Sensitive-data logging rules |

```text
Application
   ├── REST API
   ├── Logs
   └── Actuator
         ├── Health
         └── Metrics
```

---

# 10. S6 — API Security

**Status:** ⬜ Planned

## Objective

Secure REST APIs using Spring Security fundamentals.

## Backlog

| ID | Topic |
|---|---|
| S6-01 | Authentication |
| S6-02 | Authorization |
| S6-03 | Spring Security architecture |
| S6-04 | Security filter chain |
| S6-05 | Public vs protected endpoints |
| S6-06 | Roles and authorities |
| S6-07 | Password hashing |
| S6-08 | JWT |
| S6-09 | Access token |
| S6-10 | JWT validation |
| S6-11 | Stateless authentication |
| S6-12 | Authentication failure |
| S6-13 | Authorization failure |

```text
Client
  ↓
Authentication
  ↓
JWT
  ↓
Request
  ↓
Security
  ↓
Controller
```

---

# 11. S7 — Service Communication

**Status:** ⬜ Planned

## Objective

Create the bridge from a single Spring Boot application to distributed applications.

## Backlog

| ID | Topic |
|---|---|
| S7-01 | Why applications/services communicate |
| S7-02 | REST client |
| S7-03 | Application → Application HTTP call |
| S7-04 | Request headers |
| S7-05 | Request/response DTOs |
| S7-06 | Client-side error handling |
| S7-07 | Timeout |
| S7-08 | Connection failure |
| S7-09 | HTTP 4xx/5xx handling |
| S7-10 | Retry awareness |
| S7-11 | Fallback awareness |
| S7-12 | Correlation ID awareness |

```text
Employee Service
      │
      │ HTTP
      ↓
Department Service
```

---

# 12. S8 — Microservices Foundation

**Status:** ⬜ Planned

## Objective

Understand how and why a monolithic Spring Boot application can be decomposed into independently deployable services.

## Backlog

| ID | Topic |
|---|---|
| S8-01 | Monolith vs Microservices |
| S8-02 | Why microservices |
| S8-03 | Service boundaries |
| S8-04 | Bounded responsibility |
| S8-05 | Database-per-service |
| S8-06 | Independent deployment |
| S8-07 | Service ownership |
| S8-08 | Synchronous communication |
| S8-09 | Distributed failure |
| S8-10 | Data consistency challenges |

---

# 13. S9 — Microservices Infrastructure

**Status:** ⬜ Planned

## Backlog

| ID | Topic |
|---|---|
| S9-01 | Service discovery |
| S9-02 | API Gateway |
| S9-03 | Centralized configuration |
| S9-04 | Configuration management |
| S9-05 | Environment-specific configuration |
| S9-06 | Gateway routing |
| S9-07 | Gateway security awareness |

```text
Client
  ↓
API Gateway
  ↓
┌────────────┼────────────┐
↓            ↓            ↓
Employee     Order      Payment
Service      Service     Service
↓            ↓            ↓
DB           DB           DB
```

---

# 14. S10 — Resilience & Observability

**Status:** ⬜ Planned

## Backlog

| ID | Topic |
|---|---|
| S10-01 | Why distributed systems fail |
| S10-02 | Timeout |
| S10-03 | Retry |
| S10-04 | Circuit breaker |
| S10-05 | Fallback |
| S10-06 | Bulkhead awareness |
| S10-07 | Distributed tracing |
| S10-08 | Correlation IDs |
| S10-09 | Centralized logging awareness |
| S10-10 | Metrics |
| S10-11 | Health checks |

---

# 15. S11 — Event-Driven Microservices

**Status:** ⬜ Planned

## Objective

Introduce asynchronous communication after synchronous service communication is understood.

## Backlog

| ID | Topic |
|---|---|
| S11-01 | Synchronous vs asynchronous communication |
| S11-02 | Why messaging |
| S11-03 | Events |
| S11-04 | Producer |
| S11-05 | Consumer |
| S11-06 | Topic |
| S11-07 | Consumer group |
| S11-08 | Kafka basics |
| S11-09 | Event-driven workflow |
| S11-10 | Delivery/retry awareness |
| S11-11 | Idempotency awareness |

```text
Order Service
      │
      │ OrderCreated
      ↓
    Kafka
      ├────────────→ Inventory
      ├────────────→ Notification
      └────────────→ Analytics
```

---

# 16. S12 — Production Deployment

**Status:** ⬜ Planned

## Backlog

| ID | Topic |
|---|---|
| S12-01 | Build artifact |
| S12-02 | Environment configuration |
| S12-03 | Docker basics |
| S12-04 | Containerizing Spring Boot |
| S12-05 | Container networking |
| S12-06 | Database container awareness |
| S12-07 | Service deployment |
| S12-08 | Configuration/secrets awareness |
| S12-09 | Health checks |
| S12-10 | Production deployment architecture |

---

# 17. Cross-Sprint Engineering Standards

These standards apply throughout the practical track.

## Architecture

Prefer:

```text
Controller
    ↓
Service
    ↓
Repository
```

Do not bypass layers without a justified reason.

## Dependency Injection

Prefer constructor injection for required dependencies.

## DTO Boundary

Do not expose persistence entities directly as API contracts without a deliberate reason.

## Entity Design

Use appropriate JPA mappings, data types and persistence responsibilities.

## Lombok

Do not introduce Lombok merely to reduce line count. Avoid blindly applying `@Data` to JPA entities; use selective annotations where Lombok genuinely improves maintainability.

## Mapping

Use clear DTO ↔ Entity mapping. For small applications, explicit mapping is acceptable and often easier to understand. Do not introduce mapping frameworks solely for demonstration.

## Configuration

Keep environment-specific configuration outside Java code. Never commit real secrets.

## Logging

Use SLF4J-based logging. Do not use `System.out.println()` for application logging. Never log passwords, tokens, credentials or sensitive information.

## Transactions

Use transaction boundaries where a logical business operation requires them. Prefer the service/business layer. Do not annotate every method with `@Transactional` without a reason.

## Exception Handling

Handle expected exceptions meaningfully. Do not catch `Exception` everywhere or swallow exceptions.

## REST

Use resource-oriented URLs, appropriate HTTP methods and meaningful status codes.

## Git

Do not commit build output, IDE metadata, credentials or secrets. Maintain useful commits and a professional README.

---

# 18. Out-of-Scope Control

Do not introduce future-sprint functionality merely because it is common in industry.

Examples:

```text
JWT                     → S6
Service discovery       → S9
API Gateway             → S9
Circuit breaker         → S10
Kafka                   → S11
Docker                  → S12
```

Do not make a practical unnecessarily large.

The following are not prerequisites for starting Microservices:

```text
Reactive/WebFlux
GraphQL
Advanced AOP
Advanced caching
Advanced batch processing
Complex transaction propagation
Advanced JPA mappings
Kubernetes
Cloud-specific deployment
```

They may be introduced later when there is a concrete requirement.

---

# 19. AI Agent Operating Rules

## Rule 1 — Current Sprint First

Validate and implement the current sprint only. Do not implement future sprint features unless explicitly requested.

## Rule 2 — Preserve Completed Work

Existing accepted functionality must continue to work. Do not unnecessarily rewrite completed practicals.

## Rule 3 — Inspect Before Modifying

```text
Understand
   ↓
Audit
   ↓
Identify gaps
   ↓
Plan changes
   ↓
Implement
```

Do not blindly regenerate the project.

## Rule 4 — Prefer Simplicity

Use the simplest design that satisfies the requirement. Avoid unnecessary abstractions, frameworks, dependencies and design patterns.

## Rule 5 — Industry Standard, Not Trend Chasing

Use current, supported practices appropriate to the project's Spring Boot/Java version. Do not add a library merely because it is popular.

## Rule 6 — Explain Important Decisions

Use concise JavaDoc/documentation comments for meaningful architectural decisions. Explain **why**, not what obvious code does.

## Rule 7 — Do Not Over-comment

Comments must add knowledge that is not obvious from the code.

## Rule 8 — Validate After Modification

```text
Clean
  ↓
Compile
  ↓
Test
  ↓
Run
  ↓
Verify APIs
```

## Rule 9 — Do Not Hide Problems

If something cannot be implemented or verified, report it clearly. Never claim something was tested when it was not.

## Rule 10 — Avoid Scope Creep

If an issue belongs to a future sprint, record it but do not automatically implement it.

---

# 20. AI Agent Sprint Workflow

For every practical:

### Step 1 — Load Current Backlog

Identify the current sprint, completed sprints, current requirements and out-of-scope topics.

### Step 2 — Audit Existing Code

Check the areas relevant to the current sprint:

```text
Architecture
Dependencies
Configuration
Controllers
Services
Repositories
DTOs
Entities
Exception handling
Logging
Tests
Git hygiene
```

### Step 3 — Create Gap List

Classify findings:

```text
MISSING
INCORRECT
BELOW STANDARD
UNNECESSARY
OUT OF SCOPE
ALREADY COMPLIANT
```

### Step 4 — Implement

Modify only what is required.

### Step 5 — Test

Execute relevant build, unit, integration, API and negative tests.

### Step 6 — Review Standards

Perform a second standards review after implementation.

### Step 7 — Produce Sprint Report

```text
Current Sprint:
Status:

Implemented:
- ...

Modified:
- ...

Already Compliant:
- ...

Outstanding:
- ...

Future-Sprint Items:
- ...

Build:
PASS/FAIL

Tests:
PASS/FAIL

API Verification:
PASS/FAIL
```

---

# 21. Definition of Done

A sprint can be marked **Accepted** only when:

```text
[ ] All in-scope backlog items implemented
[ ] Code compiles
[ ] Application starts
[ ] Required functionality works
[ ] Relevant negative scenarios work
[ ] Tests pass
[ ] Architecture follows agreed standards
[ ] No unnecessary dependencies added
[ ] No future-sprint scope introduced
[ ] Configuration is safe
[ ] Logging follows standards
[ ] Documentation is adequate
[ ] Git changes are clean
[ ] Trainer review completed
```

---

# 22. Microservices Readiness Gate

Before beginning S8, the developer should be comfortable with:

```text
Spring Boot
REST APIs
HTTP
Spring MVC
Dependency Injection
Layered Architecture
DTOs
JPA/Hibernate
Database Transactions
Validation
Exception Handling
Testing
API Documentation
Actuator
Logging
Spring Security
JWT
REST Client
Service-to-Service Communication
Timeout/Error Handling
```

Mastery of every Spring feature is not required. The key requirement is the ability to independently build and maintain a clean Spring Boot REST service.

---

# 23. Final Learning Mental Model

```text
                 SPRING BOOT
                      │
                      ↓
                  REST API
                      │
                      ↓
             Layered Application
                      │
        ┌─────────────┼─────────────┐
        ↓             ↓             ↓
     Database       Security      Testing
        │             │             │
        └─────────────┼─────────────┘
                      ↓
              Production API
                      │
                      ↓
          Service-to-Service HTTP
                      │
                      ↓
                MICROSERVICES
                      │
          ┌───────────┼───────────┐
          ↓           ↓           ↓
       Gateway     Discovery     Config
          │
          ↓
     Resilience
          │
          ↓
   Observability
          │
          ↓
   Event-driven Systems
          │
          ↓
      Deployment
```

## Guiding Principle

> **Do not teach Microservices as a collection of Spring Cloud annotations. Build the single-service engineering foundation first, then introduce each distributed-system problem only when the architecture creates a reason to solve it.**
