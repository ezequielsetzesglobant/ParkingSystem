package com.example.parkingsystem.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.parkingsystem.databinding.FragmentSpacesParkingDialogBinding;
import com.example.parkingsystem.listener.ListenerDialogFragment;
import com.example.parkingsystem.mvp.contract.SpacesParkingDialogContract;
import com.example.parkingsystem.mvp.model.SpacesParkingDialogModel;
import com.example.parkingsystem.mvp.presenter.SpacesParkingDialogPresenter;
import com.example.parkingsystem.mvp.view.SpacesParkingDialogView;

public class SpacesParkingDialogFragment extends DialogFragment {

    private static final String LISTENER_KEY = "LISTENER_KEY";

    private FragmentSpacesParkingDialogBinding binding;
    private SpacesParkingDialogContract.DialogPresenter presenter;
    private ListenerDialogFragment listenerDialogFragment;

    public SpacesParkingDialogFragment() {
    }

    public static SpacesParkingDialogFragment newInstance(ListenerDialogFragment listenerDialogFragment) {
        SpacesParkingDialogFragment dialogFragment = new SpacesParkingDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(LISTENER_KEY, listenerDialogFragment);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSpacesParkingDialogBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new SpacesParkingDialogPresenter(new SpacesParkingDialogModel(), new SpacesParkingDialogView(this, binding));
        listenerDialogFragment = (ListenerDialogFragment) getArguments().getSerializable(LISTENER_KEY);
        binding.buttonDialogFragmentClose.setOnClickListener(v -> presenter.notifyActivity(listenerDialogFragment, binding.editTextDialogFragmentAmountSpaces.getText().toString()));
    }
}
