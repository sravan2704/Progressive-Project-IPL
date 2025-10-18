package com.wecp.progressive.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wecp.progressive.entity.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match,Integer>{

    public Match findByMatchId(int matchId);

    public List<Match> findAllByStatus(String status);

    

    @Modifying
    @Transactional
    @Query("DELETE FROM Match m WHERE m.firstTeam.teamId = : teamId OR m.secondTeam.teamId =:teamId")
    public void deleteByTeamId(int teamId);
}
