/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.bookingCoach.pojo.Station;
import com.bookingCoach.services.JdbcUtils;
import com.bookingCoach.services.StripService;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author Kiet
 */
public class AddStripTester {

    private static StripService sv;
    private static Connection conn;

    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(AddStripTester.class.getName()).log(Level.SEVERE, null, ex);
        }

        sv = new StripService();
    }

    @AfterAll
    public static void afterAll() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AddStripTester.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void testCheckExistingStrip() throws SQLException {
    
        int startLocationId = 1;
        int endLocationId = 2;

        boolean result = sv.checkExistingStrip(startLocationId, endLocationId);


        Assertions.assertTrue(result);
    }

    @Test
    public void testGetStationById() throws SQLException {

        int idStation = 1;

        Station station = sv.getStationById(idStation);

        Assertions.assertEquals("Bến xe Miền Đông", station.getName());
    }

    @Test
    public void testAddNewStrips() throws SQLException {
 
        int distance = 100;
        LocalTime arrivalTime = LocalTime.of(12, 0);
        int idStationsStart = 4;
        int idStationsEnd = 1;

        int result = sv.addNewStrips(distance, arrivalTime, idStationsStart, idStationsEnd);

        Assertions.assertEquals(1, result);
    }

    @Test
    public void testAddNewStripsWhenStripExists() throws SQLException {

        int distance = 100;
        LocalTime arrivalTime = LocalTime.of(12, 0);
        int idStationsStart = 1;
        int idStationsEnd = 2;


        int result = sv.addNewStrips(distance, arrivalTime, idStationsStart, idStationsEnd);


        Assertions.assertEquals(-1, result);
    }

    @Test
    public void testAddNewStripsWithDuplicateStartAndEndLocation() throws SQLException {

        int distance = 100;
        LocalTime arrivalTime = LocalTime.of(12, 0);
        int idStationsStart = 4;
        int idStationsEnd = 4;

    int result = sv.addNewStrips(distance, arrivalTime, idStationsStart, idStationsEnd);

    Assertions.assertEquals(-1, result);
    }
}
