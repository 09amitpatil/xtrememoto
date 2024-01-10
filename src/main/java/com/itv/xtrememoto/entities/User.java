package com.itv.xtrememoto.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer id;
    @Column(name="fname",nullable=false)
        private String firstname;
    @Column(name="lname")
        private String lastname;
    @Column(unique = true)
        private String email;
    @Column(unique = true)
        private String password;
        
        private String mobile;
 

    

}