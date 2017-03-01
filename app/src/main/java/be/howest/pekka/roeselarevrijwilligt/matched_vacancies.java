package be.howest.pekka.roeselarevrijwilligt;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class matched_vacancies extends Fragment {

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.activity_matched_vacancies, container, false);

        setActionbarTitle(getActivity().getString(R.string.Matched_vacancies));
        return myView;
    }

    public void setActionbarTitle(String title)
    {
        getActivity().setTitle(title);
    }
}
