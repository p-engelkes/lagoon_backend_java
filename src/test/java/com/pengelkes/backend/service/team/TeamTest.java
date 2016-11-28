package com.pengelkes.backend.service.team;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by pengelkes on 28.11.2016.
 */
public class TeamTest
{
    @Test
    public void fromJson() throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Team.class, new TeamDeserializer());
        mapper.registerModule(module);
        String jsonString = "{\"trainingTimes\":[{\"day\":\"Friday\",\"time\":\"19:00\"},{\"day\":\"Wednesday\",\"time\":\"19:00\"}],\"teamName\":\"TestTeam\"}";
        Team team = mapper.readValue(jsonString, Team.class);
        assertEquals(team.getTeamName(), "TestTeam");
    }
}