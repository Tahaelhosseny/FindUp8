package findupproducts.example.com.findup.UI.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.santalu.maskedittext.MaskEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.Presenter.Activities.VerifyResetPassPresenter;
import findupproducts.example.com.findup.UI.ViewModel.Activites.VerifyResetPassCodeViewModel;
import findupproducts.example.com.findup.databinding.ActivityVerifyCodeBinding;
import findupproducts.example.com.findup.netHelper.MakeRequest;
import findupproducts.example.com.findup.netHelper.OnCancelRetry;
import findupproducts.example.com.findup.netHelper.VolleyCallback;

public class VerifyCodeActivity extends AppCompatActivity {
    ActivityVerifyCodeBinding activityVerifyCodeBinding;
    VerifyResetPassCodeViewModel verifyResetPassCodeViewModel;
    public int counter=60;
    TextView txtNumber, txtTimer , btnResend;
    Button btnBack , btnCheckCode;
    MaskEditText editTextSt, editTextNd, editTextRd, editTextTh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verifyResetPassCodeViewModel = new VerifyResetPassCodeViewModel(this);
        activityVerifyCodeBinding = DataBindingUtil.setContentView(this,R.layout.activity_verify_code);
        activityVerifyCodeBinding.setVerifyCode(verifyResetPassCodeViewModel);
        String phone = getIntent().getStringExtra("email");
        activityVerifyCodeBinding.txtNumber.setText(phone);
        btnCheckCode = findViewById(R.id.btn_submit_check_code);
        txtNumber=findViewById(R.id.txtNumber);
        btnBack=findViewById(R.id.btn_verifyBack);
        txtTimer = findViewById(R.id.timer);
        btnResend=findViewById(R.id.btn_resend);
        editTextSt=findViewById(R.id.editText_stDigit);
        editTextNd=findViewById(R.id.editText_ndDigit);
        editTextRd=findViewById(R.id.editText_rdDigit);
        editTextTh=findViewById(R.id.editText_thDigit);
        btnCheckCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VerifyCodeActivity.this , ForgotPasswordActivity.class));finish();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VerifyCodeActivity.this, LoginActivity.class));
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

            public void afterTextChanged(Editable s)
            {
                // TODO Auto-generated method stub
            }

        });
        editTextTh.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(editTextSt.getText().toString().length() == 1 && editTextNd.getText().toString().length() == 1 &&
                        editTextRd.getText().toString().length() == 1 && editTextTh.getText().toString().length() == 1){
                    String code = activityVerifyCodeBinding.editTextStDigit.getRawText() +  activityVerifyCodeBinding.editTextNdDigit.getRawText()+
                            activityVerifyCodeBinding.editTextRdDigit.getRawText() + activityVerifyCodeBinding.editTextThDigit.getRawText() ;
                    verifyResetPassCodeViewModel.checkCode(phone,code);
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });




        btnResend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                counter = 60 ;
                btnResend.setVisibility(View.GONE);
               sendCode(phone , false);
            }
        });


        sendCode(phone , true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(VerifyCodeActivity.this, LoginActivity.class));
        finish();
    }




    void sendCode(String Phone , Boolean flag)
    {
        String Url = "http://findupproducts.com/findup_api/reg_login?tag=resend_code&email="+Phone+"&HashSecure=FindUpSecure_@@01072018" ;
        Activity activityApi = this ;
        Map<String , String> map = new HashMap<String, String>();
        MakeRequest makeRequest = new MakeRequest(  Url ,"0" , map , activityApi ,"resend_code" ,true);
        makeRequest.request(new VolleyCallback() {
            @Override
            public void onSuccess(Map<String, String> result)
            {
                try {
                    JSONObject jsonObject = new JSONObject(result.get("res").toString());
                    if(jsonObject.getInt("success") == 1)
                    {

                        new CountDownTimer(61000, 1000){

                            @Override
                            public void onTick(long l) {
                                txtTimer.setText(String.valueOf(counter));
                                counter--;
                            }

                            @Override
                            public void onFinish()
                            {

                                if(flag)
                                    btnResend.setVisibility(View.VISIBLE);
                                else finish();
                            }
                        }.start();


                    }else
                        {

                        }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new OnCancelRetry() {
            @Override
            public void OnCacelRetry()
            {
                Toast.makeText(getApplicationContext(),"Failed Try Again Later" , Toast.LENGTH_LONG).show();

            }
        });
    }
}