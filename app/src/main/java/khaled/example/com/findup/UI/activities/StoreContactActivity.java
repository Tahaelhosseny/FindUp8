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
import khaled.example.com.findup.R;
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

        /*@Multipart
        @Headers({"Content-Type: application/x-www-form-urlencoded"})
        @POST(ApiClient.PATH_URL+"stores?tag=create_store_account&HashSecure="+HASH)
        Call<CreateStoreResponse> createNewStore(
                @Part("store_name") RequestBody store_name,
                @Part("store_desc") RequestBody store_desc ,
                @Part("country_id") RequestBody country_id ,
                @Part("city_id") RequestBody city_id ,
                @Part("location_type") RequestBody location_type ,
                @Part("mobile") RequestBody mobile ,
                @Part("password") RequestBody password ,
                @Part("twitter_link") RequestBody twitter_link ,
                @Part("instegram_link") RequestBody instegram_link ,
                @Part("facebook_link") RequestBody facebook_link,
                @Part("cat_id") RequestBody cat_id,
                @Part MultipartBody.Part store_logo,
                @Part MultipartBody.Part store_banner,
                @Part("store_otherlang") RequestBody store_otherlang,
                @Part("store_tags") RequestBody store_tags,
                @Part("work_days") RequestBody work_days,
                @Part("work_fromtime") RequestBody work_fromtime,
                @Part("work_totime") RequestBody work_totime);*/

        MultipartBody.Part store_name = MultipartBody.Part.createFormData("store_name", createStore.getStore_name());
        MultipartBody.Part store_desc = MultipartBody.Part.createFormData("store_desc", createStore.getStore_desc());
        MultipartBody.Part country_id = MultipartBody.Part.createFormData("country_id", "1");
        MultipartBody.Part city_id = MultipartBody.Part.createFormData("city_id", "1");
        MultipartBody.Part location_type = MultipartBody.Part.createFormData("location_type", createStore.getStore_location_type());
        MultipartBody.Part mobile = MultipartBody.Part.createFormData("mobile", createStore.getStore_mobile());
        MultipartBody.Part password = MultipartBody.Part.createFormData("password", "pass");
        MultipartBody.Part twitter_link = MultipartBody.Part.createFormData("twitter_link", createStore.getStore_twitter_link());
        MultipartBody.Part instegram_link = MultipartBody.Part.createFormData("instegram_link", createStore.getStore_instegram_link());
        MultipartBody.Part facebook_link = MultipartBody.Part.createFormData("facebook_link", createStore.getStore_facebook_link());
        MultipartBody.Part cat_id = MultipartBody.Part.createFormData("cat_id", String.valueOf(getIntent().getExtras().getInt("next_id")));
        MultipartBody.Part store_otherlang = MultipartBody.Part.createFormData("store_otherlang", createStore.getStore_otherlang());
        MultipartBody.Part store_tags = MultipartBody.Part.createFormData("store_tags", createStore.getStore_tags());
        MultipartBody.Part work_days = MultipartBody.Part.createFormData("work_days", createStore.getWorkDays());
        MultipartBody.Part work_fromtime = MultipartBody.Part.createFormData("work_fromtime", "1:00");
        MultipartBody.Part work_totime = MultipartBody.Part.createFormData("work_totime", "10:00");

        /*RequestBody name = RequestBody.create(
                MediaType.parse("text/plain"),
                createStore.getStore_name());
        RequestBody desc = RequestBody.create(
                MediaType.parse("text/plain"),
                createStore.getStore_desc());
        RequestBody country_id = RequestBody.create(
                MediaType.parse("text/plain"),
                "1");
        RequestBody city_id = RequestBody.create(
                MediaType.parse("text/plain"),
                "1");
        RequestBody loc_type = RequestBody.create(
                MediaType.parse("text/plain"),
                createStore.getStore_location_type());
        RequestBody mobile = RequestBody.create(
                MediaType.parse("text/plain"),
                createStore.getStore_mobile());
        RequestBody pass = RequestBody.create(
                MediaType.parse("text/plain"),
                "pass");
        RequestBody twit = RequestBody.create(
                MediaType.parse("text/plain"),
                createStore.getStore_twitter_link());
        RequestBody insta = RequestBody.create(
                MediaType.parse("text/plain"),
                createStore.getStore_instegram_link());
        RequestBody face = RequestBody.create(
                MediaType.parse("text/plain"),
                createStore.getStore_facebook_link());
        RequestBody catId = RequestBody.create(
                MediaType.parse("text/plain"),
                createStore.getStore_cat_id());
        RequestBody lang = RequestBody.create(
                MediaType.parse("text/plain"),
                createStore.getStore_otherlang());
        RequestBody tags = RequestBody.create(
                MediaType.parse("text/plain"),
                createStore.getStore_tags());
        RequestBody days = RequestBody.create(
                MediaType.parse("text/plain"),
                createStore.getWorkDays());
        RequestBody timeFrom = RequestBody.create(
                MediaType.parse("text/plain"),
                "1:00 ");
        RequestBody timeTo = RequestBody.create(
                MediaType.parse("text/plain"),
                "10:00");*/
        RequestBody requestlogoFile =
                RequestBody.create(MediaType.parse("image/png"), logoFile);
        RequestBody requestbannerFile =
                RequestBody.create(MediaType.parse("image/png"), bannerFile);

        MultipartBody.Part bodylogoFile =
                MultipartBody.Part.createFormData("store_logo", logoFile.getName(), requestlogoFile);
        MultipartBody.Part bodybannerFile =
                MultipartBody.Part.createFormData("store_banner", bannerFile.getName(), requestbannerFile);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CreateStoreResponse> newStore =  apiService.createNewStore(
                store_name,
                store_desc,
                country_id,
                city_id,
                location_type,
                mobile,
                password,
                twitter_link,
                instegram_link,
                facebook_link,
                cat_id,
                bodylogoFile,
                bodybannerFile,
                store_otherlang,
                store_tags,
                work_days,
                work_fromtime,
                work_totime
        );

        newStore.enqueue(new Callback<CreateStoreResponse>() {
            @Override
            public void onResponse(Call<CreateStoreResponse> call, Response<CreateStoreResponse> response) {
                Log.e("Success", new Gson().toJson(response.body()));
                if (response.body().getSuccess() ==1){
                    Toast.makeText(StoreContactActivity.this,"Account Created",Toast.LENGTH_SHORT).show();
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
                else{
                    Log.e("Myeror", response.body().getError_msg());
                    Toast.makeText(StoreContactActivity.this,response.body().getError_msg(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CreateStoreResponse> call, Throwable t) {
                Log.e("Myeror", t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
