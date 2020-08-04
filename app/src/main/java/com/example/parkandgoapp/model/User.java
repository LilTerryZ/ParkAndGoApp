package com.example.parkandgoapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

/**
 * ParkAndGoApp created by nisarg
 * Student ID: 991541369
 * on 2019-11-14
 */
@Entity(tableName = "parkandgo_table")
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int id;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "phoneNumber")
    private String phoneNumber;

    @ColumnInfo(name = "numberPlate")
    private String numberPlate;

    @ColumnInfo(name = "email")
    private String email;


    @ColumnInfo(name = "cardnumber")
    private String cardnumber;

    public User(String username,String password, String phoneNumber,String numberPlate, String email,String cardnumber){
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.numberPlate = numberPlate;
        this.email = email;
        this.cardnumber = cardnumber;

    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {
        return password;
    }
    public void setPassword(String password){this.password= password;}

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}

    public String getEmail() {
        return email;
    }
    public void setEmail(String email){this.email = email;}

    public String getCardnumber(){
        return cardnumber;
    }
    public void setCardnumber(String cardnumber){this.cardnumber = cardnumber;}

    public String getNumberPlate() {return numberPlate;}
    public void setNumberPlate(String numberPlate){this.numberPlate = numberPlate;}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ",username='" +username+ '\'' +
                ",password='" + password+'\'' +
                ",phoneNumber='" +phoneNumber+ '\'' +
                ",cardnumber='" + cardnumber+'\''+
                ",numberPlate='" + numberPlate+'\'' +
                ",email='" + email+'\'' +
                '}';
    }

}
