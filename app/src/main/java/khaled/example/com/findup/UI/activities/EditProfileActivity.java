package khaled.example.com.findup.UI.activities;

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

import java.io.IOException;


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
        activityEditProfileBinding.btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfileViewModel.sendCode(SharedPrefManger.getLogin_phone());
            }
        });
        editText_phone=findViewById(R.id.editText_phone);
        btn_editProfileBack=findViewById(R.id.btn_editProfileBack);
        txt_upload_image = findViewById(R.id.txt_upload_image);
        myImg = findViewById(R.id.pic_account);
        activityEditProfileBinding.txtUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });
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
//        btn_deleteAccount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(EditProfileActivity.this, "Delete Account", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    private int PICK_IMAGE_REQUEST = 1;

    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;

    //Bitmap to get image from gallery
    private Bitmap bitmap;

    //Uri to store the image uri
    private Uri filePath;



    public void imageIntentChoose(){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 1);
    }


    //method to show file chooser
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    //handling the image chooser activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                activityEditProfileBinding.picAccount.setImageBitmap(bitmap);
                Toast.makeText(this, ""+filePath, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //method to get the file path from uri
    public String getPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }


    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

}
