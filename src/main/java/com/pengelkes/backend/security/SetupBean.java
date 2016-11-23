package com.pengelkes.backend.security;

import com.pengelkes.backend.model.User;
import com.pengelkes.backend.service.UserService;
import com.pengelkes.backend.validation.UserNameExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by pengelkes on 16.11.2016.
 */
@Component
public class SetupBean
{
    private final UserService userService;

    @Autowired
    public SetupBean(UserService userService)
    {
        this.userService = userService;
    }

    @PostConstruct
    public void setupUser()
    {
        final User user = new User("admin@fake.com", "adminpass");
        try
        {
            userService.registerNewUser(user);
        } catch (UserNameExistsException e)
        {
            e.printStackTrace();
        }
    }
}
