module com.mycompany.datvexe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    


    opens com.mycompany.datvexe to javafx.fxml;
    exports com.mycompany.datvexe;
    exports com.bookingCoach.services;
    exports com.bookingCoach.pojo;
}
