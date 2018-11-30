package findupproducts.example.com.findup.UI.activities;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.InflateException;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.f2prateek.rx.preferences2.Preference;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import findupproducts.example.com.findup.Helper.Location.LocationUtility;
import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.StoreAddressResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.Helper.UI_Utility;
import findupproducts.example.com.findup.Helper.Utility;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.models.CurrentLocation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetLocationActivity extends AppCompatActivity implements OnMapReadyCallback {

    static final int DIALOG_ID = 0;
    int hour_text , minute_text;
    int time_type = 0;

    MapView mMapView;
    LatLng selectedLatLng;
    TextView placeTxt, fromTxt, toTxt;
    String selectedAddress;
    CheckBox[] days;
    StringBuilder selectedDays;
    String from, to;
    boolean newStore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);

        if (getIntent().hasExtra("new_store"))
            newStore = true;

        placeTxt = findViewById(R.id.locationDetailsTxt);
        fromTxt = findViewById(R.id.fromTxt);
        toTxt = findViewById(R.id.toTxt);

        days = new CheckBox[7];
        days[0] = findViewById(R.id.saturday);
        days[1] = findViewById(R.id.sunday);
        days[2] = findViewById(R.id.monday);
        days[3] = findViewById(R.id.tuesday);
        days[4] = findViewById(R.id.wednesday);
        days[5] = findViewById(R.id.thursday);
        days[6] = findViewById(R.id.friday);

        try {
            MapsInitializer.initialize(this);
            mMapView = findViewById(R.id.map);
            mMapView.onCreate(savedInstanceState);
            mMapView.getMapAsync(this);
        } catch (InflateException e) {
            e.printStackTrace();
        }

        findViewById(R.id.closeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAddress();
            }
        });

        fromTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time_type = 1;
                showTimeDialog();
            }
        });
        toTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time_type = 2;
                showTimeDialog();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        CurrentLocation currentLocation = new CurrentLocation();
        Utility.UpdateCurrentLocation(this, this);

        SharedPrefManger sharedPrefManger = new SharedPrefManger(this);
        Preference<Float> Latitude = sharedPrefManger.getLatitude();
        Latitude.asObservable().subscribe(val -> LocationUtility.LatitudeToCurrentLocationModel(val, currentLocation));
        Preference<Float> Longitude = sharedPrefManger.getLongitude();
        Longitude.asObservable().subscribe(val -> LocationUtility.LongitudeToCurrentLocationModel(val, currentLocation));

        LatLng location = new LatLng(30.044281, 31.340002);
        //LatLng sydney = new LatLng(currentLocation.getLocation().latitude, currentLocation.getLocation().longitude);
        googleMap.addMarker(new MarkerOptions().position(location).icon(
                BitmapDescriptorFactory.fromResource(R.drawable.current_location_marker)
        ).title("Your Location"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.latitude + ((location.latitude * 14) / 100000), location.longitude), 14));
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                selectedLatLng = latLng;
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);

                googleMap.clear();
                //googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                googleMap.addMarker(markerOptions);

                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(SetLocationActivity.this, Locale.getDefault());

                try {
                    addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    selectedAddress = addresses.get(0).getAddressLine(0);
                    markerOptions.title(selectedAddress);
                    placeTxt.setText(selectedAddress);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void saveAddress(){
        selectedDays = new StringBuilder();

        for (CheckBox day : days) {
            if (day.isChecked())
                selectedDays.append(day.getText().toString()).append(" ");
        }

        if(TextUtils.isEmpty(selectedAddress)){
            Toast.makeText(this, "Specify Location on the map", Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.equals(fromTxt.getText().toString(), "From: ") || TextUtils.equals(toTxt.getText().toString(), "To: ")){
            Toast.makeText(this, "Specify Time", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(selectedDays.toString().isEmpty()){
            Toast.makeText(this, "Specify Days", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPrefManger sharedPrefManger = new SharedPrefManger(this);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<StoreAddressResponse> setStoreAddress = apiService.setStoreAddress(sharedPrefManger.getStore_ID(),selectedLatLng.longitude,selectedLatLng.latitude,selectedDays.toString(),fromTxt.getText().toString(),toTxt.getText().toString(),selectedAddress);
        setStoreAddress.enqueue(new Callback<StoreAddressResponse>() {
            @Override
            public void onResponse(Call<StoreAddressResponse> call, Response<StoreAddressResponse> response) {
                if(response.body().getSuccess() == 1){
                    Toast.makeText(SetLocationActivity.this, "Address Set Success", Toast.LENGTH_SHORT).show();
                    if (newStore){
                        startActivity(new Intent(SetLocationActivity.this, AddProductTruckActivity.class));
                        SetLocationActivity.this.finish();
                    }else
                        SetLocationActivity.this.finish();
                }else{
                    Toast.makeText(SetLocationActivity.this, "Error setting Address", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<StoreAddressResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void  showTimeDialog(){
        showDialog(DIALOG_ID);
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == DIALOG_ID){
            return new TimePickerDialog(SetLocationActivity.this ,timePickerListener , hour_text , minute_text , true);
        }
        return null;
    }
    protected TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            hour_text = hour; minute_text = minute;
            minute_text = (hour_text*60)+minute_text;
            String newtime = UI_Utility.fromMinutesToHHmm(minute_text);
            if(time_type == 1){
                from = newtime;
                fromTxt.setText("To: "+newtime);
            }else{
                to = newtime;
                toTxt.setText("From: "+newtime);
            }
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMapView != null)
            mMapView.onResume();
    }
}
