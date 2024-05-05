package com.jwtexample.springsecurity6jwtexample.beans;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginBean {
    private String username;
    private String password;
}
