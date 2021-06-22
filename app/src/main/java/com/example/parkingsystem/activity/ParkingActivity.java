package com.example.parkingsystem.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.parkingsystem.databinding.ActivityParkingBinding;
import com.example.parkingsystem.listener.ListenerDialogFragment;
import com.example.parkingsystem.mvp.contract.ParkingContract;
import com.example.parkingsystem.mvp.model.ParkingModel;
import com.example.parkingsystem.mvp.presenter.ParkingPresenter;
import com.example.parkingsystem.mvp.view.ParkingView;

public class ParkingActivity extends AppCompatActivity implements ListenerDialogFragment {

    private ActivityParkingBinding binding;
    private ParkingContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityParkingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new ParkingPresenter(new ParkingModel(), new ParkingView(this, binding));

        binding.buttonMainSetAmountParkingSpaces.setOnClickListener(view -> presenter.inflateDialog(this));
    }

    @Override
    public void setAmountSpaces(int spaces) {
        presenter.onSetParkingPlacesButtonPressed(spaces);
    }
}
