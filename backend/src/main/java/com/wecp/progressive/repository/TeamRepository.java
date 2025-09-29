package com.wecp.progressive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wecp.progressive.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team,Integer>
{
    public Team findByTeamId(int teamId);

    public Team findByTeamName(String name);
}
