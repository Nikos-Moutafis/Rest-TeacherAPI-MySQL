# Rest-TeacherAPI-MySQL

This is a Java-based REST API that allow clients to perform CRUD (Create, Read, Update, Delete) operations on teachers who are stored
in a MySQL database.The REST endpoints are designed to allow clients to interact 
with the database through standard HTTP methods such as GET, POST, PUT, and DELETE. 
API Endpoints

The following REST endpoints are implemented:

- GET /teachers/?lastname: Get a list of all teachers in the database
- GET /teachers/{id}: Get a specific teacher by ID
- GET /teachers/?lastname = "lastname" : Get a specific teacher by lastname
- POST /teachers: Create a new teacher
- PUT /teachers/{id}: Update an existing teacher
- DELETE /teachers/{id}: Delete a teacher by ID

Technologies Used:

- Java 8
- Java EE 8
- Apache Maven 3.3.2
- MySQL  8.0.32
- Jersey 2.34
- Commons DBCP2 2.9.0
- Jackson 2.34
- Weld CDI
