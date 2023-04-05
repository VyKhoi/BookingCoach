/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.datvexe;

import com.bookingCoach.Alias.AliasTicket;
import com.bookingCoach.services.ChangeTicketServices;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            this.loadTableViewSearchId();
        SearchIdRadioButton.setSelected(true);

//        } catch (SQLException ex) {
//            Logger.getLogger(ChangeTicketController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    // this is method to load tableview for search with id
    public void loadTableViewSearchId() throws SQLException, Exception {
        try {

            AliasTicket dt = ctk.getTickets(Integer.parseInt(tSearchIdTicket.getText()));
            if (dt == null) {
                throw new Exception("Không có thông tin");
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

// Tạo TableView và thêm các cột vào TableView
            tableViewSearch.getColumns().addAll(idTicketCol, nameCustomerCol, phoneNumberCol, addressCusCol, bookingDateCol, numberCoachCol, nameStaffCol, departureTimeCol, nameSeatCol, nameStartStationCol, addressStartCol, nameEndStationCol, addressEndCol);

// Thêm đối tượng dt vào TableView
            tableViewSearch.getItems().add(dt);

        }catch(NumberFormatException ex){
             Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Nhập mã vé sai");
//            alert.setContentText();

            alert.showAndWait();
        }
        catch (Exception ex) {
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
            
            if( ctk.getTicketsWithNumberPhone(Integer.parseInt(tSearchIdTicket.getText())).size() == 0)
            {
               throw new Exception("Không có dữ liệu trả về");
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

// Tạo TableView và thêm các cột vào TableView
                if (checkaddCol == false) {
                    tableViewSearch.getColumns().addAll(idTicketCol, nameCustomerCol, phoneNumberCol, addressCusCol, bookingDateCol, numberCoachCol, nameStaffCol, departureTimeCol, nameSeatCol, nameStartStationCol, addressStartCol, nameEndStationCol, addressEndCol);
                    tableViewSearch.getItems().add(h);
                    checkaddCol = true;
                } else {
// Thêm đối tượng dt vào TableView
                    tableViewSearch.getItems().add(h);
                }
            });
           
        }catch(NumberFormatException ex){
             Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Nhập sai");
//            alert.setContentText();

            alert.showAndWait();
        }
        catch (Exception ex) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText(ex.getMessage());
//            alert.setContentText();

            alert.showAndWait();
        }

    }

    public void SearchIdTicket(KeyEvent event) throws SQLException, Exception {

        if (SearchIdRadioButton.isSelected()) {
            this.tableViewSearch.getColumns().clear();
            this.tableViewSearch.getItems().clear();

            if (event.getCode() == KeyCode.ENTER) {
                loadTableViewSearchId();
            }
        }

        if (SearchNumberPhoneRadioButton.isSelected()) {
            this.tableViewSearch.getColumns().clear();
            this.tableViewSearch.getItems().clear();

            if (event.getCode() == KeyCode.ENTER) {
                loadTableViewSearchNumberPhone();
            }
        }
    }

}
