
# NoviFlix API

Welcome to the **NoviFlix API**, a Spring Boot-based RESTful API for managing movies in the NoviFlix application.

## Features
- **CRUD Operations** for movies
- Retrieve a **random movie**
- Load **predefined movie data**
- Built-in Swagger UI for API documentation

---

## Table of Contents

1. [Getting Started](#getting-started)
2. [API Endpoints](#api-endpoints)
3. [Swagger Documentation](#swagger-documentation)
4. [Example Models](#example-models)

---

## Getting Started

### Prerequisites
1. **Java 17 or higher**
2. **Maven** for dependency management
3. **Spring Boot 3.1.0 or later**

### Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/noviflix-api.git
   cd noviflix-api
   ```
2. Build the project:
   ```bash
   mvn clean install
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Access Swagger UI:
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

---

## API Endpoints

### 1. **Retrieve All Movies**

- **GET** `/api/v1/movies`
- **Description**: Fetch all movies in the database.
- **Response Example**:
  ```json
  [
    {
      "id": 1,
      "title": "Inception",
      "director": "Christopher Nolan",
      "plot": "A thief who steals corporate secrets through dream-sharing technology is given a task to plant an idea."
    },
    {
      "id": 2,
      "title": "Interstellar",
      "director": "Christopher Nolan",
      "plot": "A team of explorers travel through a wormhole in space to ensure humanity's survival."
    }
  ]
  ```

---

### 2. **Retrieve a Movie by ID**

- **GET** `/api/v1/movies/{id}`
- **Description**: Fetch a movie by its unique ID.
- **Response Example**:
  ```json
  {
    "id": 1,
    "title": "Inception",
    "director": "Christopher Nolan",
    "plot": "A thief who steals corporate secrets through dream-sharing technology is given a task to plant an idea."
  }
  ```

---

### 3. **Add a New Movie**

- **POST** `/api/v1/movies`
- **Description**: Add a new movie to the database.
- **Request Example**:
  ```json
  {
    "title": "The Matrix",
    "director": "Wachowski Sisters",
    "plot": "A hacker discovers the truth about his reality and his role in the war against its controllers."
  }
  ```
- **Response Example** (201 Created):
  ```json
  {
    "id": 3,
    "title": "The Matrix",
    "director": "Wachowski Sisters",
    "plot": "A hacker discovers the truth about his reality and his role in the war against its controllers."
  }
  ```

---

### 4. **Update a Movie**

- **PUT** `/api/v1/movies/{id}`
- **Description**: Update the details of an existing movie.
- **Request Example**:
  ```json
  {
    "title": "The Matrix Reloaded",
    "director": "Wachowski Sisters",
    "plot": "Neo and his allies continue their battle against the machines controlling humanity."
  }
  ```
- **Response Example** (200 OK):
  ```json
  {
    "id": 3,
    "title": "The Matrix Reloaded",
    "director": "Wachowski Sisters",
    "plot": "Neo and his allies continue their battle against the machines controlling humanity."
  }
  ```

---

### 5. **Delete a Movie**

- **DELETE** `/api/v1/movies/{id}`
- **Description**: Remove a movie from the database.
- **Response Example**:
  - **204 No Content** (if deleted)
  - **404 Not Found** (if movie not found)

---

### 6. **Retrieve a Random Movie**

- **GET** `/api/v1/movies/whatsnext`
- **Description**: Get a random movie from the database.
- **Response Example**:
  ```json
  {
    "id": 2,
    "title": "Interstellar",
    "director": "Christopher Nolan",
    "plot": "A team of explorers travel through a wormhole in space to ensure humanity's survival."
  }
  ```

---

### 7. **Load Predefined Movies**

- **GET** `/api/v1/loadmovies`
- **Description**: Load a predefined set of movies into the database.
- **Response Example**:
  ```json
  [
    {
      "id": 1,
      "title": "Memento",
      "director": "Christopher Nolan",
      "plot": "A man with short-term memory loss attempts to track down his wife's murderer."
    },
    {
      "id": 2,
      "title": "Shutter Island",
      "director": "Martin Scorsese",
      "plot": "A U.S. Marshal investigates the disappearance of a murderer from a hospital."
    }
  ]
  ```

---

## Swagger Documentation

The NoviFlix API is fully documented with Swagger. You can explore and test the API using the Swagger UI:

- **Swagger UI URL**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## Example Models

### Movie
```json
{
  "id": 1,
  "title": "Inception",
  "director": "Christopher Nolan",
  "plot": "A thief who steals corporate secrets through dream-sharing technology."
}
```

---
