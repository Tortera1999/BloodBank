package example.android.com.bloodbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class UserInfo extends AppCompatActivity{
    Spinner spinner;
    String name = "";
    String city = "";
    String state = "";
    String email = "";

    EditText nameText;
    EditText cityText;
    EditText stateText;
    EditText emailText;

    Button submitButton;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference databaseReference = database.getReference();

    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);

        //nameText = (EditText) findViewById(R.id.nameText);
        cityText = (EditText) findViewById(R.id.cityText);
        stateText = (EditText) findViewById(R.id.stateText);
        emailText = (EditText) findViewById(R.id.emailText);

        submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //name = nameText.getText().toString();

                Person newUser = (Person) getIntent().getSerializableExtra("USER");

                if (cityText.getText() == null || stateText.getText() == null || emailText.getText() == null) {
                    Toast.makeText(getApplicationContext(), "Please enter a value for all forms.", Toast.LENGTH_LONG);
                }
                else {
                    city = cityText.getText().toString();
                    state = stateText.getText().toString();
                    email = emailText.getText().toString();

                    Log.d("CITYYYY", city);

                    BloodType bloodType;

                    switch (spinner.getSelectedItem().toString()) {
                        case "O-":
                            bloodType = new BloodType('O',1);
                            break;
                        case "O+":
                            bloodType = new BloodType('O',0);
                            break;
                        case "A+":
                            bloodType = new BloodType('A',0);
                            break;
                        case "A-":
                            bloodType = new BloodType('A',1);
                            break;
                        case "B+":
                            bloodType = new BloodType('B',0);
                            break;
                        case "B-":
                            bloodType = new BloodType('B',1);
                            break;
                        case "AB+":
                            bloodType = new BloodType(true, 0);
                            break;
                        case "AB-":
                            bloodType = new BloodType(true, 1);
                            break;
                        default:
                            bloodType = new BloodType('Z', 0);
                    }

                    newUser.setCity(city);
                    newUser.setEmail(emailText.getText().toString());
                    newUser.setState(stateText.getText().toString());
                    newUser.setBType(bloodType);
                    String ID = databaseReference.push().getKey();

                    newUser.setID(ID);
                    databaseReference.child(ID).setValue(newUser);

                    Intent goToMain = new Intent(getApplicationContext(), Questionnaire.class);
                    goToMain.putExtra("USER", newUser);
                    startActivity(goToMain);
                }
            }
        });



        spinner = (Spinner) findViewById(R.id.bloodtype);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.bloodtypearray, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(), adapterView.getItemAtPosition(i)+" selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void showToast(String text) {
        Toast.makeText(UserInfo.this, text, Toast.LENGTH_SHORT).show();
    }
    private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }
}
