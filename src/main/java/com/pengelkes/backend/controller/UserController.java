package com.pengelkes.backend.controller;

import com.pengelkes.backend.model.User;
import com.pengelkes.backend.service.UserService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by pengelkes on 27.10.2016.
 */
@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public User registerUser(@RequestBody User user)
    {
        return userService.save(user);
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(@RequestBody Map<String, String> json) throws ServletException
    {
        if (json.get("username") == null || json.get("password") == null)
        {
            throw new ServletException("Please fill in username and password!");
        }

        String userName = json.get("username");
        String password = json.get("password");

        User user = userService.findByUserName(userName);

        if (user == null)
        {
            throw new ServletException("Username not found!");
        }

        String pwd = user.getPassword();

        if (!password.equals(pwd))
        {
            throw new ServletException("Invalid login. Please check your name and password!");
        }

        return Jwts.builder()
                .setSubject(userName)
                .claim("roles", "user")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey")
                .compact();
    }
}
