package com.example.parkingsystem.mvp.model.reservation;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Reservation {

    private static final String FORMAT_DATE = "dd/MM/yyyy hh:mm a";

    private static SimpleDateFormat simpleDateformat = new SimpleDateFormat(FORMAT_DATE);

    private Calendar startDateAndTime;
    private Calendar finishDateAndTime;
    private String securityCode;
    private String place;

    public Reservation(Calendar startDateAndTime, Calendar finishDateAndTime, String securityCode, String place) {
        this.startDateAndTime = startDateAndTime;
        this.finishDateAndTime = finishDateAndTime;
        this.securityCode = securityCode;
        this.place = place;
    }

    public Calendar getStartDateAndTime() {
        return startDateAndTime;
    }

    public void setStartDateAndTime(Calendar startDateAndTime) {
        this.startDateAndTime = startDateAndTime;
    }

    public Calendar getFinishDateAndTime() {
        return finishDateAndTime;
    }

    public void setFinishDateAndTime(Calendar finishDateAndTime) {
        this.finishDateAndTime = finishDateAndTime;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStartDateAndTimeFormated() {
        return simpleDateformat.format(startDateAndTime.getTime());
    }

    public String getFinishDateAndTimeFormated() {
        return simpleDateformat.format(finishDateAndTime.getTime());
    }
}
