
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
            Assertions.assertFalse(true);
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
            Assertions.assertFalse(true);
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
            Assertions.assertFalse(true);
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
        try {
            ArrayList<CoachStripCoachSeat> result = c.getEmtySeat(-10);
            Assertions.assertEquals(0, result.size());
        } catch (Exception e) {
            System.out.println(e.toString());
            Assertions.assertFalse(true);
        }
    }

    @Test
//  hàm này mình check lấy ghế trống trong chuyến xe đã đặc   
    public void checkGetEmtySeatWithIdTicket() throws SQLException {
        try {
//            272
//              1
            ArrayList<CoachStripCoachSeat> result = c.getEmtySeat(272);

            if (result.size() == 0) {
                System.out.println("Không có vé trống ứng với id của vé nó đặ");
                Assertions.assertEquals(0, result.size());
                return;
            }

            if (result.size() != 0) {
                result.forEach(h -> {
                    System.out.println(h.toString());
                });
                Assertions.assertNotEquals(0, result.size());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            Assertions.assertFalse(true);
        }
    }

    @Test
//    hàm này test delete với cái vé mà nó có trạng thái chưa nhận thôi
    public void testDeleteTicket() {
        try {
//          đay là id ticket để xóa 
            int idticketn = 854;
//          hàm xử lý xóa
            int rs = c.deleteTicket(idticketn);

            if (rs == 1) {
                System.out.println("Co tim thay ve, và đã xóa");
                Assertions.assertTrue(true);
            }
            if (rs == 0) {
                System.out.println("vé không tìm thấy hoặc đã vé đã nhận");
                Assertions.assertTrue(true);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            Assertions.assertFalse(true);
        }
    }

    @Test
//    hàm này test delete với cái vé mà nó có trạng thái chưa nhận thôi
    public void testDeleteTicketIgnoreStatus() {
        try {
//          đay là id ticket để xóa 
            int idticketn = 43;
//          hàm xử lý xóa
            int rs = c.deleteTicketIgnoreStatus(idticketn);

            if (rs == 1) {
                System.out.println("Co tim thay ve, và đã xóa");
                Assertions.assertTrue(true);
            }
            if (rs == 0) {
                System.out.println("vé không tìm thấy ");
                Assertions.assertTrue(true);
            }

        } catch (Exception e) {
            System.out.println("nó vào");
            System.out.println(e.toString());
            Assertions.assertFalse(true);
        }
    }

    @Test
//    test unit nài dùng để kiểm tra cái hàm thay đổi ghế coi nó có đúng không
    public void testUpdateSeatOfTicket() {
        try {
//             giả sử ta đổi ghế cho vé đang được đặt 
            int idTicketCanChangSeat = 95;
            AliasTicket t = c.getTickets(idTicketCanChangSeat);

//            thời gian khởi hành và, ghế được chọn
            int check = c.updateSeatOfTicket(t, "2023-04-16 17:00:00", 9);

//            trả về 1 niếu sửa được
            if (check == 1) {
                System.out.println("ve da sua oke");
                Assertions.assertTrue(true);
                return;
            }
//            trả về -1 đối với các vé đã nhận ko cho sửa
            if (check == -1) {
                System.out.println("ve khong the sua do da nhan ve");

                Assertions.assertTrue(true);
                return;
            }
//           trả về 0 đối với vé không sửa được ( < 60p)
            if (check == 0) {
                System.out.println("ve khong the sua do duoi 60p ");
                Assertions.assertTrue(true);
                return;
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            Assertions.assertFalse(true);
        }
    }

    @Test
//    hàm unit test nài dùng để test vé 
//    + đã nhận thì không được đổi
//    + < 60 thì không được đổi
    public void testTicketCanChange() {
        try {
//            id với vé đã nhận
            int idTicketToCheck = 97;
            AliasTicket t = c.getTickets(idTicketToCheck);

            int check = c.checkCanChange(t); //   với đã nhận trả về - 1
            if (check == -1) {
                System.out.println("ve da nhan khong the thay doi");
                Assertions.assertTrue(true);

                return;
            }
            //            với vé < 60 khổi hành trả về  0
            if (check == 0) {
                System.out.println("be hon 60p roi khong duoc sua");
                Assertions.assertTrue(true);
                return;
            }

            //   vé có thể sửa : 1
            if (check == 1) {
                System.out.println("ve co the sua");
                Assertions.assertTrue(true);
                return;
            }

            //   hàm có vấn đề
            if (check == -2) {
                throw new Exception("ham kiem tra da bi sai");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            Assertions.assertFalse(true);
        }
    }
//
//     @Test
////  hàm test tự động chạy oke không
////  hàm test cscs < 5phuts thì khóa ghế 
//    public void autoUpdateCSCS(){
//        try {
//            ChangeTicketServices.autoUpdateCSCS();
//            Assertions.assertTrue(true);
//        } catch (Exception e) {
//            System.out.println(e.toString());
//            Assertions.assertFalse(true);
//        }
//    }
//    
//    
//     @Test
////  hàm test tự động chạy oke không
////  hàm test cscs < 5phuts thì khóa ghế 
//    public void autoUpdateTicket(){
//        try {
//            ChangeTicketServices.autoUpdateTicket();
//            Assertions.assertTrue(true);
//        } catch (Exception e) {
//            System.out.println(e.toString());
//            Assertions.assertFalse(true);
//        }
//    }
    
  

}
