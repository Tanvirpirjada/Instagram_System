package com.example.Instagram_System.Controller;

import com.example.Instagram_System.Dao.IUserrepository;
import com.example.Instagram_System.Model.Post;
import com.example.Instagram_System.Model.User;
import com.example.Instagram_System.Service.Postservice;
import jakarta.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
public class Postcontroller {

    @Autowired
    IUserrepository userrepository;
    @Autowired
    Postservice postservice;
    @GetMapping("getpost")
    public ResponseEntity getpost(@Nullable @RequestParam  Integer user_id, @Nullable @RequestParam Integer post_id){
        JSONArray postdetails=postservice.getpost(user_id,post_id);
        return new ResponseEntity<>(postdetails.toString(),HttpStatus.OK);
    }

    @PutMapping("updatepost/{post_id}")
    public ResponseEntity updateuser(@PathVariable Integer post_id, @RequestBody String requestpost){
        JSONObject json=new JSONObject(requestpost);
        Post updatedpost=setpost(json);
        Integer updatedpost_id=postservice.updatepost(post_id,updatedpost);
        return new ResponseEntity<>("Post updatet with id"+updatedpost_id,HttpStatus.OK);
    }
    @PostMapping("post")
    public ResponseEntity createpost(@RequestBody String requestpost){
        JSONObject postdetails=new JSONObject(requestpost);
        Post posted=setpost(postdetails);
        Integer postedid=postservice.savepost(posted);
        return new ResponseEntity<>("post No : "+postedid+"has been uploaded ", HttpStatus.CREATED);
    }


    public Post setpost(JSONObject json){
        Post newpost=new Post();

        Timestamp created_date=new Timestamp(System.currentTimeMillis());
        newpost.setCreated_date(created_date);
        newpost.setPost_data(json.getString("post_data"));
        Integer user_id= json.getInt("user_id");
        User user=userrepository.findById(user_id).get();
        newpost.setUser_id(user);

        return newpost;
    }

    @DeleteMapping("deletepost/{postId}")
    public ResponseEntity<String> deletepost(@PathVariable  Integer  postId){

        postservice.deletpost(postId);
        return new ResponseEntity("post deleted",HttpStatus.NO_CONTENT);
    }
}
