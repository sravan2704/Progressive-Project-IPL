package com.wecp.progressive.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.entity.Cricketer;
import com.wecp.progressive.entity.Match;

public class MatchDAOImpl implements MatchDAO{

    @Override
    public int addMatch(Match match) throws SQLException{
        String Query = "insert into matches (first_team_id,second_team_id,match_date,venue,result,status,winner_team_id) values (?,?,?,?,?,?,?)";
        try(PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(Query,Statement.RETURN_GENERATED_KEYS))
        {
            ps.setInt(1, match.getFirstTeam().getTeamId());
            ps.setInt(2,match.getSecondTeam().getTeamId());
            ps.setDate(3, new java.sql.Date(match.getMatchDate().getTime()));
            ps.setString(4,match.getVenue());
            ps.setString(5, match.getResult());
            ps.setString(6, match.getStatus());
            ps.setInt(7,match.getWinnerTeam().getTeamId());

            int n = ps.executeUpdate();
            
            if(n>0)
            {
                ResultSet rs=ps.getGeneratedKeys();
                while (rs.next()) {
                    match.setMatchId(rs.getInt(1));
                    return rs.getInt(1);
                }  
            }
        }
        catch(SQLException se)
        {
            se.printStackTrace();
            throw new SQLException(se.getMessage());
        }
        return -1;
}

    @Override
    public void deleteMatch(int matchId) throws SQLException{
        
        String Query = "delete from matches where match_id = ?";
        try
        {
            PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(Query);
            ps.setInt(1,matchId);
            int n= ps.executeUpdate();
        }
        catch(SQLException se)
        {
            se.printStackTrace();
            throw new SQLException(se.getMessage());
        }
    }

    @Override
    public List<Match> getAllMatches() throws SQLException {
        List<Match> matches = new ArrayList<>();
        String Query = "select * from matches";
        try{
            PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(Query);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Match obj = new Match();
                obj.setMatchId(rs.getInt(1));
                obj.getFirstTeam().setTeamId((rs.getInt(2)));
                obj.getSecondTeam().setTeamId((rs.getInt(3)));
                obj.setMatchDate(rs.getDate(4));
                obj.setVenue(rs.getString(5));
                obj.setResult(rs.getString(6));
                obj.setStatus(rs.getString(7));
                obj.getWinnerTeam().setTeamId((rs.getInt(8)));
               
                matches.add(obj);
            }
        }
        catch(SQLException se)
        {
            se.printStackTrace();
            throw new SQLException(se.getMessage());
        }
        return matches;
    }

    @Override
    public Match getMatchById(int matchId) throws SQLException{
        String Query = "select * from matches where match_id = ?";
        try
        {
            PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(Query);
            ps.setInt(1,matchId);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Match obj = new Match();
                obj.setMatchId(rs.getInt(1));
                obj.getFirstTeam().setTeamId((rs.getInt(2)));
                obj.getSecondTeam().setTeamId((rs.getInt(3)));
                obj.setMatchDate(rs.getDate(4));
                obj.setVenue(rs.getString(5));
                obj.setResult(rs.getString(6));
                obj.setStatus(rs.getString(7));
                obj.getWinnerTeam().setTeamId((rs.getInt(8)));
                return obj;
            }

        }
        catch(SQLException se)
        {
            se.printStackTrace();
            throw new SQLException(se.getMessage());
        }
        return null;
    }

    @Override
    public void updateMatch(Match match) throws SQLException {
        
        String Query = "update matches set first_team_id = ?,second_team_id = ?,match_date = ?,venue = ?,result =?,status = ?,winner_team_id= ? where match_id = ?";
        try{
            PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(Query);
            ps.setInt(1, match.getFirstTeam().getTeamId());
            ps.setInt(2,match.getSecondTeam().getTeamId());
            ps.setDate(3, new java.sql.Date(match.getMatchDate().getTime()));
            ps.setString(4,match.getVenue());
            ps.setString(5, match.getResult());
            ps.setString(6, match.getStatus());
            ps.setInt(7,match.getWinnerTeam().getTeamId());
            ps.setInt(8,match.getMatchId());

            int n = ps.executeUpdate();
        }
        catch(SQLException se)
        {
            se.printStackTrace();
            throw new SQLException(se.getMessage());
        }
        
    }

    }

