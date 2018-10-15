package khaled.example.com.findup.UI.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.santalu.maskedittext.MaskEditText;

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.Presenter.Activities.VerifyDeletePresenter;
import khaled.example.com.findup.UI.ViewModel.Activites.DeleteAccountViewModel;
import khaled.example.com.findup.UI.ViewModel.Activites.EditProfileViewModel;
import khaled.example.com.findup.databinding.ActivityVerifyDeleteBinding;

public class VerifyDeleteActivity extends AppCompatActivity {

    ActivityVerifyDeleteBinding binding;
    DeleteAccountViewModel viewModel;
    public int counter=30;
    TextView txtNumber, txtTimer;
    Button btnBack, btnResend;
    MaskEditText editTextSt, editTextNd, editTextRd, editTextTh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new DeleteAccountViewModel(this);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_verify_delete);
//        setContentView(R.layout.activity_verify_delete);
        binding.setDeleteAccount(viewModel);
        String phone = getIntent().getStringExtra("mobile");
        binding.txtNumber.setText(phone);
        binding.setPresenter(new VerifyDeletePresenter() {
            @Override
            public void DeleteAccount() {
//                viewModel.resend_code(phone);
            }
        });
        txtNumber=findViewById(R.id.txtNumber);
        btnBack=findViewById(R.id.btn_verifyBack);
        txtTimer = findViewById(R.id.timer);
        btnResend=findViewById(R.id.btn_resend);
        editTextSt=findViewById(R.id.editText_stDigit);
        editTextNd=findViewById(R.id.editText_ndDigit);
        editTextRd=findViewById(R.id.editText_rdDigit);
        editTextTh=findViewById(R.id.editText_thDigit);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VerifyDeleteActivity.this, EditProfileActivity.class));
                finish();
            }
        });
        editTextSt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                editTextSt.setText("");
            }
        });
        editTextNd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                editTextNd.setText("");
            }
        });
        editTextRd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                editTextRd.setText("");
            }
        });
        editTextTh.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                editTextTh.setText("");
            }
        });

        editTextSt.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                if(editTextSt.getText().toString().length()==1)
                {
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

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                if(editTextNd.getText().toString().length()==1)
                {
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

            public void onTextChanged(CharSequence s, int start,int before, int count)
            {
                // TODO Auto-generated method stub
                if(editTextRd.getText().toString().length()==1)     //size as per your requirement
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
        editTextTh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(editTextSt.getText().toString().length() == 1 && editTextNd.getText().toString().length() == 1 &&
                        editTextRd.getText().toString().length() == 1 && editTextTh.getText().toString().length() == 1){
                    String code = binding.editTextStDigit.getRawText() +  binding.editTextNdDigit.getRawText()+
                            binding.editTextRdDigit.getRawText() + binding.editTextThDigit.getRawText() ;
                    viewModel.checkCode(phone,code);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        new CountDownTimer(90000, 1000){

            @Override
            public void onTick(long l) {
                txtTimer.setText(String.valueOf(counter));
                counter--;
            }

            @Override
            public void onFinish() {
                Toast.makeText(VerifyDeleteActivity.this, "Timer finished!!!!", Toast.LENGTH_SHORT).show();
            }
        }.start();

    }
}
