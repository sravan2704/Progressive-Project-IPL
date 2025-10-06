package com.wecp.progressive.service;

import com.wecp.progressive.entity.TicketBooking;

import java.util.List;

import org.springframework.stereotype.Service;


public interface TicketBookingService {
    List<TicketBooking> getAllTicketBookings();

    int createBooking(TicketBooking ticketBooking);

    void cancelBooking(int bookingId);

    List<TicketBooking> getBookingsByUserEmail(String email);

}
