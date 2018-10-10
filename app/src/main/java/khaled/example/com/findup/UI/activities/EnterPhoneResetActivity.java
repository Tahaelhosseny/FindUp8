package khaled.example.com.findup.UI.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.santalu.maskedittext.MaskEditText;

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.Presenter.Activities.EnterPhoneResetPresenter;
import khaled.example.com.findup.UI.ViewModel.Activites.EnterPhoneResetViewModel;
import khaled.example.com.findup.databinding.ActivityEnterPhoneResetBinding;

public class EnterPhoneResetActivity extends AppCompatActivity {
    Button submitToVerify , btn_back;
    Spinner mobileSpinner;
    MaskEditText editText_phone;
    ActivityEnterPhoneResetBinding activityEnterPhoneResetBinding;
    EnterPhoneResetViewModel enterPhoneResetViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enterPhoneResetViewModel = new EnterPhoneResetViewModel(this);
        activityEnterPhoneResetBinding= DataBindingUtil.setContentView(this,R.layout.activity_enter_phone_reset);
//        setContentView(R.layout.activity_enter_phone_reset);
        activityEnterPhoneResetBinding.setEnterPhoneToReset(enterPhoneResetViewModel);
        activityEnterPhoneResetBinding.setPresenter(new EnterPhoneResetPresenter() {
            @Override
            public void EnterPhoneReset() {
                String phone = activityEnterPhoneResetBinding.txtNumber.getText().toString();
                enterPhoneResetViewModel.setCodeToPhone(phone);
            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EnterPhoneResetActivity.this , LoginActivity.class));
        finish();
    }
}
