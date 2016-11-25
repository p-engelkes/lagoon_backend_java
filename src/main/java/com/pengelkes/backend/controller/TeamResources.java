package com.pengelkes.backend.controller;

import com.pengelkes.backend.service.team.Team;
import com.pengelkes.backend.service.team.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by pengelkes on 24.11.2016.
 */
@RestController
@RequestMapping("/rest")
public class TeamResources
{

    private final TeamService teamService;

    @Autowired
    public TeamResources(TeamService teamService)
    {
        this.teamService = teamService;
    }

    @RequestMapping("/team/teams")
    public List<Team> getTeams()
    {
        return teamService.getAllTeams();
    }

    @RequestMapping(value = "/team", method = RequestMethod.POST)
    public Optional<Team> createTeam(@RequestBody String string)
    {
        //TODO: parse JSON to team
        return Optional.empty();
    }

    @RequestMapping("/team")
    public Team getTeam(@RequestParam(value = "name") String name)
    {
        System.out.println(name);
        Optional<Team> teamOptional = teamService.getTeamByName(name);

        if (teamOptional.isPresent())
        {
            return teamOptional.get();
        }

        return new Team();
    }
}
