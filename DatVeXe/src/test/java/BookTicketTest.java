/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ACER
 */
import com.bookingCoach.pojo.CoachStripCoachSeat;
import com.bookingCoach.pojo.Coachs;
import com.bookingCoach.pojo.Coachstrips;
import com.bookingCoach.pojo.Station;
import com.bookingCoach.services.ChangeTicketServices;
import com.bookingCoach.services.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.bookingCoach.services.BookTicKet;
import java.time.format.DateTimeFormatter;

public class BookTicketTest {

    private static BookTicKet c;
    private static Connection conn;

    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(BookTicketTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        c = new BookTicKet();
    }

    @Test
    public void getAllChuyenXeSuccessfull() {
        try {
            List<Coachstrips> chuyenXeList = c.getAllChuyenXe();
            Assertions.assertEquals(3, chuyenXeList.size());
        } catch (Exception e) {
            System.out.println(e.toString());
            Assertions.assertFalse(true);
        }
    }

    @Test
    public void getAllStationSuccessful() {
        try {
            List<Station> allStation = c.getAllStation();
            Assertions.assertEquals(4, allStation.size());
        } catch (Exception e) {
            System.out.println(e.toString());
            Assertions.assertFalse(true);
        }
    }

    @Test
    public void addStripsSucessful() {
        try {
            List<String> resultList = c.addStrips();
            Assertions.assertEquals(3, resultList.size());
        } catch (Exception e) {
            System.out.println(e.toString());
            Assertions.assertFalse(true);
        }
    }

    @Test
    public void getListCoachStripCanOrderSucessful() {
        try {
            int idCoachStrips = 1;
            String time_now = "2023-04-15 07:00:00";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(time_now, formatter);
            List<CoachStripCoachSeat> listCoachstrips = c.getListCoachStripCanOrder(idCoachStrips, localDateTime);
            Assertions.assertEquals(6, listCoachstrips.size());
        } catch (Exception e) {
            System.out.println(e.toString());
            Assertions.assertFalse(true);
        }
    }

    @Test
    public void getListIDCoachSucessful() {
        try {
            String time_now = "2023-04-15 07:00:00";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(time_now, formatter);
            List<Integer> listidCoach = c.getListIDCoach(localDateTime);
            Assertions.assertEquals(2, listidCoach.size());
        } catch (Exception e) {
            System.out.println(e.toString());
            Assertions.assertFalse(true);
        }
    }

    @Test
    public void getListCoachSucessful() {
        try {
            String time_now = "2023-04-15 07:00:00";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(time_now, formatter);
            List<Integer> listidCoach = c.getListIDCoach(localDateTime);
            List<Coachs> listCoachs = c.getListCoach(listidCoach);
            Assertions.assertEquals(2, listCoachs.size());
        } catch (Exception e) {
            System.out.println(e.toString());
            Assertions.assertFalse(true);
        }
    }

    @Test
    public void getListNameSeatSucessful() {
        try {
            String time_now = "2023-04-16 22:00:00";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(time_now, formatter);
            List<Integer> listNameSeat = c.getListNameSeat(6, 3, localDateTime);
            Assertions.assertEquals(4, listNameSeat.size());
        } catch (Exception e) {
            System.out.println(e.toString());
            Assertions.assertFalse(true);
        }
    }

    @Test
    public void getIdCSCSSucessful() {
        try {
            String time_now = "2023-04-15 19:00:00";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(time_now, formatter);
            int idCSCS = c.getIdCSCS(4, 2, localDateTime, 10);
            Assertions.assertEquals(100, idCSCS);
        } catch (Exception e) {
            System.out.println(e.toString());
            Assertions.assertFalse(true);
        }
    }

    @Test
    public void getPriceSucessful() {
        try {
            int idCoach = 6;
            double price = c.getPrice(idCoach);
            Assertions.assertEquals(600000, price);
        } catch (Exception e) {
            System.out.println(e.toString());
            Assertions.assertFalse(true);
        }
    }

    @Test
    public void updateStatusSeatSucessful() {
        try {
            c.updateStatusSeat(180);
            Assertions.assertTrue(true);
        } catch (Exception e) {
            System.out.println(e.toString());
            Assertions.assertFalse(true);
        }
    }

    @Test
    public void getIdTicketSucessful() {
        try {
            int idTicket = c.getIdTicket(34);
            Assertions.assertEquals(28, idTicket);
        } catch (Exception e) {
            System.out.println(e.toString());
            Assertions.assertFalse(true);
        }
    }

    @Test
    public void getIdCusSucessful() {
        try {
            int idCus = c.getIdCus("Nguyễn Văn Đạt", "987654320", "Hưng Yên");
            Assertions.assertEquals(45, idCus);
        } catch (Exception e) {
            System.out.println(e.toString());
            Assertions.assertFalse(true);
        }
    }

    @Test
    public void addTicKetSucessful() {
        try {
            String time_now = "2023-04-15 19:00:00";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(time_now, formatter);
            c.addTicKet(412, 92, localDateTime);
            Assertions.assertTrue(true);
        } catch (Exception e) {
            System.out.println(e.toString());
            Assertions.assertFalse(true);
        }
    }
    
    @Test
    public void sellTicKetSucessful() {
        try {
            String time_now = "2023-04-15 19:00:00";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(time_now, formatter);
            c.sellTicKet(412, 92, localDateTime);
            Assertions.assertTrue(true);
        } catch (Exception e) {
            System.out.println(e.toString());
            Assertions.assertFalse(true);
        }
    }
}
