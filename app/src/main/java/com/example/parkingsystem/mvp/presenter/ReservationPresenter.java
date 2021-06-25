package com.example.parkingsystem.mvp.presenter;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import com.example.parkingsystem.mvp.contract.ReservationContract;

public class ReservationPresenter implements ReservationContract.ReservationPresenterContract {

    private ReservationContract.ReservationModelContract model;
    private ReservationContract.ReservationViewContract view;

    public ReservationPresenter(ReservationContract.ReservationModelContract model, ReservationContract.ReservationViewContract view) {
        this.model = model;
        this.view = view;
    }

    public void createDate(DatePickerDialog.OnDateSetListener onDateSetListener) {
        view.showDatePicker(onDateSetListener);
    }

    @Override
    public void saveReservationDate(String date, TimePickerDialog.OnTimeSetListener onTimeSetListener) {
        model.saveDate(date);
        view.showDate(model.getDate());
        view.showTimePicker(onTimeSetListener);
    }

    @Override
    public void saveReservationTime(String time) {
        model.saveTime(time);
        view.showTime(model.getTime());
    }

    @Override
    public void backToMainActivity() {
        view.finishActivity();
    }
}
