package example.android.com.bloodbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Questionnaire extends AppCompatActivity {

    Switch switch_1 = null;
    Switch switch_2 = null;
    Switch switch_3 = null;
    Switch switch_4 = null;
    Switch switch_5 = null;
    Switch switch_6 = null;

    Button submitButton;

    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference fireRef = mDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        final ArrayList<Switch> switchArray = new ArrayList<Switch>();

        switch_1 = (Switch) findViewById(R.id.switch_1);
        switchArray.add(switch_1);

        switch_2 = (Switch) findViewById(R.id.switch_2);
        switchArray.add(switch_2);

        switch_3 = (Switch) findViewById(R.id.switch_3);
        switchArray.add(switch_3);

        switch_4 = (Switch) findViewById(R.id.switch_4);
        switchArray.add(switch_4);

        switch_5 = (Switch) findViewById(R.id.switch_5);
        switchArray.add(switch_5);

        switch_6 = (Switch) findViewById(R.id.switch_6);
        switchArray.add(switch_6);

        //switch 7 age

        submitButton = (Button) findViewById(R.id.submit_button);

        final Person newUser = (Person) getIntent().getSerializableExtra("USER");

        submitButton.setOnClickListener(new View.OnClickListener() {

            boolean goodToGiveBlood = true;

            @Override
            public void onClick(View view) {

                for (Switch switchCheck : switchArray) {
                    if (switchCheck.isChecked()) {
                        goodToGiveBlood = false;
                        break;
                    }
                }

                if (goodToGiveBlood) {
                    Intent goToMap = new Intent(getApplicationContext(), MapActivity.class);
                    fireRef.child(newUser.getID()).child("canGiveBlood").setValue(true);
                    goToMap.putExtra("USER",newUser);
                    startActivity(goToMap);
                    finish();
                }
                else {
                    fireRef.child(newUser.getID()).child("canGiveBlood").setValue(false);
                    Toast.makeText(Questionnaire.this,"You do not meet the requirements to donate blood.", Toast.LENGTH_LONG).show();
                    goodToGiveBlood = true;
                }

            }
        });

    }
}
