package khaled.example.com.findup.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import khaled.example.com.findup.R;

public class StoreContactActivity extends AppCompatActivity {

    int btnNext_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_contact);
        final Button btn_next = findViewById(R.id.btn_next);
        btnNext_id=2;
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (btnNext_id){
                    case 1:
                        startActivity(new Intent(StoreContactActivity.this, AddProductTruckActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(StoreContactActivity.this, AddProductCraftActivity.class));
                        break;
                }
            }
        });
    }
}
