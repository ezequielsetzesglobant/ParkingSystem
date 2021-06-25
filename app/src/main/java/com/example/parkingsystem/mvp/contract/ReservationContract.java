package com.example.parkingsystem.mvp.contract;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

public interface ReservationContract {

    interface ReservationModelContract {
        void saveDate(String date);
        String getDate();
        void saveTime(String time);
        String getTime();
    }

    interface ReservationPresenterContract {
        void createDate(DatePickerDialog.OnDateSetListener onDateSetListener);
        void saveReservationDate(String date, TimePickerDialog.OnTimeSetListener onTimeSetListener);
        void saveReservationTime(String time);
        void backToMainActivity();
    }

    interface ReservationViewContract {
        void showDatePicker(DatePickerDialog.OnDateSetListener onDateSetListener);
        void showDate(String date);
        void showTimePicker(TimePickerDialog.OnTimeSetListener onTimeSetListener);
        void showTime(String time);
        void finishActivity();
    }
}
