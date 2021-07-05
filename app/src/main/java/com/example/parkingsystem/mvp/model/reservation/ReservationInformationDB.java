package com.example.parkingsystem.mvp.model.reservation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ReservationInformationDB {

    private static final int ZERO_INT = 0;

    private static ReservationInformationDB reservationInfomation = null;
    private static Map<String, List<Reservation>> reservations;

    private ReservationInformationDB() {
        reservations = new HashMap<>();
    }

    public static ReservationInformationDB getInstanceDB() {
        if (reservationInfomation == null) {
            reservationInfomation = new ReservationInformationDB();
        }
        return reservationInfomation;
    }

    public List<Reservation> getReservationsDB(String place) {
        return reservations.get(place);
    }

    public void putReservationDB(Reservation reservation) {
        List<Reservation> reservationsList = reservations.get(reservation.getPlace());
        if (reservationsList != null) {
            reservationsList.add(reservation);
        } else {
            reservationsList = new ArrayList<>();
            reservationsList.add(reservation);
            reservations.put(reservation.getPlace(), reservationsList);
        }
    }

    public Reservation getReservationDB(String place, String securityCode) {
        List<Reservation> reservationList = reservations.get(place);
        if (reservationList != null) {
            for (Reservation res : reservationList) {
                if (res.getSecurityCode().equals(securityCode)) {
                    return res;
                }
            }
        }
        return null;
    }

    public int releasePastReservations() {
        int releasedReservations = ZERO_INT;
        final Calendar calendar = Calendar.getInstance(Locale.getDefault());
        if (!reservations.isEmpty()) {
            for (List<Reservation> reservationsList : reservations.values()) {
                for (int i = ZERO_INT; i < reservationsList.size(); i++) {
                    if (reservationsList.get(i).getFinishDateAndTime().before(calendar)) {
                        reservationsList.remove(i);
                        releasedReservations++;
                    }
                }
            }
        }
        return releasedReservations;
    }
}
