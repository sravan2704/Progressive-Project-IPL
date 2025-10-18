package com.wecp.progressive.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "matches")

public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "match_id")
    private Integer matchId;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "first_team_id",nullable = false)
    private Team firstTeam;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "second_team_id",nullable = false)
    private Team secondTeam;

    @Temporal(TemporalType.DATE)
    @Column(name = "match_date",nullable = false)
    private Date matchDate;

    private String venue;
    private String result;
    private String status;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "winner_team_id")
    private Team winnerTeam;



    @OneToMany(mappedBy = "match" , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TicketBooking> ticketBookings = new ArrayList<>();

    public Match(Integer matchId, Integer firstTeamId, Integer secondTeamId, Date matchDate, String venue, String result,
            String status, int winnerTeamId) {
        this.matchId = matchId;
        this.firstTeam.setTeamId(firstTeamId);
        this.secondTeam.setTeamId(secondTeamId);
        this.matchDate = matchDate;
        this.venue = venue;
        this.result = result;
        this.status = status;
        this.winnerTeam.setTeamId(winnerTeamId);;
    }
    public Match() {
    }

    //without primary key constructor
    public Match(Integer firstTeamId, Integer secondTeamId, Date matchDate, String venue, String result, String status,
            int winnerTeamId) {
        this.firstTeam.setTeamId(firstTeamId);
        this.secondTeam.setTeamId(secondTeamId);
        this.matchDate = matchDate;
        this.venue = venue;
        this.result = result;
        this.status = status;
        this.winnerTeam.setTeamId(winnerTeamId);
    }

    public Match(Integer matchId, Integer firstTeamId, Integer secondTeamId, Date matchDate, String venue, String result,
            String status, int winnerTeamId, List<TicketBooking> ticketBookings) {
        this.matchId = matchId;
        this.firstTeam.setTeamId(firstTeamId);
        this.secondTeam.setTeamId(secondTeamId);
        this.matchDate = matchDate;
        this.venue = venue;
        this.result = result;
        this.status = status;
        this.winnerTeam.setTeamId(winnerTeamId);
        this.ticketBookings = ticketBookings;
    }

    
    public Match(Integer firstTeamId, Integer secondTeamId, Date matchDate, String venue, String result, String status,
            int winnerTeamId, List<TicketBooking> ticketBookings) {
        this.firstTeam.setTeamId(firstTeamId);
        this.secondTeam.setTeamId(secondTeamId);
        this.matchDate = matchDate;
        this.venue = venue;
        this.result = result;
        this.status = status;
        this.winnerTeam.setTeamId(winnerTeamId);;
       
        this.ticketBookings = ticketBookings;
    }
    
    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }


    
    
    public Team getFirstTeam() {
        return firstTeam;
    }
    public void setFirstTeam(Team firstTeam) {
        this.firstTeam = firstTeam;
    }
    public Team getSecondTeam() {
        return secondTeam;
    }
    public void setSecondTeam(Team secondTeam) {
        this.secondTeam = secondTeam;
    }
    public Date getMatchDate() {
        return matchDate;
    }
    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }
    public String getVenue() {
        return venue;
    }
    public void setVenue(String venue) {
        this.venue = venue;
    }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<TicketBooking> getTicketBookings() {
        return ticketBookings;
    }
    public void setTicketBookings(List<TicketBooking> ticketBookings) {
        this.ticketBookings = ticketBookings;
    }
    public Team getWinnerTeam() {
        return winnerTeam;
    }
    public void setWinnerTeam(Team winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    

}