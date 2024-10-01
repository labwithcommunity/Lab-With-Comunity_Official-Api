# Lab With Community - Official API

## Project Overview
Lab With Community is a Spring Boot application that provides an API for managing projects and user interactions within a community-driven laboratory environment.

## Features
- User management
- Project creation and management
- User participation in projects
- API documentation with OpenAPI (Springdoc)

## Technologies
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Spring Security
- MySQL
- Docker
- Maven

## Getting Started

### Prerequisites
- Java 17 or higher
- Docker and Docker Compose (for running the application with a MySQL database)
- Maven (optional, as the project includes Maven Wrapper)

### Running the Application

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/Lab-With-Comunity_Official-Api.git
   cd Lab-With-Comunity_Official-Api
   ```

2. Build and start the application along with the database using Docker Compose:
   ```
   docker compose up --build
   ```

3. The application should now be running at `http://localhost:8080`

### API Documentation
Once the application is running, you can access the OpenAPI documentation at:
```
http://localhost:8080/swagger-ui.html
```

## Project Structure
- `src/main/java/com/labwithcommunity`: Main source code
    - `domain`: Domain logic and entities
    - `infrastructure`: Controllers and external interfaces
- `src/main/resources`: Configuration files
- `src/test`: Test source code

## Contributing
Contributions are welcome! Please feel free to submit a Pull Request.

## Support
If you encounter any problems or have any questions, please open an issue in the GitHub repository.
