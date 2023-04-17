/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingCoach.pojo;

/**
 *
 * @author Vy Khoi
 */
import java.sql.Date;

public class Coachstrips {
    private int idCoachStrips;
    private int distance;
    private Date arrivalTime;
    private int idStationsStart;
    private int idStationsEnd;

    public Coachstrips(int idCoachStrips, int distance, Date arrivalTime, int idStationsStart, int idStationsEnd) 
    {
        this.idCoachStrips = idCoachStrips;
        this.distance = distance;
        this.arrivalTime = arrivalTime;
        this.idStationsStart = idStationsStart;
        this.idStationsEnd = idStationsEnd;
    }

    // getters and setters

    public int getIdCoachStrips() {
        return idCoachStrips;
    }

    public void setIdCoachStrips(int idCoachStrips) {
        this.idCoachStrips = idCoachStrips;
    }


    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
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
}

