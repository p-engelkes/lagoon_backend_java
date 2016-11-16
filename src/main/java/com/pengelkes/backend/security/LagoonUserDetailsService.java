package com.pengelkes.backend.security;

import com.pengelkes.backend.model.User;
import com.pengelkes.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by pengelkes on 16.11.2016.
 */
@Service
@Transactional
public class LagoonUserDetailsService implements UserDetailsService
{
    private static final String ROLE_USER = "ROLE_USER";

    private final UserService userService;

    @Autowired
    public LagoonUserDetailsService(UserService userService)
    {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException
    {
        final User user = userService.findByUserName(userName);
        if (user == null)
        {
            throw new UsernameNotFoundException("Username was not found: " + userName);
        }

        return new org.springframework.security.core.userdetails.User(
            user.getUserName(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                getAuthorities(ROLE_USER)
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }
}
