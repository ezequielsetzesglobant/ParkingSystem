package com.example.parkingsystem.mvp.presenter;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import com.example.parkingsystem.mvp.contract.ReservationContract;
import com.example.parkingsystem.mvp.model.reservation.Reservation;

public class ReservationPresenter implements ReservationContract.ReservationPresenterContract {

    private ReservationContract.ReservationModelContract model;
    private ReservationContract.ReservationViewContract view;

    public ReservationPresenter(ReservationContract.ReservationModelContract model, ReservationContract.ReservationViewContract view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void createDate(DatePickerDialog.OnDateSetListener onDateSetListener, boolean isStartDateAndTime) {
        model.setStartDateAndTime(isStartDateAndTime);
        view.showDatePicker(onDateSetListener);
    }

    @Override
    public void saveReservationDate(int year, int month, int dayOfMonth, TimePickerDialog.OnTimeSetListener onTimeSetListener) {
        model.saveDate(year, month, dayOfMonth);
        view.showTimePicker(onTimeSetListener);
    }

    @Override
    public void saveReservationTime(int hour, int minute) {
        model.saveTime(hour, minute);
        view.showOkDateAndTime();
    }

    @Override
    public void saveReservationInformation(String securityCode, String place) {
        model.saveReservation(securityCode, place);
        Reservation reservation = model.getReservation(place, securityCode);
        if (!model.thereWasOverlapOfReservations()) {
            if (reservation != null) {
                view.finishActivity(reservation);
            } else {
                view.showError();
            }
        } else {
            view.showOverlapMessage();
        }
    }
}
