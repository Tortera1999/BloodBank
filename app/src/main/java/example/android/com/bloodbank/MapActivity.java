package example.android.com.bloodbank;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MapActivity extends AppCompatActivity {
    private Person currentUser;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Button mapbutton = (Button) findViewById(R.id.mapbutton);
        //final UserInfo user = new UserInfo();

        currentUser = (Person) getIntent().getSerializableExtra("USER");

        mapbutton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String URL = "geo: 0, 0?z=10&q=Blood+Donation";
                Uri geo = Uri.parse(URL);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, geo);
                if(mapIntent.resolveActivity(getPackageManager()) != null ){
                    startActivity(mapIntent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out:
                Intent goToLogIn = new Intent(getApplicationContext(), LogIn.class);
                goToLogIn.putExtra("USER", currentUser);
                startActivity(goToLogIn);
                break;
            case R.id.my_account:
                Intent goToMyAccount = new Intent(getApplicationContext(), MyAccountActivity.class);
                goToMyAccount.putExtra("USER", currentUser);
                startActivity(goToMyAccount);
                break;
        }
        return true;
    }
}