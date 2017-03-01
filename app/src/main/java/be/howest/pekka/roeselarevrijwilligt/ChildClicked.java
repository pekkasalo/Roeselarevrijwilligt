package be.howest.pekka.roeselarevrijwilligt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChildClicked extends AppCompatActivity {

    // Activity so we can expand the wanted vacancy


    TextView header;
    TextView organisation;
    TextView Description;
    TextView Employer;
    TextView Amound;
    Button Subscribe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_clicked);

        Intent tIntent= getIntent();
        Bundle b = tIntent.getExtras();
        String information = (String)b.get("information");
        String information2 = (String)b.get("information2");
        String information3 = (String)b.get("information3");
        String information4 = (String)b.get("information4");
        String information6 = (String)b.get("information6");
        String information5 = (String)b.get("information5");

        header = (TextView) findViewById(R.id.child_clicked_header);
        organisation = (TextView) findViewById(R.id.child_clicked_employer_header);
        Description = (TextView) findViewById(R.id.child_clicked_description);
        Employer = (TextView) findViewById(R.id.child_clicked_employer_bot);
        Amound = (TextView) findViewById(R.id.child_clicked_amount_hired);


        header.setText(information);
        organisation.setText(information2);
        Description.setText(information6);
        Employer.setText(information4);
        Amound.setText(information5);

        Subscribe = (Button) findViewById(R.id.button_subscribe_for_vacancy);

        Subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login dialog = new login();
                dialog.show(getSupportFragmentManager(), "ChildClicked");

            }
        });

    }
}
