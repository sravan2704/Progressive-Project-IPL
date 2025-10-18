package com.wecp.progressive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wecp.progressive.entity.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Integer> {

    public Long countByCategory(String category);

    @Query("DELETE FROM Vote v WHERE v.team.teamId = : teamId ")
    public void deleteByTeamId(int teamId);

    @Query("DELETE FROM Vote v WHERE v.cricketer.cricketerId = :cricketerId")
    public void deleteByCricketerId(int cricketerId);
}
