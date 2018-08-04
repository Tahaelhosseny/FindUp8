package khaled.example.com.findup.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import khaled.example.com.findup.R;
import khaled.example.com.findup.fragments.FilterFragment;

/**
 * Created by khaled on 8/1/18.
 */

public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.filter_fragment_container, new FilterFragment()).commit();
    }
}
