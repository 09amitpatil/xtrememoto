package com.itv.xtrememoto.dtos;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegistorUserDto {
    private Integer id;
    
    @NotNull@NotEmpty
    private String firstname;
    @NotNull@NotEmpty
    private String lastname;
    @NotNull@NotEmpty
    private String email;
    @NotNull@NotEmpty
    private String password;
    @NotNull@NotEmpty
    private String confirmpassword;
    @NotNull@NotEmpty
    
    @com.itv.xtrememoto.validators.mobile
    private String  mobile;
    
    
}
