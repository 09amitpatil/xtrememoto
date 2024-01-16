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
   private UserRepository userRepository;

   public User registerUser(RegistorUserDto registorUserDto) {
      User user = new User();
      user.setFirstname(registorUserDto.getFirstname());
      user.setLastname(registorUserDto.getLastname());
      user.setEmail(registorUserDto.getEmail());
      user.setPassword(registorUserDto.getPassword());
      user.setMobile(registorUserDto.getMobile());
      userRepository.save(user);
      return user;

   }

   public List<User> getAll() {
      return userRepository.findAll();
   }

   public User getById(Integer id) {
      return userRepository.findById(id).orElse(null);
   }

   public void deleteuser(Integer id) {
      userRepository.deleteById(id);
   }

   public User updateUser(Integer id, User user) {
      user.setId(id);
      return userRepository.save(user);
   }

   public List<User> findByfirstname(String firstname) {
      return this.userRepository.findByfirstname(firstname);
   }

   public List<User> findByemail(String email) {
      return this.userRepository.findByemailContaining(email);
   }

}
