# Spring Boot Project

# Clone the Repo

# Download the Dependencies
`mvn clean install`

# Run the App
`mvn spring-boot:run`


## Access Actuator Endpoints
Once your application is running, you can check the Actuator endpoints for monitoring:

#### Health Endpoint: http://localhost:8080/actuator/health
#### Info Endpoint: http://localhost:8080/actuator/info
#### Metrics Endpoint: http://localhost:8080/actuator/metrics
These endpoints will provide various metrics about your application’s health and performance.

## Test CRUD Operations
You can test the CRUD operations via the following URLs using a tool like Postman:

#### GET all employees: http://localhost:8080/api/employees
#### GET employee by ID: http://localhost:8080/api/employees/{id}
#### POST create employee: http://localhost:8080/api/employees
#### PUT update employee: http://localhost:8080/api/employees/{id}
#### DELETE employee: http://localhost:8080/api/employees/{id}
