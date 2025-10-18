package com.wecp.progressive.dao;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wecp.progressive.config.DatabaseConnectionManager;
import com.wecp.progressive.entity.Cricketer;

public class CricketerDAOImpl implements CricketerDAO{

    @Override
    public int addCricketer(Cricketer cricketer) throws SQLException{
        
        String Query = "insert into cricketer (team_id,cricketer_name,age,nationality,experience,role,total_runs,total_wickets) values (?,?,?,?,?,?,?,?)";
        try(PreparedStatement ps=DatabaseConnectionManager.getConnection().prepareStatement(Query,Statement.RETURN_GENERATED_KEYS))
        {
            ps.setInt(1, cricketer.getTeam().getTeamId());
            ps.setString(2,cricketer.getCricketerName());
            ps.setInt(3,cricketer.getAge());
            ps.setString(4,cricketer.getNationality());
            ps.setInt(5, cricketer.getExperience());
            ps.setString(6, cricketer.getRole());
            ps.setInt(7,cricketer.getTotalRuns());
            ps.setInt(8, cricketer.getTotalWickets());


            int n = ps.executeUpdate();
            ResultSet rs=ps.getGeneratedKeys();
            if(n>0)
            {
                while (rs.next()) {
                    cricketer.setCricketerId(rs.getInt(1));
                    return rs.getInt(1);
                }
                
            }

        }
        catch(SQLException se)
        {
            // se.printStackTrace();
            throw new SQLException(se.getMessage());
        }
        return (-1);
    }

    @Override
    public void deleteCricketer(int cricketerId) throws SQLException{
        
        String Query = "delete from cricketer where cricketer_id = ?";
        try
        {
            PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(Query);
            ps.setInt(1,cricketerId);
            int n= ps.executeUpdate();
        }
        catch(SQLException se)
        {
            // se.printStackTrace();
            throw new SQLException(se.getMessage());
        }
    }

    @Override
    public List<Cricketer> getAllCricketers() throws SQLException {
        List<Cricketer> cricketers = new ArrayList<>();
        String Query = "select * from cricketer";
        try{
            PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(Query);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                Cricketer obj = new Cricketer();
                obj.setCricketerId(rs.getInt(1));
                obj.getTeam().setTeamId(rs.getInt(2));
                obj.setCricketerName(rs.getString(3));
                obj.setAge(rs.getInt(4));
                obj.setNationality(rs.getString(5));
                obj.setExperience(rs.getInt(6));
                obj.setRole(rs.getString(7));
                obj.setTotalRuns(rs.getInt(8));
                obj.setTotalWickets(rs.getInt(9));
                cricketers.add(obj);
            }
        }
        catch(SQLException se)
        {
            // se.printStackTrace();
            throw new SQLException(se.getMessage());
        }
        return cricketers;
    }

    @Override
    public Cricketer getCricketerById(int cricketerId)throws SQLException {
        
        String Query = "select * from cricketer where cricketer_id = ?";
        try
        {
            PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(Query);
            ps.setInt(1,cricketerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cricketer obj = new Cricketer();
                obj.setCricketerId(rs.getInt(1));
                obj.getTeam().setTeamId(rs.getInt(2));
                obj.setCricketerName(rs.getString(3));
                obj.setAge(rs.getInt(4));
                obj.setNationality(rs.getString(5));
                obj.setExperience(rs.getInt(6));
                obj.setRole(rs.getString(7));
                obj.setTotalRuns(rs.getInt(8));
                obj.setTotalWickets(rs.getInt(9));
                return obj;

            }
        }
        catch(SQLException se)
        {
            // se.printStackTrace();
            throw new SQLException(se.getMessage());
        }
        return null;
    }

    @Override
    public void updateCricketer(Cricketer cricketer) throws SQLException {
        
        String Query ="update cricketer set team_id = ?,cricketer_name = ?,age = ?,nationality = ?,experience = ?,role = ?,total_runs =?,total_wickets =? where cricketer_id = ?";
        try{
            PreparedStatement ps=DatabaseConnectionManager.getConnection().prepareStatement(Query);
            ps.setInt(1, cricketer.getTeam().getTeamId());
            ps.setString(2,cricketer.getCricketerName());
            ps.setInt(3,cricketer.getAge());
            ps.setString(4,cricketer.getNationality());
            ps.setInt(5, cricketer.getExperience());
            ps.setString(6, cricketer.getRole());
            ps.setInt(7,cricketer.getTotalRuns());
            ps.setInt(8, cricketer.getTotalWickets());
            ps.setInt(9,cricketer.getCricketerId());

            int n = ps.executeUpdate();
        }
        catch(SQLException se)
        {
            // se.printStackTrace();
            throw new SQLException(se.getMessage());
        }
    }

    
}
