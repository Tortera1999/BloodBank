package example.android.com.bloodbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Questionnaire extends AppCompatActivity {

    Switch switch_1 = null;
    Switch switch_2 = null;
    Switch switch_3 = null;
    Switch switch_4 = null;
    Switch switch_5 = null;
    Switch switch_6 = null;

    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        final ArrayList<Switch> switchArray = new ArrayList<Switch>();

        switch_1 = (Switch) findViewById(R.id.switch_1);
        switchArray.add(switch_1);

        switch_2 = (Switch) findViewById(R.id.switch_2);
        switchArray.add(switch_1);

        switch_3 = (Switch) findViewById(R.id.switch_3);
        switchArray.add(switch_1);

        switch_4 = (Switch) findViewById(R.id.switch_4);
        switchArray.add(switch_1);

        switch_5 = (Switch) findViewById(R.id.switch_5);
        switchArray.add(switch_1);

        switch_6 = (Switch) findViewById(R.id.switch_6);
        switchArray.add(switch_1);

        submitButton = (Button) findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {

            boolean good = true;

            @Override
            public void onClick(View view) {

                for (Switch switchCheck : switchArray) {
                    if (switchCheck.isChecked()) {
                        good = false;
                        break;
                    }
                }

                if (good) {
                    //TODO: Start activity

                }
                else {
                    Toast.makeText(Questionnaire.this,"You do not meet the requirements to donate blood.", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
