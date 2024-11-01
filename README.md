Movies API Documentation
========================

Base URL: /api/v1
-----------------

### GET /movies

Retrieves a list of all movies.

#### Response Example:

\[
    {
        "id": 1,
        "title": "Memento",
        "director": "Christopher Nolan",
        "plot": "A man with short-term memory loss attempts to track down his wife's murderer."
    },
    ...
\]

### GET /movies/{id}

Retrieves details of a movie by its ID.

#### Path Parameters:

*   **id** (Long): The ID of the movie.

#### Response Example:

{
    "id": 1,
    "title": "Memento",
    "director": "Christopher Nolan",
    "plot": "A man with short-term memory loss attempts to track down his wife's murderer."
}
        

### POST /movies

Adds a new movie.

#### Request Body Example:

{
    "title": "New Movie",
    "director": "Director Name",
    "plot": "Plot description."
}
        

#### Response Example (201 Created):

{
    "id": 8,
    "title": "New Movie",
    "director": "Director Name",
    "plot": "Plot description."
}
        

### DELETE /movies/{id}

Deletes a movie by its ID.

#### Path Parameters:

*   **id** (Long): The ID of the movie.

#### Response Example:

Status: 204 No Content

### PUT /movies/{id}

Updates a movie by its ID.

#### Path Parameters:

*   **id** (Long): The ID of the movie.

#### Request Body Example:

{
    "title": "Updated Movie Title",
    "director": "Updated Director",
    "plot": "Updated plot description."
}
        

#### Response Example:

{
    "id": 1,
    "title": "Updated Movie Title",
    "director": "Updated Director",
    "plot": "Updated plot description."
}
        

### GET /movies/whatsnext

Returns a random movie from the collection.

#### Response Example:

{
    "id": 3,
    "title": "Pulp Fiction",
    "director": "Quentin Tarantino",
    "plot": "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption."
}
        

### GET /loadmovies

Loads a set of predefined movies into the system.

#### Response Example:

Status: 200 OK
