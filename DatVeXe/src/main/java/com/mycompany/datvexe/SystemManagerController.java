/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datvexe;

import com.bookingCoach.pojo.CoachStripCoachSeat;
import com.bookingCoach.pojo.Staff;
import com.bookingCoach.pojo.Station;
import com.bookingCoach.services.CoachService;
import com.bookingCoach.services.CoachStripService;
import com.bookingCoach.services.StripService;

import com.bookingCoach.services.Login;
import com.bookingCoach.services.RegisterService;
import java.io.IOException;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
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

    @FXML
    private TextField coachTextField;
    @FXML
    private ComboBox<Integer> coachStripsComboBox;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField departureTimeTextField;
    @FXML
    private TextField staffTextField;
    @FXML
    private Label staffLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label addressLabel;

    @FXML
    private Pane imageShow;
    @FXML
    private ComboBox<Integer> stationStartCb;
    @FXML
    private ComboBox<Integer> stationEndCb;
    @FXML
    private TextField distanceTxt;

    @FXML
    private TextField arrivalTimeTxt;
    @FXML
    private TextField numberCoachTxt;
    @FXML
    private TextField capacityTxt;
    @FXML
    private ComboBox<String> typeofCoachCb;
    @FXML
    private Label stationStartLb;
    @FXML
    private Label stationEndLb;
    @FXML
    private Label coachStripInfoLabel;
    @FXML
    private Label nameSatff = new Label();

    public void signupButtonOnAction(ActionEvent e) throws SQLException, NoSuchAlgorithmException {
        String username = usernameTextField.getText();
        String password = passwordPasswordField.getText();
        String namestaff = namestaffTextField.getText();
        String address = addressTextField.getText();
        String phone = phoneTextField.getText();
        String genderStr = "";
        LocalDate birthday = null;
        // Kiểm tra các trường nhập liệu khác
        if (username.trim().isEmpty() || password.trim().isEmpty() || namestaff.trim().isEmpty() || address.trim().isEmpty() || phone.trim().isEmpty()) {
            lbMessage.setText("Vui lòng nhập đầy đủ thông tin");
            return;
        }
        // Kiểm tra các trường nhập liệu khác
        if (username.trim().isEmpty() || password.trim().isEmpty() || namestaff.trim().isEmpty() || address.trim().isEmpty() || phone.trim().isEmpty()) {
            lbMessage.setText("Vui lòng nhập đầy đủ thông tin");
            return;
        }
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

//            System.out.println(" ngay duoc pick " + birthday);
        }
        // Kiểm tra định dạng tên đăng nhập (chứa ít nhất một chữ cái, một số và tối thiểu 6 ký tự)
        if (!username.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$")) {
            lbMessage.setText("Tên đăng nhập phải chứa ít nhất 1 chữ cái, 1 số và tối thiểu 6 ký tự");
            return;
        }
        // Kiểm tra định dạng tên nhân viên (phải chứa chữ cái và khoảng trắng) (có thể viết TV)
        if (!namestaff.matches("^[\\p{L}]+(\\s[\\p{L}]+)+$")) {
            lbMessage.setText("Tên nhân viên không hợp lệ, nên nhập tên theo vd: Nguyễn Văn A");
            return;
        }

        // Kiểm tra định dạng địa chỉ (không chứa ký tự đặc biệt, chỉ chứa chữ)
        if (!address.matches("^[a-zA-Z\\s]+$")) {
            lbMessage.setText("Địa chỉ không hợp lệ, không được có số và ký tự đặc biệt");
            return;
        }
