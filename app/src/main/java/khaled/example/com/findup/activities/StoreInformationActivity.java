package khaled.example.com.findup.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import khaled.example.com.findup.R;

public class StoreInformationActivity extends AppCompatActivity {

    TextView txt_storeName, txt_otherLanguage, txt_tags;
    EditText editText_otherLanguage, editText_tags;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_information);
        txt_storeName=findViewById(R.id.txt_storeName);
        txt_otherLanguage=findViewById(R.id.txt_otherLanguage);
        txt_tags=findViewById(R.id.txt_tags);
        editText_otherLanguage=findViewById(R.id.editText_otherLanguage);
        editText_tags=findViewById(R.id.editText_tags);
        if (getIntent().getExtras().getInt("next_id")==2){
            txt_storeName.setText(R.string.your_name);
            txt_otherLanguage.setVisibility(View.GONE);
            editText_otherLanguage.setVisibility(View.GONE);
            txt_tags.setVisibility(View.GONE);
            editText_tags.setVisibility(View.GONE);
        }
        Button btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent transferIntent = new Intent(StoreInformationActivity.this, StoreContactActivity.class);
                transferIntent.putExtra("next_id",getIntent().getExtras().getInt("next_id"));
                startActivity(transferIntent);
            }
        });
    }
}
