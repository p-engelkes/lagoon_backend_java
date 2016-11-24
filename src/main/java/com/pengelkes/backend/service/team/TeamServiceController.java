package com.pengelkes.backend.service.team;

import com.pengelkes.backend.jooq.tables.records.TeamsRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.pengelkes.backend.jooq.tables.Teams.TEAMS;

/**
 * Created by pengelkes on 23.11.2016.
 */
@Service
@Transactional
public class TeamServiceController
{
    private DSLContext dsl;

    @Autowired
    public TeamServiceController(DSLContext dsl)
    {
        this.dsl = dsl;
    }

    public Optional<Team> create(Team team)
    {
        TeamsRecord teamsRecord = null;

        try
        {
            teamsRecord = dsl.newRecord(TEAMS, team);
            teamsRecord.store();
        } catch (Exception e)
        {
            System.out.println(e);
        }

        team.setId(teamsRecord.getId());
        return Optional.of(team);
    }

    public Optional<Team> getByName(String name)
    {
        TeamsRecord teamsRecord = dsl.selectFrom(TEAMS)
                .where(TEAMS.TEAM_NAME.eq(name))
                .fetchOne();

        return getTeamEntity(teamsRecord);
    }

    private Optional<Team> getTeamEntity(Record record)
    {
        if (record != null)
        {
            return Optional.of(record.into(Team.class));
        }

        return Optional.empty();
    }
}
