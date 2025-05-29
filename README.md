# 📝 Blogging Application Backend

This is a **Blogging Application Backend** built using **Spring Boot**. It provides RESTful APIs for creating,
managing, and publishing blog posts. The backend is designed with modularity and scalability in mind,
ideal for integration with a frontend built with frameworks like Angular or React.

## 🚀 Features

- User Registration and Login (JWT-based Authentication)
- CRUD Operations on:
  - Posts
  - Categories
  - Users
- Pagination and Sorting for Posts
- Image Upload Functionality
- Search Posts by Title or Content
- Role-Based Access Control (Admin/User)
- Exception Handling and Validation
- Swagger/OpenAPI documentation

## 🛠️ Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- Spring Security (JWT)
- Hibernate
- MySQL
- Maven
- Lombok
- Swagger UI

## 📁 Project Structure

src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── blog/
│   │           ├── controller/
│   │           ├── dto/
│   │           ├── entity/
│   │           ├── repository/
│   │           ├── security/
│   │           ├── service/
│   │           └── BloggingApplication.java
│   └── resources/
│       ├── application.properties
│       └── static/
🔐 Authentication & Authorization
This application uses JWT (JSON Web Token) for securing APIs. Users must log in to receive a token, which they must include in the Authorization header for protected routes.

🧪 API Documentation
API documentation is available via Swagger UI after running the application:

http://localhost:8080/swagger-ui/
🖼️ Image Upload
Images are stored in a local folder. You can configure the path in application.properties.

📦 Setup Instructions
Clone the Repository

git clone https://github.com/your-username/Blogging_Application_Backend.git
Configure Database
Update your application.properties with your MySQL credentials.

Build & Run

mvn clean install
mvn spring-boot:run
Access the API

http://localhost:9090/api/
📌 Future Enhancements
Social login with Google/GitHub

Email verification

Post comments and likes

Docker deployment

🙌 Contributing
Feel free to open issues or submit pull requests to improve this project!

📄 License
This project is licensed under the MIT License.

👤 Author
Rajeev Bartwal
📧 bartwalrajeev3@gmail.com
🔗 LinkedIn: https://www.linkedin.com/in/rajeev-bartwal-a07739306/
