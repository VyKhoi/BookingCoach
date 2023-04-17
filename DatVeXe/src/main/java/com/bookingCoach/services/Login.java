/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingCoach.services;

import com.bookingCoach.pojo.Staff;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;

/**
 *
 * @author Kiet
 */
public class Login {

    public static Staff loginStaff = null;



    public Staff validateLogin(String username, String password) throws SQLException, NoSuchAlgorithmException {
        try (Connection conn = JdbcUtils.getConn()) {
            Staff st;
            String query = "SELECT * FROM bus.staff\n"
                    + "where userName = ? AND passWord = ?";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);

            // Băm mật khẩu theo SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            pstmt.setString(2, sb.toString());

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int idStaff = rs.getInt("idStaff");
                String passWord = rs.getString("passWord");
                String userName = rs.getString("userName");
                String addressUser = rs.getString("addressUser");
                String roles = rs.getString("roles");
                String nameStaff = rs.getString("nameStaff");
                String gender = rs.getString("gender");
                String phone = rs.getString("phone");
                LocalDate birthStaff = rs.getDate("brithStaff").toLocalDate();

                st = new Staff(idStaff, passWord, userName, addressUser, roles, nameStaff, gender, phone, birthStaff);
                System.out.println(st.toString());

                return st;
            }
        }
        return null;
    }
}
