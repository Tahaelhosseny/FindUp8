package khaled.example.com.findup.UI.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import khaled.example.com.findup.R;

public class StoreEventsCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_events_category);

        Button btn_switchToCraft=findViewById(R.id.btn_eventsToCraft);
        btn_switchToCraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StoreEventsCategoryActivity.this, StoreCraftCategoryActivity.class));
                finish();
            }
        });
        Button btn_switchToTruck=findViewById(R.id.btn_eventsToTruck);
        btn_switchToTruck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StoreEventsCategoryActivity.this, StoreChooseCategoryActivity.class));
                finish();
            }
        });
        Button btn_next= findViewById(R.id.btn_eventsNext);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StoreEventsCategoryActivity.this, StoreEventsActivity.class));
            }
        });
        Button btn_eventsBack=findViewById(R.id.btn_eventsBack);
        btn_eventsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Events back",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Events back",Toast.LENGTH_SHORT).show();
    }
}
