package khaled.example.com.findup.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import khaled.example.com.findup.R;

public class StoreInformationActivity extends AppCompatActivity {

    TextView txt_storeName, txt_otherLanguage, txt_tags;
    EditText editText_otherLanguage, editText_tags;
    ImageView imgLogo, imgBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_information);

        imgLogo=findViewById(R.id.pic_logo);
        imgBanner=findViewById(R.id.pic_banner);
        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StoreInformationActivity.this, "add logo", Toast.LENGTH_SHORT).show();
            }
        });
        imgBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StoreInformationActivity.this, "add banner", Toast.LENGTH_SHORT).show();
            }
        });

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
        Button btn_informationBack=findViewById(R.id.btn_informaionBack);
        btn_informationBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoreInformationActivity.super.onBackPressed();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
