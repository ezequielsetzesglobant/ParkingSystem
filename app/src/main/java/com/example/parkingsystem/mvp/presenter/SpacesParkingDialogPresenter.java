package com.example.parkingsystem.mvp.presenter;

import com.example.parkingsystem.listener.ListenerDialogFragment;
import com.example.parkingsystem.mvp.contract.SpacesParkingDialogContract;

public class SpacesParkingDialogPresenter implements SpacesParkingDialogContract.DialogPresenter {

    SpacesParkingDialogContract.DialogModel model;
    SpacesParkingDialogContract.DialogView view;

    public SpacesParkingDialogPresenter(SpacesParkingDialogContract.DialogModel model, SpacesParkingDialogContract.DialogView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void notifyActivity(ListenerDialogFragment listenerDialogFragment, String spaces) {
        model.checkSpacesInput(spaces);
        view.closeDialog(listenerDialogFragment, model.getSpaces());
    }
}
