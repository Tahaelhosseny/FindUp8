package findupproducts.example.com.findup.UI.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import findupproducts.example.com.findup.UI.Presenter.Activities.LoginPresenter;
import findupproducts.example.com.findup.UI.ViewModel.Activites.LoginViewModel;
import findupproducts.example.com.findup.databinding.ActivityLoginBinding;
import findupproducts.example.com.findup.R;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    LoginViewModel loginViewModel;
    String phoneKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = new LoginViewModel(this);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.setLoginview(loginViewModel);
        binding.setPresenter(new LoginPresenter() {
            @Override
            public void LoginLoadData() {
                String phone = binding.editTextPhone.getRawText();
                String pass  = binding.editTextPassword.getText().toString();
                phone = phoneKey + phone;
                phone = phone.replace("+","");
                loginViewModel.sendLoginRequest(phone,pass);
            }
        });

        String[] items = new String[]{"+2", "+966", "+900"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, items);
        binding.mobileSpinner.setAdapter(adapter);
        binding.editTextPhone.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                binding.mobileSpinner.performClick();
                return true;
            }
        });

        binding.mobileSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                phoneKey = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LoginActivity.this, IntroActivity.class));
        finish();
    }
}