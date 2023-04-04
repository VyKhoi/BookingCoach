/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingCoach.services;

import com.bookingCoach.Alias.AliasTicket;
import com.bookingCoach.pojo.Ticket;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vy Khoi
 */
public class ChangeTicketServices {

    public AliasTicket getTickets(int idTicket) throws SQLException {

        // first excute to explore infor of this ticket
        try ( Connection conn = JdbcUtils.getConn()) {
            AliasTicket tickets;
            String query = "SELECT t.idTicket, c.name nameCustomer, c.phoneNumber, c.addressCus, t.bookingDate\n"
                    + ",coach.numberCoach, s.nameStaff, cscs.departureTime , cscs.nameSeat,cscs.idCSCS\n"
                    + ",cscs.idCoachStrips \n"
                    + "FROM bus.ticket t, bus.customer c, bus.staff s, bus.coachstripcoachseat cscs, bus.coachs coach\n"
                    + "where t.idCustomer = c.idCustomer\n"
                    + "and t.idStaff = s.idStaff\n"
                    + "and t.idCoachStripCoachSeat = cscs.idCSCS\n"
                    + "and cscs.idCoach = coach.idCoach\n"
                    + "and t.idTicket = ?"; // sử dụng dấu ? thay cho biến idTicket

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idTicket); // set giá trị của biến idTicket vào câu lệnh truy vấn
            ResultSet rs = pstmt.executeQuery();
            // lấy được data ban đầu

            while (rs.next()) {
                System.out.println("Chạy ra roi");
                // get start and end station
                String query2 = "SELECT st1.idStations idStart, st1.name nameStartStation , st1.address addressStart, st2.idStations idEnd, st2.name nameEndStation, st2.address addressEnd\n"
                        + "FROM bus.coachstrips cs\n"
                        + "INNER JOIN bus.stations st1 ON cs.idStationsStart = st1.idStations\n"
                        + "INNER JOIN bus.stations st2 ON cs.idStationsEnd = st2.idStations\n"
                        + "WHERE cs.idCoachStips = ?;"; // sử dụng dấu ? thay cho biến idTicket

                System.out.println("Chạy ra roi 2");

                PreparedStatement pstmt2 = conn.prepareStatement(query2);

                System.out.println("Chạy ra roi 3");
                pstmt2.setInt(1, rs.getInt("idCoachStrips")); // set giá trị của biến idTicket vào câu lệnh truy vấn

                System.out.println("Chạy ra roi 4");
                ResultSet rs2 = pstmt2.executeQuery();

                System.out.println("Chạy ra roi 5");

                String nameCustomer = rs.getString("nameCustomer");
                System.out.println(nameCustomer);

                String phoneNumber = rs.getString("phoneNumber");
                System.out.println("Chạy ra roi 6");
                String addressCus = rs.getString("addressCus");
                System.out.println("Chạy ra roi 7");
                Date bookingDate = rs.getDate("bookingDate");
                System.out.println("Chạy ra roi 8");
                int numberCoach = rs.getInt("numberCoach");
                System.out.println("Chạy ra roi 9");
                String nameStaff = rs.getString("nameStaff");
                System.out.println("Chạy ra roi 10");
                Date departureTime = rs.getDate("departureTime");
                System.out.println("Chạy ra roi 11");
                String nameSeat = rs.getString("nameSeat");
                System.out.println("Chạy ra roi 12");
                int idCSCS = rs.getInt("idCSCS");
                System.out.println("Chạy ra roi 13");
                int idCoachStrips = rs.getInt("idCoachStrips");
                System.out.println("Chạy ra roi 14");
                rs2.next();
                int idStart = rs2.getInt("idStart");
                System.out.println("Chạy ra roi 15");
                String nameStartStation = rs2.getString("nameStartStation");
                String addressStart = rs2.getString("addressStart");
                int idEnd = rs2.getInt("idEnd");
                String nameEndStation = rs2.getString("nameEndStation");
                String addressEnd = rs2.getString("addressEnd");

                System.out.println("het luôn");

                tickets = new AliasTicket(idTicket, nameCustomer, phoneNumber, addressCus, bookingDate, numberCoach, nameStaff, departureTime, nameSeat, idCSCS, idCoachStrips, idStart, nameStartStation, addressStart, idEnd, nameEndStation, addressEnd);

                return tickets;
            }

        } // Khối mã xử lý ngoại lệ IOException
        catch (Exception e) {
             e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) throws SQLException {
        ChangeTicketServices ds = new ChangeTicketServices();
        System.out.println(ds.getTickets(3).toString());
    }
}
