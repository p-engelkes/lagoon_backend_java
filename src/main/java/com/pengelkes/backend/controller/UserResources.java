package com.pengelkes.backend.controller;

import com.pengelkes.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pengelkes on 28.10.2016.
 */
@RestController
@RequestMapping("/rest")
public class UserResources
{

    @Autowired
    private UserService userService;

    @RequestMapping("/user/users")
    public String loginSuccess() {
        return "Login successfull";
    }
}
