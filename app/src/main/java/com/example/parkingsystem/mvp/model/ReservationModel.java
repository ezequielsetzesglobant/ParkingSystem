package com.example.parkingsystem.mvp.model;

import com.example.parkingsystem.mvp.contract.ReservationContract;
import com.example.parkingsystem.mvp.model.reservation.Reservation;
import com.example.parkingsystem.mvp.model.reservation.ReservationInformationDB;
import java.util.Calendar;
import java.util.List;

public class ReservationModel implements ReservationContract.ReservationModelContract {

    private ReservationInformationDB reservationInformationDB;
    private boolean isStartDateAndTime;
    private Calendar startDateAndTime = null;
    private Calendar finishDateAndTime = null;
    private boolean overlap = false;

    @Override
    public void saveDate(int year, int month, int dayOfMonth) {
        if (isStartDateAndTime) {
            startDateAndTime = Calendar.getInstance();
            startDateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            startDateAndTime.set(Calendar.MONTH, month);
            startDateAndTime.set(Calendar.YEAR, year);
        } else {
            finishDateAndTime = Calendar.getInstance();
            finishDateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            finishDateAndTime.set(Calendar.MONTH, month);
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
            reservationInformationDB = ReservationInformationDB.getInstanceDB();
            Reservation reservation = new Reservation(startDateAndTime, finishDateAndTime, securityCode, place);
            if (!isOverlap(reservation)) {
                reservationInformationDB.putReservationDB(reservation);
            }
        }
    }

    private boolean isOverlap(Reservation reservation) {
        List<Reservation> reservationList = reservationInformationDB.getReservationsDB(reservation.getPlace());
        if (reservationList != null) {
            for (Reservation res : reservationList) {
                if (!(reservation.getStartDateAndTime().after(res.getFinishDateAndTime()) || reservation.getFinishDateAndTime().before(res.getStartDateAndTime()))) {
                    overlap = true;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean thereWasOverlapOfReservations() {
        if (overlap) {
            overlap = false;
            return true;
        }
        return false;
    }

    @Override
    public Reservation getReservation(String place, String securityCode) {
        if (reservationInformationDB != null) {
            return reservationInformationDB.getReservationDB(place, securityCode);
        }
        return null;
    }

    @Override
    public void setStartDateAndTime(boolean startDateAndTime) {
        this.isStartDateAndTime = startDateAndTime;
    }

    @Override
    public String getSavedDateAndTime() {
        Reservation reservation = new Reservation(startDateAndTime, finishDateAndTime, null, null);
        if (isStartDateAndTime) {
            return reservation.getStartDateAndTimeFormated();
        } else {
            return reservation.getFinishDateAndTimeFormated();
        }
    }
}
