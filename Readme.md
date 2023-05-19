# Spring Boot Security + JWT + RestAPI + Mysql CRUD + JUNIT + MOCKITO Example

This project demonstrates the use of JWT Token-based Authentication. It utilizes several technologies and frameworks to implement the authentication system.

The project is built using Spring Boot, a popular Java framework for creating web applications. Spring Boot provides a variety of features and integrations, including security features. In this case, the Spring Security framework is used to handle authentication and authorization.

The authentication data, such as user credentials and roles, is stored in a MySQL database. Spring Data JPA is used to interact with the database. It provides a convenient way to implement the Data Access Object (DAO) layer by extending the JpaRepository interface. This allows for easy implementation of common database operations such as creating, reading, updating, and deleting user entities.

To transfer data between the controller layer and service layer, Data Transfer Objects (DTOs) are used. UserDTO and RoleDTO are defined in the DTO module. These DTOs encapsulate the necessary data for the respective operations and help maintain a separation of concerns between layers.

The UserController class contains various methods for user management. It exposes RESTful endpoints for operations such as retrieving all users, creating new users, updating existing users, and deleting users. Additionally, it provides methods for retrieving users by their ID, email, or username. These methods are responsible for handling incoming requests, invoking the appropriate services, and returning the corresponding responses.

The project includes a configuration folder and a security folder that contain code snippets related to Spring Security configuration. The configuration files override default Spring Security methods to enable JWT token authentication. These snippets define how the authentication process is handled, including the generation and validation of JWT tokens.

Test cases for the Service and Controller layers are included using JUnit and Mockito frameworks. These test cases ensure the correctness of the implemented features and validate the behavior of the API endpoints. The Controller layer test cases cover both public and private APIs, simulating various scenarios and asserting the expected outcomes.

In summary, this project utilizes Spring Boot, Spring Security, and Spring Data JPA to implement a RESTful API with JWT Token-based Authentication. 

Find the software used in the example.
1. Java 17
2. Spring Boot 3.0.6 
3. Maven 4.0
4. MySQL 8.0.32
5. Eclipse Mars

Here are few Snips of working application: 

1. Token Generation- Login(With username and password present in Database)
![Post_Login_Token_Generation](https://github.com/shreya-nipanikar/JWT_Auth_SpringBoot_Application/assets/57978423/9ea43227-3024-4a63-9ae4-9f1068113b35)

2. Create User
![Create_User](https://github.com/shreya-nipanikar/JWT_Auth_SpringBoot_Application/assets/57978423/1b245c28-e59e-40fa-8d77-72475127dea5)

3. Get All Users
![Create_User](https://github.com/shreya-nipanikar/JWT_Auth_SpringBoot_Application/assets/57978423/c2187e53-0d14-4ffb-9945-a4322052eeea)

4. Get Single User
![Get_All_Users](https://github.com/shreya-nipanikar/JWT_Auth_SpringBoot_Application/assets/57978423/74c59e3c-b802-46a7-841a-bffecafc68b5)
 
