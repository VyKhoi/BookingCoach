
package com.mycompany.datvexe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class ChangeTicketController  {

    @FXML
    public void oke() throws IOException {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("I have a great message for you!");

        alert.showAndWait();
    }

   
}
