# Digital Library Book Management System

## Project Overview

This is a Spring Boot-based Book Management System designed to help librarians efficiently manage book catalogs. The application provides comprehensive functionality for adding, updating, searching, and managing books with robust tracking capabilities.

## Features

- **Book Management**
    - Add new books to the catalog
    - View all books
    - Search books by ID or title
    - Update book details
    - Soft delete books
    - Track book availability status

- **Key Capabilities**
    - Unique book tracking
    - Availability status management
    - Metadata tracking (creation date, last modified)
    - Soft deletion mechanism

## Technology Stack

- Java 17
- Spring Boot 3.2.4
- Spring Data JPA
- H2 Database
- Lombok
- Maven

## Prerequisites

- JDK 17 or higher
- Maven 3.6+
- Git (optional)

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/your-username/digital-library-management.git
cd digital-library-management
```

### Build the Project

```bash
mvn clean install
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## Database Configuration

- **Database**: H2 In-Memory/File-based Database
- **Console Access**: `http://localhost:8080/h2-console`
    - JDBC URL: `jdbc:h2:file:C:/Users/Rohan Ravi/Downloads/Test`
    - Username: `sa`
    - Password: (empty)

## API Endpoints

### Book Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/books/createBook` | Add a new book |
| GET | `/books/getAllBooks` | Retrieve all books |
| GET | `/books/{id}` | Find book by ID |
| GET | `/books/search?title=` | Search books by title |
| PUT | `/books/{id}` | Update book details |
| DELETE | `/books/{id}` | Soft delete a book |
| GET | `/books/admin/deleted-books` | View deleted books |

### Sample Request Body (Create/Update Book)

```json
{
    "title": "Spring in Action",
    "author": "Craig Walls",
    "genre": "Programming",
    "availability": "AVAILABLE"
}
```

## Swagger Documentation

Swagger UI can be accessed at: `http://localhost:8080/swagger-ui.html`

## Logging

- SQL statements are logged to the console
- Logging level can be configured in `application.yml`


## Deployment

1. Build the JAR file:
   ```bash
   mvn clean package
   ```

2. Run the JAR:
   ```bash
   java -jar target/BookManagementSystem-1.0-SNAPSHOT.jar
   ```

## Troubleshooting

- Ensure Java 17 is installed
- Check Maven configuration
- Verify database connection settings
- Review application logs for detailed error information

