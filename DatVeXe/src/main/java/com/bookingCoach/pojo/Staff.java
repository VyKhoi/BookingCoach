/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingCoach.pojo;

/**
 *
 * @author Vy Khoi
 */
import java.time.LocalDate;

import java.time.LocalDate;

public class Staff {
    private int idStaff;
    private String passWord;
    private String userName;
    private String addressUser;
    private String roles;
    private String nameStaff;
    private int age;
    private String gender;
    private int phone;
    private LocalDate birthStaff;
    
    public Staff(int idStaff, String passWord, String userName, String addressUser, String roles,
                 String nameStaff, int age, String gender, int phone, LocalDate birthStaff) {
        this.idStaff = idStaff;
        this.passWord = passWord;
        this.userName = userName;
        this.addressUser = addressUser;
        this.roles = roles;
        this.nameStaff = nameStaff;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.birthStaff = birthStaff;
    }

    public int getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(int idStaff) {
        this.idStaff = idStaff;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddressUser() {
        return addressUser;
    }

    public void setAddressUser(String addressUser) {
        this.addressUser = addressUser;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNameStaff() {
        return nameStaff;
    }

    public void setNameStaff(String nameStaff) {
        this.nameStaff = nameStaff;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public LocalDate getBirthStaff() {
        return birthStaff;
    }

    public void setBirthStaff(LocalDate birthStaff) {
        this.birthStaff = birthStaff;
    }
}

