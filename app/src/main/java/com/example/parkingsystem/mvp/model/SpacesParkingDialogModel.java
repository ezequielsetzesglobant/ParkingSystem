package com.example.parkingsystem.mvp.model;

import com.example.parkingsystem.mvp.contract.SpacesParkingDialogContract;

public class SpacesParkingDialogModel implements SpacesParkingDialogContract.DialogModel {

    private static final int SPACE_DEFAULT = 0;

    private int spaces;

    @Override
    public void checkSpacesInput(String spaces) {
        if (spaces.isEmpty()) {
            this.spaces = SPACE_DEFAULT;
        } else {
            this.spaces = Integer.parseInt(spaces);
        }
    }

    @Override
    public int getSpaces() {
        return spaces;
    }
}
