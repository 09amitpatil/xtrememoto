package com.itv.xtrememoto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itv.xtrememoto.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByfirstname(String firstname);
    List<User> findByemailContaining(String email);

}
