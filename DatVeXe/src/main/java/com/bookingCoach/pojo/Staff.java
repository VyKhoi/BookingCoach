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
    private String gender;
    private String phone;
    private LocalDate birthStaff;
    
    public Staff(int idStaff, String passWord, String userName, String addressUser, String roles,
                 String nameStaff, String gender, String phone, LocalDate birthStaff) {
        this.idStaff = idStaff;
        this.passWord = passWord;
        this.userName = userName;
        this.addressUser = addressUser;
        this.roles = roles;
        this.nameStaff = nameStaff;

        this.gender = gender;
        this.phone = phone;
        this.birthStaff = birthStaff;
    }
      public Staff(int idStaff, String nameStaff, String phone, String addressUser) {
        this.idStaff = idStaff;
        this.nameStaff = nameStaff;
        this.phone = phone;
        this.addressUser = addressUser;
    }

    @Override
    public String toString() {
        return "Staff{" + "idStaff=" + idStaff + ", passWord=" + passWord + ", userName=" + userName + ", addressUser=" + addressUser + ", roles=" + roles + ", nameStaff=" + nameStaff +  ", gender=" + gender + ", phone=" + getPhone() + ", birthStaff=" + birthStaff + '}';
    }

    public Staff() {
        
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


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

   

    public LocalDate getBirthStaff() {
        return birthStaff;
    }

    public void setBirthStaff(LocalDate birthStaff) {
        this.birthStaff = birthStaff;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}

