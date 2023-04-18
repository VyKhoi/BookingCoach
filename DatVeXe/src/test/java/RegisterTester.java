/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.bookingCoach.services.JdbcUtils;
import com.bookingCoach.services.RegisterService;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.DateTimeException;
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
public class RegisterTester {

    private static RegisterService rs;
    private static Connection conn;

    @BeforeAll
    public static void beforeAll() {
        try {
            conn = JdbcUtils.getConn();
        } catch (SQLException ex) {
            Logger.getLogger(RegisterTester.class.getName()).log(Level.SEVERE, null, ex);
        }

        rs = new RegisterService();
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
    public void testValidateRegisterWithValidInput() throws SQLException, NoSuchAlgorithmException {
        int result = rs.validateRegister("Password123@",
                "johndoe123",
                "123 Main St",
                "Nhân viên",
                "John Doe",
                "Nam",
                "0383456782",
                LocalDate.parse("2000-01-01"));

        Assertions.assertEquals(1, result);
    }

    @Test
    public void testValidateRegisterWithInvalidRoles() throws SQLException, NoSuchAlgorithmException {
        int result = rs.validateRegister("password123",
                "johndoe",
                "123 Main St",
                "invalidRole",
                "John Doe",
                "Nam",
                "0823456771",
                LocalDate.parse("1990-01-01"));

        Assertions.assertEquals(-1, result);
    }

    @Test
    public void testValidateRegisterWithInvalidPassword() throws SQLException, NoSuchAlgorithmException {
        int result = rs.validateRegister("",
                "johndoe",
                "123 Main St",
                "Nhân Viên",
                "John Doe",
                "Nam",
                "0123456689",
                LocalDate.parse("1998-01-01"));

        Assertions.assertEquals(-1, result);
    }

    @Test
    public void testValidateRegisterWithInvalidUsername() throws SQLException, NoSuchAlgorithmException {
        int result = rs.validateRegister("leost1234@",
                "",
                "123 Main St",
                "Nhân viên",
                "Makima",
                "Nam",
                "0123456789",
                LocalDate.parse("1990-01-01"));

        Assertions.assertEquals(-1, result);
    }

    @Test
    public void testValidateRegisterWithInvalidPhone() throws SQLException, NoSuchAlgorithmException {
        int result = rs.validateRegister("password123",
                "johndoe",
                "123 Main St",
                "Nhân viên",
                "John Doe",
                "Nam",
                "abc",
                LocalDate.parse("1999-01-01"));

        Assertions.assertEquals(-1, result);
    }

    @Test
    public void testValidateRegisterWithInvalidBirthDate() throws SQLException, NoSuchAlgorithmException {
        Assertions.assertThrows(DateTimeException.class, () -> {
            LocalDate invalidDate = LocalDate.of(1999, 23, 4);
            rs.validateRegister("password123",
                    "johndoe",
                    "123 Main St",
                    "Nhân Viên",
                    "John Doe",
                    "Nữ",
                    "123456789",
                    invalidDate);
        });
    }
    @Test
	public void testValidateRegisterWithInvalidNameStaff() throws NoSuchAlgorithmException, SQLException {
		int result = rs.validateRegister("Password@123", "validUsername11", "Valid Address", "Nhân Viên", "InvalidNameStaff123", "Nam", "0353456789", LocalDate.of(1997, 1, 1));
		Assertions.assertEquals(-1, result);
	}
}