// Kiểm tra định dạng mật khẩu (chứa ít nhất 1 chữ thường và 1 chữ haa, 1 số, ký tự đặc biệt và có độ dài tối thiểu 8 ký tự)
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=_\\-!?.><,*(){}\\[\\]:;|/~`]).{8,}$")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Lỗi định dạng mật khẩu");
            alert.setContentText("Mật khẩu phải chứa ít nhất 1 chữ thường và chữ hoa, 1 số, 1 ký tự đặc biệt và có độ dài tối thiểu 8 ký tự");
            alert.showAndWait();
            return;
        }

        RegisterService registerService = new RegisterService();

        // Kiểm tra số điện thoại đã tồn tại chưa
        if (registerService.checkPhoneNumberExist(phone)) {
            lbMessage.setText("Số điện thoại đã tồn tại");
            return;
        }
        if (registerService.checkUserNameExist(username)) {
            lbMessage.setText("Tên đăng nhập đã tồn tại");
            return;
        }
        // Validate thông tin đăng ký
        int result = registerService.validateRegister(password, username, address, selectedRole, namestaff, genderStr, phone, birthday);
        
        if (result > 0) {
            lbMessage.setText("Đăng ký thành công!");
        } else {
            lbMessage.setText("Đăng ký thất bại!");
        }

    }
      @FXML
    private Button logoutButton;

    public void logoutButtonOnAction(ActionEvent event) throws IOException {
        Login.loginStaff = null; // xóa thông tin đăng nhập của nhân viên
        Node source = (Node) event.getSource();
        FadeTransition fadeOut = new FadeTransition(javafx.util.Duration.millis(500), source);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished((ActionEvent event1) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginGUI.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) source.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
            }
        });
        fadeOut.play();
    }


    public void coachstripcoachseatButtonAction(ActionEvent e) {
        // Kiểm tra xem người dùng đã nhập đầy đủ thông tin hay chưa
        if (coachTextField.getText().isEmpty() || priceTextField.getText().isEmpty() || departureTimeTextField.getText().isEmpty() || staffTextField.getText().isEmpty() || coachStripsComboBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập đầy đủ thông tin");
            alert.showAndWait();
            return;
        }
        //Kiểm tra nhập số cho mã xe
        try {
            int coach = Integer.parseInt(coachTextField.getText());
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Số xe phải là số. Vui lòng nhập lại !");
            alert.showAndWait();
            return;
        }
        //Kiểm tra nhập số cho tiền
        try {
            double priceValue = Double.parseDouble(priceTextField.getText());
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Giá tiền phải là số. Vui lòng nhập lại !");
            alert.showAndWait();
            return;
        }
        //Kiểm tra nhập số cho nhân viên
        try {
            int idStaff = Integer.parseInt(staffTextField.getText());
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Mã số nhân viên phải là số. Vui lòng nhập lại !");
            alert.showAndWait();
            return;
        }
        //Kiểm tra mã xe có tồn tại không 
        CoachStripService csC = new CoachStripService();
        int a = csC.checkIdCoach(Integer.parseInt(coachTextField.getText()));
        if( a <0){
             Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập đúng mã xe!");
            alert.showAndWait();
            return;
        }
        
        // Lấy dữ liệu từ các trường nhập
        int coach = Integer.parseInt(coachTextField.getText());
        double priceValue = Double.parseDouble(priceTextField.getText());
        String departureTime = departureTimeTextField.getText();
        int idStaff = Integer.parseInt(staffTextField.getText());
        // Lấy giá trị đã chọn trong ComboBox
        int selectCB = coachStripsComboBox.getValue();

        try {
            // Chuyển đổi chuỗi đầu vào thành đối tượng LocalDateTime
            LocalDateTime departureTimeParsed = LocalDateTime.parse(departureTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            // Kiểm tra xem người dùng đã nhập đầy đủ thông tin hay chưa

            // Nếu ngày tháng nhập vào hợp lệ, tiến hành kiểm tra xem giờ khởi hành đã tồn tại chưa
            CoachStripService sv = new CoachStripService();
            if (sv.checkDepartureTimeExist(coach, Timestamp.valueOf(departureTimeParsed))) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Chuyến đi đã tồn tại !");
                alert.showAndWait();
                return;
            }
            LocalDateTime now = LocalDateTime.now();
            if (departureTimeParsed.isBefore(now)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Giờ khởi hành phải lớn hơn thời gian hiện tại !");
                alert.showAndWait();
                return;
            }
            CoachStripCoachSeat trip = new CoachStripCoachSeat();
            trip.setIdCoach(coach);
            trip.setPrice(priceValue);
            trip.setIdCoachStrips(selectCB);

            trip.setDepartureTime(departureTimeParsed);
            trip.setIdStaff(idStaff);
            // Gọi hàm thêm chuyến đi từ service và kiểm tra kết quả
            CoachStripService service = new CoachStripService();
            boolean result = service.addNewTrip(trip);
            System.out.print(result);
            if (result) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Thêm chuyến đi thành công");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo");
                alert.setHeaderText(null);
                alert.setContentText("Thêm chuyến đi thất bại");
                alert.showAndWait();
            }

        } catch (DateTimeParseException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Định dạng ngày tháng không hợp lệ. Vui lòng nhập lại theo định dạng yyyy-MM-dd HH:mm:ss.");
            alert.showAndWait();
            return;
        }

    }

    @FXML
    public void addStripButtonOnAction(ActionEvent event) throws SQLException {
        // Kiểm tra các trường dữ liệu phải nhập
        if (stationStartCb.getValue() == null || stationEndCb.getValue() == null
                || distanceTxt.getText().isEmpty() || arrivalTimeTxt.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập đầy đủ thông tin.");
            alert.showAndWait();
            return;
        }

        // Kiểm tra định dạng giờ nhập vào
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        try {
            LocalTime time = LocalTime.parse(arrivalTimeTxt.getText(), formatter);
        } catch (DateTimeParseException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Định dạng giờ không đúng, vui lòng nhập lại theo định dạng HH:mm:ss.");
            alert.showAndWait();
            return;
        }

        // Lấy dữ liệu từ giao diện
        int distance = Integer.parseInt(distanceTxt.getText());
        LocalTime arrivalTime = LocalTime.parse(arrivalTimeTxt.getText());
        int idStationsStart = stationStartCb.getValue();
        int idStationsEnd = stationEndCb.getValue();
        // Kiểm tra điểm bắt đầu và điểm kết thúc có trùng nhau không
        if (idStationsStart == idStationsEnd) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Điểm bắt đầu và điểm kết thúc không thể trùng nhau.");
            alert.showAndWait();
            return;
        }
        // Thêm tuyến đi vào cơ sở dữ liệu
        StripService stripService = new StripService();
        // Kiểm tra xem có tồn tại tuyến đi từ điểm bắt đầu đến điểm kết thúc hay không
        if (stripService.checkExistingStrip(idStationsStart, idStationsEnd)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Đã tồn tại tuyến đi từ điểm bắt đầu đến điểm kết thúc này.");
            alert.showAndWait();
            return;
        }
        int result = stripService.addNewStrips(distance, arrivalTime, idStationsStart, idStationsEnd);
        if (result == 1) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Thêm tuyến đi thành công.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Thêm tuyến đi thất bại.");
            alert.showAndWait();
        }
    }

    public void addCoachButtonOnAction(ActionEvent e) throws SQLException {
        if (numberCoachTxt.getText().isEmpty() || capacityTxt.getText().isEmpty() || typeofCoachCb.getValue() == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập đầy đủ thông tin.");
            alert.showAndWait();
            return;
        }

        // Lấy dữ liệu từ giao diện
        int numberCoach = Integer.parseInt(numberCoachTxt.getText());
        int capacity = Integer.parseInt(capacityTxt.getText());
        String selectedtypeCoach = typeofCoachCb.getValue();

        if (capacity < 10 || capacity > 50) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Sức chứa phải nằm trong khoảng từ 10 đến 50.");
            alert.showAndWait();
            return;
        }
        CoachService coachService = new CoachService();
        if (coachService.checkCoachExists(numberCoach)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Số xe đã tồn tại.");
            alert.showAndWait();
            return;
        }
        int result = coachService.addNewCoach(numberCoach, capacity, selectedtypeCoach);
        if (result == 1) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Thêm xe thành công.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Thêm xe thất bại.");
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (Login.loginStaff != null) {
            System.out.println("co lay ten nhan vien");
            nameSatff.setText(Login.loginStaff.getNameStaff());
        }
        // Thêm các vai trò vào ComboBox
        roleComboBox.getItems().addAll("Nhân Viên", "Tài xế");

        // Đặt giá trị mặc định cho ComboBox là "Nhân Viên"
        roleComboBox.setValue("");

        //Lấy thông tin tuyến đi từ cơ sở dữ liệu để render lên comboBox
        CoachStripService sv = new CoachStripService();
        coachStripsComboBox.setOnMouseClicked(event -> {
            coachStripsComboBox.getItems().clear();
            List<Integer> coachStripIds = sv.getCoachStripIds();
            coachStripsComboBox.getItems().addAll(coachStripIds);
            coachStripsComboBox.setValue(null);
        });
        //Lấy thông tin của tuyến đi
        coachStripsComboBox.setOnAction(event -> {
            int selectedCoachStripId = coachStripsComboBox.getSelectionModel().getSelectedItem();
            // Lấy thông tin chuyến đi
            CoachStripService service = new CoachStripService();
            String tripInfo = service.getCoachStripInfo(selectedCoachStripId);

            // Hiển thị thông tin chuyến đi trên giao diện
            coachStripInfoLabel.setText(tripInfo);
        });

        stationStartCb.getItems().addAll(1, 2, 3, 4);
        stationStartCb.setValue(null);

        stationEndCb.getItems().addAll(1, 2, 3, 4);
        stationEndCb.setValue(null);

        typeofCoachCb.getItems().addAll("Xe Giường Nằm", "Xe Khách");
        typeofCoachCb.setValue("Xe Khách");
        coachStripsComboBox.setValue(1);
        staffTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int idStaff = Integer.parseInt(newValue);
                CoachStripService service = new CoachStripService();
                Staff staff = service.getStaffById(idStaff);
                if (staff != null) {
                    String name = staff.getNameStaff();
                    String phone = staff.getPhone();
                    String address = staff.getAddressUser();
                    staffLabel.setText("Tên nhân viên: " + name);
                    phoneLabel.setText("Số điện thoại: " + phone);
                    addressLabel.setText("Địa chỉ: " + address);
                } else {
                    staffLabel.setText("Không tìm thấy nhân viên");
                    phoneLabel.setText("");
                    addressLabel.setText("");
                }
            } catch (NumberFormatException e) {
                staffLabel.setText("");
                phoneLabel.setText("");
                addressLabel.setText("");
            }
        });
        //Lấy thông tin tuyến bắt đầu
        stationStartCb.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                StripService stripService = new StripService();
                Station station = stripService.getStationById(newValue);
                if (station != null) {
                    String name = station.getName();
                    stationStartLb.setText(name);
                } else {
                    stationStartLb.setText("");
                }
            } else {
                stationStartLb.setText("");
            }
        });
        //Lấy thông tin tuyến kết thúc
        stationEndCb.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Lấy thông tin về tên bến xe
            StripService stripService = new StripService();
            Station station = stripService.getStationById(newValue);
            if (station != null) {
                String name = station.getName();
                stationEndLb.setText(name);
            } else {
                stationEndLb.setText("");
            }
        });



        // ràng buộc số điện thoại
        phoneTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                String input = phoneTextField.getText().trim();
                if (!input.matches("^0\\d{9}")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText("Số điện thoại không hợp lệ");
                    alert.setContentText("Số điện thoại bắt đầu từ 0 và chỉ có 10 số");
                    alert.showAndWait();
                    phoneTextField.setText("");
                }
            }
        });
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
