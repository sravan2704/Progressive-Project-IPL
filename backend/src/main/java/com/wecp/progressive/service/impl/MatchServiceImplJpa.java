package com.wecp.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.progressive.entity.Match;
import com.wecp.progressive.exception.NoMatchesFoundException;
import com.wecp.progressive.repository.MatchRepository;
import com.wecp.progressive.service.MatchService;

@Service
public class MatchServiceImplJpa implements MatchService {

    private MatchRepository matchRepository;
    
    @Autowired
    public MatchServiceImplJpa(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public Integer addMatch(Match match) throws SQLException {
        
        Match matchObj = matchRepository.save(match);
        return matchObj.getMatchId();
    }

    @Override
    public void deleteMatch(int matchId) throws SQLException {
        
        matchRepository.deleteById(matchId);
    }

    @Override
    public List<Match> getAllMatches() throws SQLException {
        
        return matchRepository.findAll();
    }

    @Override
    public List<Match> getAllMatchesByStatus(String status) {
        
        // return MatchService.super.getAllMatchesByStatus(status);
        // return matchRepository.findAllByStatus(status);
        List<Match> listOfMatchesByStatus  = matchRepository.findAllByStatus(status);
        if(listOfMatchesByStatus.isEmpty())
        {
            throw new NoMatchesFoundException("No Matches found with given status.");
        }
        return listOfMatchesByStatus;
    }

    @Override
    public Match getMatchById(int matchId) throws SQLException {
        
        return matchRepository.findByMatchId(matchId);
    }

    @Override
    public void updateMatch(Match match) throws SQLException {
        
        Match mObj = matchRepository.findById(match.getMatchId()).get();
        mObj.setMatchId(match.getMatchId());
        mObj.getFirstTeam().setTeamId(match.getFirstTeam().getTeamId());
        mObj.setMatchDate(match.getMatchDate());
        mObj.getSecondTeam().setTeamId(match.getSecondTeam().getTeamId());
        mObj.setStatus(match.getStatus());
        mObj.setVenue(match.getVenue());
        mObj.setResult(match.getResult());
        mObj.getWinnerTeam().setTeamId(match.getWinnerTeam().getTeamId());
        mObj = matchRepository.save(mObj);
        
    }

    
    // private MatchRepository matchRepository;

    // @Autowired
    // public MatchServiceImplJpa(MatchRepository matchRepository) {
    //     this.matchRepository = matchRepository;
    // }

    // public List<Match> getAllMatches() throws SQLException
    // {
    //     List<Match> matches = new ArrayList<>();
    //     return matches;
    // }
    // public Match getMatchById(int matchId) throws SQLException
    // {
    //     return null;
    // }
    // public Integer addMatch(Match match) throws SQLException
    // {
    //     return -1;
    // }
    // public void updateMatch(Match match) throws SQLException
    // {

    // }
    // public void deleteMatch(Match match) throws SQLException
    // {

    // }
    // public List<Match> getAllMatchesByStatus(String status) throws SQLException
    // {
    //     return null;
    // }
    
    
}