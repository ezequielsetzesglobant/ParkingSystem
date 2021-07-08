package com.example.parkingsystem.mvp.presenter;

import com.example.parkingsystem.listener.ListenerDialogFragment;
import com.example.parkingsystem.mvp.contract.ParkingContract;

public class ParkingPresenter implements ParkingContract.Presenter {

    private ParkingContract.Model model;
    private ParkingContract.View view;

    public ParkingPresenter(ParkingContract.Model model, ParkingContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void inflateDialog(ListenerDialogFragment listenerDialogFragment) {
        view.showDialog(listenerDialogFragment);
    }

    @Override
    public void onSetParkingPlacesButtonPressed(int spaces) {
        model.setSpaces(spaces);
        view.showPopUp(model.getSpaces());
    }

    @Override
    public void onReservationButtonClicked() {
        model.releaseParking();
        view.openReservationScreen();
    }

    @Override
    public void onReleaseParkingButtonClicked() {
        view.showAmountOfReservationsReleased(model.releaseParking());
    }
}
