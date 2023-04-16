/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ACER
 */
import com.bookingCoach.pojo.CoachStripCoachSeat;
import com.bookingCoach.pojo.Coachstrips;
import com.bookingCoach.services.ChangeTicketServices;
import com.bookingCoach.services.JdbcUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.bookingCoach.services.BookTicKet;

public class BookTicketTest {
    
    @Test
    public void getAllChuyenXeSuccessfull(){
        BookTicKet bt = new BookTicKet();
        List<Coachstrips> chuyenXeList = bt ;
        Assertions.
    }
    
}
