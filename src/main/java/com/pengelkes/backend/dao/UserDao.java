package com.pengelkes.backend.dao;

import com.pengelkes.backend.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pengelkes on 27.10.2016.
 */
@Repository
public interface UserDao extends CrudRepository<User, Long>
{
    User save(User user);

    User findByUserName(String userName);
}
