/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datvexe;

import com.bookingCoach.services.RegisterService;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 *
 * @author Kiet
 */
public class SystemManagerController implements Initializable {

    @FXML
    private Label lbMessage;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField namestaffTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private ToggleGroup gender;
    @FXML
    private ComboBox<String> roleComboBox;
    @FXML
    private DatePicker birthdayPicker;

    public void signupButtonOnAction(ActionEvent e) throws SQLException, NoSuchAlgorithmException {
        String username = usernameTextField.getText();
        String password = passwordPasswordField.getText();
        String namestaff = namestaffTextField.getText();
        String address = addressTextField.getText();
        String phone = phoneTextField.getText();
        String genderStr = "";
        LocalDate birthday = null;
        // Kiểm tra giới tính
        if (gender.getSelectedToggle() != null) {
            RadioButton selectedRadioButton = (RadioButton) gender.getSelectedToggle();
            genderStr = selectedRadioButton.getText();
        } else {
            lbMessage.setText("Vui lòng chọn giới tính");
            return;
        }
        // Lấy giá trị đã chọn trong ComboBox
        String selectedRole = roleComboBox.getValue();

        // Kiểm tra giá trị đã chọn
        if (selectedRole == null || selectedRole.trim().isEmpty()) {
            lbMessage.setText("Vui lòng chọn vai trò");
            return;
        }

        // Kiểm tra ngày sinh
        if (birthdayPicker.getValue() == null) {
            lbMessage.setText("Vui lòng chọn ngày sinh");
            return;
        } else {
            birthday = birthdayPicker.getValue();
            
            System.out.println(" ngay duoc pick " + birthday);
        }

        // Kiểm tra các trường nhập liệu khác
        if (username.trim().isEmpty() || password.trim().isEmpty() || namestaff.trim().isEmpty() || address.trim().isEmpty() || phone.trim().isEmpty()) {
            lbMessage.setText("Vui lòng nhập đầy đủ thông tin");
            return;
        }

        RegisterService registerService = new RegisterService();
        int result = registerService.validateRegister(password, username, address, selectedRole, namestaff, genderStr, phone, birthday);

        if (result > 0) {
            lbMessage.setText("Đăng ký thành công!");
        } else {
            lbMessage.setText("Đăng ký thất bại!");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Thêm các vai trò vào ComboBox
        roleComboBox.getItems().addAll("Nhân Viên", "Tài xế");

        // Đặt giá trị mặc định cho ComboBox là "Nhân Viên"
        roleComboBox.setValue("Nhân Viên");
    }


//    nơi đây xử lý chuyển page
    
    public void switchStistical(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage currentStage = (Stage) node.getScene().getWindow();

        // Load trang mới
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StatisticalGUI.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);

        newStage.show();

        // Đóng Stage hiện tại
        currentStage.close();
    }
    
    public void switchChangeTicket(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage currentStage = (Stage) node.getScene().getWindow();

        // Load trang mới
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangeTicket.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);

        newStage.show();

        // Đóng Stage hiện tại
        currentStage.close();
    }
    
    public void switchBookTicket(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage currentStage = (Stage) node.getScene().getWindow();

        // Load trang mới
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BookTicket.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(scene);

        newStage.show();

        // Đóng Stage hiện tại
        currentStage.close();
    }
}
