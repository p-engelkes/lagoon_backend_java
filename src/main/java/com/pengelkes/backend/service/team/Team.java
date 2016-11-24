package com.pengelkes.backend.service.team;

import com.pengelkes.backend.entity.hibernate.HstoreUserType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Map;

/**
 * Created by pengelkes on 23.11.2016.
 */
@Entity
@Table(name = "teams")
@TypeDef(name = "hstore", typeClass = HstoreUserType.class)
public class Team
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "team_name")
    private String teamName;

    @Type(type = "hstore")
    @Column(name = "training_times")
    private Map<String, String> trainingTimes;

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

    public Map<String, String> getTrainingTimes()
    {
        return trainingTimes;
    }

    public void setTrainingTimes(Map<String, String> trainingTimes)
    {
        this.trainingTimes = trainingTimes;
    }
}
