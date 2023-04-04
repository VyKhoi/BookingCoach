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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Vy Khoi
 */
public class ChangeTicketController implements Initializable {

    ChangeTicketServices ctk = new ChangeTicketServices();
    @FXML private TableView tableViewSearch = new TableView();
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.loadTableViewSearchId();
        } catch (SQLException ex) {
            Logger.getLogger(ChangeTicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadTableViewSearchId() throws SQLException {
        AliasTicket dt = ctk.getTickets(1);
       
        
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
      
        tableViewSearch.getColumns().addAll(idTicketCol, nameCustomerCol, phoneNumberCol, addressCusCol, bookingDateCol, numberCoachCol, nameStaffCol, departureTimeCol, nameSeatCol, nameStartStationCol, addressStartCol,  nameEndStationCol, addressEndCol);

// Thêm đối tượng dt vào TableView
        tableViewSearch.getItems().add(dt);

    }

}
