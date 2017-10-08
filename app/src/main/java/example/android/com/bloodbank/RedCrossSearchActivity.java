package example.android.com.bloodbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RedCrossSearchActivity extends AppCompatActivity {

    private EditText mSearchQuery;
    private Button mDoSearch;
    private ListView mSearchList;
    private ArrayList<Person> searchResults;
    private ArrayList<Person> allUsers;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference fireRef = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_cross_search);

        allUsers = new ArrayList<>();
        searchResults = new ArrayList<>();
        mSearchList = (ListView) findViewById(R.id.RCS_list);
        mSearchQuery = (EditText) findViewById(R.id.RCS_search);
        mDoSearch = (Button) findViewById(R.id.RCS_search_button);

        mDoSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String searchQuery = mSearchQuery.getText().toString();

                for (Person user : allUsers) {
                    if (user.getCity() != null && user.getCity().equals(searchQuery)) {
                        searchResults.add(user);
                    }
                }

                SearchAdapter<Person> searchAdapter = new SearchAdapter<Person>(getApplicationContext(),R.layout.list_view, R.id.LL_text_box, searchResults);
                mSearchList.setAdapter(searchAdapter);
            }
        });


        fireRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                if (allUsers != null) {
                    allUsers.clear();
                }

                Log.e("Count " ,""+snapshot.getChildrenCount());

                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Person post = postSnapshot.getValue(Person.class);
                    Log.e("Get Data", "Got the data");

                    if (post.getCity() != null)
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
