package khaled.example.com.findup.UI.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.santalu.maskedittext.MaskEditText;

import khaled.example.com.findup.R;

public class EnterPhoneResetActivity extends AppCompatActivity {
    Button submitToVerify , btn_back;
    Spinner mobileSpinner;
    MaskEditText editText_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_phone_reset);
        submitToVerify = findViewById(R.id.btn_toVerifyPage);
        btn_back = findViewById(R.id.btn_toLoginPage);
        mobileSpinner=findViewById(R.id.mobileSpinner);
        editText_phone = findViewById(R.id.edit_phone_to_verify);
        String[] items = new String[]{"+2", "+966", "+900"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, items);
        mobileSpinner.setAdapter(adapter);
        editText_phone.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mobileSpinner.performClick();
                return true;
            }
        });
        submitToVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EnterPhoneResetActivity.this , VerifyCodeActivity.class));
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EnterPhoneResetActivity.this , LoginActivity.class));
                finish();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EnterPhoneResetActivity.this , LoginActivity.class));
        finish();
    }
}
