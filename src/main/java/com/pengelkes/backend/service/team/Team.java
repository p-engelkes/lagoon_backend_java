package com.pengelkes.backend.service.team;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.pengelkes.backend.entity.hibernate.HstoreUserType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

/**
 * Created by pengelkes on 23.11.2016.
 */
@Entity
@Table(name = "teams")
@TypeDef(name = "hstore", typeClass = HstoreUserType.class)
public class Team
{
    public static final String TRAINING_TIMES_JSON = "trainings";
    public static final String TRAINING_DAY_JSON = "day";
    public static final String TRAINING_TIME_JSON = "time";
    public static final String TEAM_NAME_JSON = "teamName";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "team_name")
    private String teamName;

    @Type(type = "hstore")
    @Column(name = "training_times")
    private HashMap<String, String> trainingTimes;

    public static Optional<Team> fromJson(String jsonString)
    {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Team.class, new TeamDeserializer());
        mapper.registerModule(module);

        try
        {
            return Optional.ofNullable(mapper.readValue(jsonString, Team.class));
        } catch (IOException e)
        {
            return Optional.empty();
        }
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTeamName()
    {
        return teamName;
    }

    public void setTeamName(String teamName)
    {
        this.teamName = teamName;
    }

    public HashMap<String, String> getTrainingTimes()
    {
        return trainingTimes;
    }

    public void setTrainingTimes(HashMap<String, String> trainingTimes)
    {
        this.trainingTimes = trainingTimes;
    }
}
