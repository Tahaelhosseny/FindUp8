package khaled.example.com.findup.UI.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.santalu.maskedittext.MaskEditText;

import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.Presenter.Activities.EditProfilePresenter;
import khaled.example.com.findup.UI.ViewModel.Activites.EditProfileViewModel;
import khaled.example.com.findup.databinding.ActivityEditProfileBinding;

public class EditProfileActivity extends AppCompatActivity {
    ActivityEditProfileBinding activityEditProfileBinding;
    EditProfileViewModel editProfileViewModel;
    Spinner mobileSpinner;
    MaskEditText editText_phone;
    Button btn_editProfileBack;
    ImageButton btn_deleteAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editProfileViewModel = new EditProfileViewModel(this);
        activityEditProfileBinding= DataBindingUtil.setContentView(this,R.layout.activity_edit_profile);
        editText_phone=findViewById(R.id.editText_phone);
        btn_editProfileBack=findViewById(R.id.btn_editProfileBack);
        btn_deleteAccount=findViewById(R.id.btn_deleteAccount);

        activityEditProfileBinding.setEditProfileData(editProfileViewModel);
        activityEditProfileBinding.editTextPassword.setText(SharedPrefManger.getLogin_password());
        activityEditProfileBinding.editTextUsername.setText(SharedPrefManger.getUser_name());
        activityEditProfileBinding.editTextPhone.setText(SharedPrefManger.getLogin_phone());
        activityEditProfileBinding.setPresenter(new EditProfilePresenter() {
            @Override
            public void editProfileData() {
                int account_id = SharedPrefManger.getUser_ID();
                String user_name = SharedPrefManger.getUser_name();
                String old_password = SharedPrefManger.getLogin_password();
                String newName = activityEditProfileBinding.editTextUsername.getText().toString();
                String phone = activityEditProfileBinding.editTextPhone.getRawText();
                String new_password = activityEditProfileBinding.editTextPassword.getText().toString();
                editProfileViewModel.sendEditProfileRequest(account_id , newName, old_password , new_password ,phone);
            }
        });
        btn_editProfileBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
        btn_deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditProfileActivity.this, "Delete Account", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
