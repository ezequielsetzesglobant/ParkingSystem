package com.example.parkingsystem.mvp.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import com.example.parkingsystem.R;
import com.example.parkingsystem.databinding.ActivityReservationBinding;
import com.example.parkingsystem.mvp.contract.ReservationContract;
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
    public void showDate(String date) {
        Context context = getContext();
        if (context != null) {
            Snackbar.make(binding.getRoot(), context.getResources().getString(R.string.snack_bar_button_click_message_reservation_date_reservation_activity, date), Snackbar.LENGTH_SHORT).show();
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
    public void showTime(String time) {
        Context context = getContext();
        if (context != null) {
            Snackbar.make(binding.getRoot(), context.getResources().getString(R.string.snack_bar_button_click_message_reservation_time_reservation_activity, time), Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void finishActivity() {
        Activity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }
}
