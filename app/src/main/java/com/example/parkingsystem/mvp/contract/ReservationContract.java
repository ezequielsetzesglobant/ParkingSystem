package com.example.parkingsystem.mvp.contract;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import com.example.parkingsystem.mvp.model.reservation.Reservation;

public interface ReservationContract {

    interface ReservationModelContract {
        void saveDate(int year, int month, int dayOfMonth);
        void saveTime(int hour, int minute);
        void saveReservation(String securityCode, String place);
        Reservation getReservation(String place, String securityCode);
        void setStartDateAndTime(boolean startDateAndTime);
        boolean thereWasOverlapOfReservations();
        String getSavedDateAndTime();
    }

    interface ReservationPresenterContract {
        void createDate(DatePickerDialog.OnDateSetListener onDateSetListener, boolean buttonFlag);
        void saveReservationDate(int year, int month, int dayOfMonth, TimePickerDialog.OnTimeSetListener onTimeSetListener);
        void saveReservationTime(int hourOfDay, int minute);
        void saveReservationInformation(String securityCode, String place);
    }

    interface ReservationViewContract {
        void showDatePicker(DatePickerDialog.OnDateSetListener onDateSetListener);
        void showTimePicker(TimePickerDialog.OnTimeSetListener onTimeSetListener);
        void finishActivity(Reservation reservation);
        void showError();
        void showOkDateAndTime(String savedDateAndTime);
        void showOverlapMessage();
    }
}
