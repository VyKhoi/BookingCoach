module com.mycompany.datvexe {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.datvexe to javafx.fxml;
    exports com.mycompany.datvexe;
}
