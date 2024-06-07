# Train Ticket API

Welcome to the Train Ticket API! This project is a simple Spring Boot application that simulates purchasing train tickets from London to France. It supports basic CRUD operations using an in-memory H2 database for persistence.

## Features

- Purchase a train ticket
- Retrieve ticket details (receipt) for a user
- View users and their seat allocations by section
- Remove a user from the train
- Modify a user's seat

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- H2 Database
- REST API
- JUnit for testing
- MockMvc for integration tests

## Getting Started

### Prerequisites

- Java 11 or later
- Maven

### Installation

1. Clone the repository:
    ```bash
    git clone [https://github.com/Ayudiv/train-ticket-api.git](https://github.com/Ayudiv/Train-Ticket-API.git)
    cd train-ticket-api
    ```

2. Build the project:
    ```bash
    mvn clean install
    ```

3. Run the application:
    ```bash
    mvn spring-boot:run
    ```

4. Access the H2 console (optional) at [http://localhost:8080/h2-console](http://localhost:8080/h2-console). 
   - JDBC URL: `jdbc:h2:mem:testdb`
   - Username: `sa`
   - Password: `password`

### Endpoints

#### Purchase a Ticket

- **URL:** `/api/train/purchase`
- http://localhost:8080/api/train/purchase
- **Method:** `POST`
- **Request Body:**
    ```json
    {
        "firstName": "Ayush",
        "lastName": "Kumar",
        "email": "ayushsj17@gmail.com"
    }
    ```
- **Response:**
    ```json
    {
        "from": "London",
        "to": "France",
        "user": {
            "firstName": "Ayush",
            "lastName": "Kumar",
            "email": "ayushsj17@gmail.com"
        },
        "price": 20.0,
        "seat": "A1"
    }
    ```

#### Get Receipt
- **URL:** `/api/train/receipt/{email}`
- http://localhost:8080/api/train/receipt/ayushsj17@gmail.com
- **Method:** `GET`
- **Response:**
    ```json
    {
        "from": "London",
        "to": "France",
        "user": {
            "firstName": "Ayush",
            "lastName": "Kumar",
            "email": "ayushsj17@gmail.com"
        },
        "price": 20.0,
        "seat": "A1"
    }
    ```

#### Get Users by Section

- **URL:** `/api/train/users/{section}`
- http://localhost:8080/api/train/users/A
- **Method:** `GET`
- **Response:**
    ```json
    {
        "john.doe@example.com": {
            "from": "London",
            "to": "France",
            "user": {
                "firstName": "Ayush",
                "lastName": "Kumar",
                "email": "ayushsj17@gmail.com"
            },
            "price": 20.0,
            "seat": "A1"
        }
    }
    ```

#### Remove a User

- **URL:** `/api/train/remove/{email}`
- http://localhost:8080/api/train/remove/hmehar98@gmail.com
- **Method:** `DELETE`

#### Modify Seat

- **URL:** `/api/train/modify-seat`
- http://localhost:8080/api/train/modify-seat?email=ayushsj17@gmail.com&newSeat=B1
- **Method:** `PUT`
- **Query Parameters:**
    - `email`: The user's email (e.g., `ayushsj17@gmail.com`)
    - `newSeat`: The new seat allocation (e.g., `B1`)

