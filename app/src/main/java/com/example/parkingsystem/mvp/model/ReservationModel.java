package com.example.parkingsystem.mvp.model;

import com.example.parkingsystem.mvp.contract.ReservationContract;

public class ReservationModel implements ReservationContract.ReservationModelContract {

    private String date;
    private String time;

    @Override
    public void saveDate(String date) {
        this.date = date;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public void saveTime(String time) {
        this.time = time;
    }

    @Override
    public String getTime() {
        return time;
    }
}
