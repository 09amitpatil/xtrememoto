package com.itv.xtrememoto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itv.xtrememoto.dtos.RegisterOrdersDto;
import com.itv.xtrememoto.services.OrdersServices;

import jakarta.validation.Valid;

@RestController
public class OrderController {
    @Autowired
    private OrdersServices ordersServices;

    // Adding orders
    @PostMapping("/orders")
    public ResponseEntity<?> registerOrders(@RequestBody @Valid RegisterOrdersDto registerOrdersDto) {
        return new ResponseEntity<>(this.ordersServices.registerOrders(registerOrdersDto), HttpStatus.CREATED);
    }

    // retriving orders
    @GetMapping("/orders")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.ordersServices.getAll());
    }

}
