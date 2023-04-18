/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.bookingCoach.pojo.CoachStripCoachSeat;
import com.bookingCoach.pojo.Staff;
import com.bookingCoach.services.CoachStripService;
import com.bookingCoach.services.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
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
public class CoachStripTester {

    private static CoachStripService cs;
    private static Connection conn;

    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(CoachStripTester.class.getName()).log(Level.SEVERE, null, ex);
        }

        cs = new CoachStripService();
    }

    @AfterAll
    public static void afterAll() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(CoachStripTester.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    void getCoachStripIdsShouldReturnListOfCoachStripIds() {
        List<Integer> coachStripIds = cs.getCoachStripIds();
        Assertions.assertNotNull(coachStripIds);
        Assertions.assertTrue(!coachStripIds.isEmpty());
    }

    @Test
    public void testGetStaffById() {
        Staff staff = cs.getStaffById(1);
        Assertions.assertNotNull(staff);
        Assertions.assertEquals("Nguyễn Văn An", staff.getNameStaff());
        Assertions.assertEquals("987654321", staff.getPhone());
        Assertions.assertEquals("Hà Nội", staff.getAddressUser());
    }

    @Test
    public void testGetCoachStripInfo() {
        String info = cs.getCoachStripInfo(1);
        Assertions.assertEquals("Bến xe Miền Đông - Bến xe Mỹ Đình", info);
    }

    @Test
    public void testAddNewTrip() {
        CoachStripCoachSeat trip = new CoachStripCoachSeat();
        trip.setIdCoach(1);
        trip.setIdCoachStrips(1);
        trip.setPrice(100000);
        trip.setDepartureTime(LocalDateTime.of(2023, 4, 22, 15, 0, 0));
        trip.setIdStaff(1);
        boolean result = cs.addNewTrip(trip);
        Assertions.assertTrue(result);
    }

}
