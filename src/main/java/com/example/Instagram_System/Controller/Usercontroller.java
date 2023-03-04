package com.example.Instagram_System.Controller;

import com.example.Instagram_System.Exception.RecourdNotFound;
import com.example.Instagram_System.Model.User;
import com.example.Instagram_System.Service.Userservice;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class Usercontroller {
    @Autowired
    Userservice userservice;

    //Here we post/saveuser in database with http post request through postman
    @PostMapping("saveuser")
    public ResponseEntity sevuser(@Valid @RequestBody User requestuser){// if you want to use validation with anotation so you have to pass model not String
        //JSONObject userdetails = new JSONObject(requestuser);
        //User newuser = setuser(userdetails);
       // validateuser(newuser);
        int id = userservice.saveuser(requestuser);
        return new ResponseEntity<>("user save id no " + id, HttpStatus.CREATED);

    }


    //public User validateuser(@Valid  User user){
     //   return user;
    //}


    //here we get data og users with native queary which written in repository/dao (data access object) class
    @GetMapping("getbyage")
    public User findbyname(@RequestParam Integer age){
       return userservice.getbyname(age);
    }

    //here we get data of user by pre difine methods like findById and Findall
    @GetMapping("getuser")
    public ResponseEntity<?> getuser(@Nullable @RequestParam Integer user_id) throws RecourdNotFound {
           JSONArray userslist=userservice.getuser(user_id);
           return new ResponseEntity<>(userslist.toString(),HttpStatus.OK);
    }

 //here  we are updating our userdata
    @PutMapping("updateuser/{user_id}")
    public ResponseEntity updateuser(@PathVariable Integer user_id, @RequestBody String requestuser) {
        JSONObject json=new JSONObject(requestuser);
        User updateduser=setuser(json);
       Integer updateduser_id= userservice.updateuser(user_id,updateduser);
       return new ResponseEntity<>("user id ->"+updateduser_id+"is updated",HttpStatus.OK);
    }

    //Here we dalete user
    @DeleteMapping("deleteuser/{user_id}")
    public ResponseEntity deleteuser(@PathVariable Integer user_id){
         Integer unactiveuserid=  userservice.deleteuser(user_id);
           return new ResponseEntity<>("user id->"+unactiveuserid+"is Deleted",HttpStatus.OK);
    }

  //here i am set user when i get data int string from postman and i am also able to use manual validation here but currently i am using anotation validation
    public User setuser(JSONObject json)  {
        User user = new User();
       // user.setUser_id(json.getInt("user_id"));
        user.setFirst_name(json.getString("first_name"));
        user.setLast_name(json.getString("last_name"));
        String email=json.getString("email");
        user.setEmail(email);
        user.setAge(json.getInt("age"));
        user.setPhone_number(json.getString("phone_number"));
        return user;
    }

}