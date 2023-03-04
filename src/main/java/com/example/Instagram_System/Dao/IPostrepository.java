package com.example.Instagram_System.Dao;

import com.example.Instagram_System.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.*;

@Repository
public interface IPostrepository extends JpaRepository<Post,Integer> {

}
