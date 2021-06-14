package com.example.parkingsystem.mvp.model;

import com.example.parkingsystem.mvp.contract.ParkingContract;

public class ParkingModel implements ParkingContract.Model {

    private int number;

    public void setNumber() {
        this.number = 5;
    }
}
