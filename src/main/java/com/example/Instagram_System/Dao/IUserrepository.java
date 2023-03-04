package com.example.Instagram_System.Dao;

import com.example.Instagram_System.Model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserrepository extends JpaRepository<User, Integer> {

    User findByAge(Integer age);
}
