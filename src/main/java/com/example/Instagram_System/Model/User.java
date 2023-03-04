package com.example.Instagram_System.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    private String first_name;
    private String last_name;

    //@Email(message = "email is invalid")
  //  @Size( message = "Size shold be btw 2 to 4")
    @Length(min = 2, max = 20,message = "length must be 2 to 20 ")
    @NotBlank(message="this field should be mandatory")
    private String email;
    private Integer age;

    private String phone_number;
    //private String  password;
}
