package khaled.example.com.findup.UI.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.santalu.maskedittext.MaskEditText;

import khaled.example.com.findup.R;

public class VerifyCodeActivity extends AppCompatActivity {

    public int counter = 30;
    TextView txtNumber, txtTimer;
    Button btnBack, btnResend;
    MaskEditText editTextSt, editTextNd, editTextRd, editTextTh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);

        txtNumber = findViewById(R.id.txtNumber);
        txtTimer = findViewById(R.id.timer);
        btnBack = findViewById(R.id.btn_verifyBack);
        btnResend = findViewById(R.id.btn_resend);
        editTextSt = findViewById(R.id.editText_stDigit);
        editTextNd = findViewById(R.id.editText_ndDigit);
        editTextRd = findViewById(R.id.editText_rdDigit);
        editTextTh = findViewById(R.id.editText_thDigit);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VerifyCodeActivity.this, LoginActivity.class));
                finish();
            }
        });

        btnResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(VerifyCodeActivity.this, "Resend", Toast.LENGTH_SHORT).show();
            }
        });

        editTextSt.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editTextSt.getText().toString().length() == 1) {
                    editTextNd.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }

        });

        editTextNd.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editTextSt.getText().toString().length() == 1) {
                    editTextRd.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        editTextRd.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (editTextSt.getText().toString().length() == 1)     //size as per your requirement
                {
                    editTextTh.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long l) {
                txtTimer.setText(String.valueOf(counter));
                counter--;
            }

            @Override
            public void onFinish() {
                Toast.makeText(VerifyCodeActivity.this, "Timer finished!!!!", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(VerifyCodeActivity.this, LoginActivity.class));
        finish();
    }
}