package com.wecp.progressive.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "team")
public class Team implements Comparable<Team> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Integer teamId;


    @Column(name = "team_name")
    private String teamName;

    @Column(name = "location")
    private String location;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "establishment_year")
    private int establishmentYear;

    @JsonIgnore
    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL)
    List<Cricketer> cricketer = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "firstTeam",cascade = CascadeType.ALL)
    List<Match> matchFirstTeam = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "secondTeam",cascade = CascadeType.ALL)
    List<Match> matchSecondTeam = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "winnerTeam",cascade = CascadeType.ALL)
    List<Match> matchWinnerTeam = new ArrayList<>();

    public Team(Integer teamId, String teamName, String location, String ownerName, int establishmentYear) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.location = location;
        this.ownerName = ownerName;
        this.establishmentYear = establishmentYear;
    }
    //without primary key constructor

    public Team(String teamName, String location, String ownerName, int establishmentYear) {
        this.teamName = teamName;
        this.location = location;
        this.ownerName = ownerName;
        this.establishmentYear = establishmentYear;
    }


    public Team() {
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getEstablishmentYear() {
        return establishmentYear;
    }

    public void setEstablishmentYear(int establishmentYear) {
        this.establishmentYear = establishmentYear;
    }

    @Override
    public int compareTo(Team o) {
        return this.getTeamName().compareTo(o.getTeamName());
    }

    
    

    
}