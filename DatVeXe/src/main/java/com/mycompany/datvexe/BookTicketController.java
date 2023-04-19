/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.datvexe;

import com.bookingCoach.Alias.AliasTicket;
import com.bookingCoach.pojo.CoachStripCoachSeat;
import com.bookingCoach.pojo.Coachs;
import com.bookingCoach.pojo.Coachstrips;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.bookingCoach.services.BookTicKet;
import com.bookingCoach.services.ChangeTicketServices;
import com.bookingCoach.services.JdbcUtils;
import com.bookingCoach.services.Login;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.w3c.dom.Document;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class BookTicketController implements Initializable {

    @FXML
    private Label nameSatff = new Label();
    @FXML
    private Button logoutButton;

//    vé được chuyển qua để thực hiện thay đổi chuyến
    public static AliasTicket ticketChangeCoachStrip = null;

//    public static AliasNhanVe 
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
//    private RequiredFieldValidator validator;
    @FXML
    private Button clearAllButton;
    @FXML
    private Button sellButton;
    @FXML
    private Button bookingticketButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        acceptChangeCoachStrip.setDisable(true);
        cancelChange.setVisible(false);
        if (ticketChangeCoachStrip != null) {
            System.out.println("xuat thong tin ve chuyen " + ticketChangeCoachStrip.toString());
            nameOfCus.setText(ticketChangeCoachStrip.getNameCustomer());
            address.setText(ticketChangeCoachStrip.getAddressCus());
            number.setText(ticketChangeCoachStrip.getPhoneNumber());
            acceptChangeCoachStrip.setDisable(false);
            cancelChange.setVisible(true);
            clearAllButton.setDisable(true);
            sellButton.setDisable(true);
            bookingticketButton.setDisable(true);
        }

        renderStrips();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

// Lấy thời gian hiện tại
        LocalDateTime now = LocalDateTime.now();

        // Định dạng thời gian thành chuỗi
        String formattedDateTime = now.format(formatter);

        // Gán chuỗi vào text của Label
        timeOrder.setText(formattedDateTime);
        // ChangeTicKet
        SearchIdRadioButton.setSelected(true);
//        xửa lý niếu là admin hiện button quản trị
        if (Login.loginStaff != null) {
            nameSatff.setText(Login.loginStaff.getNameStaff());
        }
        if (Login.loginStaff != null && "Admin".equals(Login.loginStaff.getRoles())) {
//         
            lbManagerSystem.setVisible(true);

            // Xử lý tại đây
        } else {
            // Nhân viên đăng nhập là nhân viên thông thường

            lbManagerSystem.setVisible(false);
            // Xử lý tại đây
        }
        // ràng buộc số điện thoại
        number.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                String input = number.getText().trim();
                if (!input.matches("^0\\d{9}")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText("Số điện thoại không hợp lệ");
                    alert.setContentText("Số điện thoại bắt đầu từ 0 và chỉ có 10 số");
                    alert.showAndWait();
                    number.setText("");
                }
            }
        });

    }
    // thuộc tính của đặt vé
    @FXML
    private ComboBox time;

    @FXML
    private ComboBox strips;

    @FXML
    private ComboBox nameSeat;

    @FXML
    private ComboBox typeOfCar;

    @FXML
    private ComboBox numberOfCar;

    @FXML
    private Label timeOrder;

    @FXML
    private TextField nameOfCus;

    @FXML
    private TextField address;

    @FXML
    private TextField number;
    @FXML
    private Button lbManagerSystem;

    @FXML
    private Label price;
    @FXML
    private Button acceptChangeCoachStrip;
    @FXML
    private Button cancelChange;

    // thuộc tính của nhận vé lấy từ changeTicKet
    ChangeTicketServices ctk = new ChangeTicketServices();
    @FXML
    private TableView tableViewSearch = new TableView();
    @FXML
    private TextField tSearchIdTicket = new TextField();
    @FXML
    private RadioButton SearchIdRadioButton = new RadioButton();
    @FXML
    private RadioButton SearchNumberPhoneRadioButton = new RadioButton();

    // phần các label trên giao diện 
    @FXML
    private Label nameCustomerLabel = new Label();
    @FXML
    private Label phoneCustomerLabel = new Label();
    @FXML
    private Label addressCustomerLabel = new Label();
    @FXML
    private Label idTicketLabel = new Label();
    @FXML
    private Label dateBookingLabel = new Label();
    @FXML
    private Label coachNumberLabel = new Label();
    @FXML
    private Label nameStaffLabel = new Label();
    @FXML
    private Label nameStationStartLabel = new Label();
    @FXML
    private Label nameStationEndLabel = new Label();
    @FXML
    private Label departureTimeLabel = new Label();

    @FXML
    ComboBox<Integer> comboBoxSeatOke = new ComboBox<>();

    AliasTicket selectedItem = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void renderStrips() {
        BookTicKet ds = new BookTicKet();
        ObservableList<String> list = FXCollections.observableArrayList(ds.addStrips());
        strips.setItems(list);

    }

    @FXML
    public void onStripsSelected() {
        BookTicKet ds = new BookTicKet();
        int selectedIndex = strips.getSelectionModel().getSelectedIndex() + 1;
        LocalDateTime now = LocalDateTime.now();
        List<CoachStripCoachSeat> listCoachStripCoachSeat = ds.getListCoachStripCanOrder(selectedIndex, now);
        List<LocalDateTime> listTime = new ArrayList<>();
        for (CoachStripCoachSeat coachStripCoachSeat : listCoachStripCoachSeat) {
            listTime.add(coachStripCoachSeat.getDepartureTime());
        }
        ObservableList<LocalDateTime> observableListTime = FXCollections.observableList(listTime);
        time.setItems(observableListTime);

    }

    @FXML
    public void onTimeSelected() {
        BookTicKet ds = new BookTicKet();
        LocalDateTime localDateTime = (LocalDateTime) time.getValue();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime thresholdTime = localDateTime.minusMinutes(60);
        //so sánh giờ sẽ được đặt trước 60 phút và 
        if (time.getValue() instanceof LocalDateTime && (now.isBefore(thresholdTime))) {
            System.out.print("nó vô đây");
            System.out.print(localDateTime);
            List<Integer> listIdCoach = ds.getListIDCoach(localDateTime);
            List<Coachs> ListCoach = ds.getListCoach(listIdCoach);
            List<String> listTypeCar = new ArrayList<>();
            List<Integer> listNumberCoach = new ArrayList<>();
            for (Coachs listCoach : ListCoach) {
                listTypeCar.add(listCoach.getTypeOfCoach());

            }
            ObservableList<String> observableListTime = FXCollections.observableList(listTypeCar);
            typeOfCar.setItems(observableListTime);
            typeOfCar.setValue(null);
//            if(typeOfCar.getValue() instanceof String){
//                for (Coachs listCoach : ListCoach) {
//                if(typeOfCar.getValue().toString().contains(listCoach.getTypeOfCoach()))
//                    listNumberCoach.add(listCoach.getNumberCoach());
//            }
//            ObservableList<Integer> observablelistNumberCoach = FXCollections.observableList(listNumberCoach);
//            numberOfCar.getItems().setAll(observablelistNumberCoach);
//            }
            typeOfCar.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    List<Integer> listNumberCoach = new ArrayList<>();
                    for (Coachs listCoach : ListCoach) {
                        if (newValue != null && newValue.contains(listCoach.getTypeOfCoach())) {
                            listNumberCoach.add(listCoach.getNumberCoach());
                        }
                    }
                    ObservableList<Integer> observablelistNumberCoach = FXCollections.observableList(listNumberCoach);
                    numberOfCar.getItems().setAll(observablelistNumberCoach);
                }
            });

        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Phải đặt vé trước 60 phút kể từ khi xe bắt đầu chạy!");
            alert.showAndWait();
        }

    }

    @FXML
    public void renderNameSeat() {
        BookTicKet ds = new BookTicKet();
        int idCoach = Integer.parseInt(numberOfCar.getValue().toString());
        double prices = ds.getPrice(idCoach);
        int intPrices = (int) prices;
        String pricesString = Integer.toString(intPrices);
        price.setText(pricesString);
        int idCoachstrip = strips.getSelectionModel().getSelectedIndex() + 1;
        LocalDateTime localDateTime = (LocalDateTime) time.getValue();
        List<Integer> listNameSeat = ds.getListNameSeat(idCoach, idCoachstrip, localDateTime);
        ObservableList<Integer> observablelistNumberSeat = FXCollections.observableList(listNameSeat);
        nameSeat.getItems().setAll(observablelistNumberSeat);
    }

    @FXML
    public void orderTicKet() {
        Alert alert = new Alert(AlertType.INFORMATION);
        if (strips.getSelectionModel().isEmpty()) {

            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn tuyến xe.");
            alert.showAndWait();
            return;
        } else if (time.getSelectionModel().isEmpty()) {
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn thời gian khởi hành.");
            alert.showAndWait();
            return;
        } else if (typeOfCar.getSelectionModel().isEmpty()) {
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn loại xe.");
            alert.showAndWait();
            return;
        } else if (numberOfCar.getSelectionModel().isEmpty()) {
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn số xe.");
            alert.showAndWait();
            return;
        } else if (nameSeat.getSelectionModel().isEmpty()) {
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn số ghế.");
            alert.showAndWait();
            return;
        } else if (nameOfCus.getText().isEmpty()) {
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập tên khách hàng.");
            alert.showAndWait();
            return;
        } else if (address.getText().isEmpty()) {
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập địa chỉ.");
            alert.showAndWait();
            return;
        } else if (number.getText().isEmpty()) {
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập số điện thoại.");
            alert.showAndWait();
            return;
        }

        try {
            System.out.println("orderTIcKet da vo day");
            BookTicKet ds = new BookTicKet();
            int idCoach = Integer.parseInt(numberOfCar.getValue().toString());
            int idCoachstrip = strips.getSelectionModel().getSelectedIndex() + 1;
            LocalDateTime localDateTime = (LocalDateTime) time.getValue();
            int nameSeats = Integer.parseInt(nameSeat.getValue().toString());
            //Lấy được idCSCS
            int idCSCS = ds.getIdCSCS(idCoach, idCoachstrip, localDateTime, nameSeats);

            String ten = nameOfCus.getText();
            String diaChi = address.getText();
            String soDienThoai = number.getText();

            String times = timeOrder.getText();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime timeOrders = LocalDateTime.parse(times, formatter);

            //Lấy được idCus
            int idCus = ds.getIdCus(ten, soDienThoai, diaChi);
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime thresholdTime = localDateTime.minusMinutes(60);
            if (now.isBefore(thresholdTime)) {
                ds.addTicKet(idCus, idCSCS, timeOrders);
                ds.updateStatusSeat(idCSCS);
            } else {
                Alert alert2 = new Alert(AlertType.INFORMATION);
                alert2.setTitle("Thông báo");
                alert2.setHeaderText(null);
                alert2.setContentText("Đặt vé thành công!");
                alert2.showAndWait();
            }

            // Thông báo
            Alert alert2 = new Alert(AlertType.INFORMATION);
            alert2.setTitle("Thông báo");
            alert2.setHeaderText(null);
            alert2.setContentText("Đặt vé thành công!");
            alert2.showAndWait();

//            // add vé
//            System.out.println("========lay idCuss=======");
//            ds.addTicKet(idCus, idCSCS, timeOrders);
//            ds.updateCSCSStatus(idCSCS);
//
//            // Thông báo
//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("Thông báo");
//            alert.setHeaderText(null);
//            alert.setContentText("Đặt vé thành công!");
//            alert.showAndWait();
            handleClearAll();
        } catch (Exception ex) {
            System.out.println("loi ben ham orderTicket" + ex.toString());
        }
    }

    @FXML
    public void changeTicKet() {
        Alert alert = new Alert(AlertType.INFORMATION);

        if (strips.getSelectionModel().isEmpty()) {

            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn tuyến xe.");
            alert.showAndWait();
            return;
        } else if (time.getSelectionModel().isEmpty()) {
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn thời gian khởi hành.");
            alert.showAndWait();
            return;
        } else if (typeOfCar.getSelectionModel().isEmpty()) {
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn loại xe.");
            alert.showAndWait();
            return;
        } else if (numberOfCar.getSelectionModel().isEmpty()) {
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn số xe.");
            alert.showAndWait();
            return;
        } else if (nameSeat.getSelectionModel().isEmpty()) {
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn số ghế.");
            alert.showAndWait();
            return;
        } else if (nameOfCus.getText().isEmpty()) {
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập tên khách hàng.");
            alert.showAndWait();
            return;
        } else if (address.getText().isEmpty()) {
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập địa chỉ.");
            alert.showAndWait();
            return;
        } else if (number.getText().isEmpty()) {
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập số điện thoại.");
            alert.showAndWait();
            return;
        }

        try {
            System.out.println("orderTIcKet da vo day");
            BookTicKet ds = new BookTicKet();
            ChangeTicketServices chticket = new ChangeTicketServices();
            int idCoach = Integer.parseInt(numberOfCar.getValue().toString());
            int idCoachstrip = strips.getSelectionModel().getSelectedIndex() + 1;
            LocalDateTime localDateTime = (LocalDateTime) time.getValue();
            int nameSeats = Integer.parseInt(nameSeat.getValue().toString());
            //Lấy được idCSCS
            int idCSCS = ds.getIdCSCS(idCoach, idCoachstrip, localDateTime, nameSeats);

            String ten = nameOfCus.getText();
            String diaChi = address.getText();
            String soDienThoai = number.getText();

            String times = timeOrder.getText();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime timeOrders = LocalDateTime.parse(times, formatter);

            //Lấy được idCus
            int idCus = ds.getIdCus(ten, soDienThoai, diaChi);
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime thresholdTime = localDateTime.minusMinutes(60);
            if (now.isBefore(thresholdTime)) {
                ds.addTicKet(idCus, idCSCS, timeOrders);
                ds.updateStatusSeat(idCSCS);
                chticket.deleteTicket(ticketChangeCoachStrip.getIdTicket());
                ticketChangeCoachStrip = null;
            } else {
                Alert alert2 = new Alert(AlertType.INFORMATION);
                alert2.setTitle("Thông báo");
                alert2.setHeaderText(null);
                alert2.setContentText("Đặt vé thất bại!");
                alert2.showAndWait();
            }

            if (ticketChangeCoachStrip == null) {
                acceptChangeCoachStrip.setDisable(true);
                nameOfCus.setText("");
                address.setText("");
                number.setText("");
            }

            // Thông báo
            Alert alert2 = new Alert(AlertType.INFORMATION);
            alert2.setTitle("Thông báo");
            alert2.setHeaderText(null);
            alert2.setContentText("Đặt vé thành công!");
            alert2.showAndWait();

        } catch (Exception ex) {
            System.out.println("loi ben ham orderTicket" + ex.toString());
        }
    }

    @FXML
    private void handleClearAll() {
        time.getSelectionModel().clearSelection();
        strips.getSelectionModel().clearSelection();
        nameSeat.getSelectionModel().clearSelection();
        typeOfCar.getSelectionModel().clearSelection();
        numberOfCar.getSelectionModel().clearSelection();
        nameOfCus.clear();
        address.clear();
        number.clear();
        price.setText("");
    }

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

    public void switchSystemManager(ActionEvent e) throws IOException {
        Node node = (Node) e.getSource();
        Stage currentStage = (Stage) node.getScene().getWindow();

        // Load trang mới
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerSystem.fxml"));
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

    private void printTicket(int idTicket) {

        try {
            File file = new File("B:\\PrintTicket/ticket.txt");

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);

            String idTicKet = Integer.toString(idTicket);
            try ( FileWriter writer = new FileWriter(file)) {
                writer.write("Mã vé: " + idTicKet + "\n");

                writer.write("Họ và tên: " + nameOfCus.getText() + "\n");
                writer.write("Địa chỉ: " + address.getText() + "\n");
                writer.write("Số điện thoại: " + number.getText() + "\n");
                writer.write("Số xe: " + numberOfCar.getValue().toString() + "\n");
                writer.write("Tuyến: " + strips.getValue().toString() + "\n");
                writer.write("Số ghế: " + nameSeat.getValue().toString() + "\n");
                writer.write("Thời gian xuất phát: " + time.getValue().toString() + "\n");
                writer.write("Giá vé: " + price.getText() + "VNĐ" + "\n");
                writer.write("Nhân viên: " + Login.loginStaff.getNameStaff() + "\n");
                writer.write("Ngày in: " + formattedDateTime + "\n");
            }
            System.out.println("Ticket saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printTicketByReceive(int IdTicket) {
        try {
            File file = new File("B:\\PrintTicket/ticket.txt");
            String idTicket = Integer.toString(IdTicket);

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);

            try ( FileWriter writer = new FileWriter(file)) {
                writer.write("Mã vé: " + idTicket + "\n");

                writer.write("Họ và tên: " + nameCustomerLabel.getText() + "\n");
                writer.write("Địa chỉ: " + addressCustomerLabel.getText() + "\n");
                writer.write("Số điện thoại: " + phoneCustomerLabel.getText() + "\n");
                writer.write("Số xe: " + coachNumberLabel.getText() + "\n");
                writer.write("Tuyến: " + nameStationStartLabel.getText() + " - " + nameStationEndLabel.getText() + "\n");

                writer.write("Giá vé: " + price.getText() + "VNĐ" + "\n");

                writer.write("Thời gian xuất phát: " + departureTimeLabel.getText() + "\n");
                writer.write("Nhân viên: " + Login.loginStaff.getNameStaff() + "\n");
                writer.write("Ngày in: " + formattedDateTime + "\n");
            }
            System.out.println("Ticket saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sellTicKet() {
        Alert alert = new Alert(AlertType.INFORMATION);
        if (strips.getSelectionModel().isEmpty()) {

            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn tuyến xe.");
            alert.showAndWait();
            return;
        } else if (time.getSelectionModel().isEmpty()) {
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn thời gian khởi hành.");
            alert.showAndWait();
            return;
        } else if (typeOfCar.getSelectionModel().isEmpty()) {
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn loại xe.");
            alert.showAndWait();
            return;
        } else if (numberOfCar.getSelectionModel().isEmpty()) {
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn số xe.");
            alert.showAndWait();
            return;
        } else if (nameSeat.getSelectionModel().isEmpty()) {
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng chọn số ghế.");
            alert.showAndWait();
            return;
        } else if (nameOfCus.getText().isEmpty()) {
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập tên khách hàng.");
            alert.showAndWait();
            return;
        } else if (address.getText().isEmpty()) {
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập địa chỉ.");
            alert.showAndWait();
            return;
        } else if (number.getText().isEmpty()) {
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập số điện thoại.");
            alert.showAndWait();
            return;
        }

        try {
            System.out.println("orderTIcKet da vo day");
            BookTicKet ds = new BookTicKet();
            int idCoach = Integer.parseInt(numberOfCar.getValue().toString());
            int idCoachstrip = strips.getSelectionModel().getSelectedIndex() + 1;
            LocalDateTime localDateTime = (LocalDateTime) time.getValue();
            int nameSeats = Integer.parseInt(nameSeat.getValue().toString());
            //Lấy được idCSCS
            int idCSCS = ds.getIdCSCS(idCoach, idCoachstrip, localDateTime, nameSeats);
            System.out.print("Mã chuyến");
            System.out.print(idCSCS);

            String ten = nameOfCus.getText();
            String diaChi = address.getText();
            String soDienThoai = number.getText();

            String times = timeOrder.getText();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime timeOrders = LocalDateTime.parse(times, formatter);

            //Lấy được idCus
            int idCus = ds.getIdCus(ten, soDienThoai, diaChi);
            // so sánh giờ bán vé phải trước 5 phút so với giờ khởi hành
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime thresholdTime = localDateTime.minusMinutes(5);
            if (now.isBefore(thresholdTime)) {
                ds.sellTicKet(idCus, idCSCS, timeOrders);
                ds.updateStatusSeat(idCSCS);

            } else {
                Alert alert2 = new Alert(AlertType.INFORMATION);
                alert2.setTitle("Thông báo");
                alert2.setHeaderText(null);
                alert2.setContentText("Chỉ được bán vé trước 5 phút kể từ khi xe bắt đầu chạy!");
                alert2.showAndWait();
            }
            int idTicket = ds.getIdTicket(idCSCS);
            printTicket(idTicket);
            // Thông báo
            Alert alert2 = new Alert(AlertType.INFORMATION);
            alert2.setTitle("Thông báo");
            alert2.setHeaderText(null);
            alert2.setContentText("Bán vé thành công!");
            alert2.showAndWait();

            handleClearAll();
        } catch (Exception ex) {
            System.out.println("loi ben ham orderTicket" + ex.toString());
        }
    }

    public void receiveTicket() throws URISyntaxException {

        if ("".equals(idTicketLabel.getText())) {
            Alert alert2 = new Alert(AlertType.INFORMATION);
            alert2.setTitle("Thông báo");
            alert2.setHeaderText(null);
            alert2.setContentText("Chưa nhập thông tin, không thể nhận vé");
            alert2.showAndWait();
        } else {
            int idTicket = Integer.parseInt(idTicketLabel.getText());
            String mySQL = "UPDATE bus.ticket SET status = 1 WHERE idTicket = ?";
            try {
                Connection conn = JdbcUtils.getConn();
                PreparedStatement statement = conn.prepareStatement(mySQL);
                statement.setInt(1, idTicket);
                int rowsUpdated = statement.executeUpdate();
                if (!"Đã nhận".equals(selectedItem.getStatusTicket()) && rowsUpdated > 0) {
                    printTicketByReceive(idTicket);
                    Alert alert2 = new Alert(AlertType.INFORMATION);
                    alert2.setTitle("Thông báo");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Nhận vé thành công");
                    alert2.showAndWait();
                } else {
                    Alert alert2 = new Alert(AlertType.INFORMATION);
                    alert2.setTitle("Thông báo");
                    alert2.setHeaderText(null);
                    alert2.setContentText("Vé đã nhận rồi nên không nhận nữa");
                    alert2.showAndWait();
                }

                statement.close();
                conn.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

    }

    // hàm xử lí của changeTicKet
    public void loadTableViewSearchId() throws SQLException, Exception {
        try {
            System.out.println("dit me vo day");
            AliasTicket dt = ctk.getTickets(Integer.parseInt(tSearchIdTicket.getText()));
            if (dt == null) {
                throw new Exception("Không có vcl thông tin");
            }
            // Khai báo các cột cho TableView
            TableColumn<AliasTicket, Integer> idTicketCol = new TableColumn<>("Mã vé");
            TableColumn<AliasTicket, String> nameCustomerCol = new TableColumn<>("Tên khách hàng");
            TableColumn<AliasTicket, String> phoneNumberCol = new TableColumn<>("Số điện thoại");
            TableColumn<AliasTicket, String> addressCusCol = new TableColumn<>("Địa chỉ");
            TableColumn<AliasTicket, Date> bookingDateCol = new TableColumn<>("Ngày đặt vé");
            TableColumn<AliasTicket, Integer> numberCoachCol = new TableColumn<>("Số xe");
            TableColumn<AliasTicket, String> nameStaffCol = new TableColumn<>("Tên nhân viên");
            TableColumn<AliasTicket, Date> departureTimeCol = new TableColumn<>("Giờ xuất phát");
            TableColumn<AliasTicket, String> nameSeatCol = new TableColumn<>("Tên ghế");

            TableColumn<AliasTicket, String> nameStartStationCol = new TableColumn<>("Tên điểm đầu");
            TableColumn<AliasTicket, String> addressStartCol = new TableColumn<>("Địa chỉ điểm đầu");

            TableColumn<AliasTicket, String> nameEndStationCol = new TableColumn<>("Tên điểm cuối");
            TableColumn<AliasTicket, String> addressEndCol = new TableColumn<>("Địa chỉ điểm cuối");
            TableColumn<AliasTicket, Integer> statusTicket = new TableColumn<>("Trạng thái");

// Đặt giá trị cho các cột
            idTicketCol.setCellValueFactory(new PropertyValueFactory<>("idTicket"));
            nameCustomerCol.setCellValueFactory(new PropertyValueFactory<>("nameCustomer"));
            phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            addressCusCol.setCellValueFactory(new PropertyValueFactory<>("addressCus"));
            bookingDateCol.setCellValueFactory(new PropertyValueFactory<>("bookingDate"));
            numberCoachCol.setCellValueFactory(new PropertyValueFactory<>("numberCoach"));
            nameStaffCol.setCellValueFactory(new PropertyValueFactory<>("nameStaff"));
            departureTimeCol.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
            nameSeatCol.setCellValueFactory(new PropertyValueFactory<>("nameSeat"));
//      
            nameStartStationCol.setCellValueFactory(new PropertyValueFactory<>("nameStartStation"));
            addressStartCol.setCellValueFactory(new PropertyValueFactory<>("addressStart"));

            nameEndStationCol.setCellValueFactory(new PropertyValueFactory<>("nameEndStation"));
            addressEndCol.setCellValueFactory(new PropertyValueFactory<>("addressEnd"));
            statusTicket.setCellValueFactory(new PropertyValueFactory<>("statusTicket"));

// Tạo TableView và thêm các cột vào TableView
            tableViewSearch.getColumns().addAll(idTicketCol, nameCustomerCol, phoneNumberCol, addressCusCol, bookingDateCol, numberCoachCol, nameStaffCol, departureTimeCol, nameSeatCol, nameStartStationCol, addressStartCol, nameEndStationCol, addressEndCol, statusTicket);

// Thêm đối tượng dt vào TableView
            tableViewSearch.getItems().add(dt);

            tableViewSearch.setOnMouseClicked(e -> {
                if (e.getClickCount() == 1) { // kiểm tra xem người dùng click một lần hay không
                    selectedItem = (AliasTicket) tableViewSearch.getSelectionModel().getSelectedItem();
                    if (selectedItem != null) {
                        nameCustomerLabel.setText(selectedItem.getNameCustomer());
                        phoneCustomerLabel.setText(selectedItem.getPhoneNumber());
                        addressCustomerLabel.setText(selectedItem.getAddressCus());
                        idTicketLabel.setText(String.valueOf(selectedItem.getIdTicket()));
                        // Chuyển đổi kiểu Date sang String để hiển thị trên Label

                        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                        dateBookingLabel.setText(formatter.format(selectedItem.getBookingDate()));

                        coachNumberLabel.setText(String.valueOf(selectedItem.getNumberCoach()));
                        nameStaffLabel.setText(selectedItem.getNameStaff());
                        nameStationStartLabel.setText(selectedItem.getAddressStart());
                        nameStationEndLabel.setText(selectedItem.getAddressEnd());
                        // Chuyển đổi kiểu Date sang String để hiển thị trên Label
                        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                        departureTimeLabel.setText(timeFormatter.format(selectedItem.getDepartureTime()));
//                        // Xuất giá trị ra console
                        System.out.println("gia tri duoc click là " + selectedItem);
                    }
                    System.out.println("Có vô ìf");

                    try {
                        // hiển thị các ghế còn trống
                        ArrayList<CoachStripCoachSeat> ds = ctk.getEmtySeat(selectedItem.getIdTicket());
                        comboBoxSeatOke.getItems().clear();
                        for (CoachStripCoachSeat seat : ds) {
                            comboBoxSeatOke.getItems().add(seat.getNameSeat());
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(ChangeTicketController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println("Có click");
            });

        } catch (NumberFormatException ex) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Nhập mã vé sai");
//            alert.setContentText();

            alert.showAndWait();
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText(ex.getMessage());
//            alert.setContentText();

            alert.showAndWait();
        }

    }

// this is method to load tableview for search with Number phone customer
    boolean checkaddCol = false;

    public void loadTableViewSearchNumberPhone() {
        checkaddCol = false;
        try {

            System.out.println("có chạy loadTableViewSearchNumberPhone()");

            if (ctk.getTicketsWithNumberPhone(Integer.parseInt(tSearchIdTicket.getText())).isEmpty()) {
                throw new Exception("Không có dữ liệu trả adu về");
            }
            System.out.println("co chay toi day");
            ctk.getTicketsWithNumberPhone(Integer.parseInt(tSearchIdTicket.getText())).forEach(h -> {
                // Khai báo các cột cho TableView
                TableColumn<AliasTicket, Integer> idTicketCol = new TableColumn<>("Mã vé");
                TableColumn<AliasTicket, String> nameCustomerCol = new TableColumn<>("Tên khách hàng");
                TableColumn<AliasTicket, String> phoneNumberCol = new TableColumn<>("Số điện thoại");
                TableColumn<AliasTicket, String> addressCusCol = new TableColumn<>("Địa chỉ");
                TableColumn<AliasTicket, Date> bookingDateCol = new TableColumn<>("Ngày đặt vé");
                TableColumn<AliasTicket, Integer> numberCoachCol = new TableColumn<>("Số xe");
                TableColumn<AliasTicket, String> nameStaffCol = new TableColumn<>("Tên nhân viên");
                TableColumn<AliasTicket, Date> departureTimeCol = new TableColumn<>("Giờ xuất phát");
                TableColumn<AliasTicket, String> nameSeatCol = new TableColumn<>("Tên ghế");

                TableColumn<AliasTicket, String> nameStartStationCol = new TableColumn<>("Tên điểm đầu");
                TableColumn<AliasTicket, String> addressStartCol = new TableColumn<>("Địa chỉ điểm đầu");

                TableColumn<AliasTicket, String> nameEndStationCol = new TableColumn<>("Tên điểm cuối");
                TableColumn<AliasTicket, String> addressEndCol = new TableColumn<>("Địa chỉ điểm cuối");
                TableColumn<AliasTicket, Integer> statusTicket = new TableColumn<>("Trạng thái");

// Đặt giá trị cho các cột
                idTicketCol.setCellValueFactory(new PropertyValueFactory<>("idTicket"));
                nameCustomerCol.setCellValueFactory(new PropertyValueFactory<>("nameCustomer"));
                phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
                addressCusCol.setCellValueFactory(new PropertyValueFactory<>("addressCus"));
                bookingDateCol.setCellValueFactory(new PropertyValueFactory<>("bookingDate"));
                numberCoachCol.setCellValueFactory(new PropertyValueFactory<>("numberCoach"));
                nameStaffCol.setCellValueFactory(new PropertyValueFactory<>("nameStaff"));
                departureTimeCol.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
                nameSeatCol.setCellValueFactory(new PropertyValueFactory<>("nameSeat"));
//      
                nameStartStationCol.setCellValueFactory(new PropertyValueFactory<>("nameStartStation"));
                addressStartCol.setCellValueFactory(new PropertyValueFactory<>("addressStart"));

                nameEndStationCol.setCellValueFactory(new PropertyValueFactory<>("nameEndStation"));
                addressEndCol.setCellValueFactory(new PropertyValueFactory<>("addressEnd"));
                statusTicket.setCellValueFactory(new PropertyValueFactory<>("statusTicket"));

// Tạo TableView và thêm các cột vào TableView
                if (checkaddCol == false) {
                    tableViewSearch.getColumns().addAll(idTicketCol, nameCustomerCol, phoneNumberCol, addressCusCol, bookingDateCol, numberCoachCol, nameStaffCol, departureTimeCol, nameSeatCol, nameStartStationCol, addressStartCol, nameEndStationCol, addressEndCol, statusTicket);
                    tableViewSearch.getItems().add(h);
                    checkaddCol = true;
                } else {
// Thêm đối tượng dt vào TableView
                    tableViewSearch.getItems().add(h);
                }
            });

            tableViewSearch.setOnMouseClicked(e -> {
                if (e.getClickCount() == 1) { // kiểm tra xem người dùng click một lần hay không
                    selectedItem = (AliasTicket) tableViewSearch.getSelectionModel().getSelectedItem();
                    if (selectedItem != null) {
                        nameCustomerLabel.setText(selectedItem.getNameCustomer());
                        phoneCustomerLabel.setText(selectedItem.getPhoneNumber());
                        addressCustomerLabel.setText(selectedItem.getAddressCus());
                        idTicketLabel.setText(String.valueOf(selectedItem.getIdTicket()));
                        // Chuyển đổi kiểu Date sang String để hiển thị trên Label

                        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                        dateBookingLabel.setText(formatter.format(selectedItem.getBookingDate()));

                        coachNumberLabel.setText(String.valueOf(selectedItem.getNumberCoach()));
                        nameStaffLabel.setText(selectedItem.getNameStaff());
                        nameStationStartLabel.setText(selectedItem.getAddressStart());
                        nameStationEndLabel.setText(selectedItem.getAddressEnd());
                        // Chuyển đổi kiểu Date sang String để hiển thị trên Label
                        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                        departureTimeLabel.setText(timeFormatter.format(selectedItem.getDepartureTime()));
//                      

// Xuất giá trị ra console
                        System.out.println("gia tri duoc click là " + selectedItem);
                    }
                    System.out.println("Có vô ìf");
                    try {
                        // hiển thị các ghế còn trống
                        ArrayList<CoachStripCoachSeat> ds = ctk.getEmtySeat(selectedItem.getIdTicket());

                        comboBoxSeatOke.getItems().clear();
                        for (CoachStripCoachSeat seat : ds) {

                            comboBoxSeatOke.getItems().add(seat.getNameSeat());
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(ChangeTicketController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                System.out.println("Có click");

            });

        } catch (NumberFormatException ex) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Nhập sai");
//            alert.setContentText();

            alert.showAndWait();
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText(ex.getMessage());
//            alert.setContentText();

            alert.showAndWait();
        }

    }

    public void SearchIdTicket(KeyEvent event) throws SQLException, Exception {
        selectedItem = null;
        // xóa trắng 
        nameCustomerLabel.setText("");
        phoneCustomerLabel.setText("");
        addressCustomerLabel.setText("");
        idTicketLabel.setText("");
        dateBookingLabel.setText("");
        coachNumberLabel.setText("");
        nameStaffLabel.setText("");
        nameStationStartLabel.setText("");
        nameStationEndLabel.setText("");
        departureTimeLabel.setText("");
        comboBoxSeatOke.getItems().clear();
        if (SearchIdRadioButton.isSelected()) {
            this.tableViewSearch.getColumns().clear();
            this.tableViewSearch.getItems().clear();

            if (event.getCode() == KeyCode.ENTER) {
                System.out.println("nó vào key enter search id");
                loadTableViewSearchId();
            }
        }

        if (SearchNumberPhoneRadioButton.isSelected()) {
            this.tableViewSearch.getColumns().clear();
            this.tableViewSearch.getItems().clear();

            if (event.getCode() == KeyCode.ENTER) {
                System.out.println("nó vào key enter search numbetphone");
                loadTableViewSearchNumberPhone();
            }
        }
    }

    public void deleteTicket() throws SQLException {
        if (selectedItem != null) {
            if (ctk.deleteTicket(selectedItem.getIdTicket()) == 1) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Hủy thành công");
//            alert.setContentText();

                alert.showAndWait();
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Vé đã nhận, không thể hủy");
//            alert.setContentText();

                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText("lỗi");
//            alert.setContentText();

            alert.showAndWait();
        }

    }

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

    public void saveChangeSeatTicket() throws ParseException, SQLException {

        try {
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            Date date = dateFormat2.parse(departureTimeLabel.getText());
//        System.out.println("bị sai lúc chuyển");

//        System.out.println("Ngày lấy ra được là: " + dateFormat2.format(date));
            // Sử dụng đối tượng DateFormat để chuyển đổi ngược lại thành chuỗi
            String formattedDate = dateFormat.format(date);
// lấy tên ghế
            int selectedSeat = (int) comboBoxSeatOke.getValue();

            System.out.println("lay dc ngay gio "
                    + formattedDate + " ghế " + selectedSeat + " ghế cũ " + selectedItem.getNameSeat());
            int runUpdate = ctk.updateSeatOfTicket(selectedItem, formattedDate, selectedSeat);
            if (runUpdate == 1) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Thông báo");
                alert.setHeaderText("sửa thành công");
                alert.showAndWait();
            } else {
                if (runUpdate == -1) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Vé đã nhận không thể sửa");
                    alert.showAndWait();
                }
                if (runUpdate == 0) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("sửa thất bại do quá 60 phút kể từ xe chạy");
                    alert.showAndWait();
                }

            }

        } catch (Exception ex) {
            System.out.println("loi xuat ra : " + ex.toString());
        }

//        ctk.updateSeatOfTicket(selectedItem, formattedDate, selectedSeat);
    }

    public void cancelChangeTicket() {
        handleClearAll();
        ticketChangeCoachStrip = null;
        acceptChangeCoachStrip.setDisable(true);
        cancelChange.setVisible(false);
          clearAllButton.setDisable(false);
            sellButton.setDisable(false);
            bookingticketButton.setDisable(false);
    }
//    bộ 3 hàm chuyển page 
}
