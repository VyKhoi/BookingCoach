/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingCoach.pojo;

/**
 *
 * @author Vy Khoi
 */
public class Station {
    private int idStation;
    private String name;
    private String address;
    
    public Station(int idStation, String name, String address) {
        this.idStation = idStation;
        this.name = name;
        this.address = address;
    }

    public Station(String name) {
        this.name = name;
    }
    
    public int getIdStation() {
        return idStation;
    }
    
    public void setIdStation(int idStation) {
        this.idStation = idStation;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
}

