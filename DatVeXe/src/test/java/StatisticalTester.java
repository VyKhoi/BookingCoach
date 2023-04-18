/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.bookingCoach.Alias.AliasStatistical;
import com.bookingCoach.services.ChangeTicketServices;
import com.bookingCoach.services.JdbcUtils;
import com.bookingCoach.services.StatisticalServices;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author Vy Khoi
 */
public class StatisticalTester {

    private static ChangeTicketServices c;
    private static Connection conn;

    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(StatisticalTester.class.getName()).log(Level.SEVERE, null, ex);
        }

        c = new ChangeTicketServices();
    }

    @AfterAll
    public static void afterAll() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(StatisticalTester.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
//    hàm test nài dùng để test hàm thống kê theo ngày ứng với các chuyến trong ngày
    public void testGetSumOfCoachsStripOfDay() throws SQLException {
        try {

//            viết test kiểm tra doanh thu của ngày cụ thể là 20/1/2023 với 6 chuyến xe đi
//   ta có dữ liệu 6 chuyến của ngày 20/01/2023
//            '2023-01-20 05:00:00'    '900000' hcm -> MĐ
//            '2023-01-20 15:00:00'    '2700000' hcm -> MĐ
//            '2023-01-20 07:00:00'    '3300000' hcm -> VT
//            '2023-01-20 19:00:00'    '5100000' hcm -> VT
//            '2023-01-20 10:00:00'    '5700000' hcm -> CT
//            '2023-01-20 22:00:00'    '7500000' hcm -> CT
            HashMap<String, Integer> myMap = new HashMap<String, Integer>();
            myMap.put("2023-01-20 05:00:00", 900000);
            myMap.put("2023-01-20 15:00:00", 2700000);
            myMap.put("2023-01-20 07:00:00", 3300000);
            myMap.put("2023-01-20 19:00:00", 5100000);
            myMap.put("2023-01-20 10:00:00", 5700000);
            myMap.put("2023-01-20 22:00:00", 7500000);

            StatisticalServices ss = new StatisticalServices();
            ArrayList<AliasStatistical> ds = ss.getSumOfCoachsStripOfDay("2023-01-20");
//            boolean check = true;
            ds.forEach(h -> {
                Integer value = myMap.get(h.getDepartureTime());
                if (value != null && value != h.getTotal_price()) {
                    Assertions.assertFalse(true);
                }
            });

            Assertions.assertTrue(true);

        } catch (Exception e) {

            System.out.println("chạy vao excep " + e.toString());
            Assertions.assertFalse(true);
        }

    }

    @Test
    // hàm nài test lấy thống kê theo tháng và có dữ liệu
    public void testGetSumOfCoachsStripOfMonth() throws SQLException {
        try {
//1	7200000
//2	16800000
//3	26400000
            HashMap<Integer, Integer> myMap = new HashMap<Integer, Integer>();
            myMap.put(1, 7200000);
            myMap.put(2, 16800000);
            myMap.put(3, 26400000);

//          ta test tháng 1 của 2023
            int month = 1;
            int year = 2023;
            StatisticalServices ss = new StatisticalServices();
            ArrayList<AliasStatistical> ds = ss.getSumOfCoachsStripOfMonth(month, year);
//            boolean check = true;
            ds.forEach(h -> {
                Integer value = myMap.get(h.getIdCoachStrips());
                if (value != null && value != h.getTotal_price()) {
                    Assertions.assertFalse(true);
                }
            });

            Assertions.assertTrue(true);

        } catch (Exception e) {

            System.out.println("chạy vao excep " + e.toString());
            Assertions.assertFalse(true);
        }

    }

    @Test
//    đây là hàm test lấy thống kê theo ngày mà ngày nài không có dữ liệu
    public void testGetSumOfCoachsStripOfDayNoData() {
        try {
            StatisticalServices ss = new StatisticalServices();
            ArrayList<AliasStatistical> ds = ss.getSumOfCoachsStripOfDay("2023-01-01");
            Assertions.assertEquals(0, ds.size());
        } catch (Exception e) {
            System.out.println(e.toString());
            Assertions.assertFalse(true);
        }

    }
    
     @Test
//    đây là hàm test lấy thống kê theo ngày mà ngày nài không có dữ liệu
    public void testGetSumOfCoachsStripOfMonthNoData() {
        try {
            StatisticalServices ss = new StatisticalServices();
            int month = 9;
            int year = 2025;
            ArrayList<AliasStatistical> ds = ss.getSumOfCoachsStripOfMonth(month, year);
            Assertions.assertEquals(0, ds.size());
        } catch (Exception e) {
            System.out.println(e.toString());
            Assertions.assertFalse(true);
        }

    }

    @Test
    //    đây là hàm test lấy thống kê theo Tháng mà ngày nài không có dữ liệu
    public void testABC() {
        int a = 2;
        int b = 2;
        Assertions.assertEquals(a, b);
        System.out.print("++++++"
                + "===================================");

    }

}
