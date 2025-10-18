package com.wecp.progressive.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cricketer implements Comparable<Cricketer>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cricketer_id")
    private Integer cricketerId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="team_id")
    private Team team;
    
    @Column(name = "cricketer_name" ,nullable = false)
    private String cricketerName;
    @Column(name = "age")
    private int age;

    @Column(name ="nationality")
    private String nationality;

    @Column(name = "experience")
    private int experience;

    @Column(name = "role")
    private String role;

    @Column(name = "total_runs")
    private int totalRuns;

    @Column(name = "total_wickets")
    private int totalWickets;

    



    public Cricketer(Integer cricketerId, Integer teamId, String cricketerName, int age, String nationality, int experience,
            String role, int totalRuns, int totalWickets) {
        this.cricketerId = cricketerId;
        this.team.setTeamId(teamId);
        this.cricketerName = cricketerName;
        this.age = age;
        this.nationality = nationality;
        this.experience = experience;
        this.role = role;
        this.totalRuns = totalRuns;
        this.totalWickets = totalWickets;
    }
    //without primary key constructor
    public Cricketer(Integer teamId, String cricketerName, int age, String nationality, int experience, String role,
            int totalRuns, int totalWickets) {
        this.team.setTeamId(teamId);
        this.cricketerName = cricketerName;
        this.age = age;
        this.nationality = nationality;
        this.experience = experience;
        this.role = role;
        this.totalRuns = totalRuns;
        this.totalWickets = totalWickets;
    }

    public Cricketer() {
    }
    public Integer getCricketerId() {
        return cricketerId;
    }
    public void setCricketerId(Integer cricketerId) {
        this.cricketerId = cricketerId;
    }
    
    public String getCricketerName() {
        return cricketerName;
    }
    public void setCricketerName(String cricketerName) {
        this.cricketerName = cricketerName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public int getTotalRuns() {
        return totalRuns;
    }
    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }
    public int getTotalWickets() {
        return totalWickets;
    }
    public void setTotalWickets(int totalWickets) {
        this.totalWickets = totalWickets;
    }

    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }
    @Override
    public int compareTo(Cricketer o) {
        
        return Integer.compare(this.getExperience(), o.getExperience());
    }
    

    

    
}