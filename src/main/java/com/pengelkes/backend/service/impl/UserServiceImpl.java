package com.pengelkes.backend.service.impl;

import com.pengelkes.backend.model.User;
import com.pengelkes.backend.service.UserService;
import com.pengelkes.backend.service.UserServiceController;
import com.pengelkes.backend.validation.UserNameExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by pengelkes on 27.10.2016.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService
{
    private final UserServiceController userServiceController;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserServiceController userServiceController,
                           PasswordEncoder passwordEncoder)
    {
        this.userServiceController = userServiceController;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> registerNewUser(User user) throws UserNameExistsException
    {
        if (userNameExists(user.getUserName()))
        {
            throw new UserNameExistsException("There is already an account with that username: " + user.getUserName());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userServiceController.create(user);
    }

    @Override
    public Optional<User> save(User user)
    {

        return userServiceController.create(user);
    }

    @Override
    public Optional<User> findByUserName(String userName)
    {
        return userServiceController.findByUserName(userName);
    }

    private boolean userNameExists(String userName)
    {
        final Optional<User> userOptional = userServiceController.findByUserName(userName);

        return userOptional.isPresent();
    }
}
