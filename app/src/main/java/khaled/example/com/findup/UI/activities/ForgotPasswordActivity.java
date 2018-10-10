package khaled.example.com.findup.UI.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.santalu.maskedittext.MaskEditText;

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.Presenter.Activities.ForgetPasswordPresenter;
import khaled.example.com.findup.UI.ViewModel.Activites.CreateEventViewModel;
import khaled.example.com.findup.UI.ViewModel.Activites.ForgetPasswordViewModel;
import khaled.example.com.findup.databinding.ActivityForgotPasswordBinding;

public class ForgotPasswordActivity extends AppCompatActivity {
    ActivityForgotPasswordBinding activityForgotPasswordBinding;
    ForgetPasswordViewModel forgetPasswordViewModel;
    MaskEditText editText_phone;
    Spinner mobileSpinner;
    Button btn_submit, btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forgetPasswordViewModel = new ForgetPasswordViewModel(this);
        activityForgotPasswordBinding= DataBindingUtil.setContentView(this,R.layout.activity_forgot_password);
        activityForgotPasswordBinding.setForgetPassword(forgetPasswordViewModel);
        String phone = getIntent().getStringExtra("phone");
        activityForgotPasswordBinding.editTextPhone.setText(phone);
        activityForgotPasswordBinding.setPresenter(new ForgetPasswordPresenter() {
            @Override
            public void UpdateNewPassword() {
                String pass = activityForgotPasswordBinding.editTextPassword.getText().toString();
                String rePass = activityForgotPasswordBinding.editTextRepassword.getText().toString();
                if(TextUtils.isEmpty(pass) || TextUtils.isEmpty(rePass)){
                    Toast.makeText(ForgotPasswordActivity.this, "Please Fill The Empty Field", Toast.LENGTH_SHORT).show();
                }else{
                    if(pass == rePass){
                        forgetPasswordViewModel.updateNewPassword(phone,rePass);
                    }else{
                        Toast.makeText(ForgotPasswordActivity.this, "Password didn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        editText_phone=findViewById(R.id.editText_phone);
        btn_submit=findViewById(R.id.btn_submit);
        btn_back=findViewById(R.id.btn_passwordBack);
        mobileSpinner=findViewById(R.id.mobileSpinner);
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
