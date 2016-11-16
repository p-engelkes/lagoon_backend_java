package com.pengelkes.backend;

import com.pengelkes.backend.model.User;
import com.pengelkes.backend.security.LagoonUserDetailsService;
import com.pengelkes.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;

/**
 * Created by pengelkes on 16.11.2016.
 */
@EnableWebSecurity
public class LagoonSecurityConfig extends WebSecurityConfigurerAdapter
{
    private final LagoonUserDetailsService lagoonUserDetailsService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LagoonSecurityConfig(LagoonUserDetailsService lagoonUserDetailsService,
                                UserService userService,
                                PasswordEncoder passwordEncoder)
    {
        super();

        this.lagoonUserDetailsService = lagoonUserDetailsService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void saveTestUser()
    {
        final User user = new User();
        if (userService.findByUserName("test") == null)
        {
            user.setUserName("test");
            user.setPassword(this.passwordEncoder.encode("pass"));
            userService.save(user);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/user/register", "/user/login").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
        ;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(this.lagoonUserDetailsService).passwordEncoder(this.passwordEncoder);
    }
}
