package com.jwtexample.springsecurity6jwtexample.controller;


import com.jwtexample.springsecurity6jwtexample.beans.JwtAuthResponse;
import com.jwtexample.springsecurity6jwtexample.beans.LoginBean;
import com.jwtexample.springsecurity6jwtexample.service.LoginService;
import com.jwtexample.springsecurity6jwtexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/login")
    public JwtAuthResponse loginUser(LoginBean loginRequest) throws Exception{
        return loginService.logonUser(loginRequest);
    }

    @RequestMapping("/protectedEp")
    public String hello(){
        return "Hello this is protected endpoint";
    }

    @RequestMapping("/normalEp")
    public String hello2(){
        return "Hello this is protected endpoint only for normal user";
    }

    @RequestMapping("/suEp")
    public String hello3(){
        return "Hello this is protected endpoint only for admin";
    }
}
