package com.example.parkingsystem.mvp.model.reservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationInfomationDB {

    private static ReservationInfomationDB reservationInfomation = null;
    private static Map<String, List<Reservation>> reservations;

    private ReservationInfomationDB() {
        reservations = new HashMap<>();
    }

    public static ReservationInfomationDB getInstanceDB() {
        if (reservationInfomation == null) {
            reservationInfomation = new ReservationInfomationDB();
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
}
