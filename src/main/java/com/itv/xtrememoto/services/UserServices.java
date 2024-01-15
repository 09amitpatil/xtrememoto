package com.itv.xtrememoto.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itv.xtrememoto.dtos.RegistorUserDto;
import com.itv.xtrememoto.entities.User;
import com.itv.xtrememoto.repositories.UserRepository;

@Service
public class UserServices {

   @Autowired
   private UserRepository repository;

   public User registerUser(RegistorUserDto registorUserDto) {
      User user = new User();
      user.setFirstname(registorUserDto.getFirstname());
      user.setLastname(registorUserDto.getLastname());
      user.setEmail(registorUserDto.getEmail());
      user.setPassword(registorUserDto.getPassword());
      user.setMobile(registorUserDto.getMobile());
      repository.save(user);
      return user;

   }

   public List<User> getAll() {
      return repository.findAll();
   }

   public User getById(Integer id) {
      return repository.findById(id).orElse(null);
   }

   public void deleteuser(Integer id) {
      repository.deleteById(id);
   }

   public User updateUser(Integer id, User user) {
      user.setId(id);
      return repository.save(user);
   }

   public List<User> findByfirstname(String firstname) {
      return this.repository.findByfirstname(firstname);
   }

   public List<User> findByemail(String email) {
      return this.repository.findByemailContaining(email);
   }

}
