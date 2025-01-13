## Psi Planner API

This RESTful API is part of a major full-stack project called "Psi Planner".
It uses Spring Boot powered with technologies like:
- Spring Web
- Spring Data JPA
- Spring Security
- Spring Validation
- Flyway Migration
- PostgreSQL
- Swagger UI (OpenAPI)
- Docker Compose
- JWT
- Lombok

### How to Run

The easiest way to run the project is by having Docker and Docker Compose installed on your machine.

First, create a `.env` file in the project root directory. It must contain the following environment variables:

```
DB_USER=psiplanner
DB_PASSWORD=psiplanner
SECURITY_CONFIG_KEY= *** insert your generated key here! ***
```

To generate a key, simply run this command in a Linux terminal:

 ```bash
        openssl rand -base64 32
 ```

After that, just run the BackPsiplannerApplication class on IntelliJ or your favorite Java IDE and you are ready to go.

Check also: [Psiplanner Front](https://github.com/igorzig13/front-psiplanner)

### Endpoints and documentation
To explore API endpoints visit `http://localhost:8080/swagger-ui.html`