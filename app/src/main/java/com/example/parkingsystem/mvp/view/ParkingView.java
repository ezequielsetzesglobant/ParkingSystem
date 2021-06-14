package com.example.parkingsystem.mvp.view;

import android.app.Activity;
import android.content.Context;

import com.example.parkingsystem.R;
import com.example.parkingsystem.databinding.ActivityParkingBinding;
import com.example.parkingsystem.mvp.contract.ParkingContract;
import com.example.parkingsystem.mvp.view.base.ActivityView;
import com.google.android.material.snackbar.Snackbar;

public class ParkingView extends ActivityView implements ParkingContract.View {

    private final ActivityParkingBinding binding;

    public ParkingView(Activity activity, ActivityParkingBinding binding) {
        super(activity);
        this.binding = binding;
    }

    public void showPopUp() {
        Context context = getContext();
        if (context != null) {
            Snackbar.make(binding.getRoot(), context.getResources().getString(R.string.snack_bar_button_click_message_main_activity), Snackbar.LENGTH_SHORT).show();
        }
    }
}
