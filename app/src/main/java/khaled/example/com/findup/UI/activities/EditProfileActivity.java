package khaled.example.com.findup.UI.activities;

import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

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
    TextView txt_upload_image;
    Button btn_editProfileBack;
    ImageView myImg;
    ImageButton btn_deleteAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editProfileViewModel = new EditProfileViewModel(this);
        activityEditProfileBinding= DataBindingUtil.setContentView(this,R.layout.activity_edit_profile);
        editText_phone=findViewById(R.id.editText_phone);
        btn_editProfileBack=findViewById(R.id.btn_editProfileBack);
        btn_deleteAccount=findViewById(R.id.btn_deleteAccount);
        txt_upload_image = findViewById(R.id.txt_upload_image);
        myImg = findViewById(R.id.pic_account);
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
    private void selectImage(){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, IMAGE_REQUEST);
    }
    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        else {
            return false;
        }
    }
    public static final int MEDIA_TYPE_IMAGE = 1;
    private String mediaPath;
    private final int IMAGE_REQUEST = 1;
    private String postPath;
    public static final String IMAGE_DIRECTORY_NAME = "Android File Upload";
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            if (requestCode == IMAGE_REQUEST) {
                if(data != null){
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {
                            MediaStore.Images.Media.DATA
                    };
                    Cursor cursor = getContentResolver().query(selectedImage , filePathColumn , null , null , null);
                    assert cursor != null;
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    mediaPath = cursor.getString(columnIndex);
//                    myImg.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                    Picasso.with(this).load(mediaPath).into(myImg);
                    cursor.close();
                    postPath = mediaPath;
                }
            }
        }else if(requestCode != RESULT_CANCELED){
            Toast.makeText(this, "You Cancelled Choosing photo", Toast.LENGTH_SHORT).show();
        }

    }

    private void upload_photo() {
        if (postPath == null || postPath.equals("")) {
            Toast.makeText(this, "please select an image ", Toast.LENGTH_LONG).show();
            return;
        } else {
            File file = new File(postPath);

        }
    }
}
