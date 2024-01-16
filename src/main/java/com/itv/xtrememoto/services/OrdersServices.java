package com.itv.xtrememoto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itv.xtrememoto.dtos.RegisterOrdersDto;
import com.itv.xtrememoto.entities.Orders;
import com.itv.xtrememoto.entities.Product;
import com.itv.xtrememoto.repositories.OrderRepository;

@Service
public class OrdersServices {
    @Autowired
    private OrderRepository orderRepository;

    public Orders registerOrders(RegisterOrdersDto registerOrdersDto){
        Orders orders =new Orders();
        orders.setStatus(registerOrdersDto.getStatus());
        orders.setTotalprice(registerOrdersDto.getTotalprice());
        orderRepository.save(orders);
        return orders;

    }

    public List<Orders>getAll(){
        return orderRepository.findAll();
    }

    public Orders getById(Integer id){
        return orderRepository.findById(id).orElse(null);
    }
    public void deleteorders(Integer id) {
        orderRepository.deleteById(id);
    }

    public Orders updateorders(Integer id, Orders orders) {
        orders.setId(id);
        return orderRepository.save(orders);
    }

}
