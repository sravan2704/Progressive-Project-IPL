package com.wecp.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import com.wecp.progressive.entity.Team;
import com.wecp.progressive.service.TeamService;

@Service
public class TeamServiceImplArraylist implements TeamService  {

    List<Team> teams = new ArrayList<>();
    public TeamServiceImplArraylist()
    {
    teams.add(new Team(1,"RCB","Benguluru","KingFisher",2007));
    teams.add(new Team(2, "SRH", "Hyderabad", "SunNetwork", 2014));
    }
    @Override
    public int addTeam(Team team) {
        teams.add(team);
        return teams.size();
    }

    @Override
    public void emptyArrayList() {
        // TeamService.super.emptyArrayList();
        teams = new ArrayList<>();
    }

    @Override
    public List<Team> getAllTeams() {
        // List<Team> teams = new ArrayList<>();
        return teams;
    }

    @Override
    public List<Team> getAllTeamsSortedByName() {
        List<Team> sortedteams = teams;
        // return teamsSortedByName;
        sortedteams.sort(Comparator.comparing(Team::getTeamName));
        return sortedteams;
    }

    
}