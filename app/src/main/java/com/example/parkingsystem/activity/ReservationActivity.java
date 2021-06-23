package com.example.parkingsystem.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import com.example.parkingsystem.databinding.ActivityReservationBinding;
import com.example.parkingsystem.mvp.contract.ReservationContract;
import com.example.parkingsystem.mvp.model.ReservationModel;
import com.example.parkingsystem.mvp.presenter.ReservationPresenter;
import com.example.parkingsystem.mvp.view.ReservationView;

public class ReservationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private static final String SLASH = "/";
    private static final String TWO_POINTS = ":";

    private ActivityReservationBinding binding;
    private ReservationContract.ReservationPresenterContract presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReservationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new ReservationPresenter(new ReservationModel(), new ReservationView(this, binding));

        binding.buttonReservationActivityStartDate.setOnClickListener(view -> presenter.createDate(this));
        binding.buttonReservationActivityFinishDate.setOnClickListener(view -> presenter.createDate(this));
        binding.buttonReservationActivitySave.setOnClickListener(view -> presenter.backToMainActivity());
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + SLASH + month + SLASH + year;
        presenter.saveReservationDate(date, this);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String time = hourOfDay + TWO_POINTS + minute;
        presenter.saveReservationTime(time);
    }
}
