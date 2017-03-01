package be.howest.pekka.roeselarevrijwilligt;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by peksu on 6.11.2016.
 */

public class Partners extends Fragment {

    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.partners, container, false);
        setActionbarTitle("Partners");
        return myView;
    }

    private void setActionbarTitle(String title)
    {
        getActivity().setTitle(title);
    }
}
