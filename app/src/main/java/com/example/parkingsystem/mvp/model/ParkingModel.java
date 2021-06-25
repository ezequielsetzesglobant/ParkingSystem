package com.example.parkingsystem.mvp.model;

import com.example.parkingsystem.mvp.contract.ParkingContract;

public class ParkingModel implements ParkingContract.Model {

    private int spaces;

    @Override
    public void setSpaces(int spaces) {
        this.spaces = spaces;
    }

    @Override
    public int getSpaces() {
        return spaces;
    }
}
