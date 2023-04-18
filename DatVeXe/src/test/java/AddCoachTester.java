/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.bookingCoach.services.CoachService;
import com.bookingCoach.services.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
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
public class AddCoachTester {

    private static CoachService cs;
    private static Connection conn;

    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(AddCoachTester.class.getName()).log(Level.SEVERE, null, ex);
        }

        cs = new CoachService();
    }

    @AfterAll
    public static void afterAll() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AddCoachTester.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void testAddNewCoachWithExistingNumberCoach() throws SQLException {

        int numberCoach = 1;
        int capacity = 30;
        String typeOfCoach = "Xe khách";

        cs.addNewCoach(numberCoach, capacity, typeOfCoach);

        int result = cs.addNewCoach(numberCoach, capacity, typeOfCoach);

        Assertions.assertEquals(-1, result);
    }

    @Test
    public void testCheckCoachExists() throws SQLException {

        int numberCoach = 1;

        boolean result = cs.checkCoachExists(numberCoach);

        Assertions.assertTrue(result);
    }

    @Test
    public void testAddNewCoachWithTypeOfCoachNull() throws SQLException {

        int numberCoach = 71;
        int capacity = 30;
        String typeOfCoach = null;

        int result = cs.addNewCoach(numberCoach, capacity, typeOfCoach);

        Assertions.assertEquals(-1, result);
    }

    @Test
    public void testAddNewCoachWithNegativeCapacity() throws SQLException {

        int numberCoach = 74;
        int capacity = -20;
        String typeOfCoach = "Xe khách";

        int result = cs.addNewCoach(numberCoach, capacity, typeOfCoach);

        Assertions.assertEquals(-1, result);
    }

    @Test
    public void testAddNewCoachWithInvalidCapacity() throws SQLException {
        
        int result = cs.addNewCoach(89, 5, "Xe Khách");
        Assertions.assertEquals(-1, result);
    }

    @Test
    public void testAddNewCoachWithExceedingCapacity() throws SQLException {

        int numberCoach = 77;
        int capacity = 100;
        String typeOfCoach = "Xe khách";
        int result = cs.addNewCoach(numberCoach, capacity, typeOfCoach);

        Assertions.assertEquals(-1, result);
    }
}
