/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingCoach.pojo;

/**
 *
 * @author Vy Khoi
 */
import java.time.LocalDateTime;

public class Ticket {
    private int idTicket;
    private int idStationBuy;
    private LocalDateTime bookingDate;
    private int idCustomer;
    private int idStaff;
    private int status;
    private int idCoachStripCoachSeat;

    public Ticket(int idTicket, int idStationBuy, LocalDateTime bookingDate, int idCustomer, int idStaff, int status,
                  int idCoachStripCoachSeat) {
        this.idTicket = idTicket;
        this.idStationBuy = idStationBuy;
        this.bookingDate = bookingDate;
        this.idCustomer = idCustomer;
        this.idStaff = idStaff;
        this.status = status;
        this.idCoachStripCoachSeat = idCoachStripCoachSeat;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public int getIdStationBuy() {
        return idStationBuy;
    }

    public void setIdStationBuy(int idStationBuy) {
        this.idStationBuy = idStationBuy;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(int idStaff) {
        this.idStaff = idStaff;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIdCoachStripCoachSeat() {
        return idCoachStripCoachSeat;
    }

    public void setIdCoachStripCoachSeat(int idCoachStripCoachSeat) {
        this.idCoachStripCoachSeat = idCoachStripCoachSeat;
    }
}
