package khaled.example.com.findup.UI.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.fragments.CategoryFragment;
import khaled.example.com.findup.UI.fragments.NotificationsFragment;

public class StoreChooseCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_choose_category);

        Button btn_next= findViewById(R.id.btn_truckNext);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoryFragment.ExpandableSection  expandableSection= CategoryFragment.getExpanddedSection();
                if (expandableSection.getTitle().equals(view.getContext().getResources().getString(R.string.events)))
                    startActivity(new Intent(view.getContext(),StoreEventsActivity.class));
                else {
                    Intent transferIntent = new Intent(StoreChooseCategoryActivity.this, StoreInformationActivity.class);
                    transferIntent.putExtra("next_id", getIntent().getExtras().getInt("next_id"));
                    startActivity(transferIntent);
                }
            }
        });
        Button btn_truckBack=findViewById(R.id.btn_truckCategoryBack);
        btn_truckBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    finish();
            }
        });

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.cats_fragment_container, new CategoryFragment()).commit();
    }
}