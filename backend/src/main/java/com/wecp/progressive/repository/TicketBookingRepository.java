package com.wecp.progressive.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wecp.progressive.entity.TicketBooking;

@Repository
public interface TicketBookingRepository extends JpaRepository<TicketBooking,Integer>
{
   
    public List<TicketBooking> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("DELETE FROM TicketBooking t WHERE t.match.firstTeam.teamId = :teamId OR t.match.secondTeam.teamId = :teamId")
    public void deleteByTeamId(int teamId);

    @Modifying
    @Transactional
    @Query("DELETE FROM TicketBooking t WHERE t.match.matchId = :matchId")
    public void deleteByMatchId(int matchId);
}
