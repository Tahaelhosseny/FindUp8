package findupproducts.example.com.findup.UI.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.fragments.StoreDetailsFragment;

public class StoreDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details);

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.store_fragment_container, new StoreDetailsFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(StoreDetailsActivity.this , MainActivity.class));
        finish();
    }
}
