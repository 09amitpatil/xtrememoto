package com.itv.xtrememoto.dtos;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
//@VerifyPasswod(Filed = "password", matchingFiled = "confirmpassword")
public class RegistorUserDto {
    private Integer id;
    @Size(min = 4,max = 10,message = "pet name length should have min 4 max 10 char")
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
