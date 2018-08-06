package khaled.example.com.findup.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import khaled.example.com.findup.R;

public class StoreCraftCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_craft_category);
        Button btn_switchToTruck=findViewById(R.id.btn_switchToTruck);
        btn_switchToTruck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StoreCraftCategoryActivity.this, StoreChooseCategoryActivity.class));
                finish();
            }
        });
        Button btn_next= findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StoreCraftCategoryActivity.this, StoreInformationActivity.class));
            }
        });
    }
}
