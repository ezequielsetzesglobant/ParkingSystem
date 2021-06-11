package com.example.parkingsystem.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.parkingsystem.databinding.ActivityParkingBinding;
import com.example.parkingsystem.mvp.contract.ParkingContract;
import com.example.parkingsystem.mvp.model.ParkingModel;
import com.example.parkingsystem.mvp.presenter.ParkingPresenter;
import com.example.parkingsystem.mvp.view.ParkingView;

public class ParkingActivity extends AppCompatActivity {

    private ActivityParkingBinding binding;
    private ParkingContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityParkingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new ParkingPresenter(new ParkingModel(), new ParkingView(this, binding));

        binding.buttonMainSetAmountParkingSpaces.setOnClickListener(view -> presenter.onSetParkingPlacesButtonPressed());
    }
}
