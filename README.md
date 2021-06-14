# DATASERVE Interview Task

## Statement

- Build a course registration application, with an endpoint for **batch** registration, where each registration should spawn its own thread, and upon completion print `"SUCCESS"` to the console.

- Use **Spring Boot** and **Hibernate**.

- Use a **Many to Many** relationship between Student and Course.

- Use **proper layering**, with one layer for each of the following:

  - Model
  - Repository
  - Service
  - Controller

- Write **clear** code and comment where necessary.

- Input format

  - A JSON array of objects with the following 2 keys:
    1. studentId
    2. courseId

## Solution

- Running the application

  - Clone the repository
  - Run the following command from the project's directory:
    - $./mvnw spring-boot:run

- Endpoints

  - All endpoints can be view by running the server and heading to:
    - [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/)

- Database

  - The project uses an **H2** in memory database for simplicity and faster testing.

- Data

  - **The database is seeded with 10 students(id 1 to 10) and 10 courses (id 1 to 10), to ease your testing.**

- Example: Registering 2 students (with id=1 and id=2) in the course (with id=1):

  - issue a POST request to the following endpoint:
    - [http://localhost:8080/students/courses/register/batch](http://localhost:8080/students/courses/register/batch)
  - with the following body:
    - [{"studentId": 1, "courseId": 1},{"studentId": 2, "courseId": 1}]

- Notes
  - In addition to registering students in courses, I added a couple more endpoints for:
    1. Creating, viewing and deleting students and courses.
    2. Registering a single student to a single course.
  - I wrote my own Entity to DTO mappers, but in a larger project with more entities I would use **ObjectMapper** library instead.
  - Instead of using multiple threads for insertion, we can use utilize CrudRepository.saveAll() method to batch the inserts together into a single database statement.
