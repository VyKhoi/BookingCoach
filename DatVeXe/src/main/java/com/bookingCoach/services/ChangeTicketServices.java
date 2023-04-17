/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingCoach.services;

import com.bookingCoach.Alias.AliasTicket;
import com.bookingCoach.pojo.CoachStripCoachSeat;
import com.bookingCoach.pojo.Ticket;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import static java.time.temporal.TemporalQueries.zone;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javax.security.auth.AuthPermission;

/**
 *
 * @author Vy Khoi
 */
public class ChangeTicketServices {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public AliasTicket getTickets(int idTicket) throws SQLException {

        // first excute to explore infor of this ticket
        try ( Connection conn = JdbcUtils.getConn()) {
            AliasTicket tickets;
            String query = "SELECT t.idTicket, c.name nameCustomer, c.phoneNumber, c.addressCus, t.bookingDate\n"
                    + ",coach.numberCoach, s.nameStaff, cscs.departureTime , cscs.nameSeat,cscs.idCSCS\n"
                    + ",cscs.idCoachStrips,t.status \n"
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

                Timestamp bookingDateTimestamp = rs.getTimestamp("bookingDate");
                Date bookingDate = new Date(bookingDateTimestamp.getTime());

                System.out.println("Chạy ra roi 8");
                int numberCoach = rs.getInt("numberCoach");
                System.out.println("Chạy ra roi 9");
                String nameStaff = rs.getString("nameStaff");
                System.out.println("Chạy ra roi 10");

                Timestamp departureTimestamp = rs.getTimestamp("departureTime");
                Date departureTime = new Date(departureTimestamp.getTime());

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

                int status = rs.getInt("status");
                System.out.println("het luôn");

                tickets = new AliasTicket(idTicket, nameCustomer, phoneNumber, addressCus, bookingDate, numberCoach, nameStaff, departureTime, nameSeat, idCSCS, idCoachStrips, idStart, nameStartStation, addressStart, idEnd, nameEndStation, addressEnd, status);

                return tickets;
            }

        } // Khối mã xử lý ngoại lệ IOException
        catch (Exception e) {
            System.out.println(e.toString());
        }

