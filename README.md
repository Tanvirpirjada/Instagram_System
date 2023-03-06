# Instagram_System

* This is a java and Springboot based project. In this project we build System Like Instaram.Just like an instagram Here we register user ,delete user, update user, get user And also We create post, delete post and update the post and get the post, So Here We performe Crud Operations.
 

# Data Flow
* Cotroller
->In controller we declair end points. In based on end point the methods in Cotroller class has been called. ex we Send sum data from postman using Post request
So in cotroller We declair method with name savedata With @PostMapping annotation in the top. Which tell Post request has to be come here. there is an posibility for more post methods in one class so tats why we Write end point for each methods based on request intrest.

In controller class there is Mehtods based on request ex getdata method for get request, updatedata for put request, deletedata for delete request. form controller class methods called service class methods in where our main buisness logic is written

* Service 
-> Service class in delete,Update,get,Save data based on cotroller methods requests and ints connect with the repository interface which exctends JPAREPOsitory which connect with database.

* Repository interface
->its Exctends with JPA repository which connect our applicatuon with data base.


# DataStructure and Databased used in Project
* List
* mysql

# Request Methods End points with link

* for User class
-> Create user : http://localhost:8080/saveuser

->Get user : http://localhost:8080/getuser

->get user by age : http://localhost:8080/getbyage

->Update user: http://localhost:8080/updateuser

->Delete user: http://localhost:8080/deleteuser

* for  Post Class 

-> post post :  http://localhost:8080/savepost

-> get post :  http://localhost:8080/getpost

-> delete post : http://localhost:8080/updatepost

-> update post : http://localhost:8080/deletepost

