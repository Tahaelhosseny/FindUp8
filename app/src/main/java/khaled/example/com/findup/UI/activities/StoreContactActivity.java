package khaled.example.com.findup.UI.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.File;

import khaled.example.com.findup.Helper.Database.DBUtility;
import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.CreateStoreResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.StoresResponse;
import khaled.example.com.findup.R;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static khaled.example.com.findup.UI.activities.StoreInformationActivity.createStore;

public class StoreContactActivity extends AppCompatActivity {

    RadioGroup radioLocation;
    RadioButton radioShowCity;
    EditText editText_country, editText_city, editText_website, editText_instagram, editText_twitter, editText_facebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_contact);

        editText_country = findViewById(R.id.editText_country);
        editText_city = findViewById(R.id.editText_city);
        editText_website = findViewById(R.id.editText_website);
        editText_instagram = findViewById(R.id.editText_instagram);
        editText_twitter = findViewById(R.id.editText_twitter);
        editText_facebook = findViewById(R.id.editText_facebook);
        radioLocation = findViewById(R.id.radioLocation);

        Button btn_contactBack = findViewById(R.id.btn_contactBack);
        btn_contactBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoreContactActivity.super.onBackPressed();
                finish();
            }
        });
        radioShowCity = findViewById(R.id.radioShowCity);
        if (getIntent().getExtras().getInt("next_id") == 2) {
            radioShowCity.setVisibility(View.GONE);
        }
        final Button btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveStore();
            }
        });
    }

    private void saveStore(){
        if (TextUtils.isEmpty(editText_country.getText().toString())){
            Toast.makeText(this, "Enter country", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(editText_city.getText().toString())){
            Toast.makeText(this, "Enter City", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(editText_website.getText().toString())){
            Toast.makeText(this, "Enter Website", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(editText_instagram.getText().toString())){
            Toast.makeText(this, "Enter Instagram", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(editText_twitter.getText().toString())){
            Toast.makeText(this, "Enter Twitter", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(editText_facebook.getText().toString())){
            Toast.makeText(this, "Enter Facebook", Toast.LENGTH_LONG).show();
            return;
        } else if (radioLocation.getCheckedRadioButtonId() == -1){
            Toast.makeText(this, "Choose Location Type", Toast.LENGTH_LONG).show();
            return;
        }

        createStore.setCountry_name_en(editText_country.getText().toString());
        createStore.setCity_name_en(editText_city.getText().toString());
        createStore.setStore_website_link(editText_website.getText().toString());
        createStore.setStore_instegram_link(editText_instagram.getText().toString());
        createStore.setStore_twitter_link(editText_twitter.getText().toString());
        createStore.setStore_facebook_link(editText_facebook.getText().toString());
        createStore.setStore_location_type(findViewById(radioLocation.getCheckedRadioButtonId()).getTag().toString());
        File logoFile = new File(createStore.getStore_logo());
        File bannerFile = new File(createStore.getStore_banner());
        RequestBody requestlogoFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), logoFile);
        RequestBody requestbannerFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), bannerFile);

        MultipartBody.Part bodylogoFile =
                MultipartBody.Part.createFormData("image", logoFile.getName(), requestlogoFile);
        MultipartBody.Part bodybannerFile =
                MultipartBody.Part.createFormData("image", bannerFile.getName(), requestbannerFile);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CreateStoreResponse> newStore =  apiService.createNewStore(createStore.getStore_desc(),
                1,
                1,
                createStore.getStore_location_type(),
                createStore.getStore_mobile(),
                "pass",
                createStore.getStore_twitter_link(),
                createStore.getStore_instegram_link(),
                createStore.getStore_facebook_link(),
                getIntent().getExtras().getInt("next_id"),
                bodylogoFile,
                bodybannerFile,
                createStore.getStore_otherlang(),
                createStore.getStore_tags(),
                createStore.getWorkDays(),
                "1:00 ",
                "10:00");

        newStore.enqueue(new Callback<CreateStoreResponse>() {
            @Override
            public void onResponse(Call<CreateStoreResponse> call, Response<CreateStoreResponse> response) {
                if (response.body().getSuccess() ==1){
                    Toast.makeText(StoreContactActivity.this,"Account Created",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(StoreContactActivity.this,response.body().getError_msg(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CreateStoreResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        switch (getIntent().getExtras().getInt("next_id")) {
            case 1:
                startActivity(new Intent(StoreContactActivity.this, AddProductTruckActivity.class));
                break;
            case 2:
                startActivity(new Intent(StoreContactActivity.this, AddProductCraftActivity.class));
                break;
            default:
                startActivity(new Intent(StoreContactActivity.this, AddProductTruckActivity.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
