package com.itv.xtrememoto.dtos;


import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterProductDto {
    private Integer id;
    @Size(min = 4,max = 20,message = "name length should have min 4 max 20 char")
    private String name;
    
    private String Manufature;
 
    private String price;
    
    private String description;

}