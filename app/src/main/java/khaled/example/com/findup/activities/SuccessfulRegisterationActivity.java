package khaled.example.com.findup.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import khaled.example.com.findup.R;

public class SuccessfulRegisterationActivity extends AppCompatActivity {

    Button btn_switch;
    private final int SPLASH_DISPLAY_LENGTH=5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_registeration);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SuccessfulRegisterationActivity.this, MainStoreActivity.class));
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
        
        btn_switch=findViewById(R.id.btn_switch);
        btn_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SuccessfulRegisterationActivity.this, "Switch",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
