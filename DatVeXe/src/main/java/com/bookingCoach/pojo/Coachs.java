/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingCoach.pojo;

/**
 *
 * @author Vy Khoi
 */
public class Coachs {
    private int idCoach;
    private int numberCoach;
    private int capacity;
    private String typeOfCoach;
    
    public Coachs(int idCoach, int numberCoach, int capacity, String typeOfCoach) {
        this.idCoach = idCoach;
        this.numberCoach = numberCoach;
        this.capacity = capacity;
        this.typeOfCoach = typeOfCoach;
    }

    public int getIdCoach() {
        return idCoach;
    }

    public void setIdCoach(int idCoach) {
        this.idCoach = idCoach;
    }

    public int getNumberCoach() {
        return numberCoach;
    }

    public void setNumberCoach(int numberCoach) {
        this.numberCoach = numberCoach;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getTypeOfCoach() {
        return typeOfCoach;
    }

    public void setTypeOfCoach(String typeOfCoach) {
        this.typeOfCoach = typeOfCoach;
    }
}

