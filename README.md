# Online Learning Platform API

A RESTful backend service for managing an online learning platform. Built with **Spring Boot**, it provides a robust API for handling users, courses, enrollments, categories, and locations.

---

## What It Does

The Online Learning Platform API serves as the backend engine for an e-learning system. It enables:

- **Administrators, instructors, and students** to be registered and managed with distinct roles.
- **Instructors** to create and categorize courses with defined difficulty levels and pricing.
- **Students** to discover courses, enroll in them, and track their enrollment status.
- **Location-aware user management**, supporting a hierarchical geographic structure (Province → District → Sector → Cell → Village) to segment and query users by their location.
- **Rich profile management** with extended user profile information.

---

## Key Features

- **Role-Based User Management** — Three user roles: `ADMIN`, `INSTRUCTOR`, and `STUDENT`
- **Course Management** — Create courses with title, description, price, difficulty level (`BEGINNER`, `INTERMEDIATE`, `ADVANCED`), and instructor assignment
- **Category System** — Many-to-many course categorization for flexible course discovery
- **Enrollment Lifecycle** — Enroll students in courses with timestamped status tracking
- **Hierarchical Location Structure** — Five-level geographic hierarchy (Province, District, Sector, Cell, Village) with parent-child relationships
- **Advanced Search & Filtering** — Search courses by instructor; filter users by any level of the location hierarchy or by first name
- **Pagination & Sorting** — Paginated course listings (default 10 per page) with configurable sorting
- **UUID Primary Keys** — All entities use universally unique identifiers
- **Audit Timestamps** — Automatic `createdAt` / `enrolledAt` timestamps on relevant entities

---

## Technology Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 4.0.3 |
| Web Layer | Spring MVC (spring-boot-starter-webmvc) |
| Data Access | Spring Data JPA (spring-boot-starter-data-jpa) |
| ORM | Hibernate (JPA implementation) |
| Database | PostgreSQL |
| Build Tool | Apache Maven (Maven Wrapper included) |

---

## Prerequisites

Before running this application, ensure you have the following installed:

- **Java 17** or later — [Download from Adoptium](https://adoptium.net/)
- **Apache Maven 3.8+** — Optional; the Maven Wrapper (`mvnw`) is included in the project
- **PostgreSQL 13+** — [Download PostgreSQL](https://www.postgresql.org/download/)
- A PostgreSQL client (e.g., `psql`, pgAdmin, DBeaver) for database setup

---

## Installation & Usage

### 1. Clone the Repository

```bash
git clone https://github.com/Div-30/midterm_27003_groupE.git
cd midterm_27003_groupE
```

### 2. Set Up the Database

Open a PostgreSQL client and create a database for the application:

```sql
CREATE DATABASE online_learning_platform;
```

### 3. Configure the Application

Open `src/main/resources/application.properties` and update the datasource credentials to match your PostgreSQL setup:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/online_learning_platform
spring.datasource.username=your_postgres_username
spring.datasource.password=your_postgres_password
```

The remaining properties configure Hibernate to auto-create/update schema and enable SQL logging:

```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

### 4. Build the Project

Use the included Maven Wrapper to build the project (no local Maven installation required):

```bash
# Linux / macOS
./mvn clean install

# Windows
mvn.cmd clean install
```

### 5. Run the Application

```bash
# Linux / macOS
./mvn spring-boot:run

# Windows
mvn.cmd spring-boot:run
```

The API server will start on `http://localhost:8080` by default.

### 6. Run Tests

```bash
# Linux / macOS
./mvn test

# Windows
mvn.cmd test
```


