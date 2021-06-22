package com.example.parkingsystem.mvp.contract;

import com.example.parkingsystem.listener.ListenerDialogFragment;

public interface ParkingContract {

    interface Model {
        void setSpaces(int spaces);
        int getSpaces();
    }

    interface Presenter {
        void onSetParkingPlacesButtonPressed(int spaces);
        void inflateDialog(ListenerDialogFragment listenerDialogFragment);
    }

    interface View {
        void showPopUp(int spaces);
        void showDialog(ListenerDialogFragment listenerDialogFragment);
    }
}
