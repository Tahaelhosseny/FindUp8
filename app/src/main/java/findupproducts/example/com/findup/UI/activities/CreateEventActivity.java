package findupproducts.example.com.findup.UI.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.sql.PooledConnection;

import findupproducts.example.com.findup.Helper.FilePath;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.Presenter.Activities.CreateEventPresenter;
import findupproducts.example.com.findup.UI.ViewModel.Activites.CreateEventViewModel;
import findupproducts.example.com.findup.databinding.ActivityCreateEventBinding;
import findupproducts.example.com.findup.models.Event;

public class CreateEventActivity extends AppCompatActivity {
    private static final int PICK_BANNER = 1;
    private static final int PLACE_PICKER_REQUEST = 2;
    Event eventToCreate;
    Uri selectedBanner;
    static final int DIALOG_ID = 0;
    int hour_text , minute_text;
    int status = 0;
    final int appVersion = Build.VERSION.SDK_INT;
    int date_status = 0;
    DatePickerDialog datePickerDialog;
    Calendar calendar;
    ActivityCreateEventBinding activityCreateEventBinding;
    CreateEventViewModel createEventViewModel;
    Button mapBtn;
    String selectedAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createEventViewModel = new CreateEventViewModel(this);
        activityCreateEventBinding= DataBindingUtil.setContentView(this,R.layout.activity_create_event);
        if (appVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (!checkIfAlreadyhavePermission()) {
                ActivityCompat.requestPermissions(CreateEventActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
        eventToCreate = new Event();
        mapBtn = findViewById(R.id.setLocBtn);
        Button btn_createEventBack=findViewById(R.id.btn_createEventBack);
        activityCreateEventBinding.setCreateStoreEvents(createEventViewModel);
        activityCreateEventBinding.picBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImg(PICK_BANNER);
            }
        });
        activityCreateEventBinding.setPresenter(new CreateEventPresenter() {
            @Override
            public void addNewStoreEvent() {
                saveEvent();
            }
        });
        activityCreateEventBinding.startAtTxtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = 1;
                showTimeDialog();
            }
        });
        activityCreateEventBinding.endAtTxtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = 2;
                showTimeDialog();
            }
        });
        activityCreateEventBinding.daysInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date_status = 1;
                pickDate();
            }
        });

        btn_createEventBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateEventActivity.super.onBackPressed();
                finish();
            }
        });

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    pickAddress();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    private void pickDate(){
        calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        datePickerDialog = new DatePickerDialog(CreateEventActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                day = day;
                year = year;
                month = month+1;
                activityCreateEventBinding.daysInfo.setText(day + "-" + month + "-" + year);
            }
        },day , month , year
        );
        datePickerDialog.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null){
            switch(requestCode) {
                case (PICK_BANNER) : {
                    selectedBanner = data.getData();
                    assert selectedBanner != null;
                    String path = FilePath.getPath(this, selectedBanner);
                    Bitmap selectFile = BitmapFactory.decodeFile(path);
                    activityCreateEventBinding.picBanner.setImageBitmap(selectFile);
                    eventToCreate.setEvent_photo(path);
                    break;
                   }
             case (PLACE_PICKER_REQUEST):{
                 Place place = PlacePicker.getPlace(this, data);
                 eventToCreate.setEvent_latitude(String.valueOf(place.getLatLng().latitude));
                 eventToCreate.setEvent_longitude(String.valueOf(place.getLatLng().longitude));

                 Geocoder geocoder;
                 List<Address> addresses;
                 geocoder = new Geocoder(this, Locale.getDefault());

                 try {
                     addresses = geocoder.getFromLocation(place.getLatLng().latitude, place.getLatLng().longitude, 1);
                     selectedAddress = addresses.get(0).getAddressLine(0);
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
                }
            }
        }
    }

    private void saveEvent(){
        if(eventToCreate.getEvent_photo() == null){
            Toast.makeText(CreateEventActivity.this, "Choose Event Photo", Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(activityCreateEventBinding.editTextEventName.getText())){
            Toast.makeText(CreateEventActivity.this, "Specify Event Name", Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(activityCreateEventBinding.editTextDescription.getText())){
            Toast.makeText(CreateEventActivity.this, "Specify Event Description", Toast.LENGTH_SHORT).show();
            return;
        }else if(activityCreateEventBinding.startAtTxtTime.getText().equals("09:00") || activityCreateEventBinding.endAtTxtTime.getText().equals("06:00")){
            Toast.makeText(CreateEventActivity.this, "Specify Event Start Time And End Time", Toast.LENGTH_SHORT).show();
            return;
        }else if(activityCreateEventBinding.daysInfo.getText().equals("start date")){
            Toast.makeText(CreateEventActivity.this, "Specify Event Date", Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(selectedAddress)){
            Toast.makeText(CreateEventActivity.this, "Specify Address", Toast.LENGTH_SHORT).show();
            return;
        }

        createEventViewModel.addNewEvent(String.valueOf(activityCreateEventBinding.editTextEventName.getText()) ,
                String.valueOf(activityCreateEventBinding.daysInfo.getText()) ,
                activityCreateEventBinding.daysInfo.getText().toString() ,
                (String.valueOf(activityCreateEventBinding.startAtTxtTime.getText())+String.valueOf( activityCreateEventBinding.endAtTxtTime.getText())),
                String.valueOf(activityCreateEventBinding.editTextDescription.getText()),
                selectedAddress , SharedPrefManger.getStore_ID() , eventToCreate.getEvent_longitude() , eventToCreate.getEvent_latitude() , eventToCreate.getEvent_photo());
    }

    private void pickImg(int pickerTag){
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), pickerTag);
    }
    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CreateEventActivity.this , StoreEventsActivity.class));
        finish();
    }
    private void  showTimeDialog(){
        showDialog(DIALOG_ID);
        }
    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == DIALOG_ID){
            return new TimePickerDialog(CreateEventActivity.this ,timePickerListener , hour_text , minute_text , true);
        }
        return null;
    }
    protected TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            hour_text = hour; minute_text = minute;
            if(status == 1){
                activityCreateEventBinding.startAtTxtTime.setText(hour_text+":"+minute_text);
            }else{
                activityCreateEventBinding.endAtTxtTime.setText(hour_text+":"+minute_text);
            }
        }
    };

    private void pickAddress() throws GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
    }
}
