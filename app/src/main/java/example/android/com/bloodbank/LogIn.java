package example.android.com.bloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LogIn extends AppCompatActivity {

    private TextView mSignUp;
    private EditText mUsername;
    private EditText mPassword;
    private Button mLogInButton;

    String username = "";
    String password = "";

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference fireRef = firebaseDatabase.getReference();

    ArrayList<Person> allUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mSignUp = (TextView) findViewById(R.id.text_view_go_to_signup);
        mUsername = (EditText) findViewById(R.id.edit_text_login_username);
        mPassword = (EditText) findViewById(R.id.edit_text_login_password);
        mLogInButton = (Button) findViewById(R.id.log_in_button);

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSignUp = new Intent(getApplicationContext(), SignUp.class);
                startActivity(goToSignUp);
            }
        });

        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = mUsername.getText().toString();
                password = mPassword.getText().toString();

                for (Person user : allUsers) {
                    if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                        //TODO: add intent to main page
                        Intent newIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(newIntent);
                        break;
                    }
                }

                Toast.makeText(getApplicationContext(),"Wrong username or password.", Toast.LENGTH_LONG);
            }
        });

        fireRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                allUsers.clear();

                Log.e("Count " ,""+snapshot.getChildrenCount());

                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Person post = postSnapshot.getValue(Person.class);
                    Log.e("Get Data", "Got the data");
                    allUsers.add(post);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("FAILURE", "reading failure");
            }
        });
    }

}
