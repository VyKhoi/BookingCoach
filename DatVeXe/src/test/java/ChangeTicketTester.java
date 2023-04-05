
import com.bookingCoach.services.ChangeTicketServices;
import com.bookingCoach.services.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ChangeTicketTester {

    private static ChangeTicketServices c;
    private static Connection conn;

    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(ChangeTicketTester.class.getName()).log(Level.SEVERE, null, ex);
        }

        c = new ChangeTicketServices();
    }

    @AfterAll
    public static void tearDownClass() {
        System.out.println("Gọi sau tất cả TC.");
    }

    @Test
    public void testNumbersAreEqual() {
        int a = 5;
        int b = 9;
        Assertions.assertEquals(a, b);
    }

    @Test
    public void testNumbersNotEqual() {
        int a = 5;
        int b = 9;
        Assertions.assertNotEquals(a, b);
    }

    //test case này, dùng để check xem có null khi không có vé nào theo mã không ?
    @Test
    public void testGetTicketsNotFound() throws SQLException {
        int idTicket = -1; // giả định rằng không có vé nào có idTicket là -1
        Assertions.assertNull(c.getTickets(idTicket));
    }

    @Test
    public void testGetTicketsƯithNumberPhoneNotFound() throws SQLException {
        int numberphone = 1; // giả định rằng không có vé nào có idTicket là -1
        Assertions.assertNull(c.getTicketsWithNumberPhone(numberphone));
    }

}
