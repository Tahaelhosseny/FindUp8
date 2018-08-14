package khaled.example.com.findup.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import khaled.example.com.findup.R;

public class StoreChooseCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_choose_category);

        Button btn_next= findViewById(R.id.btn_truckNext);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent transferIntent = new Intent(StoreChooseCategoryActivity.this, StoreInformationActivity.class);
                transferIntent.putExtra("next_id",getIntent().getExtras().getInt("next_id"));
                startActivity(transferIntent);
            }
        });
        Button btn_switchToCraft =findViewById(R.id.btn_truckToCraft);
        btn_switchToCraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent transferIntent = new Intent(StoreChooseCategoryActivity.this, StoreCraftCategoryActivity.class);
                transferIntent.putExtra("next_id",2);
                startActivity(transferIntent);
                finish();
            }
        });
        Button btn_switchToEvents =findViewById(R.id.btn_truckToEvents);
        btn_switchToEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StoreChooseCategoryActivity.this, StoreEventsCategoryActivity.class));
                finish();
            }
        });
        Button btn_truckBack=findViewById(R.id.btn_truckCategoryBack);
        btn_truckBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StoreChooseCategoryActivity.this, IntroActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(StoreChooseCategoryActivity.this, IntroActivity.class));
        finish();
    }
}