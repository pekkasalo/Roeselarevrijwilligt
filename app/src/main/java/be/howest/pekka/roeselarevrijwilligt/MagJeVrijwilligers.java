package be.howest.pekka.roeselarevrijwilligt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class MagJeVrijwilligers extends android.app.Fragment {


    // Code behind the MagJeVrijwilligers layout
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_mag_je_vrijwilligers, container, false);
        setActionbarTitle("MAG JE VRIJWILLIGERS INSCHAKELEN?");
        return myView;
    }

    private void setActionbarTitle(String title)
    {
        getActivity().setTitle(title);
    }


}
