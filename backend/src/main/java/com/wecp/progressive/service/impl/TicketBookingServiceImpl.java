package com.wecp.progressive.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.progressive.entity.TicketBooking;
import com.wecp.progressive.repository.TicketBookingRepository;
import com.wecp.progressive.service.TicketBookingService;

@Service
public class TicketBookingServiceImpl implements TicketBookingService
{

    private TicketBookingRepository ticketBookingRepository;

    @Autowired
    public TicketBookingServiceImpl(TicketBookingRepository ticketBookingRepository) {
        this.ticketBookingRepository = ticketBookingRepository;
    }

    @Override
    public void cancelBooking(int bookingId) {
        
        ticketBookingRepository.deleteById(bookingId);
    }

    @Override
    public int createBooking(TicketBooking ticketBooking) {
        TicketBooking tObj =  ticketBookingRepository.save(ticketBooking);
        return tObj.getBookingId();
    }

    @Override
    public List<TicketBooking> getAllTicketBookings() {
        
        return ticketBookingRepository.findAll();
    }

    @Override
    public List<TicketBooking> getBookingsByUserEmail(String email) {
        
        return ticketBookingRepository.findByEmail(email);
    }
}