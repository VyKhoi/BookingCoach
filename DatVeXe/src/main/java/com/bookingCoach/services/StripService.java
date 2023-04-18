/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingCoach.services;

import com.bookingCoach.pojo.Station;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

/**
 *
 * @author Kiet
 */
public class StripService {

    public boolean checkExistingStrip(int startLocationId, int endLocationId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM coachstrips WHERE idStationsStart = ? AND idStationsEnd = ?";
        try (Connection conn = JdbcUtils.getConn(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, startLocationId);
            stmt.setInt(2, endLocationId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        }
        return false;
    }

    public Station getStationById(int idStation) {
        Station station = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConn();
            String sql = "SELECT name FROM bus.stations WHERE idStations=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idStation);
            rs = ps.executeQuery();
            if (rs.next()) {
                String nameStation = rs.getString("name");
                station = new Station(nameStation);
            }
        } catch (SQLException ex) {
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
        return station;
    }

    public int addNewStrips(
            int distance,
            LocalTime arrivalTime,
            int idStationsStart,
            int idStationsEnd
    ) throws SQLException {
         if (idStationsStart == idStationsEnd) {
        return -1;
    }
        
        boolean isExistingStrip = checkExistingStrip(idStationsStart, idStationsEnd);
        if (isExistingStrip) {
            return -1;
        }
        try (Connection conn = JdbcUtils.getConn()) {

            String queryStrips = "INSERT INTO coachstrips (distance, arrivalTime,idStationsStart,idStationsEnd)\n"
                    + "VALUES (?, ?, ?, ?);";

            PreparedStatement psStrips = conn.prepareStatement(queryStrips);
            psStrips.setInt(1, distance);
            psStrips.setTime(2, Time.valueOf(arrivalTime));
            psStrips.setInt(3, idStationsStart);
            psStrips.setInt(4, idStationsEnd);
            int affectedRows = psStrips.executeUpdate();

            if (affectedRows > 0) {
                System.out.println(affectedRows + " record(s) inserted.");
                return 1;
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return -1;
        }

    }
}
