/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.datvexe;

import com.bookingCoach.Alias.AliasTicket;
import com.bookingCoach.pojo.CoachStripCoachSeat;
import com.bookingCoach.pojo.Role;
import com.bookingCoach.services.ChangeTicketServices;
import com.bookingCoach.services.Login;
import java.io.IOException;
import java.net.URL;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import javafx.event.EventHandler;
import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vy Khoi
 */
public class ChangeTicketController implements Initializable {

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

    @FXML
    private Button logoutButton;
    @FXML
    private Button lbManagerSystem;
    // selectedItem đây là alias dduwojcnajp khi mình lick trên dòng
    AliasTicket selectedItem = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @FXML
    private Label nameSatff = new Label();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        SearchIdRadioButton.setSelected(true);
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
    }

    // this is method to load tableview for search with id
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
                if (tSearchIdTicket.getText().length() != 10) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Thông báo");
                    alert.setHeaderText("Nhập số điện thoại sai");
//            alert.setContentText();

                    alert.showAndWait();
                    return;
                }
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

        clear();

    }

    public void logoutButtonOnAction(ActionEvent event) throws IOException {
        Login.loginStaff = null; // xóa thông tin đăng nhập của nhân viên
        Node source = (Node) event.getSource();
        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), source);
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
        System.out.println("Vào hàm save");
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
                clear();
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

    public void switchToChangeCoachStrip(ActionEvent e) throws IOException, SQLException {
        if (selectedItem == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText("vui lòng chọn vé để đổi chuyến");
            alert.showAndWait();
            return;
        }
//        check có quuyen doi khong
        int check = ctk.checkCanChange(selectedItem);
        if (check == 0 || check == -1) {
            if (check == 0) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Không được đổi dưới 60 phút xe chạy");
                alert.showAndWait();
                return;
            }
            if (check == -1) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Thông báo");
                alert.setHeaderText("vé đã nhận không được đổi");
                alert.showAndWait();
                return;
            }
        }
        BookTicketController.ticketChangeCoachStrip = selectedItem;

//         thực hiện chuyển page
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

    public void clear() {
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
        this.tableViewSearch.getColumns().clear();
        this.tableViewSearch.getItems().clear();
    }
//    bộ 3 hàm chuyển page s

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
