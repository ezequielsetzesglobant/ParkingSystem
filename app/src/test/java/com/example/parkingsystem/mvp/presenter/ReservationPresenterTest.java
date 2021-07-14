package com.example.parkingsystem.mvp.presenter;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import com.example.parkingsystem.mvp.contract.ReservationContract;
import com.example.parkingsystem.mvp.model.ReservationModel;
import com.example.parkingsystem.mvp.model.reservation.Reservation;
import com.example.parkingsystem.mvp.model.reservation.ReservationInformationDB;
import com.example.parkingsystem.mvp.view.ReservationView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class ReservationPresenterTest {

    public static final String SECURITY_CODE = "1234";
    public static final String PLACE = "10";
    public static final String EMPTY_STRING = "";

    public static final int YEAR_START = 2021;
    public static final int MONTH_START = 7;
    public static final int DAY_OF_MONTH_START = 8;
    public static final int HOUR_START = 3;
    public static final int MINUTE_START = 17;

    public static final int YEAR_FINISH = 2022;
    public static final int MONTH_FINISH = 9;
    public static final int DAY_OF_MONTH_FINISH = 10;
    public static final int HOUR_FINISH = 6;
    public static final int MINUTE_FINISH = 25;

    @Mock
    ReservationInformationDB reservationInformationDB;
    @Mock
    TimePickerDialog.OnTimeSetListener listenerTime;
    @Mock
    DatePickerDialog.OnDateSetListener listenerDate;
    ReservationContract.ReservationViewContract view = mock(ReservationView.class);
    ReservationContract.ReservationModelContract model;
    ReservationContract.ReservationPresenterContract presenter;
    Reservation reservation = new Reservation(getCalendarDate(YEAR_START, MONTH_START, DAY_OF_MONTH_START, HOUR_START, MINUTE_START),
            getCalendarDate(YEAR_FINISH, MONTH_FINISH, DAY_OF_MONTH_FINISH, HOUR_FINISH, MINUTE_FINISH),
            SECURITY_CODE,
            PLACE);
    List<Reservation> reservationsToPlace = new ArrayList<>();

    @Before
    public void setup() {
        model = new ReservationModel(reservationInformationDB);
        presenter = new ReservationPresenter(model, view);
    }

    @Test
    public void saveReservationInformationWithReservationOkTest() {
        //Setup
        when(reservationInformationDB.getReservationDB(PLACE, SECURITY_CODE)).thenReturn(reservation);
        //Execute
        presenter.saveReservationInformation(SECURITY_CODE, PLACE);
        //Assert
        verify(view).finishActivity(model.getReservation(PLACE, SECURITY_CODE));
        assertEqualsReservationFields();
    }

    @Test
    public void saveReservationInformationWithIncompleteReservationTest() {
        //Setup
        when(reservationInformationDB.getReservationDB(EMPTY_STRING, EMPTY_STRING)).thenReturn(null);
        //Execute
        presenter.saveReservationInformation(EMPTY_STRING, EMPTY_STRING);
        //Assert
        verify(view).showError();
        assertEquals(null, model.getReservation(EMPTY_STRING, EMPTY_STRING));
    }

    @Test
    public void saveReservationInformationWithOverlapReservationTest() {
        //Setup
        when(reservationInformationDB.getReservationDB(PLACE, SECURITY_CODE)).thenReturn(reservation);
        reservationsToPlace.add(reservation);
        when(reservationInformationDB.getReservationsDB(PLACE)).thenReturn(reservationsToPlace);
        saveReservationDateTime();
        //Execute
        presenter.saveReservationInformation(SECURITY_CODE, PLACE);
        //Assert
        verify(view).showOverlapMessage();
        assertEqualsReservationFields();
    }

    @Test
    public void saveReservationTimeTest() {
        //Setup
        presenter.createDate(listenerDate, true);
        presenter.saveReservationDate(YEAR_START, MONTH_START, DAY_OF_MONTH_START, listenerTime);
        //Execute
        presenter.saveReservationTime(HOUR_START, MINUTE_START);
        //Assert
        verify(view).showOkDateAndTime(model.getSavedDateAndTime());
        Reservation reservation = new Reservation(getCalendarDate(YEAR_START, MONTH_START, DAY_OF_MONTH_START, HOUR_START, MINUTE_START),
                null,
                null,
                null);
        assertEquals(reservation.getStartDateAndTimeFormated(), model.getSavedDateAndTime());
    }

    @Test
    public void saveReservationDateTest() {
        //Setup
        presenter.createDate(listenerDate, true);
        //Execute
        presenter.saveReservationDate(YEAR_START, MONTH_START, DAY_OF_MONTH_START, listenerTime);
        //Assert
        verify(view).showTimePicker(listenerTime);
    }

    @Test
    public void createDateTest() {
        //Execute
        presenter.createDate(listenerDate, true);
        //Assert
        verify(view).showDatePicker(listenerDate);
    }

    private Calendar getCalendarDate(int year, int month, int dayOfMonth, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        return calendar;
    }

    private void saveReservationDateTime() {
        presenter.createDate(listenerDate, true);
        presenter.saveReservationDate(YEAR_START, MONTH_START, DAY_OF_MONTH_START, listenerTime);
        presenter.saveReservationTime(HOUR_START, MINUTE_START);
        presenter.createDate(listenerDate, false);
        presenter.saveReservationDate(YEAR_FINISH, MONTH_FINISH, DAY_OF_MONTH_FINISH, listenerTime);
        presenter.saveReservationTime(HOUR_FINISH, MINUTE_FINISH);
    }

    private void assertEqualsReservationFields() {
        assertEquals(YEAR_START, model.getReservation(PLACE, SECURITY_CODE).getStartDateAndTime().get(Calendar.YEAR));
        assertEquals(MONTH_START, model.getReservation(PLACE, SECURITY_CODE).getStartDateAndTime().get(Calendar.MONTH));
        assertEquals(DAY_OF_MONTH_START, model.getReservation(PLACE, SECURITY_CODE).getStartDateAndTime().get(Calendar.DAY_OF_MONTH));
        assertEquals(HOUR_START, model.getReservation(PLACE, SECURITY_CODE).getStartDateAndTime().get(Calendar.HOUR));
        assertEquals(MINUTE_START, model.getReservation(PLACE, SECURITY_CODE).getStartDateAndTime().get(Calendar.MINUTE));
        assertEquals(YEAR_FINISH, model.getReservation(PLACE, SECURITY_CODE).getFinishDateAndTime().get(Calendar.YEAR));
        assertEquals(MONTH_FINISH, model.getReservation(PLACE, SECURITY_CODE).getFinishDateAndTime().get(Calendar.MONTH));
        assertEquals(DAY_OF_MONTH_FINISH, model.getReservation(PLACE, SECURITY_CODE).getFinishDateAndTime().get(Calendar.DAY_OF_MONTH));
        assertEquals(HOUR_FINISH, model.getReservation(PLACE, SECURITY_CODE).getFinishDateAndTime().get(Calendar.HOUR));
        assertEquals(MINUTE_FINISH, model.getReservation(PLACE, SECURITY_CODE).getFinishDateAndTime().get(Calendar.MINUTE));
        assertEquals(SECURITY_CODE, model.getReservation(PLACE, SECURITY_CODE).getSecurityCode());
        assertEquals(PLACE, model.getReservation(PLACE, SECURITY_CODE).getPlace());
    }
}
