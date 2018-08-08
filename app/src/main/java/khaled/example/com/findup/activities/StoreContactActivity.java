package khaled.example.com.findup.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import khaled.example.com.findup.R;

public class StoreContactActivity extends AppCompatActivity {

    RadioButton radioShowCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_contact);
        radioShowCity=findViewById(R.id.radioShowCity);
        if (getIntent().getExtras().getInt("next_id")==2){
            radioShowCity.setVisibility(View.GONE);
        }
        final Button btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (getIntent().getExtras().getInt("next_id")){
                    case 1:
                        startActivity(new Intent(StoreContactActivity.this, AddProductTruckActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(StoreContactActivity.this, AddProductCraftActivity.class));
                        break;
                    default:
                        startActivity(new Intent(StoreContactActivity.this, AddProductTruckActivity.class));
                        break;
                }
            }
        });
    }
}
