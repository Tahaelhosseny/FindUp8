package khaled.example.com.findup.UI.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import khaled.example.com.findup.UI.Presenter.Activities.LoginPresenter;
import khaled.example.com.findup.UI.ViewModel.Activites.LoginViewModel;
import khaled.example.com.findup.databinding.ActivityLoginBinding;
import com.santalu.maskedittext.MaskEditText;

import khaled.example.com.findup.R;

public class LoginActivity extends AppCompatActivity {

    ImageView pic_account;
    EditText editText_password;
    MaskEditText editText_phone;
    Spinner mobileSpinner;
    Button btn_forgotPassword, btn_login, btn_signup, btn_loginBack;
    ActivityLoginBinding binding;
    LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
        loginViewModel = new LoginViewModel(this);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.setLoginview(loginViewModel);
        binding.setPresenter(new LoginPresenter() {
            @Override
            public void LoginLoadData() {
                String phone = binding.editTextPhone.getRawText();
                String pass  = binding.editTextPassword.getText().toString();
                loginViewModel.sendLoginRequest(phone,pass);
            }
        });

        pic_account=findViewById(R.id.pic_account);
        editText_phone=findViewById(R.id.editText_phone);
        editText_password=findViewById(R.id.editText_password);
        btn_forgotPassword=findViewById(R.id.btn_forgotPassword);
        btn_login=findViewById(R.id.btn_login);
        btn_signup=findViewById(R.id.btn_signup);
        btn_loginBack=findViewById(R.id.btn_loginBack);

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

        btn_forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
            }
        });

        /*btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this,"login",Toast.LENGTH_SHORT).show();
            }
        });*/

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                finish();
            }
        });

        btn_loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, IntroActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LoginActivity.this, IntroActivity.class));
        finish();
    }
}