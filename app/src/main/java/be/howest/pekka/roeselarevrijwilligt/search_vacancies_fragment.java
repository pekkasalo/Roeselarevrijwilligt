package be.howest.pekka.roeselarevrijwilligt;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link search_vacancies_fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link search_vacancies_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class search_vacancies_fragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setActionbarTitle("Search Vacancies");
        return inflater.inflate(R.layout.fragment_search_vacancies_fragment, container, false);


    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     **/
    public void setActionbarTitle(String title)
    {
        getActivity().setTitle(title);
    }
}

