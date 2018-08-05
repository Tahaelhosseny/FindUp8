package khaled.example.com.findup.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import khaled.example.com.findup.R;

public class SuccessfulRegisterationActivity extends AppCompatActivity {

    Button btn_switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_registeration);

        btn_switch=findViewById(R.id.btn_switch);
        btn_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SuccessfulRegisterationActivity.this, "Switch",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
