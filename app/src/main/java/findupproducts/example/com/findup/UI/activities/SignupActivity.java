package findupproducts.example.com.findup.UI.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
    Button btn_signupBack;
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
        registerBinding.editTextMail.setText(extras.getString("Email"));
        registerBinding.editTextUsername.setText(extras.getString("Name"));
        registerBinding.setPresenter(new RegisterPresenter() {
            @Override
            public void RegisterLoadData() {
                String name , phone , pass , mail;
                name = registerBinding.editTextUsername.getText().toString();
                phone = phoneKey + registerBinding.editTextPhone.getRawText();
                phone = phone.replace("+","");
                pass  = registerBinding.editTextPassword.getText().toString();
                mail = registerBinding.editTextMail.getText().toString();
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(SignupActivity.this, "Please Enter User Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mail)){
                    Toast.makeText(SignupActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phone)){
                    Toast.makeText(SignupActivity.this, "Please Enter Phone", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    Toast.makeText(SignupActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                registerViewModel.sendRegisterRequest(name,pass , phone , mail);

            }
        });
        pic_account=findViewById(R.id.pic_account);
        editText_username=findViewById(R.id.editText_username);
        Toast.makeText(this, ""+extras.getString("Name"), Toast.LENGTH_LONG).show();
        editText_username.setText(extras.getString("Name"));
        editText_phone=findViewById(R.id.editText_phone);
        editText_password=findViewById(R.id.editText_password);
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
