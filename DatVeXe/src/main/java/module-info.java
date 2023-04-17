module com.mycompany.datvexe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;



    opens com.mycompany.datvexe to javafx.fxml;
    exports com.mycompany.datvexe;
    exports com.bookingCoach.services;
    exports com.bookingCoach.pojo;
    exports com.bookingCoach.Alias;

}
