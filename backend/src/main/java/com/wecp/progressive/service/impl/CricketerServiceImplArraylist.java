package com.wecp.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.wecp.progressive.entity.Cricketer;
import com.wecp.progressive.service.CricketerService;

public class CricketerServiceImplArraylist implements CricketerService {

    List<Cricketer> cricketers = new ArrayList<>();

    public CricketerServiceImplArraylist()
    {
        cricketers.add(new Cricketer(18, 1, "Kohli", 40, "Indian", 30, "batter", 4000, 30));
        cricketers.add(new Cricketer(3, 27, "Abhisheik", 22, "Indian", 10, "Allrounder", 5000, 20));
    }
    @Override
    public Integer addCricketer(Cricketer cricketer) {

        cricketers.add(cricketer);
        return cricketers.size();
    }

    @Override
    public void emptyArrayList() {

        // CricketerService.super.emptyArrayList();
        cricketers = new ArrayList<>();
    }

    @Override
    public List<Cricketer> getAllCricketers() {
        // List<Cricketer> cricketers = new ArrayList<>();
        return cricketers;
    }

    @Override
    public List<Cricketer> getAllCricketersSortedByExperience() {
        // List<Cricketer> cricketersSortedByExperience = new ArrayList<>();
        // return cricketersSortedByExperience;
        // Collections.sort(cricketers);
        // return cricketers;
        List<Cricketer> sortedCricketers = cricketers;
        sortedCricketers.sort(Comparator.comparing(Cricketer::getExperience));
        return sortedCricketers;
    }

    
}