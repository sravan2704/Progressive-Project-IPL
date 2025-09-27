package com.wecp.progressive.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.entity.Team;

public class TeamDAOImpl implements TeamDAO {

    @Override
    public int addTeam(Team team) {
        String Query ="insert into team (team_name, location, owner_name, establishment_year) values (?,?,?,?)";
        try(PreparedStatement ps=DatabaseConnectionManager.getConnection().prepareStatement(Query,Statement.RETURN_GENERATED_KEYS))
        {
            ps.setString(1, team.getTeamName());
            ps.setString(2, team.getLocation());
            ps.setString(3,team.getOwnerName());
            ps.setInt(4,team.getEstablishmentYear());
            int n = ps.executeUpdate();
            
            if(n>0)
            {
                ResultSet rs=ps.getGeneratedKeys();
                while (rs.next()) {
                    team.setTeamId(rs.getInt(1));
                    return rs.getInt(1);
                }  
            }
        }

        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void deleteTeam(int teamId) {
        
        String Query = "delete from team where team_id = ?";
        try{
            PreparedStatement ps=DatabaseConnectionManager.getConnection().prepareStatement(Query);
            ps.setInt(1,teamId);
            int n= ps.executeUpdate();
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<>();
        String Query = "select * from team";
        try{
            PreparedStatement ps=DatabaseConnectionManager.getConnection().prepareStatement(Query);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                Team obj =new Team();
                obj.setTeamId(rs.getInt(1));
                obj.setTeamName(rs.getString(2));
                obj.setLocation(rs.getString(3));
                obj.setOwnerName(rs.getString(4));
                obj.setEstablishmentYear(rs.getInt(5));
                teams.add(obj); 
            }
        }
        catch(SQLException se)
        {
            se.printStackTrace();;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return teams;
    }

    @Override
    public Team getTeamById(int teamId) {
        String Query = "select * from team where team_id = ?";
        try{
            PreparedStatement ps=DatabaseConnectionManager.getConnection().prepareStatement(Query);
            ps.setInt(1,teamId);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                Team obj =new Team();
                obj.setTeamId(rs.getInt(1));
                obj.setTeamName(rs.getString(2));
                obj.setLocation(rs.getString(3));
                obj.setOwnerName(rs.getString(4));
                obj.setEstablishmentYear(rs.getInt(5));
                return obj;
            }
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateTeam(Team team) {
        String Query = "update team set team_name = ?,location = ?,owner_name = ?,establishment_year = ? where team_id = ?";
        try(PreparedStatement ps=DatabaseConnectionManager.getConnection().prepareStatement(Query))
        {
            ps.setString(1, team.getTeamName());
            ps.setString(2, team.getLocation());
            ps.setString(3,team.getOwnerName());
            ps.setInt(4,team.getEstablishmentYear());
            ps.setInt(5,team.getTeamId());
            int n = ps.executeUpdate();
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }


}
