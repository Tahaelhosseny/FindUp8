package findupproducts.example.com.findup.UI.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.santalu.maskedittext.MaskEditText;
import com.squareup.picasso.Picasso;

import java.io.IOException;


import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.Presenter.Activities.EditProfilePresenter;
import findupproducts.example.com.findup.UI.ViewModel.Activites.EditProfileViewModel;
import findupproducts.example.com.findup.databinding.ActivityEditProfileBinding;

public class EditProfileActivity extends AppCompatActivity {
    ActivityEditProfileBinding activityEditProfileBinding;
    EditProfileViewModel editProfileViewModel;
    Spinner mobileSpinner;
    MaskEditText editText_phone;
    TextView txt_upload_image;
    Button btn_editProfileBack;
    ImageView myImg;
    TextView type;
    ImageButton btn_deleteAccount;
    private SharedPrefManger sharedPrefManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefManger = new SharedPrefManger(this);
        editProfileViewModel = new EditProfileViewModel(this);
        activityEditProfileBinding= DataBindingUtil.setContentView(this,R.layout.activity_edit_profile);
        activityEditProfileBinding.btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfileViewModel.sendCode(sharedPrefManger.getLogin_phone());
            }
        });
        editText_phone=findViewById(R.id.editText_phone);
        btn_editProfileBack=findViewById(R.id.btn_editProfileBack);
        txt_upload_image = findViewById(R.id.txt_upload_image);
        myImg = findViewById(R.id.pic_account);
        activityEditProfileBinding.txtUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        activityEditProfileBinding.setEditProfileData(editProfileViewModel);
        if(sharedPrefManger.getLogin_type().equals("store")){
            type = findViewById(R.id.textView); type.setText("Store Name");
            activityEditProfileBinding.editTextPassword.setText(sharedPrefManger.getLogin_password());
            activityEditProfileBinding.editTextUsername.setText(sharedPrefManger.getStore_namee());
            activityEditProfileBinding.editTextPhone.setText(sharedPrefManger.getLogin_phone());
            if (!sharedPrefManger.getStore_logo().isEmpty())
                Picasso.with(this).load(SharedPrefManger.getStore_logo()).placeholder(R.color.material_color_grey_500).into(activityEditProfileBinding.picAccount);
        }else{
            activityEditProfileBinding.editTextPassword.setText(sharedPrefManger.getLogin_password());
            activityEditProfileBinding.editTextUsername.setText(sharedPrefManger.getUser_name());
            activityEditProfileBinding.editTextPhone.setText(sharedPrefManger.getLogin_phone());
        }
        activityEditProfileBinding.setPresenter(new EditProfilePresenter() {
            @Override
            public void editProfileData() {
                int account_id = sharedPrefManger.getUser_ID();
                int store_id = sharedPrefManger.getStore_ID();
                String old_password = sharedPrefManger.getLogin_password();
                String newName = activityEditProfileBinding.editTextUsername.getText().toString();
                String phone = activityEditProfileBinding.editTextPhone.getRawText();
                String new_password = activityEditProfileBinding.editTextPassword.getText().toString();
                if(account_id == 0){
                    editProfileViewModel.sendEditProfileStoreRequest(store_id , newName, old_password , new_password ,phone);
                }else{
                    editProfileViewModel.sendEditProfileRequest(account_id , newName, old_password , new_password ,phone);
                }
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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
