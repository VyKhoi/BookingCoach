/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.datvexe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import java.sql.Connection;
/**
 * FXML Controller class
 *
 * @author Kiet
 */
public class LoginGUIController implements Initializable {

    
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    public void cancelButtonOnAction(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    public void loginButtonOnAction(ActionEvent e){  
        if(usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false){
            loginMessageLabel.setText("You try to login");
         
        }else{
            loginMessageLabel.setText("Please enter username and password.");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }    
    
}
