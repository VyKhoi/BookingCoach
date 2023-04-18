/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.datvexe;

import com.bookingCoach.Alias.AliasStatistical;
import com.bookingCoach.services.Login;
import com.bookingCoach.services.StatisticalServices;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vy Khoi
 */
public class StatisticalGUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
//    piechart nài dùng để hiển thị biểu đồ theo ngày
    @FXML
    private PieChart pieChart = new PieChart();

//    biểu đồ theo tháng
    @FXML
    private PieChart pieChartMonth = new PieChart();

    @FXML
    private Button lbManagerSystem;

    @FXML
    private DatePicker dateTimePicker = new DatePicker();
    @FXML
    private Button buttonOK = new Button();

    private StatisticalServices st = new StatisticalServices();

//    combobox select belong to month
    @FXML
    ComboBox<String> comboBoxMonth = new ComboBox<>();
    //combobox select belong to year
    @FXML
    ComboBox<String> comboBoxYear = new ComboBox<>();

    @FXML
    private Label nameSatff = new Label();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (Login.loginStaff != null) {
            nameSatff.setText(Login.loginStaff.getNameStaff());
        }
//        xửa lý niếu là admin hiện button quản trị
        if (Login.loginStaff != null && "Admin".equals(Login.loginStaff.getRoles())) {
            lbManagerSystem.setVisible(true);
            // Xử lý tại đây
        } else {
            // Nhân viên đăng nhập là nhân viên thông thường
            lbManagerSystem.setVisible(false);
            // Xử lý tại đây
        }

//         thực hiện render các tháng và năm 
        comboBoxMonth.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
        comboBoxYear.getItems().addAll("2022", "2023");
//       đặt giá trị mặt định là tháng hiện tại
        comboBoxMonth.setValue(String.valueOf(java.time.LocalDate.now().getMonthValue()));

        comboBoxYear.setValue("2023");

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

    
//    hàm này để chạy thống kê theo ngày khi lick ok
    public void handleStatistical() throws SQLException {
        // clear dữ liệu cũ
        pieChart.getData().clear();
//        dateTimePicker.setValue(null);

        if (dateTimePicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn ngày ");

            alert.showAndWait();
            return;
        }

        System.out.println(dateTimePicker.getValue().toString());

        ArrayList<AliasStatistical> ds = st.getSumOfCoachsStripOfDay(dateTimePicker.getValue().toString());
        ArrayList<Data> pieChartDataList = new ArrayList<>();

        if (ds.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Không có dữ liệu");

            alert.showAndWait();
            return;
        }

        // TODO
        // Tạo Pie Chart Data cho các giá trị đã tính toán được
        for (int i = 0; i < ds.size(); i++) {
            AliasStatistical stat = ds.get(i);
            pieChartDataList.add(new Data(stat.getLocationStart() + "->" + stat.getLocationEnd() + " lúc: "+stat.getDepartureTimeHHmmss(), stat.getTotal_price()));
        }

// Thêm Pie Chart Data vào Pie Chart
        pieChart.getData().addAll(pieChartDataList);

        for (Data data : pieChart.getData()) {
            data.setName(data.getName() + ": " + String.format("%.00f", data.getPieValue()));
        }

    }

//    hàm này để chạy thống kê theo tháng khi lick ok 
    public void handleStatisticalMonth() throws SQLException {
        // clear dữ liệu cũ
        pieChartMonth.getData().clear();
//        dateTimePicker.setValue(null);

        if (comboBoxMonth.getValue() == null || comboBoxYear.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Vui lòng chọn tháng , năm ");

            alert.showAndWait();
            return;
        }

//        System.out.println(dateTimePicker.getValue().toString());
        int monthSelect = Integer.parseInt(comboBoxMonth.getValue());
        int yearSelect = Integer.parseInt(comboBoxYear.getValue());
        ArrayList<AliasStatistical> ds = st.getSumOfCoachsStripOfMonth(monthSelect, yearSelect);
        ArrayList<Data> pieChartDataList = new ArrayList<>();

        if (ds.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Không có dữ liệu");

            alert.showAndWait();
            return;
        }

        // TODO
        // Tạo Pie Chart Data cho các giá trị đã tính toán được
        for (int i = 0; i < ds.size(); i++) {
            AliasStatistical stat = ds.get(i);
            pieChartDataList.add(new Data(stat.getLocationStart() + " đến " + stat.getLocationEnd(), stat.getTotal_price()));
        }

// Thêm Pie Chart Data vào Pie Chart
        pieChartMonth.getData().addAll(pieChartDataList);

        for (Data data : pieChartMonth.getData()) {
            data.setName(data.getName() + ": " + String.format("%.00f", data.getPieValue()));
        }

    }

//    bộ các hàm xử lý chuyển page
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
