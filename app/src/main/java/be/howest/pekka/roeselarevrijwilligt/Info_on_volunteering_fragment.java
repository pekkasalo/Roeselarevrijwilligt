package be.howest.pekka.roeselarevrijwilligt;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by peksu on 7.11.2016.
 */

public class Info_on_volunteering_fragment extends Fragment {

    // Controlling info on the volunteering

    View myView;
    Button buttonfirst;
    Button buttonsecond;
    android.app.FragmentManager fragmentManager = getFragmentManager();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setActionbarTitle("Info on Volunteering");


        myView = inflater.inflate(R.layout.info_on_volunteering, container, false);
        buttonfirst = (Button) myView.findViewById(R.id.info_buttonfirst);

buttonfirst.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        MagJeVrijwilligers fragment2 = new MagJeVrijwilligers();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment2);
        fragmentTransaction.addToBackStack("magJewilligers");
        fragmentTransaction.commit();


    }
});


        buttonsecond = (Button) myView.findViewById(R.id.info_buttonsecond);
        buttonsecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Watisvrijwilligen fragment2 = new Watisvrijwilligen();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment2);
                fragmentTransaction.addToBackStack("watisvrijwilligen");
                fragmentTransaction.commit();

            }
        });




        return myView;
    }
    public void setActionbarTitle(String title)
    {
        getActivity().setTitle(title);
    }
}