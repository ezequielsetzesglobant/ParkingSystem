package com.example.parkingsystem.mvp.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.Toast;
import com.example.parkingsystem.R;
import com.example.parkingsystem.databinding.ActivityReservationBinding;
import com.example.parkingsystem.mvp.contract.ReservationContract;
import com.example.parkingsystem.mvp.model.reservation.Reservation;
import com.example.parkingsystem.mvp.view.base.ActivityView;
import com.google.android.material.snackbar.Snackbar;
import java.util.Calendar;
import java.util.Locale;

public class ReservationView extends ActivityView implements ReservationContract.ReservationViewContract {

    private final ActivityReservationBinding binding;

    public ReservationView(Activity activity, ActivityReservationBinding binding) {
        super(activity);
        this.binding = binding;
    }

    @Override
    public void showDatePicker(DatePickerDialog.OnDateSetListener onDateSetListener) {
        final Calendar calendar = Calendar.getInstance(Locale.getDefault());
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        Context context = getContext();
        if (context != null) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), onDateSetListener, year, month, day);
            datePickerDialog.show();
            datePickerDialog.setCancelable(false);
        }
    }

    @Override
    public void showTimePicker(TimePickerDialog.OnTimeSetListener onTimeSetListener) {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        Context context = getContext();
        if (context != null) {
            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), onTimeSetListener, hour, minute, false);
            timePickerDialog.show();
            timePickerDialog.setCancelable(false);
        }
    }

    @Override
    public void finishActivity(Reservation reservation) {
        Activity activity = getActivity();
        if (activity != null) {
            activity.finish();
            Toast.makeText(activity,
                    activity.getResources().getString(R.string.toast_save_reservation_information_message_reservation_activity,
                            reservation.getStartDateAndTimeFormated(),
                            reservation.getFinishDateAndTimeFormated(),
                            reservation.getSecurityCode(),
                            reservation.getPlace()),
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showError() {
        Context context = getContext();
        if (context != null) {
            Snackbar.make(binding.getRoot(),
                    context.getResources().getString(R.string.snack_bar_save_reservation_error_message_reservation_activity),
                    Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showOkDateAndTime() {
        Activity activity = getActivity();
        if (activity != null) {
            Toast.makeText(activity,
                    activity.getResources().getString(R.string.toast_date_and_time_ok_message_reservation_activity),
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void showOverlapMessage() {
        Context context = getContext();
        if (context != null) {
            Snackbar.make(binding.getRoot(),
                    context.getResources().getString(R.string.snack_bar_overlap_message_reservation_activity),
                    Snackbar.LENGTH_SHORT).show();
        }
    }
}
