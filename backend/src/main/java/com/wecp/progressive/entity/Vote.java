package com.wecp.progressive.entity;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer voteId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String category;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Cricketer cricketer;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    private Team team;

    public Vote() {
    }

    public Vote(Integer voteId, String email, String category, Cricketer cricketer, Team team) {
        this.voteId = voteId;
        this.email = email;
        this.category = category;
        this.cricketer = cricketer;
        this.team = team;
    }

    public Vote(String email, String category) {
        this.email = email;
        this.category = category;
    }

    public Vote(String email, String category, Cricketer cricketer, Team team) {
        this.email = email;
        this.category = category;
        this.cricketer = cricketer;
        this.team = team;
    }

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Cricketer getCricketer() {
        return cricketer;
    }

    public void setCricketer(Cricketer cricketer) {
        this.cricketer = cricketer;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    
}