        return null;
    }

    public ArrayList<AliasTicket> getTicketsWithNumberPhone(int numberPhone) {
        ArrayList<AliasTicket> ds = new ArrayList<AliasTicket>();

        try ( Connection conn = JdbcUtils.getConn()) {
            AliasTicket tickets;
            String query = "SELECT t.idTicket, c.name nameCustomer, c.phoneNumber, c.addressCus, t.bookingDate\n"
                    + ",coach.numberCoach, s.nameStaff, cscs.departureTime , cscs.nameSeat,cscs.idCSCS\n"
                    + ",cscs.idCoachStrips,t.status \n"
                    + "FROM bus.ticket t, bus.customer c, bus.staff s, bus.coachstripcoachseat cscs, bus.coachs coach\n"
                    + "where t.idCustomer = c.idCustomer\n"
                    + "and t.idStaff = s.idStaff\n"
                    + "and t.idCoachStripCoachSeat = cscs.idCSCS\n"
                    + "and cscs.idCoach = coach.idCoach\n"
                    + "and c.phoneNumber = ?"; // sử dụng dấu ? thay cho biến idTicket

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, numberPhone); // set giá trị của biến idTicket vào câu lệnh truy vấn
            ResultSet rs = pstmt.executeQuery();

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

                int idTicket = rs.getInt("idTicket");
                String nameCustomer = rs.getString("nameCustomer");
                System.out.println(nameCustomer);

                String phoneNumber = rs.getString("phoneNumber");
                System.out.println("Chạy ra roi 6");
                String addressCus = rs.getString("addressCus");
                System.out.println("Chạy ra roi 7");

                Timestamp bookingDateTimestamp = rs.getTimestamp("bookingDate");
                Date bookingDate = new Date(bookingDateTimestamp.getTime());

                System.out.println("Chạy ra roi 8");
                int numberCoach = rs.getInt("numberCoach");
                System.out.println("Chạy ra roi 9");
                String nameStaff = rs.getString("nameStaff");
                System.out.println("Chạy ra roi 10");

                Timestamp departureTimestamp = rs.getTimestamp("departureTime");
                Date departureTime = new Date(departureTimestamp.getTime());

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
                int status = rs.getInt("status");
                System.out.println("het luôn");

                tickets = new AliasTicket(idTicket, nameCustomer, phoneNumber, addressCus, bookingDate, numberCoach, nameStaff, departureTime, nameSeat, idCSCS, idCoachStrips, idStart, nameStartStation, addressStart, idEnd, nameEndStation, addressEnd, status);

                ds.add(tickets);
            }
            if (ds.isEmpty()) {
                throw new Exception("trống");
            }
            return ds;
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }

        return ds;
    }

    public ArrayList<CoachStripCoachSeat> getEmtySeat(int idTicket) throws SQLException {
        ArrayList<CoachStripCoachSeat> ds = new ArrayList<>();
        // hàm này lấy tất cả các ghế còn khả dụng ứng với chuyến mà vé đặt
        System.out.println("da chay vo day");
        try ( Connection conn = JdbcUtils.getConn()) {
            String query = "SELECT cscs.idCoachStrips, cscs.departureTime, cscs.idCoach FROM bus.ticket t,bus.coachstripcoachseat cscs\n"
                    + "where t.idCoachStripCoachSeat = cscs.idCSCS\n"
                    + "and t.idTicket = ?"; // sử dụng dấu ? thay cho biến idTicket

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idTicket); // set giá trị của biến idTicket vào câu lệnh truy vấn
            ResultSet rs = pstmt.executeQuery();

            int idCoachStrips = -1;
            int idCoachOfTicket = -1;
            String strDate = null;

            // lấy mã chuyến và giờ
            if (rs.next()) {
                idCoachStrips = rs.getInt("idCoachStrips");
                // lấy xe
                idCoachOfTicket = rs.getInt("idCoach");

                //lấy thời gian
                Timestamp departureTimestamp = rs.getTimestamp("departureTime");
                Date departureTime = new Date(departureTimestamp.getTime());

                // Chuyển đổi sang định dạng cần thiết
                strDate = dateFormat.format(departureTime);
                System.out.println("idCoachStrips: " + idCoachStrips + ", departureTime: " + strDate);
            }

            if (idCoachStrips != -1) {
                // lọc xem các ghế còn trống trong chuyến đó, giờ đó
                String querySelectCSCS = "SELECT * FROM bus.coachstripcoachseat cscs\n"
                        + "where\n"
                        + "cscs.idCoachStrips = ?\n"
                        + "and cscs.departureTime = ?\n"
                        + "and cscs.idCoach = ?\n"
                        + "and statusSeat = 0"; // sử dụng dấu ? thay cho biến idTicket

                PreparedStatement pstmt2 = conn.prepareStatement(querySelectCSCS);
                pstmt2.setInt(1, idCoachStrips);
                pstmt2.setString(2, strDate);
                pstmt2.setInt(3, idCoachOfTicket);
                ResultSet rs2 = pstmt2.executeQuery();

                // thực hiện nhét vào danh sách
                while (rs2.next()) {
                    int idCSCS = rs2.getInt("idCSCS");
                    int idCoach = rs2.getInt("idCoach");
                    int idCoachStrip = rs2.getInt("idCoachStrips");
                    double price = rs2.getDouble("price");
                    LocalDateTime departureTime2 = rs2.getObject("departureTime", LocalDateTime.class);

                    int statusSeat = rs2.getInt("statusSeat");
                    int nameSeat = rs2.getInt("nameSeat");
                    int idStaff = rs2.getInt("idStaff");

                    // Tạo đối tượng CoachStripCoachSeat từ các giá trị lấy được từ ResultSet
                    CoachStripCoachSeat seat = new CoachStripCoachSeat(idCSCS, idCoach, idCoachStrip, price, departureTime2, statusSeat, nameSeat, idStaff);
                    // Thêm đối tượng CoachStripCoachSeat vào ArrayList
                    ds.add(seat);
                }
                return ds;
            }
        }
        return ds;
    }

    public int deleteTicket(int idTicket) throws SQLException {
        // lấy thông tin vé được xóa
        Ticket t;
        try ( Connection conn = JdbcUtils.getConn()) {
            String query = "SELECT * FROM bus.ticket t\n"
                    + "where t.idTicket = ?"; // sử dụng dấu ? thay cho biến idTicket

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idTicket); // set giá trị của biến idTicket vào câu lệnh truy vấn
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int status = rs.getInt("status");
                if (status == 1) {
                    return 0;
                }
                int idTicket2 = rs.getInt("idTicket");
                int idStationBuy = rs.getInt("idStationBuy");

                //lấy thời gian
                Timestamp bookingDateStamp = rs.getTimestamp("bookingDate");
                Date bookingDate = new Date(bookingDateStamp.getTime());
//                Date bookingDate = dateFormat.format(departureTime);

                int idCustomer = rs.getInt("idCustomer");
                int idStaff = rs.getInt("idStaff");

                int idCoachStripCoachSeat = rs.getInt("idCoachStripCoachSeat");

                t = new Ticket(idTicket2, idStationBuy, bookingDate, idCustomer, idStaff, status, idCoachStripCoachSeat);

                System.err.println(t.toString());

                // xóa vé 
                String querydeleTicket = "DELETE FROM bus.ticket WHERE idTicket = ?";
                PreparedStatement pstmtDelete = conn.prepareStatement(querydeleTicket);
                pstmtDelete.setInt(1, idTicket);
                int affectedRows = pstmtDelete.executeUpdate();

                /// xóa đi chỗ đặt ghế
                String query2 = "UPDATE bus.coachstripcoachseat SET statusSeat = 0 WHERE idCSCS = ?";
                PreparedStatement pstmt2 = conn.prepareStatement(query2);
                pstmt2.setInt(1, t.getIdCoachStripCoachSeat());
                int affectedRows2 = pstmt2.executeUpdate();

                return 1;
            }
            return 0;
        }

    }

    public int deleteTicketIgnoreStatus(int idTicket) throws SQLException {
        // lấy thông tin vé được xóa
        Ticket t;
        try ( Connection conn = JdbcUtils.getConn()) {
            String query = "SELECT * FROM bus.ticket t\n"
                    + "where t.idTicket = ?"; // sử dụng dấu ? thay cho biến idTicket

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, idTicket); // set giá trị của biến idTicket vào câu lệnh truy vấn
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int status = rs.getInt("status");
//                if (status == 1) {
//                    return 0;
//                }
                int idTicket2 = rs.getInt("idTicket");
                int idStationBuy = rs.getInt("idStationBuy");

                //lấy thời gian
                Timestamp bookingDateStamp = rs.getTimestamp("bookingDate");
                Date bookingDate = new Date(bookingDateStamp.getTime());
//                Date bookingDate = dateFormat.format(departureTime);

                int idCustomer = rs.getInt("idCustomer");
                int idStaff = rs.getInt("idStaff");

                int idCoachStripCoachSeat = rs.getInt("idCoachStripCoachSeat");

                t = new Ticket(idTicket2, idStationBuy, bookingDate, idCustomer, idStaff, status, idCoachStripCoachSeat);

                System.err.println(t.toString());

                // xóa vé 
                String querydeleTicket = "DELETE FROM bus.ticket WHERE idTicket = ?";
                PreparedStatement pstmtDelete = conn.prepareStatement(querydeleTicket);
                pstmtDelete.setInt(1, idTicket);
                int affectedRows = pstmtDelete.executeUpdate();

                /// xóa đi chỗ đặt ghế
                String query2 = "UPDATE bus.coachstripcoachseat SET statusSeat = 0 WHERE idCSCS = ?";
                PreparedStatement pstmt2 = conn.prepareStatement(query2);
                pstmt2.setInt(1, t.getIdCoachStripCoachSeat());
                int affectedRows2 = pstmt2.executeUpdate();

                return 1;
            }
            return 0;
        }

    }

