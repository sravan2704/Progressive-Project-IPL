package com.wecp.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.progressive.entity.Cricketer;
import com.wecp.progressive.exception.TeamCricketerLimitExceededException;
import com.wecp.progressive.repository.CricketerRepository;
import com.wecp.progressive.service.CricketerService;

@Service
public class CricketerServiceImplJpa implements CricketerService  {

    private CricketerRepository cricketerRepository;
    
    public CricketerServiceImplJpa(CricketerRepository cricketerRepository) {
        this.cricketerRepository = cricketerRepository;
    }

    @Override
    public Integer addCricketer(Cricketer cricketer) throws SQLException {

        // List<Cricketer> listOfCrickers = cricketerRepository.findAll();
        // if(listOfCrickers.size()>11)
        // {
        //     throw new TeamCricketerLimitExceededException("");
        // }
        int teamId = cricketer.getTeam().getTeamId();
        Long crickterCount = cricketerRepository.countByTeam_TeamId(teamId);

        if(crickterCount > 11)
        {
            throw new TeamCricketerLimitExceededException("Team limit exceeded.");
        }
        Cricketer obj = cricketerRepository.save(cricketer);
        return obj.getCricketerId();

    }

    @Override
    public void deleteCricketer(int cricketerId) throws SQLException {
        
        // CricketerService.super.deleteCricketer(cricketerId);
        cricketerRepository.deleteById(cricketerId);
    }

    @Override
    public List<Cricketer> getAllCricketers() throws SQLException {
        
        return cricketerRepository.findAll();
    }

    @Override
    public List<Cricketer> getAllCricketersSortedByExperience() throws SQLException {
        
        List<Cricketer> list = cricketerRepository.findAll();
        list.sort(Comparator.comparing(Cricketer::getExperience));
        return list;
    }

    @Override
    public Cricketer getCricketerById(int cricketerId) throws SQLException {
        
        // return CricketerService.super.getCricketerById(cricketerId);
        return cricketerRepository.findByCricketerId(cricketerId);
    }

    @Override
    public List<Cricketer> getCricketersByTeam(int teamId) throws SQLException {
        
        // return CricketerService.super.getCricketersByTeam(teamId);
        return cricketerRepository.findByTeam_TeamId(teamId);
    }

    @Override
    public void updateCricketer(Cricketer cricketer) throws SQLException {
        
        // CricketerService.super.updateCricketer(cricketer);

        Cricketer cricketerObj = cricketerRepository.findById(cricketer.getCricketerId()).get();
        cricketerObj.setCricketerId(cricketer.getCricketerId());
        cricketerObj.setCricketerName(cricketer.getCricketerName());
        cricketerObj.getTeam().setTeamId(cricketer.getTeam().getTeamId());
        cricketer.setAge(cricketer.getAge());
        cricketerObj.setNationality(cricketer.getNationality());
        cricketerObj.setExperience(cricketer.getExperience());
        cricketerObj.setRole(cricketer.getRole());
        cricketerObj.setTotalRuns(cricketer.getTotalRuns());
        cricketerObj.setTotalWickets(cricketer.getTotalWickets());
        cricketerRepository.save(cricketerObj);
    }

    
    // private CricketerRepository cricketerRepository;

    // @Autowired
    // public CricketerServiceImplJpa(CricketerRepository cricketerRepository) {
    //     this.cricketerRepository = cricketerRepository;
    // }
    // public List<Cricketer> getAllCricketer() throws SQLException
    // {
    //     List<Cricketer> cricketers = new ArrayList<>();
    //     return cricketers;
    // }
    // public Integer addCricketer(Cricketer cricketer)throws SQLException
    // {
    //     return -1;
    // }
    // public List<Cricketer> getAllCricketersSortedByExperience() throws SQLException
    // {
    //     List<Cricketer> cricketersSortedByName = new ArrayList<>();
    //     return cricketersSortedByName;
    // }
    // public void updateCricketer(Cricketer cricketer) throws SQLException
    // {
        
    // }
    // public void deleteCricketer(int cricketerId) throws SQLException
    // {

    // }
    // public Cricketer getCricketerById(int cricketerId) throws SQLException
    // {
    //     return null;
    // }
    // public List<Cricketer> getCricketersByTeam(int teamId) throws SQLException
    // {
    //     return null;
    // }

    
    

}