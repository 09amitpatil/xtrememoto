package com.itv.xtrememoto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itv.xtrememoto.dtos.RegistorUserDto;
import com.itv.xtrememoto.entities.User;
import com.itv.xtrememoto.services.UserServices;

import jakarta.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private UserServices userServices;

    // adding user
    @PostMapping("/users")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegistorUserDto registerUserDto) {
        return new ResponseEntity<>(this.userServices.registerUser(registerUserDto), HttpStatus.CREATED);
    }

    // retriving user
    @GetMapping("/users")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.userServices.getAll());
    }

    // searching user
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        User userExits = this.userServices.getById(id);
        if (userExits != null) {
            return new ResponseEntity<>(userExits, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
        }
    }
    //deleting user
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteuser(@PathVariable Integer id) {
        User userExits = this.userServices.getById(id);
        if (userExits != null) {
            this.userServices.deleteuser(id);
            return new ResponseEntity<>("user deleted sucessfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
        }
    }
    //update user
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateuser(@PathVariable Integer id) {
        User userExits = this.userServices.getById(id);
        if (userExits != null) {
            this.userServices.deleteuser(id);
            return new ResponseEntity<>("user update sucessfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/searchbyfname")
    public ResponseEntity<?> findByFirstname(@RequestParam("firstname") String firstname) {
        List<User> users = this.userServices.findByfirstname(firstname);
        if (users.isEmpty()) {
            return new ResponseEntity<>("no user exist with this  name", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(this.userServices.findByfirstname(firstname), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/searchbyemail")
    public ResponseEntity<?> findByemailContaining(@RequestParam String email) {
        List<User> users = this.userServices.findByemail(email);
        if (users.isEmpty()) {
            return new ResponseEntity<>("no user exist with this  email", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(users, HttpStatus.NOT_FOUND);
        }
    }
}