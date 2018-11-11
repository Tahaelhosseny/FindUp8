package findupproducts.example.com.findup.UI.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.santalu.maskedittext.MaskEditText;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.Presenter.Activities.RegisterPresenter;
import findupproducts.example.com.findup.UI.ViewModel.Activites.RegisterViewModel;
import findupproducts.example.com.findup.databinding.ActivitySignupBinding;


public class SignupActivity extends AppCompatActivity {

    ImageButton pic_account;
    EditText editText_username, editText_password;
    MaskEditText editText_phone;
    Button btn_login, btn_signup, btn_signupBack;
    Spinner mobileSpinner;
    RegisterViewModel registerViewModel;
    ActivitySignupBinding registerBinding;
    String phoneKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerViewModel = new RegisterViewModel(this);
        registerBinding= DataBindingUtil.setContentView(this,R.layout.activity_signup);
        registerBinding.setLoginview(registerViewModel);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        registerBinding.setPresenter(new RegisterPresenter() {
            @Override
            public void RegisterLoadData() {
                String name , phone , pass;
                name = registerBinding.editTextUsername.getText().toString();
                phone = phoneKey + registerBinding.editTextPhone.getRawText();
                pass  = registerBinding.editTextPassword.getText().toString();
                registerViewModel.sendRegisterRequest(name,pass , phone , extras.getString("Email"));

            }
        });
        pic_account=findViewById(R.id.pic_account);
        editText_username=findViewById(R.id.editText_username);
        Toast.makeText(this, ""+extras.getString("Name"), Toast.LENGTH_LONG).show();
        editText_username.setText(extras.getString("Name"));


        editText_phone=findViewById(R.id.editText_phone);
        editText_password=findViewById(R.id.editText_password);
        btn_login=findViewById(R.id.btn_login);
        btn_signupBack=findViewById(R.id.btn_signupBack);
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

        mobileSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                phoneKey = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        pic_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignupActivity.this,"add pic",Toast.LENGTH_SHORT).show();
            }
        });

//        btn_signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(SignupActivity.this,"signup",Toast.LENGTH_SHORT).show();
//            }
//        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            }
        });

        btn_signupBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, IntroActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SignupActivity.this, IntroActivity.class));
        finish();
    }
}
