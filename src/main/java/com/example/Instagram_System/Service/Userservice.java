package com.example.Instagram_System.Service;

import com.example.Instagram_System.Dao.IUserrepository;
import com.example.Instagram_System.Exception.RecourdNotFound;
import com.example.Instagram_System.Model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Userservice {

    @Autowired
    IUserrepository userrepository;

    public int saveuser(User newuser) {
        userrepository.save(newuser);
        return newuser.getUser_id();
    }

    public JSONArray getuser(Integer userId) {
        JSONArray getarray = new JSONArray();
        List<User> getusers;
        if (userId == null) {
            getusers = userrepository.findAll();
            getarray = setuser(getusers);
        } else if (userId != null && userrepository.findById(userId).isPresent()) {
            getusers = new ArrayList<>();
            getusers.add(userrepository.findById(userId).get());
            getarray = setuser(getusers);

        }

        return getarray;
    }


    public JSONArray setuser(List<User> user) {
        JSONArray jsonArray = new JSONArray();
        for (User users : user) {
            JSONObject json = new JSONObject();
            json.put("user_id", users.getUser_id());
            json.put("first_name", users.getFirst_name());
            json.put("last_name", users.getLast_name());
            json.put("email", users.getEmail());
            json.put("age", users.getAge());
            json.put("phone_number", users.getPhone_number());
            jsonArray.put(json);
        }
        return jsonArray;
    }

    public Integer updateuser(Integer userId, User updateduser) {
        User olduser = userrepository.findById(userId).get();
        updateduser.setUser_id(olduser.getUser_id());
        userrepository.save(updateduser);
        return updateduser.getUser_id();
    }

    public Integer deleteuser(Integer userId) {
        User unactiveuser=userrepository.findById(userId).get();
        userrepository.delete(unactiveuser);
        return unactiveuser.getUser_id();
    }

    public User getbyname(Integer age) {
        return userrepository.findByAge(age);
    }
}
