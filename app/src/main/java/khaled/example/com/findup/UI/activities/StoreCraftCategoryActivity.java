package khaled.example.com.findup.UI.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import khaled.example.com.findup.R;

public class StoreCraftCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_craft_category);
        Button btn_craftBack = findViewById(R.id.btn_craftBack);
        btn_craftBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StoreCraftCategoryActivity.this, IntroActivity.class));
                finish();
            }
        });
        Button btn_switchToTruck = findViewById(R.id.btn_craftToTruck);
        btn_switchToTruck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent transferIntent = new Intent(StoreCraftCategoryActivity.this, StoreChooseCategoryActivity.class);
                transferIntent.putExtra("next_id", 1);
                startActivity(transferIntent);
                finish();
            }
        });
        Button btn_switchToEvents = findViewById(R.id.btn_craftToEvents);
        btn_switchToEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent transferIntent = new Intent(StoreCraftCategoryActivity.this, StoreEventsCategoryActivity.class);
                startActivity(transferIntent);
                finish();
            }
        });
        Button btn_next = findViewById(R.id.btn_craftNext);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent transferIntent = new Intent(StoreCraftCategoryActivity.this, StoreInformationActivity.class);
                transferIntent.putExtra("next_id", getIntent().getExtras().getInt("next_id"));
                startActivity(transferIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(StoreCraftCategoryActivity.this, IntroActivity.class));
        finish();
    }
}
