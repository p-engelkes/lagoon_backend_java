package com.pengelkes.backend.service.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by pengelkes on 23.11.2016.
 */
@Service
@Transactional
public class TeamServiceImpl implements TeamService
{
    private final TeamServiceController teamServiceController;

    @Autowired
    public TeamServiceImpl(TeamServiceController teamServiceController)
    {
        this.teamServiceController = teamServiceController;
    }

    @Override
    public Optional<Team> create(Team team)
    {
        return teamServiceController.create(team);
    }

    @Override
    public Optional<Team> getByName(String name)
    {
        return teamServiceController.getByName(name);
    }

    @Override
    public List<Team> getAllTeams()
    {
        return teamServiceController.getAllTeams();
    }

    @Override
    public Optional<Team> getTeamByName(String name)
    {
        return teamServiceController.getTeamByName(name);
    }
}
