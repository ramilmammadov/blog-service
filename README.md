<<<<<<< HEAD
# blog-service
=======
# Blog Service - Spring Boot Microservice

## 📌 Project Overview
This is a **Spring Boot-based microservice** for a **Blog Application** that supports CRUD operations for articles, authentication and authorization using **Spring Security**, and service discovery using **Eureka**. The project is designed for cloud deployment.

---

## 📋 Features Implemented
### ✅ **Task 1: Blog Application**
1. **Data Model & JPA Entities**
    - Designed a relational model for the blog application.
    - Implemented JPA entities for **Article, User, and Role**.

2. **Spring Data JPA Queries**
    - Query to find **all articles published before a given date**.
    - Search articles **between two dates and by keyword**.
    - Soft delete functionality (status change instead of actual deletion).

3. **REST API for CRUD Operations**
    - Created **ArticleController** to handle CRUD operations.
    - Implemented **Service Layer** for business logic.
    - **Unit tests** for controller and service methods.

4. **Exception Handling**
    - **GlobalExceptionHandler** to return custom error messages.
    - Handled `EntityNotFoundException`, `MethodArgumentNotValidException`, and authentication errors.

---

### ✅ **Task 2: Microservice Application**
1. **Eureka Integration**
    - Registered service with **Spring Cloud Netflix Eureka**.

2. **Spring Security Authentication**
    - Configured **authentication via a database**.
    - Users and roles stored in **H2 database**.
    - Implemented **BCrypt password encoding**.

3. **Authorization Based on User Roles**
    - Allowed **only authenticated users** to access API.
    - Restricted **admin-only endpoints**.
    - Used `@PreAuthorize` for method-level security.

4. **Swagger API Documentation with Security**
    - Integrated **SpringDoc OpenAPI**.
    - Enabled JWT authentication in Swagger UI.
    - Provided a **static JWT token** for local testing.

---

## 🛠️ **Security Configuration (SecurityConfig.java)**
The project uses **Spring Security** to protect API endpoints:

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/auth/**").permitAll()
            .requestMatchers("/api/articles/**").hasAnyRole("USER", "ADMIN")
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        )
        .addFilterBefore(new StaticTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    return http.build();
}
```

### **🔹 Static Token for Local Testing**
A **static JWT token** is used to simplify authentication during local testing.

- **Token:** `Bearer my-static-jwt-token`
- **How to Use:**
    1. Open **Swagger UI** (`http://localhost:8080/swagger-ui.html`)
    2. Click the **Authorize (🔑) button**
    3. Enter the token: `Bearer my-static-jwt-token`
    4. Now, authenticated API requests can be tested.

#### **Custom Static Token Filter**
```java
public static class StaticTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if ("Bearer my-static-jwt-token".equals(authHeader)) {
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid or missing Authorization header");
        }
    }
}
```

---

## 🚀 **Deployment Guide**
This microservice is designed for deployment in a **cloud environment** like **AWS or Azure**.

### **🔹 Deployment Considerations**
- **Profiles:**
    - `dev`: Local development, in-memory **H2 database**, **Swagger enabled**.
    - `test`: Used for testing, **H2 database**, runs on `8081`.
    - `prod`: Production-ready, should use **MySQL/PostgreSQL**, **Swagger disabled**.

- **Eureka Registration:** Enabled in production for service discovery.
- **Database:** Update `application-prod.properties` to use a **real database**.

### **🔹 Steps for Deployment on AWS/Azure**
1. **Database Setup:** Use **Amazon RDS** (PostgreSQL/MySQL) or **Azure Database**.
2. **Build the Application:**
   ```sh
   mvn clean install
   ```
3. **Containerization:** (Optional for Kubernetes Deployment)
   ```sh
   docker build -t blog-service .
   ```
4. **Push to Cloud Service:**
    - **AWS:** Deploy using **Elastic Beanstalk / ECS**.
    - **Azure:** Use **Azure App Service**.

### **🔹 Running in Production**
Use the active profile for production:
```sh
java -jar -Dspring.profiles.active=prod blog-service.jar
```

---

## 📜 **API Documentation (Swagger UI)**
By default, **Swagger is disabled in production**, but it can be enabled by modifying:
```properties
springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true
```

Once enabled, API docs are available at:
- **Swagger UI:** [`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html)
- **OpenAPI JSON:** [`http://localhost:8080/v3/api-docs`](http://localhost:8080/v3/api-docs)

---

## 🛠 **How to Run Locally**
1. **Clone the repository:**
   ```sh
   git clone https://github.com/your-repo/blog-service.git
   cd blog-service
   ```
2. **Run the application with the active profile (`dev`):**
   ```sh
   mvn clean install
   mvn spring-boot:run -Dspring.profiles.active=dev
   ```
3. **Access the API:**
    - Swagger: `http://localhost:8080/swagger-ui.html`
    - H2 Database Console: `http://localhost:8080/h2-console`

---

## **👥 Author**
- **Ramil Mammadov**

---

🚀 **Enjoy Building Your Microservice!** 🎯

>>>>>>> task-1
