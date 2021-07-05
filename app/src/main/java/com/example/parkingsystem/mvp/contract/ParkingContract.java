package com.example.parkingsystem.mvp.contract;

import com.example.parkingsystem.listener.ListenerDialogFragment;

public interface ParkingContract {

    interface Model {
        void setSpaces(int spaces);
        int getSpaces();
        int releaseParking();
    }

    interface Presenter {
        void inflateDialog(ListenerDialogFragment listenerDialogFragment);
        void onSetParkingPlacesButtonPressed(int spaces);
        void onReservationButtonClicked();
        void onReleaseParkingButtonClicked();
    }

    interface View {
        void showDialog(ListenerDialogFragment listenerDialogFragment);
        void showPopUp(int spaces);
        void openReservationScreen();
        void showAmountOfReservationsReleased(int releasedReservations);
    }
}
