package com.pengelkes.backend.service.team;

import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by pengelkes on 23.11.2016.
 */
@Service
public interface TeamService
{
    Optional<Team> create(Team team);

    Optional<Team> getByName(String name);
}
