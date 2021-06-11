package com.example.parkingsystem.mvp.contract;

public interface ParkingContract {

    interface Model {
        void setNumber();
    }

    interface Presenter {
        void onSetParkingPlacesButtonPressed();
    }

    interface View {
        void showPopUp();
    }
}
