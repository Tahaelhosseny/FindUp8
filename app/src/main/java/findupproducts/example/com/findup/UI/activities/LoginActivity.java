package findupproducts.example.com.findup.UI.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.UI.Presenter.Activities.LoginPresenter;
import findupproducts.example.com.findup.UI.ViewModel.Activites.LoginViewModel;
import findupproducts.example.com.findup.databinding.ActivityLoginBinding;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.models.Store;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    LoginViewModel loginViewModel;
    String phoneKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        loginViewModel = new LoginViewModel(this);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.setLoginview(loginViewModel);


        if(SharedPrefManger.isIsLoggedIn())
        {
            if(SharedPrefManger.isIsLoggedInAsCustomer())
                startActivity(new Intent(this , MainActivity.class));
            else
                startActivity(new Intent(this , Store.class));
        }


        binding.setPresenter(new LoginPresenter() {
            @Override
            public void LoginLoadData() {
                String phone = binding.editTextPhone.getText().toString();
                String pass  = binding.editTextPassword.getText().toString();
                if(TextUtils.isEmpty(phone)){
                    Toast.makeText(LoginActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(LoginActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                loginViewModel.sendLoginRequest(phone,pass);
            }
        });

        binding.btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,EnterPhoneResetActivity.class));
            }
        });

        binding.btnLoginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, IntroActivity.class));
                finish();
            }
        });

        binding.createNewAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
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