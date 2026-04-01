# 📘 Student Course Registration and Management System

---

## 📌 Project Description

The **Student Course Registration and Management System** is a web-based application developed using Spring Boot. It is designed to help universities manage student records, course information, and course registrations efficiently.

The system allows administrators to manage students and courses, while enabling students to register and drop courses. It follows the **Model-View-Controller (MVC)** architecture and integrates with a **MySQL database** for persistent data storage.

---

## 🚀 Features

### 🎓 Student Management

* Add new student
* View all students
* Edit student details
* Delete student

---

### 📚 Course Management

* Add new course
* View all courses
* Edit course details
* Delete course

---

### 📝 Course Registration

* Register student for a course
* View registered courses
* Drop registered course

---

### 📊 Dashboard

* Total number of students
* Total number of courses
* Total registrations

---

### 🔐 Authentication (Optional Bonus)

* Login system using Spring Security
* Restricted access to system features

---

## 🏗️ System Architecture

The application follows the **Spring Boot MVC architecture**:

* **Model Layer**: Represents database entities (`Student`, `Course`, `Registration`)
* **View Layer**: Thymeleaf templates (HTML pages)
* **Controller Layer**: Handles HTTP requests
* **Service Layer**: Contains business logic
* **Repository Layer**: Handles database operations using Spring Data JPA

---

## 🛠️ Technologies Used

* Java
* Spring Boot
* Spring MVC
* Spring Data JPA
* Thymeleaf
* MySQL Database
* Maven
* HTML/CSS
* Bootstrap

---

## 🗄️ Database Design

The system uses three main tables:

### 1. Student

* StudentID (Primary Key)
* Name
* Email

### 2. Course

* CourseID (Primary Key)
* Title
* Credits

### 3. Registration

* RegistrationID (Primary Key)
* StudentID (Foreign Key)
* CourseID (Foreign Key)
* RegisteredAt
* Status
* Grade
* Term
* Section

📌 A many-to-many relationship exists between Student and Course, resolved using the Registration table.

---

## ⚙️ How to Run the Project

### 🔹 Prerequisites

* Java JDK 17+
* Maven
* MySQL Server
* IDE (IntelliJ or Eclipse)

---

### 🔹 Steps

1. Clone the repository:

```
git clone https://github.com/your-username/student-management-system.git
```

2. Navigate into the project:

```
cd student-management-system
```

3. Create MySQL database:

```sql
CREATE DATABASE student_management;
```

4. Update `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/student_management
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
```

5. Run the application:

```
mvn spring-boot:run
```

6. Open in browser:

```
http://localhost:8080
```

---

## 📸 Screenshots

### Dashboard
![Dashboard](screenshots/Admin.png)

### Students Page
![Students](screenshots/Student-portal.png)

### Courses Page
![Courses](screenshots/Course-catalog.png)

### Registrations Page
![Registrations](screenshots/Registration-catalog.png)

---

## ⚠️ Challenges Faced

* Implementing entity relationships (Many-to-Many)
* Integrating Thymeleaf with Spring Boot controllers
* Managing database connectivity with MySQL
* Handling course registration logic

---

## 📚 Lessons Learned

* Understanding Spring Boot MVC architecture
* Working with Spring Data JPA and MySQL
* Building a full-stack web application
* Implementing CRUD operations effectively

---

## 💡 Unique Features / Innovations

* Dashboard with real-time statistics
* Clean and responsive UI using Bootstrap
* Course registration system with relationship mapping
* Secure login functionality (optional)

---

## 👥 Group Members

* Member 1: _CS/MK/1870/09/23_________________
* Member 2: __________________
* Member 3: __________________
* Member 4: __________________

---

## 🌐 Live Demo (Optional)

```
https://your-app-link.onrender.com
```

---

## 📄 License

This project is for academic purposes only.

---

## 🙌 Acknowledgement

We would like to thank our lecturer and institution for guiding us through this project and providing the knowledge required to successfully implement it.

---
