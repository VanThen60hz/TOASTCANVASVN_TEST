# TOASTCANVASVN_TEST

## Overview

TOASTCANVASVN_TEST is a Spring Boot application designed to manage a bookstore. It provides a RESTful API for managing books and authors, including CRUD operations and additional features like validation and error handling.

## Features

-   **CRUD Operations**: Create, read, update, and delete books and authors.
-   **Validation**: Ensures unique ISBNs and validates other fields.
-   **Error Handling**: Provides meaningful error messages for various exceptions.
-   **Filtering**: Retrieve books with optional filters such as author ID, published date, title, and price range.

## Technologies Used

-   **Java 17**
-   **Spring Boot 3.4.2**
-   **Spring Data JPA**
-   **MySQL**
-   **Lombok**
-   **Springdoc OpenAPI**

## Database Schema

The application uses a MySQL database with the following schema:

### Books Table

-   `id` (Primary Key)
-   `title` (String)
-   `author_id` (Foreign Key)
-   `published_date` (Date)
-   `isbn` (String, unique)
-   `price` (Decimal)

### Authors Table

-   `id` (Primary Key)
-   `name` (String)
-   `nationality` (String)

## Setup Instructions

1. **Clone the Repository**:

    ```bash
    git clone https://github.com/VanThen60hz/TOASTCANVASVN_TEST
    cd TOASTCANVASVN_TEST
    ```

2. **Configure the Database**:

    - Ensure MySQL is running.
    - Update the database connection details in `src/main/resources/application.properties`:
        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/toastcanvasvn-test-bookstore?createDatabaseIfNotExist=true
        spring.datasource.username=<your-username>
        spring.datasource.password=<your-password>
        ```

3. **Build and Run the Application**:

    ```bash
    ./gradlew bootRun
    ```

4. **Access the API**:
    - The API will be available at `http://localhost:8080/api`.

## API Endpoints

### Books

-   **Create Book**: `POST /api/books`

    -   **Request Body**:
        ```json
        {
            "title": "Sample Book Title",
            "authorId": 1,
            "publishedDate": "2023-10-01",
            "isbn": "978-3-16-148410-0",
            "price": 19.99
        }
        ```

-   **Get Book by ID**: `GET /api/books/{id}`

-   **Get All Books**: `GET /api/books`

    -   **Query Parameters** (optional):
        -   `authorId`: Filter by author ID
        -   `publishedDate`: Filter by published date
        -   `title`: Filter by title
        -   `minPrice`: Filter by minimum price
        -   `maxPrice`: Filter by maximum price

-   **Update Book**: `PATCH /api/books/{id}`

    -   **Request Body**:
        ```json
        {
            "title": "Updated Book Title",
            "authorId": 1,
            "publishedDate": "2023-10-01",
            "isbn": "978-3-16-148410-0",
            "price": 24.99
        }
        ```

-   **Delete Book**: `DELETE /api/books/{id}`

### Authors

-   **Create Author**: `POST /api/authors`

    -   **Request Body**:
        ```json
        {
            "name": "Author Name",
            "nationality": "Country"
        }
        ```

-   **Get Author by ID**: `GET /api/authors/{id}`

-   **Get All Authors**: `GET /api/authors`

-   **Update Author**: `PATCH /api/authors/{id}`

    -   **Request Body**:
        ```json
        {
            "name": "Updated Author Name",
            "nationality": "Updated Country"
        }
        ```

-   **Delete Author**: `DELETE /api/authors/{id}`

## Testing

Run the tests using:

```bash
./gradlew test
```

## Faking Data

To fake data for testing purposes, you can use the provided Python scripts. Ensure you have Python and the `Faker` library installed.

1. **Install Faker**:

    ```bash
    pip install faker
    ```

2. **Run the Book Faker Script**:

    ```bash
    python faker-api/book.py
    ```

3. **Run the Author Faker Script**:
    ```bash
    python faker-api/author.py
    ```

These scripts will generate and send fake data to the API endpoints.

## Error Handling

The application uses a global exception handler to manage errors, providing structured error responses for resource not found and illegal argument exceptions.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any improvements.

## License

This project is licensed under the MIT License.
