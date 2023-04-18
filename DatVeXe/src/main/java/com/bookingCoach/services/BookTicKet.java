/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingCoach.services;

import com.bookingCoach.pojo.CoachStripCoachSeat;
import com.bookingCoach.pojo.Coachs;
import com.bookingCoach.pojo.Coachstrips;
import com.bookingCoach.pojo.Station;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author ACER
 */
public class BookTicKet {

    public List<Coachstrips> getAllChuyenXe() {
        List<Coachstrips> chuyenXeList = new ArrayList<>();
        String sql = "SELECT * FROM Coachstrips";

        try (Connection conn = JdbcUtils.getConn(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Lấy dữ liệu từ ResultSet và đưa vào đối tượng ChuyenXe
                int idCoachStips = rs.getInt("idCoachStips");
                int distance = rs.getInt("distance");
                int idStationsStart = rs.getInt("idStationsStart");
                int idStationsEnd = rs.getInt("idStationsEnd");
                Date arrivalTime = rs.getDate("arrivalTime");

                Coachstrips chuyenXe = new Coachstrips(idCoachStips, distance, arrivalTime, idStationsStart, idStationsEnd);
                chuyenXeList.add(chuyenXe);
            }

        } catch (SQLException ex) {
            // Xử lý ngoại lệ
            ex.printStackTrace();
        }

        return chuyenXeList;
    }

    public List<Station> getAllStation() {
        List<Station> allStation = new ArrayList<>();
        String sql = "SELECT * FROM bus.stations";

        try (Connection conn = JdbcUtils.getConn(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Lấy dữ liệu từ ResultSet và đưa vào đối tượng ChuyenXe
                int idStations = rs.getInt("idStations");
                String name = rs.getString("name");
                String address = rs.getString("address");

                Station station = new Station(idStations, name, address);
                allStation.add(station);
            }

        } catch (SQLException ex) {
            // Xử lý ngoại lệ
            ex.printStackTrace();
        }

        return allStation;
    }

    public List<String> addStrips() {
        BookTicKet ds = new BookTicKet();
        List<Coachstrips> listStrip = ds.getAllChuyenXe();
        List<Station> listStation = ds.getAllStation();
        List<String> listNameOfStartStrip = new ArrayList<>();
        List<String> listNameOfEndStrip = new ArrayList<>();
        //1
        //1 2
        //a-b
        // 1 3
        listStrip.forEach(Strip -> {
            listStation.forEach(Station -> {
                if (Station.getIdStation() == Strip.getIdStationsStart()) {
                    listNameOfStartStrip.add(Station.getAddress());
                }
            });
        });
        listStrip.forEach(Strip -> {

            listStation.forEach(Station -> {
                if (Station.getIdStation() == Strip.getIdStationsEnd()) {
                    listNameOfEndStrip.add(Station.getAddress());
                }
            });
        });
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < Math.min(listNameOfStartStrip.size(), listNameOfEndStrip.size()); i++) {
            String result = listNameOfStartStrip.get(i) + " - " + listNameOfEndStrip.get(i);
            resultList.add(result);
        }

