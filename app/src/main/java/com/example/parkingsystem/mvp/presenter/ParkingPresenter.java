package com.example.parkingsystem.mvp.presenter;

import com.example.parkingsystem.mvp.contract.ParkingContract;

public class ParkingPresenter implements ParkingContract.Presenter {

    private ParkingContract.Model model;
    private ParkingContract.View view;

    public ParkingPresenter(ParkingContract.Model model, ParkingContract.View view) {
        this.model = model;
        this.view = view;
    }

    public void onSetParkingPlacesButtonPressed() {
        model.setNumber();
        view.showPopUp();
    }
}
