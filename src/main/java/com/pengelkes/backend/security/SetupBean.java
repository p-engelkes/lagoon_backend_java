package com.pengelkes.backend.security;

import com.pengelkes.backend.service.team.TeamService;
import com.pengelkes.backend.service.user.User;
import com.pengelkes.backend.service.user.UserService;
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
    private final TeamService teamService;

    @Autowired
    public SetupBean(UserService userService,
                     TeamService teamService)
    {
        this.userService = userService;
        this.teamService = teamService;
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

        }
    }
}
