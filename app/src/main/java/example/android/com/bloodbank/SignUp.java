package example.android.com.bloodbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SignUp extends AppCompatActivity {
    private EditText mUsername;
    private EditText mPassword;
    private EditText mPasswordCheck;
    private Button mSignUp;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference datRef = database.getReference().child("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mUsername = (EditText) findViewById(R.id.edit_text_email);
        mPassword = (EditText) findViewById(R.id.edit_text_password);
        mPasswordCheck = (EditText) findViewById(R.id.edit_text_password_check);
        mSignUp = (Button) findViewById(R.id.sign_up_button);

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                String passCheck = mPasswordCheck.getText().toString();

                if (password.equals(passCheck)) {
                    Person newUser = new Person(username,password);
                    Intent newIntent = new Intent(getApplicationContext(), UserInfo.class);
                    newIntent.putExtra("USER", newUser);
                    startActivity(newIntent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Your passwords do not match.", Toast.LENGTH_LONG);
                }
            }
        });

    }

}
