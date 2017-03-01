package be.howest.pekka.roeselarevrijwilligt;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by peksu on 14.12.2016.
 */

public class loginmatched extends Fragment {

    // Called when user wants to see his subscribed vacancies. Not working yet.

    SharedPreferences sharedPreferences;

    View myView;
    BackgroundWorker backgroundWorker;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myView = inflater.inflate(R.layout.activity_login, container, false);

        final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.AppTheme);

       Button button = (Button) myView.findViewById(R.id.button_login);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameTXT = (EditText) myView.findViewById(R.id.Username);
                EditText passwordTXT = (EditText) myView.findViewById(R.id.Password);
                String type = "login";
                String username = usernameTXT.getText().toString();
                String password = passwordTXT.getText().toString();
                String param = "1";
                backgroundWorker = new BackgroundWorker(contextThemeWrapper);
                backgroundWorker.execute(type,param,username, password);


            }
        });
        setActionbarTitle(getActivity().getString(R.string.Login));
        return myView;
    }

    public void setActionbarTitle(String title)
    {
        getActivity().setTitle(title);
    }


}

