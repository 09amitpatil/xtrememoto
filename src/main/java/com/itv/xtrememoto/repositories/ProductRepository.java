package com.itv.xtrememoto.repositories;


import java.util.List;

//import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.itv.xtrememoto.entities.Product;
@CrossOrigin(origins = {"*"},maxAge = 4800,allowCredentials = "false")

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByName(String name);

}