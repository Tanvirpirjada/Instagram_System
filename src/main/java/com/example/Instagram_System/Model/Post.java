package com.example.Instagram_System.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post_tbl")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer post_id;

    private Timestamp created_date;
    private Timestamp updated_date;

    private String  post_data;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private User user_id;



}
