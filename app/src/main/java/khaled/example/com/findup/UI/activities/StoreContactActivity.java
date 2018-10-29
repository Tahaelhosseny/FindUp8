package khaled.example.com.findup.UI.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;

import khaled.example.com.findup.Helper.Database.DBUtility;
import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.CreateStoreResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.StoresResponse;
import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.R;
import khaled.example.com.findup.models.CreateStore;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import static khaled.example.com.findup.UI.activities.StoreInformationActivity.createStore;

public class StoreContactActivity extends AppCompatActivity {

    RadioGroup radioLocation;
    RadioButton radioShowCity;
    EditText editText_country, editText_city, editText_website, editText_instagram, editText_twitter, editText_facebook, editText_mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_contact);

        editText_country = findViewById(R.id.editText_country);
        editText_city = findViewById(R.id.editText_city);
        editText_mobile = findViewById(R.id.editText_mobile);
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
            Toast.makeText(this, "Enter Country", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(editText_city.getText().toString())){
            Toast.makeText(this, "Enter City", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(editText_mobile.getText().toString())){
            Toast.makeText(this, "Enter Mobile", Toast.LENGTH_LONG).show();
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
        createStore.setStore_mobile(editText_mobile.getText().toString());
        createStore.setStore_instegram_link(editText_instagram.getText().toString());
        createStore.setStore_twitter_link(editText_twitter.getText().toString());
        createStore.setStore_facebook_link(editText_facebook.getText().toString());
        createStore.setStore_location_type(findViewById(radioLocation.getCheckedRadioButtonId()).getTag().toString());
        createStore.setStore_cat_id(String.valueOf(getIntent().getExtras().getInt("next_id")));
        File logoFile = new File(createStore.getStore_logo());
        File bannerFile = new File(createStore.getStore_banner());

        MultipartBody.Part store_name = MultipartBody.Part.createFormData("store_name", createStore.getStore_name());
        MultipartBody.Part store_desc = MultipartBody.Part.createFormData("store_desc", createStore.getStore_desc());
        MultipartBody.Part country_id = MultipartBody.Part.createFormData("country_id", "1");
        MultipartBody.Part city_id = MultipartBody.Part.createFormData("city_id", "1");
        MultipartBody.Part location_type = MultipartBody.Part.createFormData("location_type", createStore.getStore_location_type());
        MultipartBody.Part mobile = MultipartBody.Part.createFormData("mobile", createStore.getStore_mobile());
        MultipartBody.Part password = MultipartBody.Part.createFormData("password", "pass");
        MultipartBody.Part website_link = MultipartBody.Part.createFormData("website_link", createStore.getStore_website_link());
        MultipartBody.Part twitter_link = MultipartBody.Part.createFormData("twitter_link", createStore.getStore_twitter_link());
        MultipartBody.Part instegram_link = MultipartBody.Part.createFormData("instegram_link", createStore.getStore_instegram_link());
        MultipartBody.Part facebook_link = MultipartBody.Part.createFormData("facebook_link", createStore.getStore_facebook_link());
        MultipartBody.Part cat_id = MultipartBody.Part.createFormData("cat_id", "1");
        MultipartBody.Part user_id = MultipartBody.Part.createFormData("parent_user_id", "1");
        MultipartBody.Part store_otherlang = MultipartBody.Part.createFormData("store_otherlang", createStore.getStore_otherlang());
        MultipartBody.Part store_tags = MultipartBody.Part.createFormData("store_tags", createStore.getStore_tags());
        /*MultipartBody.Part work_days = MultipartBody.Part.createFormData("work_days", createStore.getWorkDays());
        MultipartBody.Part work_fromtime = MultipartBody.Part.createFormData("work_fromtime", "1:00");
        MultipartBody.Part work_totime = MultipartBody.Part.createFormData("work_totime", "10:00");
        MultipartBody.Part store_logo_base64 = MultipartBody.Part.createFormData("store_logo_base64", "");
        MultipartBody.Part store_banner_base64 = MultipartBody.Part.createFormData("store_banner_base64", "-");*/

        RequestBody requestlogoFile = RequestBody.create(MediaType.parse("image/png"), logoFile);
        RequestBody requestbannerFile = RequestBody.create(MediaType.parse("image/png"), bannerFile);

        MultipartBody.Part bodylogoFile = MultipartBody.Part.createFormData("store_logo", logoFile.getName(), requestlogoFile);
        MultipartBody.Part bodybannerFile = MultipartBody.Part.createFormData("store_banner", bannerFile.getName(), requestbannerFile);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CreateStoreResponse> newStore =  apiService.createNewStore(
                store_name,
                store_desc,
                country_id,
                city_id,
                location_type,
                mobile,
                password,
                website_link,
                twitter_link,
                instegram_link,
                facebook_link,
                cat_id,
                bodylogoFile,
                bodybannerFile,
                store_otherlang,
                store_tags,
                user_id
        );

        newStore.enqueue(new Callback<CreateStoreResponse>() {
            @Override
            public void onResponse(Call<CreateStoreResponse> call, Response<CreateStoreResponse> response) {
                Log.e("Success", new Gson().toJson(response.body()));
                nextStep(response.body().getData().get(0).getStore_id());
            }

            @Override
            public void onFailure(Call<CreateStoreResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void nextStep(int storeId){
        SharedPrefManger sharedPrefManger = new SharedPrefManger(this);
        sharedPrefManger.setStoreID(storeId);
        Toast.makeText(StoreContactActivity.this,""+storeId,Toast.LENGTH_SHORT).show();
        if (radioLocation.getCheckedRadioButtonId() == R.id.radioDynamicLocation){
            Toast.makeText(StoreContactActivity.this,"Account Created",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(StoreContactActivity.this, AddProductTruckActivity.class));
            finish();
        } else {
            startActivity(new Intent(StoreContactActivity.this, WorkDaysActivity.class));
            finish();
        }
    }
}
