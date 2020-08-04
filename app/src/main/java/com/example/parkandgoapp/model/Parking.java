package com.example.parkandgoapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "parking_table")

public class Parking implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int id;

    @ColumnInfo(name = "buildingNumber")
    private int buildingNumber;

    @ColumnInfo(name = "numberOfHours")
    private int numberOfHours;

    @ColumnInfo(name = "licensePlate")
    private String licensePlate;

    @ColumnInfo(name = "aptNumber")
    private int aptNumber;

    @ColumnInfo(name = "datetime")
    private String datetime;

    @ColumnInfo(name = "charges")
    private int charges;

    public Parking(int buildingNumber, int numberOfHours, String licensePlate,int aptNumber,String datetime,int charges){
        this.buildingNumber = buildingNumber;
        this.numberOfHours = numberOfHours;
        this.licensePlate = licensePlate;
        this.aptNumber = aptNumber;
        this.datetime = datetime;
        this.charges = charges;

    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public int getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(int numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getAptNumber() {
        return aptNumber;
    }

    public void setAptNumber(int aptNumber) {
        this.aptNumber = aptNumber;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }


    @Override
    public String toString() {
        return "Parking{" +
                "id=" + id +
                ",buildingNumber='" + buildingNumber + '\'' +
                ",numberOfHours='" + numberOfHours + '\'' +
                ",licensePlate='" + licensePlate + '\'' +
                ",aptNumber='" + aptNumber + '\'' +
                ",datetime'" + datetime + '\'' +
                "charges'" + charges + '\'' +
                '}';

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCharges() {
        return charges;
    }

    public void setCharges(int charges) {
        this.charges = charges;
    }
}
