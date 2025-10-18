package com.wecp.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.progressive.entity.Team;
import com.wecp.progressive.exception.TeamAlreadyExistsException;
import com.wecp.progressive.exception.TeamDoesNotExistException;
import com.wecp.progressive.repository.CricketerRepository;
import com.wecp.progressive.repository.TeamRepository;
import com.wecp.progressive.service.TeamService;

@Service
public class TeamServiceImplJpa  implements TeamService{

    
    private TeamRepository teamRepository;

    @Autowired
    private CricketerRepository cricketerRepository;

    @Autowired
    public TeamServiceImplJpa(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
        // this.cricketerRepository = cricketerRepository;
    }

    @Override
    public int addTeam(Team team)  {
        // if(teamRepository.existsById(team.getTeamId()))
        // {
        //     throw new TeamAlreadyExistsException("");
        // }
        // Optional<Team> teamop = teamRepository.findById(team.getTeamId());
        Team teamObj = teamRepository.findByTeamName(team.getTeamName());
        if(teamObj !=null && teamObj.getTeamName().equalsIgnoreCase(team.getTeamName()))
        {
            throw new TeamAlreadyExistsException("Team already exists with Team Name"+team.getTeamName());
        }
        
        teamObj = teamRepository.save(team);
        return teamObj.getTeamId();
    }

    @Override
    public void deleteTeam(int teamId) {
        if(!teamRepository.existsById(teamId))
        {
            throw new TeamDoesNotExistException("Team with given "+ teamId +" Team Id does not exist.");
        }
        // TeamService.super.deleteTeam(teamId);
        // teamRepository.deleteById(teamId);
        cricketerRepository.deleteByTeamId(teamId);
    }

    @Override
    public List<Team> getAllTeams() {
        
        return teamRepository.findAll();
    }

    @Override
    public List<Team> getAllTeamsSortedByName() throws SQLException 
    {
        List<Team> teams = teamRepository.findAll();
        teams.sort(Comparator.comparing(Team::getTeamName));
        return teams;
        // List<Team> teams = getAllTeams();
        // Collections.sort(teams);
        // return teams;
    }

    @Override
    public void updateTeam(Team team)  {
        if(!teamRepository.existsById(team.getTeamId()))
        {
            throw new TeamDoesNotExistException("Team with given "+ team.getTeamId() +" Team Id does not exist.");
        }
        Team mObj1 = teamRepository.findByTeamName(team.getTeamName());
        if( mObj1 != null && mObj1.getTeamId() !=team.getTeamId() && mObj1.getTeamName().equalsIgnoreCase(team.getTeamName()))
        {
            throw new TeamAlreadyExistsException("Team already exists with Team Name"+team.getTeamName());
        }
        // TeamService.super.updateTeam(team);
        Team mObj=teamRepository.findById(team.getTeamId()).get();
        mObj.setTeamId(team.getTeamId());
        mObj.setTeamName(team.getTeamName());
        mObj.setOwnerName(team.getOwnerName());
        mObj.setLocation(team.getLocation());
        mObj.setEstablishmentYear(team.getEstablishmentYear());
        teamRepository.save(mObj);
    }

    @Override
    public Team getTeamById(int teamId)  {
        if(!teamRepository.existsById(teamId))
        {
            throw new TeamDoesNotExistException("Team does not exist with "+teamId);
        }
        // return TeamService.super.getTeamById(teamId);
        return teamRepository.findByTeamId(teamId);
    }
    
    
}