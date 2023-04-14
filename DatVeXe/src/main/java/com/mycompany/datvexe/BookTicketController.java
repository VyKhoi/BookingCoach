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
import com.bookingCoach.services.Login;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
//
//        xửa lý niếu là admin hiện button quản trị
        if (Login.loginStaff != null && "Admin".equals(Login.loginStaff.getRoles())) {
//         
            lbManagerSystem.setVisible(true);
            // Xử lý tại đây
        } else {
            // Nhân viên đăng nhập là nhân viên thông thường

            lbManagerSystem.setVisible(false);
            // Xử lý tại đây
        }
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
    private Button lbManagerSystem;

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
        if (time.getValue() instanceof LocalDateTime) {
            System.out.print("nó vô đây");
            LocalDateTime localDateTime = (LocalDateTime) time.getValue();
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
        try {
            System.out.println("orderTIcKet da vo day");
            BookTicKet ds = new BookTicKet();
            int idCoach = Integer.parseInt(numberOfCar.getValue().toString());
            int idCoachstrip = strips.getSelectionModel().getSelectedIndex() + 1;
            LocalDateTime localDateTime = (LocalDateTime) time.getValue();
            int nameSeats = Integer.parseInt(nameSeat.getValue().toString());
            //Lấy được idCSCS
            int idCSCS = ds.getIdCSCS(idCoach, idCoachstrip, localDateTime, nameSeats);
            System.out.println("========lay id cscs=======" + idCSCS);

            String ten = nameOfCus.getText();
            String diaChi = address.getText();
            String soDienThoai = number.getText();

            String times = timeOrder.getText();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime timeOrders = LocalDateTime.parse(times, formatter);

            //Lấy được idCus
            int idCus = ds.getIdCus(ten, soDienThoai, diaChi);

            System.out.println("id cua customer " + idCus);

            System.out.println("========lay idCuss=======");
            ds.addTicKet(idCus, idCSCS, timeOrders);
        } catch (Exception ex) {
            System.out.println("loi ben ham orderTicket" + ex.toString());
        }
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
}
