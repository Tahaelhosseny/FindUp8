package khaled.example.com.findup.UI.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.santalu.maskedittext.MaskEditText;

import khaled.example.com.findup.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText editText_password, editText_repassword;
    MaskEditText editText_phone;
    Spinner mobileSpinner;
    Button btn_submit, btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        editText_phone = findViewById(R.id.editText_phone);
        editText_password = findViewById(R.id.editText_password);
        editText_repassword = findViewById(R.id.editText_repassword);
        btn_submit = findViewById(R.id.btn_submit);
        btn_back = findViewById(R.id.btn_passwordBack);

        mobileSpinner = findViewById(R.id.mobileSpinner);
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

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
                finish();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
        finish();
    }
}