        return resultList;
    }

    public List<CoachStripCoachSeat> getListCoachStripCanOrder(int idCoachStrips, LocalDateTime timeNow) {
        List<CoachStripCoachSeat> listCoachstrips = new ArrayList<>();

//        String query = "SELECT * FROM bus.coachstripcoachseat WHERE idCoachStrips = ?";
        String query = "SELECT * FROM bus.coachstripcoachseat WHERE idCoachStrips = ? "
                + "AND departureTime > ? "
                + "GROUP BY departureTime "
                + "ORDER BY departureTime";
        try {
            try (Connection conn = JdbcUtils.getConn()) {
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, idCoachStrips);
                pstmt.setTimestamp(2, Timestamp.valueOf(timeNow));
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    // Lấy giá trị từ ResultSet và thêm vào danh sách coachStripsList
                    int idCSCS = rs.getInt("idCSCS");
                    int idCoach = rs.getInt("idCoach");
                    int price = rs.getInt("price");
                    java.sql.Timestamp departureTime = rs.getTimestamp("departureTime");
                    LocalDateTime localDepartureTime = departureTime.toLocalDateTime();
                    int statusSeat = rs.getInt("statusSeat");
                    int nameSeat = rs.getInt("nameSeat");
                    int idStaff = rs.getInt("idStaff");

                    CoachStripCoachSeat coachStrips = new CoachStripCoachSeat(idCSCS, idCoach, idCoachStrips, price, localDepartureTime, statusSeat, nameSeat, idStaff);
                    listCoachstrips.add(coachStrips);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listCoachstrips;
    }

    public List<Integer> getListIDCoach(LocalDateTime time) {
        String query = "SELECT idCoach FROM bus.coachstripcoachseat WHERE departureTime = ? GROUP BY idCoach";
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setTimestamp(1, Timestamp.valueOf(time));
            ResultSet rs = pstmt.executeQuery();

            List<Integer> listidCoach = new ArrayList<>();
            while (rs.next()) {
                int idCoach = rs.getInt("idCoach");
                listidCoach.add(idCoach);
            }

            return listidCoach;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Coachs> getListCoach(List<Integer> listIdCoach) {
        List<Coachs> listCoachs = new ArrayList<>();
        String query = "SELECT * FROM bus.coachs WHERE idCoach IN (";
        for (int i = 0; i < listIdCoach.size(); i++) {
            query += "?";
            if (i < listIdCoach.size() - 1) {
                query += ",";
            }
        }
        query += ") GROUP BY typeOfCoach";

        try {
            Connection conn = JdbcUtils.getConn();
            PreparedStatement pstmt = conn.prepareStatement(query);
            for (int i = 0; i < listIdCoach.size(); i++) {
                pstmt.setInt(i + 1, listIdCoach.get(i));
            }
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                // Lấy giá trị từ ResultSet và thêm vào danh sách listCoachs
                int idCoach = rs.getInt("idCoach");
                int numberCoach = rs.getInt("numberCoach");
                int capacity = rs.getInt("capacity");
                String typeOfCoach = rs.getString("typeOfCoach");

                Coachs coachs = new Coachs(idCoach, numberCoach, capacity, typeOfCoach);
                listCoachs.add(coachs);
            }

            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listCoachs;
    }

    public List<Integer> getListNameSeat(int idCoach, int idCoachStrip, LocalDateTime deprature) {
        List<Integer> listNameSeat = new ArrayList();
        String sql = "SELECT nameSeat FROM bus.coachstripcoachseat WHERE statusSeat=0 AND idCoach = ? AND idCoachStrips = ? AND departureTime = ?";
        try {
            Connection conn = JdbcUtils.getConn();

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idCoach);
            statement.setInt(2, idCoachStrip);
            statement.setTimestamp(3, Timestamp.valueOf(deprature));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idNameSeat = resultSet.getInt("nameSeat");

                listNameSeat.add(idNameSeat);
            }

            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listNameSeat;
    }

    public int getIdCSCS(int idCoach, int idCoachStrips, LocalDateTime deprature, int nameSeat) {
        int idCSCS = 0;
        String sql = "SELECT idCSCS FROM bus.coachstripcoachseat WHERE statusSeat=0 AND idCoach = ? AND idCoachStrips = ? AND departureTime = ? AND nameSeat =?";
        try {
            Connection conn = JdbcUtils.getConn();

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idCoach);
            statement.setInt(2, idCoachStrips);
            statement.setTimestamp(3, Timestamp.valueOf(deprature));
            statement.setInt(4, nameSeat);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                idCSCS = resultSet.getInt("idCSCS");
            }
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idCSCS;

    }

    public double getPrice(int idCoach) {
        double price = 0;
        String sql = "SELECT price FROM bus.coachstripcoachseat WHERE idCoach = ?";
        try {
            Connection conn = JdbcUtils.getConn();

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idCoach);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                price = resultSet.getDouble("price");
            }
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return price;

    }

    public void updateStatusSeat(int idCSCS) {
        String sql = "UPDATE bus.coachstripcoachseat SET statusSeat = 1 WHERE idCSCS = ?";
        try {
            Connection conn = JdbcUtils.getConn();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idCSCS);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Trạng thái CSCS đã được cập nhật thành công.");
            }
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int getIdCus(String ten, String number, String Address) {
        int idCus = 0;
        String sql = "SELECT idCustomer FROM customer WHERE name = ? AND phoneNumber = ?";
        try {
            Connection conn = JdbcUtils.getConn();

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, ten);
            statement.setString(2, number);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                idCus = resultSet.getInt("idCustomer");
            } else {
                // insert new customer data and retrieve the new id
                String insertSql = "INSERT INTO customer (name, phoneNumber, addressCus) VALUES (?, ?, ?)";
                PreparedStatement insertStatement = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                insertStatement.setString(1, ten);
                insertStatement.setString(2, number);
                insertStatement.setString(3, Address);
                insertStatement.executeUpdate();
                ResultSet generatedKeys = insertStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    idCus = generatedKeys.getInt(1);
                }
                insertStatement.close();
            }
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idCus;
    }

    public void addTicKet(int idCus, int idCSCS, LocalDateTime Time) {
        try {
            Connection conn = JdbcUtils.getConn();
            String insertSql = "INSERT INTO bus.ticket (idStationBuy, bookingDate, idCustomer, idStaff, status, idCoachStripCoachSeat) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = conn.prepareStatement(insertSql);
            insertStatement.setInt(1, 1);
            insertStatement.setTimestamp(2, Timestamp.valueOf(Time));
            insertStatement.setInt(3, idCus);
            insertStatement.setInt(4, 3);
            insertStatement.setInt(5, 0);
            insertStatement.setInt(6, idCSCS);
            insertStatement.executeUpdate();


            ResultSet generatedKeys = insertStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
//                int idTicket = generatedKeys.getInt(1);
                // xử lý dữ liệu khi lấy được idBooking tự động tăng
            }
            insertStatement.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void sellTicKet(int idCus, int idCSCS, LocalDateTime Time) {
        try {
            Connection conn = JdbcUtils.getConn();
            System.out.println("da chay toi addTiket");
            String insertSql = "INSERT INTO bus.ticket (idStationBuy, bookingDate, idCustomer, idStaff, status, idCoachStripCoachSeat) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = conn.prepareStatement(insertSql);
            insertStatement.setInt(1, 1);
            insertStatement.setTimestamp(2, Timestamp.valueOf(Time));
            insertStatement.setInt(3, idCus);
            insertStatement.setInt(4, 3);
            insertStatement.setInt(5, 1);
            insertStatement.setInt(6, idCSCS);
            insertStatement.executeUpdate();

            ResultSet generatedKeys = insertStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
//                int idTicket = generatedKeys.getInt(1);
                // xử lý dữ liệu khi lấy được idBooking tự động tăng
            }
            insertStatement.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public static void main(String[] args) {
        BookTicKet ds = new BookTicKet();
//      


    }

    public int getIdTicket(int idCSCS) {
        int idTicket = 0;
        String mySQL = "SELECT idTicket FROM bus.ticket WHERE idCoachStripCoachSeat = ? ";
        try {
            Connection conn = JdbcUtils.getConn();

            PreparedStatement statement = conn.prepareStatement(mySQL);
            statement.setInt(1, idCSCS);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                idTicket = resultSet.getInt("idTicket");
            }
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idTicket;
    }


}
