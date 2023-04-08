/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingCoach.Alias;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Vy Khoi
 */
public class AliasTicket {
  SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                     
    private int idTicket;
    private String nameCustomer;
    private String phoneNumber;
    private String addressCus;
    private Date bookingDate;
    private int numberCoach;
    private String nameStaff;
    private Date departureTime;
    private String nameSeat;
    private int idCSCS;
    private int idCoachStrips;
    private int idStart;
    private String nameStartStation;
    private String addressStart;
    private int idEnd;
    private String nameEndStation;
    private String addressEnd;

    public AliasTicket(int idTicket, String nameCustomer, String phoneNumber, String addressCus, Date bookingDate, int numberCoach, String nameStaff, Date departureTime, String nameSeat, int idCSCS, int idCoachStrips, int idStart, String nameStartStation, String addressStart, int idEnd, String nameEndStation, String addressEnd, int statusTicket) {
        this.idTicket = idTicket;
        this.nameCustomer = nameCustomer;
        this.phoneNumber = phoneNumber;
        this.addressCus = addressCus;
        this.bookingDate = bookingDate;
        this.numberCoach = numberCoach;
        this.nameStaff = nameStaff;
        this.departureTime = departureTime;
        this.nameSeat = nameSeat;
        this.idCSCS = idCSCS;
        this.idCoachStrips = idCoachStrips;
        this.idStart = idStart;
        this.nameStartStation = nameStartStation;
        this.addressStart = addressStart;
        this.idEnd = idEnd;
        this.nameEndStation = nameEndStation;
        this.addressEnd = addressEnd;
        this.statusTicket = statusTicket;
    }

    private int statusTicket;

//    public int getStatusTicket() {
//        return statusTicket;
//    }

    public String getStatusTicket() {
        String result = "";
        switch (statusTicket) {
            case 1:
                result = "Đã nhận";
                break;
            case 0:
                result = "Chưa nhận";
                break;
            default:
                result = "Trạng thái không hợp lệ";
                break;
        }
        return result;
    }

    public void setStatusTicket(int statusTicket) {
        this.statusTicket = statusTicket;
    }

    public AliasTicket() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressCus() {
        return addressCus;
    }

    public void setAddressCus(String addressCus) {
        this.addressCus = addressCus;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getNumberCoach() {
        return numberCoach;
    }

    public void setNumberCoach(int numberCoach) {
        this.numberCoach = numberCoach;
    }

    public String getNameStaff() {
        return nameStaff;
    }

    public void setNameStaff(String nameStaff) {
        this.nameStaff = nameStaff;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public String getNameSeat() {
        return nameSeat;
    }

    public void setNameSeat(String nameSeat) {
        this.nameSeat = nameSeat;
    }

    public int getIdCSCS() {
        return idCSCS;
    }

    public void setIdCSCS(int idCSCS) {
        this.idCSCS = idCSCS;
    }

    public int getIdCoachStrips() {
        return idCoachStrips;
    }

    public void setIdCoachStrips(int idCoachStrips) {
        this.idCoachStrips = idCoachStrips;
    }

    public int getIdStart() {
        return idStart;
    }

    public void setIdStart(int idStart) {
        this.idStart = idStart;
    }

    public String getNameStartStation() {
        return nameStartStation;
    }

    public void setNameStartStation(String nameStartStation) {
        this.nameStartStation = nameStartStation;
    }

    public String getAddressStart() {
        return addressStart;
    }

    public void setAddressStart(String addressStart) {
        this.addressStart = addressStart;
    }

    public int getIdEnd() {
        return idEnd;
    }

    public void setIdEnd(int idEnd) {
        this.idEnd = idEnd;
    }

    public String getNameEndStation() {
        return nameEndStation;
    }

    public void setNameEndStation(String nameEndStation) {
        this.nameEndStation = nameEndStation;
    }

    public String getAddressEnd() {
        return addressEnd;
    }

    public void setAddressEnd(String addressEnd) {
        this.addressEnd = addressEnd;
    }

    public AliasTicket(int idTicket, String nameCustomer, String phoneNumber, String addressCus, Date bookingDate, int numberCoach, String nameStaff, Date departureTime, String nameSeat, int idCSCS, int idCoachStrips, int idStart, String nameStartStation, String addressStart, int idEnd, String nameEndStation, String addressEnd) {
        this.idTicket = idTicket;
        this.nameCustomer = nameCustomer;
        this.phoneNumber = phoneNumber;
        this.addressCus = addressCus;
        this.bookingDate = bookingDate;
        this.numberCoach = numberCoach;
        this.nameStaff = nameStaff;
        this.departureTime = departureTime;
        this.nameSeat = nameSeat;
        this.idCSCS = idCSCS;
        this.idCoachStrips = idCoachStrips;
        this.idStart = idStart;
        this.nameStartStation = nameStartStation;
        this.addressStart = addressStart;
        this.idEnd = idEnd;
        this.nameEndStation = nameEndStation;
        this.addressEnd = addressEnd;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return "AliasTicket{" + "idTicket=" + idTicket + ", nameCustomer=" + nameCustomer + ", phoneNumber=" + phoneNumber + ", addressCus=" + addressCus + ", bookingDate=" + formatter.format(bookingDate) + ", numberCoach=" + numberCoach + ", nameStaff=" + nameStaff + ", departureTime=" + formatter.format(departureTime) + ", nameSeat=" + nameSeat + ", idCSCS=" + idCSCS + ", idCoachStrips=" + idCoachStrips + ", idStart=" + idStart + ", nameStartStation=" + nameStartStation + ", addressStart=" + addressStart + ", idEnd=" + idEnd + ", nameEndStation=" + nameEndStation + ", addressEnd=" + addressEnd + '}';
    }
}
