package com.pengelkes.backend.service.impl;

import com.pengelkes.backend.dao.UserDao;
import com.pengelkes.backend.model.User;
import com.pengelkes.backend.service.UserService;
import com.pengelkes.backend.validation.UserNameExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pengelkes on 27.10.2016.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService
{
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao,
                           PasswordEncoder passwordEncoder)
    {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerNewUser(User user) throws UserNameExistsException
    {
        if (userNameExists(user.getUserName()))
        {
            throw new UserNameExistsException("There is already an account with that username: " + user.getUserName());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public User save(User user)
    {

        return userDao.save(user);
    }

    @Override
    public User findByUserName(String userName)
    {
        return userDao.findByUserName(userName);
    }

    private boolean userNameExists(String userName)
    {
        final User user = userDao.findByUserName(userName);

        return user != null;
    }
}
