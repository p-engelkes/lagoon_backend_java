package com.pengelkes.backend.service;

import com.pengelkes.backend.model.User;
import com.pengelkes.backend.validation.UserNameExistsException;
import org.springframework.stereotype.Service;

/**
 * Created by pengelkes on 27.10.2016.
 */
@Service
public interface UserService
{
    User registerNewUser(User user) throws UserNameExistsException;

    User save(User user);

    User findByUserName(String userName);
}
