/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.bookingCoach.pojo.Staff;
import com.bookingCoach.services.JdbcUtils;
import com.bookingCoach.services.Login;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
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
public class LoginTester {

    private static Login l;
    private static Connection conn;

    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(LoginTester.class.getName()).log(Level.SEVERE, null, ex);
        }

        l = new Login();
    }

    @AfterAll
    public static void tearDownClass() {
        System.out.println("Gọi sau tất cả TC.");
    }

    @Test
    public void testNumbersNotEqual() {
        int a = 5;
        int b = 9;
        Assertions.assertNotEquals(a, b);
    }

    @Test
    public void testValidateLoginWithCorrectCredentials() throws Exception {
        Login login = new Login();
        String password = "leogem14";
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] hash = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        password = sb.toString();
        Staff expectedStaff = new Staff(17, password, "Kingj", "Bình Định", "Nhân Viên", "Nguyễn Quốc Kỳ", "Nam", "0352557084", LocalDate.of(2000, 04, 13));
        Staff actualStaff = login.validateLogin("Kingj", "leogem14");
        if (actualStaff != null) {
            Assertions.assertEquals(expectedStaff.toString(), actualStaff.toString());
        } else {
            Assertions.fail("Login failed.");
        }
    }

    @Test //Đăng nhập sai mật khẩu
    public void testValidateLoginWithIncorrectPassword() throws Exception {
        Login login = new Login();
        Staff actualStaff = login.validateLogin("nnl", "vykhoi1811");
        Assertions.assertNull(actualStaff);
    }

    @Test //Người dùng không tồn tại
    public void testValidateLoginWithNonexistentUsername() throws Exception {
        Login login = new Login();
        Staff actualStaff = login.validateLogin("leost", "123456");
        Assertions.assertNull(actualStaff);
    }

    @Test //Kiểm tra username, password là Null
    public void testValidateLoginWithNullUsernameAndPassword() throws Exception {
        Login login = new Login();
        Staff actualStaff = login.validateLogin(null, "");
        Assertions.assertNull(actualStaff);
    }

    @Test //Kiểm tra password trống
    public void testValidateLoginWithNullPassword() throws Exception {
        Login login = new Login();
        Staff actualStaff = login.validateLogin("nva", "");
        Assertions.assertNull(actualStaff);
    }

    @Test //Kiểm tra username trống
    public void testValidateLoginWithNullUsername() throws Exception {
        Login login = new Login();
        Staff actualStaff = login.validateLogin(null, "12345678");
        Assertions.assertNull(actualStaff);
    }
}
