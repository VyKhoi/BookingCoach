/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.datvexe;

import com.bookingCoach.services.Login;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import java.sql.SQLException;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Kiet
 */
public class LoginGUIController implements Initializable {

    Login lg = new Login();

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;

    public void cancelButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonOnAction(ActionEvent e) throws SQLException, IOException, NoSuchAlgorithmException {
        if (usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
//            loginMessageLabel.setText("You try to login");
            Login.loginStaff = lg.validateLogin(usernameTextField.getText(), passwordPasswordField.getText());
            if (Login.loginStaff == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Tên đăng nhập hoặc mật khẩu không chính xác");
                //            alert.setContentText();

                alert.showAndWait();
            } else {
                Node node = (Node) e.getSource();
                Stage currentStage = (Stage) node.getScene().getWindow();
                // Tạo hiệu ứng Fade Out
                FadeTransition fadeOut = new FadeTransition(Duration.millis(500), currentStage.getScene().getRoot());
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);
                fadeOut.setOnFinished((ActionEvent event) -> {
                    try {
                        // Load trang mới
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("BookTicket.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage newStage = new Stage();
                        newStage.setScene(scene);

                        // Tạo hiệu ứng Fade In
                        FadeTransition fadeIn = new FadeTransition(Duration.millis(500), scene.getRoot());
                        fadeIn.setFromValue(0.0);
                        fadeIn.setToValue(1.0);
                        fadeIn.play();

                        // Hiển thị trang mới
                        newStage.show();

                        // Đóng Stage hiện tại
                        currentStage.close();
                    } catch (IOException ex) {
                    }
                });

// Thực hiện hiệu ứng Fade Out
                fadeOut.play();

            }
        } else {
            loginMessageLabel.setText("Vui lòng nhập đầy đủ thông tin!!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }

}
