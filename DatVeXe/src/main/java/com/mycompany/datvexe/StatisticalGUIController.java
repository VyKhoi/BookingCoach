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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
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
    @FXML
    private PieChart pieChart;
    @FXML
    private Button lbManagerSystem;

    @FXML
    private DatePicker dateTimePicker = new DatePicker();
    @FXML
    private Button buttonOK = new Button();

    private StatisticalServices st = new StatisticalServices();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
            pieChartDataList.add(new Data(stat.getLocationStart() + " đến " + stat.getLocationEnd(), stat.getTotal_price()));
        }

// Thêm Pie Chart Data vào Pie Chart
        pieChart.getData().addAll(pieChartDataList);

        for (Data data : pieChart.getData()) {
            data.setName(data.getName() + ": " + String.format("%.0f", data.getPieValue()));
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
