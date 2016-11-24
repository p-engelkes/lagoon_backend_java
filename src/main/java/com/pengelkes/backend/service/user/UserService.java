package com.pengelkes.backend.service.user;

import com.pengelkes.backend.validation.UserNameExistsException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by pengelkes on 27.10.2016.
 */
@Service
public interface UserService
{
    Optional<User> registerNewUser(User user) throws UserNameExistsException;

    Optional<User> save(User user);

    Optional<User> findByUserName(String userName);
}
