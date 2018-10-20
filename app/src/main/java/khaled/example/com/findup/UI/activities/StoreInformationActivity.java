package khaled.example.com.findup.UI.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import khaled.example.com.findup.Manifest;
import khaled.example.com.findup.R;
import khaled.example.com.findup.models.Store;

public class StoreInformationActivity extends AppCompatActivity {

    private static final int PICK_LOGO = 1;
    private static final int PICK_BANNER = 2;
    private static final int PICK_WORK_DAYS = 3;

    public static Store createStore;

    final int appVersion = Build.VERSION.SDK_INT;

    TextView txt_storeName, txt_otherLanguage, txt_tags, txt_workdays;
    EditText editText_storeName, editText_otherLanguage, editText_tags;
    ImageView imgLogo, imgBanner;
    Uri selectedLogo, selectedBanner;
    String workDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_information);
        createStore = new Store();

        if (appVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (!checkIfAlreadyhavePermission()) {
                ActivityCompat.requestPermissions(StoreInformationActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }

        imgLogo = findViewById(R.id.pic_logo);
        imgBanner = findViewById(R.id.pic_banner);
        imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImg(PICK_LOGO);
            }
        });

        imgBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImg(PICK_BANNER);
            }
        });

        editText_storeName = findViewById(R.id.editText_storeName);
        txt_storeName = findViewById(R.id.txt_storeName);
        txt_otherLanguage = findViewById(R.id.txt_otherLanguage);
        txt_tags = findViewById(R.id.txt_tags);
        txt_workdays = findViewById(R.id.txt_workdays);
        editText_otherLanguage = findViewById(R.id.editText_otherLanguage);
        editText_tags = findViewById(R.id.editText_tags);
        if (getIntent().getExtras().getInt("next_id") == 2) {
            txt_storeName.setText(R.string.your_name);
            txt_otherLanguage.setVisibility(View.GONE);
            editText_otherLanguage.setVisibility(View.GONE);
            txt_tags.setVisibility(View.GONE);
            editText_tags.setVisibility(View.GONE);
        }
        Button btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextStep();
            }
        });
        Button btn_informationBack = findViewById(R.id.btn_informaionBack);
        btn_informationBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoreInformationActivity.super.onBackPressed();
                finish();
            }
        });



       /* final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = getLayoutInflater().inflate(R.layout.week_days_bottom_sheet, null);
        mBottomSheetDialog.setContentView(sheetView);
*/
        LinearLayout add_days = findViewById(R.id.add_days);


        add_days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mBottomSheetDialog.show();
                startActivityForResult(new Intent(StoreInformationActivity.this, WorkDaysActivity.class), 3);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK && data != null){
            switch(requestCode) {
                case (PICK_LOGO) : {
                    selectedLogo = data.getData();
                    assert selectedLogo != null;
                    Bitmap bitmap = BitmapFactory.decodeFile(selectedLogo.getPath());
                    imgLogo.setImageBitmap(bitmap);
                    createStore.setStore_logo(selectedLogo.getPath());
                    break;
                }
                case (PICK_BANNER) : {
                    selectedBanner = data.getData();
                    assert selectedBanner != null;
                    Bitmap bitmap = BitmapFactory.decodeFile(selectedBanner.getPath());
                    imgBanner.setImageBitmap(bitmap);
                    createStore.setStore_banner(selectedBanner.getPath());
                    break;
                }
                case (PICK_WORK_DAYS) : {
                    String[] days = data.getStringArrayExtra("days");
                    workDays =  Arrays.toString(days);
                    txt_workdays.setText(workDays);
                    createStore.setStore_workdays(workDays);
                    break;
                }
            }
        }
    }

    private void pickImg(int pickerTag){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), pickerTag);
    }

    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void nextStep(){
        if (TextUtils.isEmpty(editText_storeName.getText().toString())){
            Toast.makeText(this, "Enter Store Name", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(editText_otherLanguage.getText().toString())){
            Toast.makeText(this, "Enter Language", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(editText_tags.getText().toString())){
            Toast.makeText(this, "Enter Tags", Toast.LENGTH_LONG).show();
            return;
        }

        createStore.setStore_name(editText_storeName.getText().toString());
        createStore.setStore_otherlang(editText_otherLanguage.getText().toString());
        createStore.setStore_tags(editText_tags.getText().toString());

        Intent transferIntent = new Intent(StoreInformationActivity.this, StoreContactActivity.class);
        transferIntent.putExtra("next_id", getIntent().getExtras().getInt("next_id"));
        startActivity(transferIntent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