//    hàm này dùng để cập nhật lại ghế 
    public int updateSeatOfTicket(AliasTicket ticket, String departureTime, int numberSeat) throws SQLException {

        if (ticket.getStatusTicket() == "Đã nhận") {
            return -1;
        }

        try ( Connection conn = JdbcUtils.getConn()) {

            // lấy xe
            String queryGetCoach = "SELECT cscs.idCoachStrips, cscs.departureTime, cscs.idCoach FROM bus.ticket t,bus.coachstripcoachseat cscs\n"
                    + "where t.idCoachStripCoachSeat = cscs.idCSCS\n"
                    + "and t.idTicket = ?"; // sử dụng dấu ? thay cho biến idTicket

            PreparedStatement pstmtGetCoach = conn.prepareStatement(queryGetCoach);
            pstmtGetCoach.setInt(1, ticket.getIdTicket()); // set giá trị của biến idTicket vào câu lệnh truy vấn
            ResultSet rsGetCoach = pstmtGetCoach.executeQuery();
            int idCoachOfTicket = -1;
            if (rsGetCoach.next()) {
                idCoachOfTicket = rsGetCoach.getInt("idCoach");
            }
            System.out.println("Id cửa chiếc xe là " + idCoachOfTicket);

            java.util.Date depa60 = ticket.getDepartureTime();
            Calendar cal = Calendar.getInstance();
            cal.setTime(depa60);
            cal.add(Calendar.MINUTE, -60);
            depa60 = cal.getTime();

            java.util.Date now = new java.util.Date();

            if (now.compareTo(depa60) > 0) {
                System.out.println("Đéo sửa đc , sau 60 roi " + now + "  :  " + depa60);
                return 0;
            }

            System.out.println("sửa đc");

            System.out.println("chạy qua if updateSeatOfTicket");

            String query = "SELECT * FROM bus.coachstripcoachseat cscs\n"
                    + "where cscs.departureTime = ?\n"
                    + "and cscs.idCoach = ?\n"
                    + "and nameSeat = ?"; // sử dụng dấu ? thay cho biến idTicket

            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, departureTime); // set giá trị của biến idTicket vào câu lệnh truy vấn
            pstmt.setInt(2, idCoachOfTicket);
            pstmt.setInt(3, numberSeat);

            System.out.println("chuan bi excute");
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                // sửa ghế đặt thành 1
                int idCSCS = rs.getInt("idCSCS");
                System.out.println("id cscs khi chon ghe la " + idCSCS);
                String query2 = "UPDATE bus.coachstripcoachseat SET statusSeat = 1 WHERE idCSCS = ?;";
                PreparedStatement pstmt2 = conn.prepareStatement(query2);
                pstmt2.setInt(1, idCSCS);
                int rs2 = pstmt2.executeUpdate();

                System.out.println("OKE");

                // sủa ghế đã đặt trc đó thành 0 sau khi đổi
                String query3 = "UPDATE bus.coachstripcoachseat SET statusSeat = 0 WHERE idCSCS = ?;";
                PreparedStatement pstmt3 = conn.prepareStatement(query3);
                pstmt3.setInt(1, ticket.getIdCSCS());
                int rs3 = pstmt3.executeUpdate();

                // sửa idCSCS của vé thành idCSCS ghế nó đặt
                String query4 = "UPDATE bus.ticket SET idCoachStripCoachSeat = ? WHERE idTicket = ?;";
                PreparedStatement pstmt4 = conn.prepareStatement(query4);
                pstmt4.setInt(1, idCSCS);
                pstmt4.setInt(2, ticket.getIdTicket());
                int rs4 = pstmt4.executeUpdate();

                return 1;
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return 0;
    }

    public int checkCanChange(AliasTicket ticket) throws SQLException {
        if (ticket.getStatusTicket() == "Đã nhận") {
            return -1;
        }

        try ( Connection conn = JdbcUtils.getConn()) {

            // lấy xe
            String queryGetCoach = "SELECT cscs.idCoachStrips, cscs.departureTime, cscs.idCoach FROM bus.ticket t,bus.coachstripcoachseat cscs\n"
                    + "where t.idCoachStripCoachSeat = cscs.idCSCS\n"
                    + "and t.idTicket = ?"; // sử dụng dấu ? thay cho biến idTicket

            PreparedStatement pstmtGetCoach = conn.prepareStatement(queryGetCoach);
            pstmtGetCoach.setInt(1, ticket.getIdTicket()); // set giá trị của biến idTicket vào câu lệnh truy vấn
            ResultSet rsGetCoach = pstmtGetCoach.executeQuery();
            int idCoachOfTicket = -1;
            if (rsGetCoach.next()) {
                idCoachOfTicket = rsGetCoach.getInt("idCoach");
            }
            System.out.println("Id cửa chiếc xe là " + idCoachOfTicket);

            java.util.Date depa60 = ticket.getDepartureTime();
            Calendar cal = Calendar.getInstance();
            cal.setTime(depa60);
            cal.add(Calendar.MINUTE, -60);
            depa60 = cal.getTime();

            java.util.Date now = new java.util.Date();

            if (now.compareTo(depa60) > 0) {
                System.out.println("Khong sửa đc , sau 60 roi " + now + "  :  " + depa60);
                return 0;
            }
            return 1;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return -2;
    }

//    hàm nài chạy auto quét csdl để tìm vé đặt mà không nhận
    public static final void autoUpdateTicket() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, event -> {
                    // Gọi phương thức cập nhật dữ liệu của bạn ở đây
                    System.out.println("Có chạy 10s");

                    try ( Connection conn = JdbcUtils.getConn()) {
                        String query = "SELECT * FROM bus.ticket WHERE status = 0;";
                        PreparedStatement pstmt = conn.prepareStatement(query);
                        ResultSet rs = pstmt.executeQuery();
                        // rs danh sách ticket đang đặt mà chưa nhận
                        while (rs.next()) {
                            ChangeTicketServices c = new ChangeTicketServices();
                            int idTK = rs.getInt("idTicket");

                            //ghế
                            int idCSCS = rs.getInt("idCoachStripCoachSeat");

                            //check xem với cái vé chưa nhận nài nó có quá hạn chưa
                            String query2 = "SELECT * FROM bus.coachstripcoachseat cscs\n"
                                    + "where cscs.idCSCS = ?"; // sử dụng dấu ? thay cho biến idTicket

                            PreparedStatement pstmt2 = conn.prepareStatement(query2);
                            pstmt2.setInt(1, idCSCS); // set giá trị của biến idTicket vào câu lệnh truy vấn
                            ResultSet rs2 = pstmt2.executeQuery();

                            // kiểm tra ngày hiện tại
                            if (rs2.next()) {
                                LocalDateTime departureTime = rs2.getTimestamp("departureTime").toLocalDateTime();
                                LocalDateTime timeBefore30Min = departureTime.minusMinutes(30);
                                LocalDateTime now = LocalDateTime.now();
                                System.out.println(timeBefore30Min + "   :   " + now);
                                long minutesDifference = ChronoUnit.MINUTES.between(timeBefore30Min, now);
                                if (now.isAfter(timeBefore30Min)) {
                                    c.deleteTicket(idTK);
                                    System.out.println("xóa " + idTK);
                                }

                                System.out.println("da check update cac ve ");
                            }

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ChangeTicketServices.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }),
                new KeyFrame(Duration.seconds(10))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
// hàm nài liên tục quét csdl để tìm các ghế mà không ai đặt < 5p khóa ghế

    public static void autoUpdateCSCS() throws SQLException {
        try {

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, event -> {
                        try ( Connection conn = JdbcUtils.getConn()) {
                            String query = "UPDATE bus.coachstripcoachseat SET statusSeat = 1 WHERE DATE_SUB(departureTime, INTERVAL 5 MINUTE) <= ?";
                            PreparedStatement pstmt = conn.prepareStatement(query);

                            pstmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                            int updatedRows = pstmt.executeUpdate();

                            System.out.println("đã check update ghế " + updatedRows);
                        } catch (SQLException ex) {
                            Logger.getLogger(ChangeTicketServices.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }),
                    new KeyFrame(Duration.seconds(10))
            );
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        } catch (Exception e) {
            System.out.println(e.toString());
            
        }
    }

    public static void main(String[] args) throws SQLException {
        ChangeTicketServices ds = new ChangeTicketServices();

                ds.getTicketsWithNumberPhone(984376291).forEach(h -> {
                    System.out.println(h.toString());
                });
                System.out.println(ds.getTickets(1).toString());
                ds.getEmtySeat(1).forEach(h -> {
                    System.out.println(h.toString());
                });;
                System.err.println(ds.deleteTicket(-1));

        ds.deleteTicketIgnoreStatus(858);
//        //        ds.getTicketsWithNumberPhone(984376291).forEach(h -> {
//        //            System.out.println(h.toString());
//        //        });
//        //        System.out.println(ds.getTickets(1).toString());
//        //        ds.getEmtySeat(1).forEach(h -> {
//        //            System.out.println(h.toString());
//        //        });;
//        //        System.err.println(ds.deleteTicket(-1));

//        ChangeTicketServices.autoUpdateCSCS();

    }
}
