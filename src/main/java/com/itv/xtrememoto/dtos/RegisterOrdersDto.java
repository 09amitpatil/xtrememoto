package com.itv.xtrememoto.dtos;

import lombok.Data;

@Data
public class RegisterOrdersDto {
    private Integer id;
    private String status;
    private String items;
    private Float totalprice;

}
