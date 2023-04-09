/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingCoach.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Kiet
 */
public class RegisterService {

    public int validateRegister(String passWord,
            String userName,
            String addressUser,
            String roles,
            String nameStaff,
            String gender,
            String phone,
            LocalDate birthStaff
    ) throws SQLException, NoSuchAlgorithmException {
        try (Connection conn = JdbcUtils.getConn()) {
             // Băm mật khẩu sử dụng SHA-512
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] passwordBytes = passWord.getBytes();
            byte[] hashedBytes = digest.digest(passwordBytes);
            String hashedPassword = bytesToHex(hashedBytes);
            String query = "INSERT INTO staff (userName, passWord, addressUser, roles, nameStaff, gender, phone, brithStaff)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userName); // set giá trị của biến userName vào câu lệnh truy vấn
            pstmt.setString(2, hashedPassword); // set giá trị của biến passWord vào câu lệnh truy vấn
            pstmt.setString(3, addressUser);
            pstmt.setString(4, roles);
            pstmt.setString(5, nameStaff);
            pstmt.setString(6, gender);
            pstmt.setString(7, phone);
            LocalDate birthStaff2 = birthStaff;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String birthStaffString = birthStaff2.format(formatter);
            pstmt.setString(8, birthStaffString);
                                    
                        

        
            int affectedRows = pstmt.executeUpdate();
            
            System.out.println("dit me chay qua truy van");
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
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
