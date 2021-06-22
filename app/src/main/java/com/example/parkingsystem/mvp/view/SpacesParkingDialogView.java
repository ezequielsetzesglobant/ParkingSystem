package com.example.parkingsystem.mvp.view;

import com.example.parkingsystem.databinding.FragmentSpacesParkingDialogBinding;
import com.example.parkingsystem.listener.ListenerDialogFragment;
import com.example.parkingsystem.fragment.SpacesParkingDialogFragment;
import com.example.parkingsystem.mvp.contract.SpacesParkingDialogContract;
import com.example.parkingsystem.mvp.view.base.FragmentView;

public class SpacesParkingDialogView extends FragmentView implements SpacesParkingDialogContract.DialogView {

    private final FragmentSpacesParkingDialogBinding binding;

    public SpacesParkingDialogView(SpacesParkingDialogFragment fragment, FragmentSpacesParkingDialogBinding binding) {
        super(fragment);
        this.binding = binding;
    }

    @Override
    public void closeDialog(ListenerDialogFragment listenerDialogFragment, int spaces) {
        SpacesParkingDialogFragment spacesParkingDialogFragment = (SpacesParkingDialogFragment) getFragment();
        if (spacesParkingDialogFragment != null) {
            spacesParkingDialogFragment.dismiss();
            listenerDialogFragment.setAmountSpaces(spaces);
        }
    }
}
