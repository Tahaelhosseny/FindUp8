package khaled.example.com.findup.UI.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.fragments.ProfileStoreFragment;
import khaled.example.com.findup.UI.fragments.StoreAccountHomeFragment;

public class StoreAccountHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_account_home);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.home_fragment_container, new ProfileStoreFragment()).commit();
    }
}
