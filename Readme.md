# Spring Boot Security + JWT + RestAPI + Mysql CRUD + JUNIT + MOCKITO Example

This project demonstrates the use of JWT Token-based Authentication. In this example, we will preserve data related to user authentication in a database. The UserController includes methods for getting all users, creating users, updating users, getting users by id, getting users by email, searching for users by username, and deleting users. Essentially, we constructed a rest API utilizing the DAO layer. 

This sample also includes test cases for the Service and Controller layers. For testing, we utilized Junit and Mockito. There are test cases written in Controller layer for accessing private and public APIs.

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

4. Get All Users
![Create_User](https://github.com/shreya-nipanikar/JWT_Auth_SpringBoot_Application/assets/57978423/c2187e53-0d14-4ffb-9945-a4322052eeea)

6. Get Single User
![Get_All_Users](https://github.com/shreya-nipanikar/JWT_Auth_SpringBoot_Application/assets/57978423/74c59e3c-b802-46a7-841a-bffecafc68b5)
 
