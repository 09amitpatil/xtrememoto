package com.itv.xtrememoto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itv.xtrememoto.entities.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

}
