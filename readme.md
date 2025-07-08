## Order Spring
Created by Agus Siswanto

### Dependencies
- Lombok
- Spring Web
- Spring Data JPA
- MySQL Driver
- Jakarta Validation
- JWT

## Requirements

- **Java 21** (make sure `JAVA_HOME` points to Java 21)
- **Maven 3.8+**
- **MySQL 8+** (ensure the database server is running and accessible)
- **Git** (for cloning the repository, if needed)
- **IDE** such as IntelliJ IDEA, VS Code, or any preferred editor

### Environment Setup

- Ensure `JAVA_HOME` is set to Java 21
- Make sure MySQL is running on port 3306 (or update the configuration accordingly)
- Create a database named `order_spring` in your MySQL server

Sample `application.properties` configuration:

```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=xcodeme21
spring.datasource.password=xxxxx
spring.datasource.url=jdbc:mysql://localhost:3306/order_spring
spring.datasource.type=com.zaxxer.hikari.HikariDataSource
spring.datasource.hikari.minimum-idle=10
spring.datasource.hikari.maximum-pool-size=50
