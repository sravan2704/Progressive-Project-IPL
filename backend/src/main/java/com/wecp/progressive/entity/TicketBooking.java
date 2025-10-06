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
public class TicketBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;


    @Column(nullable = false)
    private String email;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "match_id")
    private Match match;

    @Column(nullable = false)
    private Integer numberOfTickets;

    public TicketBooking(Integer bookingId, String email, Match match, Integer numberOfTickets) {
        this.bookingId = bookingId;
        this.email = email;
        this.match = match;
        this.numberOfTickets = numberOfTickets;
    }
    

    public TicketBooking(String email, Match match, Integer numberOfTickets) {
        this.email = email;
        this.match = match;
        this.numberOfTickets = numberOfTickets;
    }

    public TicketBooking(Integer bookingId, String email, Integer numberOfTickets) {
        this.bookingId = bookingId;
        this.email = email;
        this.numberOfTickets = numberOfTickets;
    }

    public TicketBooking(String email, Integer numberOfTickets) {
        this.email = email;
        this.numberOfTickets = numberOfTickets;
    }

    public TicketBooking() {
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Integer getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(Integer numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    
    
}