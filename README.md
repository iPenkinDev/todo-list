# TodoList


The simple CRUD program ToDoList.

### Technology stack:
 -Java 17    
 -Spring Framework  
 -Apache Maven  
 -Tomcat  
 -PostgreSQL    
 -Hibernate     
 -Thymeleaf  
 -Lombok  
 -Docker  
 
 ### Deploy application
 **Prerequisites** 
 1. Java 17+
 2. Apache Maven
 3. Docker
 
 **Build**
 
 ```mvn clean package```  
 ```docker build -t todo_app .```  
 ```docker-compose up --build```  
 
 **In browser** 
 
 ```http://localhost:8080/TodoList/tasks```
