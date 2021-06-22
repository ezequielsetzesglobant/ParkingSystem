package com.example.parkingsystem.mvp.contract;

import com.example.parkingsystem.listener.ListenerDialogFragment;

public interface SpacesParkingDialogContract {

    interface DialogModel {
        void checkSpacesInput(String spaces);
        int getSpaces();
    }

    interface DialogPresenter {
        void notifyActivity(ListenerDialogFragment listenerDialogFragment, String spaces);
    }

    interface DialogView {
        void closeDialog(ListenerDialogFragment listenerDialogFragment, int spaces);
    }
}
