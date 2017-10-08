package example.android.com.bloodbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MyAccountActivity extends AppCompatActivity {

    private TextView mMAA_name;
    private TextView mMAA_email;
    private TextView mMAA_city;
    private TextView mMAA_state;
    private TextView mMAA_canGiveBlood;
    private TextView mMAA_BloodType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        mMAA_name = (TextView) findViewById(R.id.MAA_name);
        mMAA_email = (TextView) findViewById(R.id.MAA_email);
        mMAA_city = (TextView) findViewById(R.id.MAA_city);
        mMAA_state = (TextView) findViewById(R.id.MAA_state);
        mMAA_canGiveBlood = (TextView) findViewById(R.id.MAA_canGiveBloo);
        mMAA_BloodType = (TextView) findViewById(R.id.MAA_bloodType);

        Person currentUser = (Person) getIntent().getSerializableExtra("USER");

        mMAA_name.setText("Name: " + currentUser.getName());
        mMAA_email.setText("Email: " + currentUser.getEmail());
        mMAA_city.setText("City: " + currentUser.getCity());
        mMAA_state.setText("State: " + currentUser.getState());

        BloodType bloodType = currentUser.getBType();
        mMAA_BloodType.setText("Blood Type: " + bloodType.getbType());

        if (currentUser.isCanGiveBlood()) {
            mMAA_canGiveBlood.setText("Status: Can give blood");
        }
        else {
            mMAA_canGiveBlood.setText("Status: Unable to give blood");
        }


    }
}
