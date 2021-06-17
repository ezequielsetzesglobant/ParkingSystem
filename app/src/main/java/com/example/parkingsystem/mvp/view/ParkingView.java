package com.example.parkingsystem.mvp.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import com.example.parkingsystem.R;
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

    public void showPopUp(int spaces) {
        Context context = getContext();
        if (context != null) {
            Snackbar.make(binding.getRoot(), context.getResources().getString(R.string.snack_bar_button_click_message_main_activity, String.valueOf(spaces)), Snackbar.LENGTH_SHORT).show();
        }
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
}
