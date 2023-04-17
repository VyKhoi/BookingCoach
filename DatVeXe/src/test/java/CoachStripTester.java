/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.bookingCoach.pojo.Staff;
import com.bookingCoach.services.CoachStripService;
import com.bookingCoach.services.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalTime;
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
    private static CoachStripService cs ;
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
    public void testGetCoachStripIds() {
        List<Integer> ids = cs.getCoachStripIds();
        Assertions.assertNotNull(ids);
        Assertions.assertEquals(9, ids.size());
    }
    @Test
    public void testGetStaffById() {
        Staff staff = cs.getStaffById(1);
        Assertions.assertNotNull(staff);
        Assertions.assertEquals("Nguyễn Văn An", staff.getNameStaff());
        Assertions.assertEquals("987654321", staff.getPhone());
    }
    @Test
    public void testGetCoachStripInfo() { 
        String info = cs.getCoachStripInfo(1);
        Assertions.assertEquals("Bến xe Miền Đông - Bến xe Mỹ Đình", info);
    }
}
