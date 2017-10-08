package example.android.com.bloodbank;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class main_page extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);



        Button mapbutton = (Button) findViewById(R.id.mapbutton);
        final UserInfo user = new UserInfo();
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
}
