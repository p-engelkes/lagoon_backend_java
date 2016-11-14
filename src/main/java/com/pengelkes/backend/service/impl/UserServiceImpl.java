package com.pengelkes.backend.service.impl;

import com.pengelkes.backend.dao.UserDao;
import com.pengelkes.backend.model.User;
import com.pengelkes.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pengelkes on 27.10.2016.
 */
@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserDao userDao;

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
}
