package com.example.parkingsystem.mvp.contract;

import com.example.parkingsystem.listener.ListenerDialogFragment;

public interface ParkingContract {

    interface Model {
        void setSpaces(int spaces);

        int getSpaces();
    }

    interface Presenter {
        void inflateDialog(ListenerDialogFragment listenerDialogFragment);
        void onSetParkingPlacesButtonPressed(int spaces);
        void onReservationButtonClicked();
    }

    interface View {
        void showDialog(ListenerDialogFragment listenerDialogFragment);
        void showPopUp(int spaces);
        void openReservationScreen();
    }
}
