/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingCoach.pojo;

import java.time.LocalDateTime;

/**
 *
 * @author Vy Khoi
 */
public class CoachStripCoachSeat {

    private int idCSCS;
    private int idCoach;
    private int idCoachStrips;
    private double price;
    private LocalDateTime departureTime;
    private int statusSeat;
    private int nameSeat;
    private int idStaff;

    public CoachStripCoachSeat(int idCSCS, int idCoach, int idCoachStrips, double price, LocalDateTime departureTime, int statusSeat, int nameSeat, int idStaff) {
        this.idCSCS = idCSCS;
        this.idCoach = idCoach;
        this.idCoachStrips = idCoachStrips;
        this.price = price;
        this.departureTime = departureTime;
        this.statusSeat = statusSeat;
        this.nameSeat = nameSeat;
        this.idStaff = idStaff;
    }

    public CoachStripCoachSeat() {

    }

    public int getIdCSCS() {
        return idCSCS;
    }

    public void setIdCSCS(int idCSCS) {
        this.idCSCS = idCSCS;
    }

    public int getIdCoach() {
        return idCoach;
    }

    public void setIdCoach(int idCoach) {
        this.idCoach = idCoach;
    }

    public int getIdCoachStrips() {
        return idCoachStrips;
    }

    public void setIdCoachStrips(int idCoachStrips) {
        this.idCoachStrips = idCoachStrips;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public int getStatusSeat() {
        return statusSeat;
    }

    public void setStatusSeat(int statusSeat) {
        this.statusSeat = statusSeat;
    }

    public int getNameSeat() {
        return nameSeat;
    }

    public void setNameSeat(int nameSeat) {
        this.nameSeat = nameSeat;
    }

    public int getIdStaff() {
        return idStaff;
    }

    @Override
    public String toString() {
        return "CoachStripCoachSeat{" + "idCSCS=" + idCSCS + ", idCoach=" + idCoach + ", idCoachStrips=" + idCoachStrips + ", price=" + price + ", departureTime=" + departureTime + ", statusSeat=" + statusSeat + ", nameSeat=" + nameSeat + ", idStaff=" + idStaff + '}';
    }

    public void setIdStaff(int idStaff) {
        this.idStaff = idStaff;
    }
}
