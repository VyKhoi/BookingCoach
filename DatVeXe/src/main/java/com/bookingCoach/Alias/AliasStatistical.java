/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingCoach.Alias;

/**
 *
 * @author Vy Khoi
 */
public class AliasStatistical {

    String departureTime;
    int idCoachStrips;
    double total_price;
    int idStationsStart;
    int idStationsEnd;
    String locationStart;

    public AliasStatistical(String departureTime, int idCoachStrips, double total_price, int idStationsStart, int idStationsEnd, String locationStart, String locationEnd) {
        this.departureTime = departureTime;
        this.idCoachStrips = idCoachStrips;
        this.total_price = total_price;
        this.idStationsStart = idStationsStart;
        this.idStationsEnd = idStationsEnd;
        this.locationStart = locationStart;
        this.locationEnd = locationEnd;
    }
    String locationEnd;

    public String getDepartureTime() {
        return departureTime;
    }

    @Override
    public String toString() {
        return "AliasStatistical{" + "departureTime=" + departureTime + ", idCoachStrips=" + idCoachStrips + ", total_price=" + total_price + ", idStationsStart=" + idStationsStart + ", idStationsEnd=" + idStationsEnd + ", locationStart=" + locationStart + ", locationEnd=" + locationEnd + '}';
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public int getIdCoachStrips() {
        return idCoachStrips;
    }

    public void setIdCoachStrips(int idCoachStrips) {
        this.idCoachStrips = idCoachStrips;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public int getIdStationsStart() {
        return idStationsStart;
    }

    public void setIdStationsStart(int idStationsStart) {
        this.idStationsStart = idStationsStart;
    }

    public int getIdStationsEnd() {
        return idStationsEnd;
    }

    public void setIdStationsEnd(int idStationsEnd) {
        this.idStationsEnd = idStationsEnd;
    }

    public String getLocationStart() {
        return locationStart;
    }

    public void setLocationStart(String locationStart) {
        this.locationStart = locationStart;
    }

    public String getLocationEnd() {
        return locationEnd;
    }

    public void setLocationEnd(String locationEnd) {
        this.locationEnd = locationEnd;
    }

}
