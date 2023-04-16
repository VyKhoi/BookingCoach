
import com.bookingCoach.Alias.AliasTicket;
import com.bookingCoach.pojo.CoachStripCoachSeat;
import com.bookingCoach.pojo.Ticket;
import com.bookingCoach.services.ChangeTicketServices;
import com.bookingCoach.services.JdbcUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public static void afterAll() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ChangeTicketTester.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

//    ======================= TEST GET TICKET ===============
    //test this case, used to check if there is null when there is no ticket according to the id ticket?
    @Test
    public void testGetTicketsNotFound() throws SQLException {
        try {
            int idTicket = -100; //assume that there are no tickets with idTicket of -1
            Assertions.assertNull(c.getTickets(idTicket));

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
//     hàm này test tìm thấy vé với id

    @Test
    public void testGetTicketIsFound() throws SQLException, ParseException {

        try {
            //        ta có dữ liệu ban đầu
            String dateString = "2023-04-08 03:00:00";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date date = dateFormat.parse(dateString);
            long time = date.getTime();
            Timestamp sqlTimestamp = new Timestamp(time);
            java.sql.Date sqlDate = new java.sql.Date(sqlTimestamp.getTime());
            Ticket t = new Ticket(1, 1, sqlDate, 1, 1, 1, 1);

            System.out.println(t.toString());
//            viết test
            int idTicket = 1; //assume that there are no tickets with idTicket of -1
            AliasTicket tmp = c.getTickets(idTicket);

            if (tmp.getIdTicket() == t.getIdTicket() && tmp.getIdCSCS() == t.getIdCoachStripCoachSeat()) {
//                niếu nó lấy ra ticket đúng với id đó thì oke

                Assertions.assertTrue(true);
            } else {
                Assertions.assertTrue(false);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    // which test case is used to check if an empty array is returned when the ticket by phone number is not found
    @Test
    public void testGetTicketswithNumberPhoneNotFound() throws SQLException {
        try {
            int numberphone = -1; // Assume that there are no tickets with a phone number of -1s
            ArrayList<AliasTicket> ds = c.getTicketsWithNumberPhone(numberphone);
            Assertions.assertEquals(0, ds.size());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    // hàm test nài dùng để test niếu thấy có số điện thoại thì trả ra mảng vé của họ
    @Test
    public void testGetTicketswithNumberPhoneIsFound() throws SQLException {

//         984376291 thì bây giờ có 3 id vé
        int[] a = {1, 49, 97};

        try {
            int numberphone = 984376291; // Assume that there are no tickets with a phone number of -1s
            ArrayList<AliasTicket> ds = c.getTicketsWithNumberPhone(numberphone);

            if (ds.size() == 0) {
                throw new Exception("Không tìm thấy số điện thoại");
            }

            boolean hasSameElements = true;

            for (int i = 0; i < a.length; i++) {
                boolean found = false;
                for (int j = 0; j < ds.size(); j++) {
                    if (a[i] == ds.get(j).getIdTicket()) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    hasSameElements = false;
                    break;
                }
            }

            if (hasSameElements == true) {
                Assertions.assertTrue(true);
            } else {
                Assertions.assertFalse(true);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            Assertions.assertFalse(true);
        }
    }

    //tese case nài dùng để kiểm tra việc lấy các ghế rỗng trong chuyến xe ứng với id vé
    // test case nài dùng để check niếu vé không tìm thấy thì trả về mảng rỗng
    @Test
    public void checkGetCSCSWithIdTicketNotFind() throws SQLException {
//        giả sử 
        ArrayList<CoachStripCoachSeat> result = c.getEmtySeat(-10);
        Assertions.assertEquals(0, result.size());
    }

//    test unit test
    @Test
    public void testABC() {
        int a = 2;
        int b = 2;
//        Assertions.assertEquals(a, b);
        System.out.print("++++++"
                + "===================================");
//        Assertions.assertNotEquals(a, b);
    }
}
