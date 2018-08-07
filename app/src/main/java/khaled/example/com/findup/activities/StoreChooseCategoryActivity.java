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

        Button btn_next= findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent transferIntent = new Intent(StoreChooseCategoryActivity.this, StoreInformationActivity.class);
                transferIntent.putExtra("next_id",getIntent().getExtras().getInt("next_id"));
                startActivity(transferIntent);
            }
        });
        Button btn_switchtoCraft =findViewById(R.id.btn_switchToCraft);
        btn_switchtoCraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent transferIntent = new Intent(StoreChooseCategoryActivity.this, StoreCraftCategoryActivity.class);
                transferIntent.putExtra("next_id",2);
                startActivity(transferIntent);
                finish();
            }
        });
    }
}