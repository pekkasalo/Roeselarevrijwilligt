package be.howest.pekka.roeselarevrijwilligt;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.EditText;


public class login extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // This login is called when user wants to subscribe to specific vacancy. Not working yet.

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity().getApplicationContext());

        builder.setView(inflater.inflate(R.layout.activity_login, null))

        .setPositiveButton(R.string.Login, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                EditText usernameTXT = (EditText) getActivity().findViewById(R.id.Username);
                EditText passwordTXT = (EditText) getActivity().findViewById(R.id.Password);
                String type = "login";

                String username = usernameTXT.getText().toString();
                String password = passwordTXT.getText().toString();

                backgroundWorker.execute(type, username, password);
               login.this.getDialog().cancel();
            }
        })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        login.this.getDialog().cancel();
                    }
                });
        return builder.create();

    }
}

