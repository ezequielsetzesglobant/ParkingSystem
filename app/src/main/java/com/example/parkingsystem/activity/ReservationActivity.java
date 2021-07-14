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
import com.example.parkingsystem.mvp.model.reservation.ReservationInformationDB;
import com.example.parkingsystem.mvp.presenter.ReservationPresenter;
import com.example.parkingsystem.mvp.view.ReservationView;

public class ReservationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private ActivityReservationBinding binding;
    private ReservationContract.ReservationPresenterContract presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReservationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new ReservationPresenter(new ReservationModel(ReservationInformationDB.getInstanceDB()), new ReservationView(this, binding));

        binding.buttonReservationActivityStartDate.setOnClickListener(view -> presenter.createDate(this, true));
        binding.buttonReservationActivityFinishDate.setOnClickListener(view -> presenter.createDate(this, false));

        binding.buttonReservationActivitySave.setOnClickListener(view -> {
            String securityCode = binding.editTextReservationActivitySecurityCode.getText().toString();
            String place = binding.editTextReservationActivityPlace.getText().toString();
            presenter.saveReservationInformation(securityCode, place);
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        presenter.saveReservationDate(year, month, dayOfMonth, this);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        presenter.saveReservationTime(hourOfDay, minute);
    }
}
