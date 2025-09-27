package com.wecp.progressive.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.wecp.progressive.dao.CricketerDAO;
import com.wecp.progressive.entity.Cricketer;
import com.wecp.progressive.service.CricketerService;

public class CricketerServiceImplJdbc implements CricketerService {

    private CricketerDAO cricketerDAO;
    

    public CricketerServiceImplJdbc(CricketerDAO cricketerDAO)  {
        this.cricketerDAO = cricketerDAO;
    }

    @Override
    public Integer addCricketer(Cricketer cricketer) throws SQLException{
        return cricketerDAO.addCricketer(cricketer);
        
    }

    @Override
    public void deleteCricketer(int cricketerId) throws SQLException {
        
        // CricketerService.super.deleteCricketer(cricketerId);
        cricketerDAO.deleteCricketer(cricketerId);
    }

    @Override
    public List<Cricketer> getAllCricketers() throws SQLException{
        // List<Cricketer> cricketers = new ArrayList<>();
        return cricketerDAO.getAllCricketers();
    }

    @Override
    public List<Cricketer> getAllCricketersSortedByExperience() throws SQLException{
        List<Cricketer> cricketersSortedByExperience = cricketerDAO.getAllCricketers();
        cricketersSortedByExperience.sort(Comparator.comparing(Cricketer::getExperience));
        return cricketersSortedByExperience;
        // return List.of();
    }

    @Override
    public Cricketer getCricketerById(int cricketerId) throws SQLException{
        
        // return CricketerService.super.getCricketerById(cricketerId);
        return cricketerDAO.getCricketerById(cricketerId);
    }

    @Override
    public void updateCricketer(Cricketer cricketer) throws SQLException {
        
        // CricketerService.super.updateCricketer(cricketer);
        cricketerDAO.updateCricketer(cricketer);
        
    }

    

}