## Student Management and Fee Collection Microservices

This project consists of two microservices built using Spring Boot to manage student information and fee collection. It includes RESTful APIs to add students, collect fees, and view receipts. The project also provides Swagger specifications for documentation and testing purpose

### Table of Contents
1. [Project Structure](#project-structure)
2. [Technologies Used](#technologies-used)
3. [Setup Instructions](#setup-instructions)
4. [Swagger Documentation](#swagger-documentation)
5. [Endpoints](#endpoints)
6. [Problem Statement](#problem-statement)
7. [Fee Collection Details](#fee-collection-details)

### Project Structure
- `ms-student-service`: Manages student data.
- `ms-fee-collection-service`: Manages fee collection and receipts.

### Technologies Used
- Spring Boot (latest version)
- Spring Data JPA
- H2 In-memory Database
- Swagger for API Documentation
- Postman for API Testing

### Setup Instructions

#### Prerequisites
- Java 17
- Maven
- Docker (optional, for containerization)
- Git

#### Clone the Repository
```bash
git clone https://github.com/your-github-username/student-management-system.git
cd student-management-system
git checkout master
```

#### Build and Run


##### Using Docker (optional)
```bash
# From the root directory of the project
docker-compose up --build
```
##### Using IDE (Optional)
`ms-student-service` -Navigate to The Root Project and Import `ms-student-service` to your IDE and run build and start the service

`ms-fee-collection-service`: Navigate to The Root Project and Import `ms-fee-collection-service` to your IDE and run build and start the service.
### Swagger Documentation
Once the services are up and running, Swagger UI can be accessed at:
- **Student Service**: [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)
- **Fee Service**: [http://localhost:8083/swagger-ui.html](http://localhost:8083/swagger-ui.html)

### Endpoints

#### Student Service
- **POST /student**: Add a new student
- **GET /student/{studentId}**: Get student details by ID

#### Fee Service
- **POST /fees**: Collect fee for a student
- **GET /receipt/{studentId}**: Get receipts by student ID

### Problem Statement

The goal of this project is to create a set of microservices to manage student fee collection. The functionalities include:

- **Student Management**: APIs to add students and retrieve student details.
  - **Fields**:
    - Student Name
    - Student Id
    - Grade
    - Mobile Number
    - School Name

- **Fee Collection**: APIs to collect fees and view receipts.
  - **Fields**:
    - Refer to the sample receipt provided in the documentation.
    - Match the fields as closely as possible to the sample.

- **Data Access**: Spring Data JPA for data access and H2 in-memory database for storage.

- **API Design**: API First or Code First design approach for the APIs.

- **Documentation and Testing**: Swagger specifications and Postman for API testing.

### Fee Collection Details

#### Fields
The fields for the receipt should closely match the following sample receipt structure:

- **Receipt Fields**:
  - `receiptId`: Unique identifier for the receipt.
  - `studentId`: Unique identifier for the student.
  - `amount`: The fee amount collected.
  - `date`: The date of fee collection.
  - `description`: Description of the fee collected (optional).

#### Data Access
- **Spring Data JPA**: Used for data access.
- **H2 In-memory Database**: Used for storing data.

#### API Design
- **API First**: Define the API using Swagger/OpenAPI specification first and generate code stubs.
- **Code First**: Write the code first and generate Swagger documentation from annotations.

This project follows the **Code First** approach, where APIs are defined using Spring Boot annotations, and Swagger documentation is generated from these annotations.

#### Documentation and Testing
- **Swagger**: Used for documenting the APIs. The Swagger UI is available at the endpoints mentioned above.

Feel free to reach out if you have any questions or need further assistance.

