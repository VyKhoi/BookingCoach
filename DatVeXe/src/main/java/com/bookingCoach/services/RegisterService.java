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
import java.time.DateTimeException;
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
        if (passWord.isEmpty()) {
            return -1; // Không thể đăng ký với mật khẩu rỗng

        }
        if (userName.isEmpty()) {
            return -1; // Không thể đăng ký với tên đăng nhập rỗng
        }
        if (!phone.matches("\\d{10}")) {
            System.out.print("sdt sai");
            return -1; // Không thể đăng ký với số điện thoại không hợp lệ
        }
        try (Connection conn = JdbcUtils.getConn()) {
            // Băm mật khẩu sử dụng SHA-512

            String query = "INSERT INTO staff (userName, passWord, addressUser, roles, nameStaff, gender, phone, brithStaff)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userName); // set giá trị của biến userName vào câu lệnh truy vấn
            // Hash password using SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hash = md.digest(passWord.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            String hashedPassword = sb.toString();

            pstmt.setString(2, hashedPassword);
            pstmt.setString(3, addressUser);
            pstmt.setString(4, roles);
            pstmt.setString(5, nameStaff);
            pstmt.setString(6, gender);
            pstmt.setString(7, phone);

            LocalDate birthStaff2 = birthStaff;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String birthStaffString = birthStaff2.format(formatter);
                pstmt.setString(8, birthStaffString);
            } catch (DateTimeException e) {
                throw new IllegalArgumentException("Invalid birth date", e);
            }
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println(affectedRows + " record(s) inserted.");
                return 1;
            } else {
                System.out.print("loi vai lz lun");
                return -1;
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            System.out.print("loi vai lz lun11111");
            return -1;
        }

    }
}
