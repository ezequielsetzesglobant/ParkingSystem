package com.example.parkingsystem.mvp.model;

import com.example.parkingsystem.mvp.contract.ReservationContract;
import com.example.parkingsystem.mvp.model.reservation.Reservation;
import com.example.parkingsystem.mvp.model.reservation.ReservationInfomationDB;
import java.util.Calendar;

public class ReservationModel implements ReservationContract.ReservationModelContract {

    private static final int ONE = 1;

    private ReservationInfomationDB reservationInfomationDB;
    private boolean isStartDateAndTime;
    private Calendar startDateAndTime = null;
    private Calendar finishDateAndTime = null;

    @Override
    public void saveDate(int year, int month, int dayOfMonth) {
        if (isStartDateAndTime) {
            startDateAndTime = Calendar.getInstance();
            startDateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            startDateAndTime.set(Calendar.MONTH, month - ONE);
            startDateAndTime.set(Calendar.YEAR, year);
        } else {
            finishDateAndTime = Calendar.getInstance();
            finishDateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            finishDateAndTime.set(Calendar.MONTH, month - ONE);
            finishDateAndTime.set(Calendar.YEAR, year);
        }
    }

    @Override
    public void saveTime(int hour, int minute) {
        if (isStartDateAndTime) {
            startDateAndTime.set(Calendar.HOUR_OF_DAY, hour);
            startDateAndTime.set(Calendar.MINUTE, minute);
        } else {
            finishDateAndTime.set(Calendar.HOUR_OF_DAY, hour);
            finishDateAndTime.set(Calendar.MINUTE, minute);
        }
    }

    @Override
    public void saveReservation(String securityCode, String place) {
        if (startDateAndTime != null && finishDateAndTime != null && !securityCode.isEmpty() && !place.isEmpty()) {
            reservationInfomationDB = ReservationInfomationDB.getInstanceDB();
            Reservation reservation = new Reservation(startDateAndTime, finishDateAndTime, securityCode, place);
            reservationInfomationDB.putReservationDB(reservation);
        }
    }

    @Override
    public Reservation getReservation(String place, String securityCode) {
        if (reservationInfomationDB != null) {
            return reservationInfomationDB.getReservationDB(place, securityCode);
        }
        return null;
    }

    @Override
    public void setStartDateAndTime(boolean startDateAndTime) {
        this.isStartDateAndTime = startDateAndTime;
    }
}
