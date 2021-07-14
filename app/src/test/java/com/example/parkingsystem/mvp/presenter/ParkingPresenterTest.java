package com.example.parkingsystem.mvp.presenter;

import com.example.parkingsystem.listener.ListenerDialogFragment;
import com.example.parkingsystem.mvp.contract.ParkingContract;
import com.example.parkingsystem.mvp.model.ParkingModel;
import com.example.parkingsystem.mvp.model.reservation.Reservation;
import com.example.parkingsystem.mvp.model.reservation.ReservationInformationDB;
import com.example.parkingsystem.mvp.view.ParkingView;
import java.util.Calendar;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class ParkingPresenterTest {

    public static final int FIVE_INT = 5;
    public static final String SECURITY_CODE = "111";
    public static final String PLACE = "20";
    public static final int YEAR_START = 2019;
    public static final int MONTH_START = 3;
    public static final int DAY_OF_MONTH_START = 5;
    public static final int HOUR_START = 4;
    public static final int MINUTE_START = 20;
    public static final int YEAR_FINISH = 2020;
    public static final int MONTH_FINISH = 5;
    public static final int DAY_OF_MONTH_FINISH = 15;
    public static final int HOUR_FINISH = 7;
    public static final int MINUTE_FINISH = 45;
    public static final int ONE_INT = 1;
    private static final int ZERO_INT = 0;

    @Mock
    ListenerDialogFragment listener;
    ParkingContract.View view = mock(ParkingView.class);
    ParkingContract.Model model = new ParkingModel();
    ParkingContract.Presenter presenter = new ParkingPresenter(model, view);
    Reservation reservation = new Reservation(getCalendarDate(YEAR_START, MONTH_START, DAY_OF_MONTH_START, HOUR_START, MINUTE_START),
            getCalendarDate(YEAR_FINISH, MONTH_FINISH, DAY_OF_MONTH_FINISH, HOUR_FINISH, MINUTE_FINISH),
            SECURITY_CODE,
            PLACE);

    @Test
    public void inflateDialogTest() {
        //Execute
        presenter.inflateDialog(listener);
        //Assert
        verify(view).showDialog(listener);
    }

    @Test
    public void onSetParkingPlacesButtonPressedTest() {
        //Execute
        presenter.onSetParkingPlacesButtonPressed(FIVE_INT);
        //Assert
        verify(view).showPopUp(model.getSpaces());
        assertEquals(FIVE_INT, model.getSpaces());
    }

    @Test
    public void onReservationButtonClickedTest() {
        //Setup
        makeReservation();
        //Execute
        presenter.onReservationButtonClicked();
        //Assert
        verify(view).openReservationScreen();
        assertEquals(ZERO_INT, model.releaseParking());
    }

    @Test
    public void onReleaseParkingButtonClickedTest() {
        //Setup
        makeReservation();
        //Execute
        presenter.onReleaseParkingButtonClicked();
        //Assert
        verify(view).showAmountOfReservationsReleased(ONE_INT);
        assertEquals(ZERO_INT, model.releaseParking());
    }

    private void makeReservation() {
        ReservationInformationDB.getInstanceDB().putReservationDB(reservation);
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
}
