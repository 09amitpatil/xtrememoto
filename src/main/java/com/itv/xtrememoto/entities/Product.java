package com.itv.xtrememoto.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String manufature;
    private String price;
    private String description;
    private String imageUrl;


    
@ManyToMany
@JoinTable(
    name="myproduct_orders",
    joinColumns = @JoinColumn(name="product_id" ,referencedColumnName="id"),
    inverseJoinColumns = @JoinColumn(name="order_id" ,referencedColumnName="id")
    )
    private List<Orders>orders;
}