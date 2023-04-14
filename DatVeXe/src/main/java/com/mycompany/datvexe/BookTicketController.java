/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.datvexe;

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
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class BookTicketController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     */
//    private RequiredFieldValidator validator;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        renderStrips();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

// Lấy thời gian hiện tại
        LocalDateTime now = LocalDateTime.now();

        // Định dạng thời gian thành chuỗi
        String formattedDateTime = now.format(formatter);

        // Gán chuỗi vào text của Label
        timeOrder.setText(formattedDateTime);

    }

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
    private Label price;

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
        double prices = ds.getPrice(selectedIndex);
        String pricesString = Double.toString(prices);
        price.setText(pricesString);
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
        Duration duration = Duration.between(localDateTime, now);
        if (time.getValue() instanceof LocalDateTime && duration.toMinutes() < 60) {
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

            System.out.println("id cua customer " + idCus);

            /// kiểm tra tất cả điều đã chọnn
//            Alert alert = new Alert(AlertType.WARNING);
//            alert.setTitle("Thông báo");
//            alert.setHeaderText("Lỗi chỗ bị");
            // add vé
            System.out.println("========lay idCuss=======");
            ds.addTicKet(idCus, idCSCS, timeOrders);
            ds.updateCSCSStatus(idCSCS);

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
    }

}
