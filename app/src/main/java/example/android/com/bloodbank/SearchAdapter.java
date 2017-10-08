package example.android.com.bloodbank;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import example.android.com.bloodbank.Person;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by default on 10/8/2017.
 */

public class SearchAdapter<Person> extends ArrayAdapter<Person> {
    public SearchAdapter(Context context, ArrayList<Person> users) {
        super(context, 0, users);
    }

    public SearchAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List<Person> objects) {
        super(context, resource, textViewResourceId, objects);
        if (objects == null) {
            Log.d("AAAAAAAA", "objects are null");
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        example.android.com.bloodbank.Person user = (example.android.com.bloodbank.Person) getItem(position);
        String userEmail = user.getEmail();

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
        }
        example.android.com.bloodbank.BloodType bloodType = user.getBType();

        TextView mListViewText = convertView.findViewById(R.id.LL_text_box);
        mListViewText.setText("Email: " + user.getEmail() + "\nBlood Type: " + user.getBType().getbType());
        Log.d("Blood type", user.getBType().getbType());

        return convertView;
    }
}
