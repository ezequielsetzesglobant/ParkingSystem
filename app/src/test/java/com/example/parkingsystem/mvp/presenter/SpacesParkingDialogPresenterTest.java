package com.example.parkingsystem.mvp.presenter;

import com.example.parkingsystem.listener.ListenerDialogFragment;
import com.example.parkingsystem.mvp.contract.SpacesParkingDialogContract;
import com.example.parkingsystem.mvp.model.SpacesParkingDialogModel;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class SpacesParkingDialogPresenterTest {

    public static final String THREE_STRING = "3";
    public static final String EMPTY_STRING = "";
    public static final int THREE_INT = 3;
    public static final int ZERO_INT = 0;

    @Mock
    ListenerDialogFragment listener;
    SpacesParkingDialogContract.DialogView view = mock(SpacesParkingDialogContract.DialogView.class);
    SpacesParkingDialogContract.DialogModel model = new SpacesParkingDialogModel();
    SpacesParkingDialogContract.DialogPresenter presenter = new SpacesParkingDialogPresenter(model, view);

    @Test
    public void notifyActivityWithSpacesTest() {
        //Execute
        presenter.notifyActivity(listener, THREE_STRING);
        //Assert
        verify(view).closeDialog(listener, THREE_INT);
        assertEquals(THREE_INT, model.getSpaces());
    }

    @Test
    public void notifyActivityWithEmptySpacesTest() {
        //Execute
        presenter.notifyActivity(listener, EMPTY_STRING);
        //Assert
        verify(view).closeDialog(listener, ZERO_INT);
        assertEquals(ZERO_INT, model.getSpaces());
    }
}
