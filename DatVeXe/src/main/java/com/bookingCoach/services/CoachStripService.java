/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingCoach.services;

import com.bookingCoach.pojo.CoachStripCoachSeat;
import com.bookingCoach.pojo.Staff;
import com.bookingCoach.pojo.Station;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kiet
 */
public class CoachStripService {

    public List<Integer> getCoachStripIds() {
        List<Integer> coachStripIds = new ArrayList<>();
        try {
            Connection connection = JdbcUtils.getConn();
            String query = "SELECT idCoachStips FROM coachstrips";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int coachStripId = resultSet.getInt("idCoachStips");
                coachStripIds.add(coachStripId);
            }
        } catch (SQLException e) {
        }
        return coachStripIds;
    }

    public String getCoachStripInfo(int coachStripId) {
        String coachStripInfo = "";
        StripService strips = new StripService();
        try {
            Connection connection = JdbcUtils.getConn();
            String query = "SELECT idStationsStart, idStationsEnd FROM coachstrips WHERE idCoachStips = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, coachStripId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int startLocationId = resultSet.getInt("idStationsStart");
                int endLocationId = resultSet.getInt("idStationsEnd");
                Station startLocation = strips.getStationById(startLocationId);
                Station endLocation = strips.getStationById(endLocationId);
                coachStripInfo = startLocation.getName() + " - " + endLocation.getName();

            }
        } catch (SQLException e) {
        }
        return coachStripInfo;
    }

    public Staff getStaffById(int idStaff) {
        Staff staff = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConn();
            String sql = "SELECT nameStaff, phone, addressUser FROM bus.staff WHERE idStaff=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idStaff);
            rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("nameStaff");
                String phone = rs.getString("phone");
                String address = rs.getString("addressUser");
                staff = new Staff(idStaff, name, phone, address);
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
        return staff;
    }

    public boolean addNewTrip(CoachStripCoachSeat trip) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement psTrip = null;
        try {
            conn = JdbcUtils.getConn();
            conn.setAutoCommit(false);
// Lấy capacity từ bảng Coach
            int capacity = 0;
            String sqlCapacity = "SELECT capacity FROM bus.coachs WHERE idCoach = ?";
            PreparedStatement psCapacity = conn.prepareStatement(sqlCapacity);
            psCapacity.setInt(1, trip.getIdCoach());
            ResultSet rsCapacity = psCapacity.executeQuery();
            if (rsCapacity.next()) {
                capacity = rsCapacity.getInt("capacity");
            }
            System.out.println("capacity của xe " + capacity);
// Thêm các ghế vào chuyến đi

            for (int i = 1; i <= capacity; i++) {
                String sqlSeats = "INSERT INTO bus.coachstripcoachseat(idCoach, idCoachStrips, price, departureTime, statusSeat, nameSeat, idStaff) VALUES (?, ?, ?, ?, ?, ?, ?)";
                psTrip = conn.prepareStatement(sqlSeats, Statement.RETURN_GENERATED_KEYS);
                psTrip.setInt(1, trip.getIdCoach());

                System.out.println(trip.getIdCoach());

                psTrip.setInt(2, trip.getIdCoachStrips());

                System.out.println(trip.getIdCoachStrips());

                psTrip.setDouble(3, trip.getPrice());

                System.out.println(trip.getPrice());

                psTrip.setString(4, Timestamp.valueOf(trip.getDepartureTime()).toString());

                System.out.println(Timestamp.valueOf(trip.getDepartureTime()).toString());

                psTrip.setInt(5, 0);
                psTrip.setInt(6, i);
                psTrip.setInt(7, trip.getIdStaff());
                psTrip.executeUpdate();
            }

            conn.commit();
            result = true;
        } catch (SQLException ex) {
            System.out.println("cath thu nhat " + ex.toString());
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                }
            }
        } finally {
            try {
                if (psTrip != null) {
                    psTrip.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println("cath thu hai " + ex.toString());

            }
        }
        return result;

    }

    public boolean checkDepartureTimeExist(int idCoach, Timestamp departureTime) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConn();
            String sql = "SELECT COUNT(*) as count FROM bus.coachstripcoachseat WHERE idCoach=? AND departureTime=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idCoach);
            ps.setTimestamp(2, departureTime);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                result = count > 0;
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
        return result;
    }

    public boolean checkDepartureTime(Date departureTime) {
        Date currentTime = new Date();
        return !departureTime.before(currentTime);
    }

    public int checkIdCoach(int idCoach) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConn();
            String sql = "SELECT * FROM bus.coachs\n"
                    + "where idCoach = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idCoach);
            rs = ps.executeQuery();
            if (rs.next()) {
                return 1;
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
        return -1;
    }

}

//    public static void main(String[] args) {
//        LocalDateTime t = LocalDateTime.parse("2023-04-20 16:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        String formattedDateTime = t.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        System.out.println(t);
//
//        CoachStripCoachSeat strips = new CoachStripCoachSeat(0, 1, 1, 69000, t, 0, 0, 1);
//
//        CoachStripService cs = new CoachStripService();
//        cs.addNewTrip(strips);
//    }
