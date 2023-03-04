package com.example.Instagram_System.Service;

import com.example.Instagram_System.Dao.IPostrepository;
import com.example.Instagram_System.Dao.IUserrepository;
import com.example.Instagram_System.Model.Post;
import com.example.Instagram_System.Model.User;
import com.fasterxml.jackson.annotation.JsonRawValue;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class Postservice {


    @Autowired
    IUserrepository userrepository;
    @Autowired
    IPostrepository postrepository;
    public Integer savepost(Post posted) {
        postrepository.save(posted);
        return posted.getPost_id();
    }

    public JSONArray getpost(Integer userId, Integer postId) {
        JSONArray getposts=new JSONArray();
        JSONObject json=new JSONObject();
        List<Post> post;
        if(postId!=null && userId!=null ){//one post
            User user=userrepository.findById(userId).get();
            if(postrepository.findById(postId).isPresent() && postrepository.findById(user.getUser_id()).isPresent()){
                Post posts=postrepository.findById(postId).get();
                json=setResponse(posts);
                getposts.put(json);
            }

        }
        else if(postId!=null) {//one post
             Post posts=postrepository.findById(postId).get();
             json=setResponse(posts);
             getposts.put(json);
        }
        else if(userId!=null){//post related to user
          post=new ArrayList<>();
          post=postrepository.findAll();
          for(Post posts: post){
              if(posts.getUser_id().getUser_id()==userId){
                  json=setResponse(posts);
                  getposts.put(json);
              }
          }
        }
        else {//all post
            post=postrepository.findAll();
            for(Post posts: post) {
                json = setResponse(posts);
                getposts.put(json);
            }
        }

        return getposts;
    }

    public JSONObject setResponse(Post post){

                JSONObject json = new JSONObject();
                json.put("post_id", post.getPost_id());
                json.put("created_date", post.getCreated_date());
                json.put("post_data", post.getPost_data());
                User user = userrepository.findById(post.getUser_id().getUser_id()).get();
                JSONObject userobj = new JSONObject();
                userobj.put("user_id", user.getUser_id());
                userobj.put("first_name", user.getFirst_name());
                userobj.put("email", user.getEmail());
                json.put("user", userobj);

       return json;

    }

    public Integer updatepost(Integer postId, Post updatedpost) {
        Post oldpost=postrepository.findById(postId).get();

        updatedpost.setPost_id(oldpost.getPost_id());
        Timestamp updateddate=new Timestamp(System.currentTimeMillis());
        updatedpost.setUpdated_date(updateddate);

        postrepository.save(updatedpost);
        return updatedpost.getPost_id();
    }

    public void deletpost(Integer postId) {
        Post oldPost=postrepository.findById(postId).get();
        postrepository.delete(oldPost);
    }
}
