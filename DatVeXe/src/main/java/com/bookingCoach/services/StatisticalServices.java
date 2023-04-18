/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingCoach.services;

import java.util.ArrayList;
import com.bookingCoach.Alias.AliasStatistical;
import com.bookingCoach.Alias.AliasTicket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;

/**
 *
 * @author Vy Khoi
 */
public class StatisticalServices {

    public ArrayList<AliasStatistical> getSumOfCoachsStripOfDay(String dateOfStatistical) throws SQLException {
        ArrayList<AliasStatistical> ds = new ArrayList<>();

        System.out.println("no vo duocj ham getSumOfCoachsStripOfDay");
        // lọc lấy các tuyến chạy trong ngày 
        try ( Connection conn = JdbcUtils.getConn()) {

            AliasTicket tickets;
            String query = "SELECT departureTime ,idCoachStrips , SUM(price) AS total_price  ,cs.idStationsStart,cs.idStationsEnd FROM bus.coachstripcoachseat cscs, bus.coachstrips cs, bus.ticket t\n"
                    + "                  WHERE departureTime like ?\n"
                    + "                  and cs.idCoachStips = cscs.idCoachStrips\n"
                    + "                  and cscs.idCSCS = t.idCoachStripCoachSeat\n"
                    + "and t.status = 1\n"
                    + "                    group by departureTime,idCoachStrips"; // sử dụng dấu ? thay cho biến idTicket

            PreparedStatement pstmt = conn.prepareStatement(query);

            // mình sẽ chuyền ngày theo yyyy/mm/dd
            pstmt.setString(1, dateOfStatistical + "%"); // set giá trị của biến idTicket vào câu lệnh truy vấn
            ResultSet rs = pstmt.executeQuery();

            System.out.println("chay qua exxcute");
            while (rs.next()) {

                String departureTime = rs.getString("departureTime");
                int idCoachStrips = rs.getInt("idCoachStrips");
                double total_price = rs.getDouble("total_price");
                int idStationsStart = rs.getInt("idStationsStart");
                int idStationsEnd = rs.getInt("idStationsEnd");
                String locationStart = "tam o tphcm";
                String locationEnd = "tam o long an";

                // lấy điểm đi và điểm đến
                String query2 = "SELECT * FROM bus.stations\n"
                        + "where idStations = ?\n"
                        + "or idStations = ?";
                PreparedStatement pstmt2 = conn.prepareStatement(query2);

                pstmt2.setInt(1, idStationsStart);
                pstmt2.setInt(2, idStationsEnd);
                ResultSet rs2 = pstmt2.executeQuery();

                rs2.next(); // laasy name cua sattion
                String tmpnameStart = rs2.getString("name");
                String tmpaddressStart = rs2.getString("address");
                locationStart = tmpnameStart + " : " + tmpaddressStart;

                rs2.next();
                String tmpnameEnd = rs2.getString("name");
                String tmpaddressEnd = rs2.getString("address");
                locationEnd = tmpnameEnd + " : " + tmpaddressEnd;

//                System.out.println(locationStart);
                // gắn đối tượng
                AliasStatistical a = new AliasStatistical(departureTime, idCoachStrips, total_price, idStationsStart, idStationsEnd, locationStart, locationEnd);

                System.out.println("doi tuong dc lay ra la " + a.toString());
                ds.add(a);
            }
            return ds;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return ds;
    }

    public ArrayList<AliasStatistical> getSumOfCoachsStripOfMonth(int month, int year) throws SQLException {
        ArrayList<AliasStatistical> ds = new ArrayList<>();

        System.out.println("no vo duocj ham getSumOfCoachsStripOfDay");
        // lọc lấy các tuyến chạy trong ngày 
        try ( Connection conn = JdbcUtils.getConn()) {

            AliasTicket tickets;
            String query = "SELECT departureTime ,idCoachStrips , SUM(price) AS total_price  ,cs.idStationsStart,cs.idStationsEnd FROM bus.coachstripcoachseat cscs, bus.coachstrips cs,bus.ticket t\n"
                    + "                               WHERE departureTime like ?\n"
                    + "                             and cs.idCoachStips = cscs.idCoachStrips\n"
                    + "                             and t.idCoachStripCoachSeat = cscs.idCSCS\n"
                    + "and t.status = 1\n"
                    + "                            group by idCoachStrips"; // sử dụng dấu ? thay cho biến idTicket

            PreparedStatement pstmt = conn.prepareStatement(query);

            // tạo đối tượng DecimalFormat để định dạng chuỗi
            DecimalFormat decimalFormat = new DecimalFormat("00");
// định dạng tháng thành chuỗi có hai chữ số
            String monthStr = decimalFormat.format(month);
// định dạng năm thành chuỗi có bốn chữ số
            String yearStr = decimalFormat.format(year);
// tạo chuỗi ngày tháng dạng yyyy-mm-%%
            String month_year = yearStr + "-" + monthStr + "-%";

            pstmt.setString(1, month_year);

            ResultSet rs = pstmt.executeQuery();

            System.out.println("chay qua exxcute");
            while (rs.next()) {

                String departureTime = rs.getString("departureTime");
                int idCoachStrips = rs.getInt("idCoachStrips");
                double total_price = rs.getDouble("total_price");
                int idStationsStart = rs.getInt("idStationsStart");
                int idStationsEnd = rs.getInt("idStationsEnd");
                String locationStart = "tam o tphcm";
                String locationEnd = "tam o long an";

                // lấy điểm đi và điểm đến
                String query2 = "SELECT * FROM bus.stations\n"
                        + "where idStations = ?\n"
                        + "or idStations = ?";
                PreparedStatement pstmt2 = conn.prepareStatement(query2);

                pstmt2.setInt(1, idStationsStart);
                pstmt2.setInt(2, idStationsEnd);
                ResultSet rs2 = pstmt2.executeQuery();

                rs2.next(); // laasy name cua sattion
                String tmpnameStart = rs2.getString("name");
                String tmpaddressStart = rs2.getString("address");
                locationStart = tmpnameStart + " : " + tmpaddressStart;

                rs2.next();
                String tmpnameEnd = rs2.getString("name");
                String tmpaddressEnd = rs2.getString("address");
                locationEnd = tmpnameEnd + " : " + tmpaddressEnd;

//                System.out.println(locationStart);
                // gắn đối tượng
                AliasStatistical a = new AliasStatistical(departureTime, idCoachStrips, total_price, idStationsStart, idStationsEnd, locationStart, locationEnd);

                System.out.println("doi tuong dc lay ra la " + a.toString());
                ds.add(a);
            }
            return ds;
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return ds;
    }

//    
    public static void main(String[] args) throws SQLException {
        StatisticalServices c = new StatisticalServices();

        System.out.println("co chay vo day");
        c.getSumOfCoachsStripOfMonth(4, 2023).forEach(h -> {
            System.out.println(h.toString());
        });
    }
}
