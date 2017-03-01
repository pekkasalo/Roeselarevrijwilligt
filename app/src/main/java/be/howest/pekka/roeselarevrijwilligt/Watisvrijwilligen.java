package be.howest.pekka.roeselarevrijwilligt;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Watisvrijwilligen extends Fragment {


    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Controller for the Was is Vrijwilligen layuout

        myView = inflater.inflate(R.layout.fragment_watisvrijwilligen, container, false);
        setActionbarTitle("Wat is Vrijwilligen");
        return myView;
    }

    private void setActionbarTitle(String title)
    {
        getActivity().setTitle(title);
    }
}
