/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingCoach.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author Kiet
 */
public class CoachService {

    public boolean checkCoachExists(int numberCoach) throws SQLException {
        try (Connection conn = JdbcUtils.getConn()) {
            String query = "SELECT COUNT(*) FROM coachs WHERE numberCoach = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, numberCoach);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    public int addNewCoach(int numberCoach, int capacity, String typeOfCoach) throws SQLException {
        if (typeOfCoach == null || capacity < 10 || capacity > 50) {
            return -1;
        }
        try (Connection conn = JdbcUtils.getConn()) {
            if (checkCoachExists(numberCoach)) {
                return -1;
            }
            String queryCoachs = "INSERT INTO coachs(numberCoach, capacity, typeofCoach)\n" + "VALUES (?, ?, ?);";
            PreparedStatement psCoachs = conn.prepareStatement(queryCoachs);
            psCoachs.setInt(1, numberCoach);
            psCoachs.setInt(2, capacity);
            if (typeOfCoach != null) {
                psCoachs.setString(3, typeOfCoach);
            }
            int affectedRows = psCoachs.executeUpdate();
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
