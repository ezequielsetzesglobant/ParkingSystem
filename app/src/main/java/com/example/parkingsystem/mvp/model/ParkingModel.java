package com.example.parkingsystem.mvp.model;

import com.example.parkingsystem.mvp.contract.ParkingContract;

public class ParkingModel implements ParkingContract.Model {

    private int spaces;

    public void setSpaces(int spaces) {
        this.spaces = spaces;
    }

    public int getSpaces() {
        return spaces;
    }
}
