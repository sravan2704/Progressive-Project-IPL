package com.wecp.progressive.controller;

import com.wecp.progressive.entity.Cricketer;
import com.wecp.progressive.exception.TeamCricketerLimitExceededException;
import com.wecp.progressive.service.impl.CricketerServiceImplJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
@RestController
@RequestMapping("/cricketer")
public class CricketerController {

    private CricketerServiceImplJpa cricketerServiceImplJpa;

    @Autowired
    public CricketerController(CricketerServiceImplJpa cricketerServiceImplJpa) {
        this.cricketerServiceImplJpa = cricketerServiceImplJpa;
    }

    @GetMapping()
    public ResponseEntity<List<Cricketer>> getAllCricketers() {
        try{
            return new ResponseEntity<>(cricketerServiceImplJpa.getAllCricketers(),HttpStatus.OK);
        }
        catch(SQLException sqlEx){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{cricketerId}")
    public ResponseEntity<Cricketer> getCricketerById(@PathVariable int cricketerId) {
        try{
            return new ResponseEntity<>(cricketerServiceImplJpa.getCricketerById(cricketerId),HttpStatus.OK);
        }
        catch(SQLException sqlEx){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Integer> addCricketer(@RequestBody Cricketer cricketer) {
        try{
            return new ResponseEntity<>(cricketerServiceImplJpa.addCricketer(cricketer),HttpStatus.CREATED);
        }
        catch(TeamCricketerLimitExceededException tex)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch(SQLException sqlEx){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @PutMapping("/{cricketerId}")
    public ResponseEntity<Void> updateCricketer(@PathVariable int cricketerId,@RequestBody Cricketer cricketer) {
        try{
            cricketerServiceImplJpa.updateCricketer(cricketer);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch(SQLException sqlEx){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{cricketerId}")
    public ResponseEntity<Void> deleteCricketer(@PathVariable int cricketerId) {
        try{
            cricketerServiceImplJpa.deleteCricketer(cricketerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch(SQLException sqlEx){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/cricketer/team/{teamid}")
    public ResponseEntity<List<Cricketer>> getCricketersByTeam(@PathVariable int teamId) {
        try{
            return new ResponseEntity<>(cricketerServiceImplJpa.getCricketersByTeam(teamId),HttpStatus.OK);
        }
        catch(SQLException sqlEx){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}