package com.example.parkingsystem.mvp.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import com.example.parkingsystem.R;
import com.example.parkingsystem.activity.ReservationActivity;
import com.example.parkingsystem.databinding.ActivityParkingBinding;
import com.example.parkingsystem.fragment.SpacesParkingDialogFragment;
import com.example.parkingsystem.listener.ListenerDialogFragment;
import com.example.parkingsystem.mvp.contract.ParkingContract;
import com.example.parkingsystem.mvp.view.base.ActivityView;
import com.google.android.material.snackbar.Snackbar;

public class ParkingView extends ActivityView implements ParkingContract.View {

    private static final String TAG = "FRAGMENT";

    private final ActivityParkingBinding binding;

    public ParkingView(Activity activity, ActivityParkingBinding binding) {
        super(activity);
        this.binding = binding;
    }

    @Override
    public void showDialog(ListenerDialogFragment listenerDialogFragment) {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            SpacesParkingDialogFragment dialogFragment = SpacesParkingDialogFragment.newInstance(listenerDialogFragment);
            dialogFragment.show(fragmentManager, TAG);
            dialogFragment.setCancelable(false);
        }
    }

    @Override
    public void showPopUp(int spaces) {
        showSnackbar(R.string.snack_bar_button_click_message_main_activity, spaces);
    }

    @Override
    public void openReservationScreen() {
        Activity activity = getActivity();
        if (activity != null) {
            Intent intent = new Intent(activity, ReservationActivity.class);
            activity.startActivity(intent);
        }
    }

    @Override
    public void showAmountOfReservationsReleased(int releasedReservations) {
        showSnackbar(R.string.snack_bar_released_reservations_message_main_activity, releasedReservations);
    }

    private void showSnackbar(int resourceStrings, int parameterValue) {
        Context context = getContext();
        if (context != null) {
            Snackbar.make(binding.getRoot(),
                    context.getResources().getString(resourceStrings, String.valueOf(parameterValue)),
                    Snackbar.LENGTH_SHORT).show();
        }
    }
}
