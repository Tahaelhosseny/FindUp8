package findupproducts.example.com.findup.UI.activities;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;

import findupproducts.example.com.findup.Helper.Database.DBUtility;
import findupproducts.example.com.findup.Helper.FilePath;
import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.CitiesResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.CountriesResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.CreateStoreResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.StoresResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.Helper.UI_Utility;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.models.City;
import findupproducts.example.com.findup.models.Country;
import findupproducts.example.com.findup.models.CreateStore;
import findupproducts.example.com.findup.models.User;
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

import static findupproducts.example.com.findup.UI.activities.StoreInformationActivity.createStore;

public class StoreContactActivity extends AppCompatActivity {

    RadioGroup radioLocation;
    RadioButton radioShowCity;
    EditText editText_website, editText_instagram, editText_twitter, editText_facebook, editText_mobile, editText_password,editText_email;
    AutoCompleteTextView editText_country , editText_city;
    int countryId = 0;
    int cityId = 0;
    private String phoneKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_contact);

        editText_country = findViewById(R.id.editText_country);
        editText_city = findViewById(R.id.editText_city);
        editText_mobile = findViewById(R.id.editText_mobile);
        editText_email = findViewById(R.id.editText_email);
        editText_password = findViewById(R.id.editText_password);
        editText_website = findViewById(R.id.editText_website);
        editText_instagram = findViewById(R.id.editText_instagram);
        editText_twitter = findViewById(R.id.editText_twitter);
        editText_facebook = findViewById(R.id.editText_facebook);
        radioLocation = findViewById(R.id.radioLocation);
        Spinner mobileSpinner = findViewById(R.id.mobileSpinner);

        String[] items = new String[]{"+2", "+966", "+900"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, items);
        mobileSpinner.setAdapter(adapter);
        editText_mobile.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mobileSpinner.performClick();
                return true;
            }
        });

        mobileSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                phoneKey = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button btn_contactBack = findViewById(R.id.btn_contactBack);
        btn_contactBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoreContactActivity.super.onBackPressed();
                finish();
            }
        });
        /*radioShowCity = findViewById(R.id.radioShowCity);
        if (getIntent().getExtras().getInt("next_id") == 2) {
            radioShowCity.setVisibility(View.GONE);
        }*/
        final Button btn_next = findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveStore();
            }
        });

        loadCountries();
    }

    private void saveStore(){
        String website, Instagram, Twitter, facebook;
        website = "";
        Instagram = "";
        Twitter = "";
        facebook = "";

        /*if (TextUtils.isEmpty(editText_country.getText().toString()) && countryId == -1){
            Toast.makeText(this, "Enter Country", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(editText_city.getText().toString()) && cityId == -1){
            Toast.makeText(this, "Enter City", Toast.LENGTH_LONG).show();
            return;
        } else*/ if (TextUtils.isEmpty(editText_mobile.getText().toString())){
            Toast.makeText(this, "Enter Mobile", Toast.LENGTH_LONG).show();
            return;
        } else if (radioLocation.getCheckedRadioButtonId() == -1){
            Toast.makeText(this, "Select Location", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(editText_password.getText().toString())){
            Toast.makeText(this, "Enter Mobile", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(editText_email.getText().toString())){
            Toast.makeText(this, "Enter Email", Toast.LENGTH_LONG).show();
            return;
        }/*else if (TextUtils.isEmpty(editText_website.getText().toString())){
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
        }*/

        final AlertDialog alertDialog = UI_Utility.ShowProgressDialog(this, true);
        alertDialog.show();

        String storeMobile = phoneKey + editText_mobile.getText().toString();
        storeMobile = storeMobile.replace("|","");
        storeMobile = storeMobile.replace("+","");

        createStore.setStore_website_link(website);
        createStore.setStore_mobile(storeMobile);
        createStore.setStore_instegram_link(Instagram);
        createStore.setStore_twitter_link(Twitter);
        createStore.setStore_facebook_link(facebook);
        createStore.setStore_location_type(findViewById(radioLocation.getCheckedRadioButtonId()).getTag().toString());
        File logoFile = new File(createStore.getStore_logo());
        Log.e("MyPath", createStore.getStore_logo());
        File bannerFile = new File(createStore.getStore_banner());

        MultipartBody.Part store_name = MultipartBody.Part.createFormData("store_name", createStore.getStore_name());
        MultipartBody.Part store_desc = MultipartBody.Part.createFormData("store_desc", createStore.getStore_desc());
        MultipartBody.Part country_id = MultipartBody.Part.createFormData("country_id", ""+countryId);
        MultipartBody.Part city_id = MultipartBody.Part.createFormData("city_id", ""+cityId);
        MultipartBody.Part location_type = MultipartBody.Part.createFormData("location_type", createStore.getStore_location_type());
        MultipartBody.Part mobile = MultipartBody.Part.createFormData("mobile", createStore.getStore_mobile());
        MultipartBody.Part email = MultipartBody.Part.createFormData("email", editText_email.getText().toString());
        MultipartBody.Part password = MultipartBody.Part.createFormData("password", editText_password.getText().toString());
        MultipartBody.Part website_link = MultipartBody.Part.createFormData("website_link", createStore.getStore_website_link());
        MultipartBody.Part twitter_link = MultipartBody.Part.createFormData("twitter_link", createStore.getStore_twitter_link());
        MultipartBody.Part instegram_link = MultipartBody.Part.createFormData("instegram_link", createStore.getStore_instegram_link());
        MultipartBody.Part facebook_link = MultipartBody.Part.createFormData("facebook_link", createStore.getStore_facebook_link());
        MultipartBody.Part cat_id = MultipartBody.Part.createFormData("cat_id", createStore.getStore_cat_id());
        MultipartBody.Part user_id = MultipartBody.Part.createFormData("parent_user_id", "1");
        MultipartBody.Part store_otherlang = MultipartBody.Part.createFormData("store_otherlang", createStore.getStore_otherlang());
        MultipartBody.Part store_tags = MultipartBody.Part.createFormData("store_tags", createStore.getStore_tags());
        RequestBody requestlogoFile = RequestBody.create(MediaType.parse("image/png"), logoFile);
        RequestBody requestbannerFile = RequestBody.create(MediaType.parse("image/png"), bannerFile);
        MultipartBody.Part bodylogoFile = MultipartBody.Part.createFormData("store_logo", logoFile.getName(), requestlogoFile);
        MultipartBody.Part bodybannerFile = MultipartBody.Part.createFormData("store_banner", bannerFile.getName(), requestbannerFile);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<CreateStoreResponse> newStore =  apiInterface.createNewStore(
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
                user_id,
                email
        );

        newStore.enqueue(new Callback<CreateStoreResponse>() {
            @Override
            public void onResponse(Call<CreateStoreResponse> call, Response<CreateStoreResponse> response) {
                Log.e("Success", new Gson().toJson(response.body()));
                if (response.body().getSuccess() == 1)
                    nextStep(response.body().getData().get(0).getStore_id());
                else
                    Toast.makeText(StoreContactActivity.this,response.body().getError_msg(),Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }

            @Override
            public void onFailure(Call<CreateStoreResponse> call, Throwable t) {
                t.printStackTrace();
                alertDialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void nextStep(int storeId) {
        SharedPrefManger sharedPrefManger = new SharedPrefManger(this);
        sharedPrefManger.setIsLoggedIn(true);
        sharedPrefManger.setLoginType("store");
        sharedPrefManger.setStoreID(storeId);
        sharedPrefManger.setStore_namee(createStore.getStore_name());
        sharedPrefManger.setStore_desc(createStore.getStore_desc());
        sharedPrefManger.setStore_rev("0.0");
        Toast.makeText(StoreContactActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
        if (radioLocation.getCheckedRadioButtonId() == R.id.radioStaticLocation) {
            sharedPrefManger.setStoreLocation_type("Static");
            startActivity(new Intent(StoreContactActivity.this, SetLocationActivity.class)
                    .putExtra("new_store", true));
            finish();
        } else if ((radioLocation.getCheckedRadioButtonId() == R.id.radioDynamicLocation)){
            sharedPrefManger.setStoreLocation_type("Dynamic");
            goToAddProducts();
        }else
            goToAddProducts();
    }

    private void goToAddProducts(){
        if (createStore.getStore_cat_id().equals("2"))
            startActivity(new Intent(StoreContactActivity.this, AddProductTruckActivity.class)
                    .putExtra("isCraft", true));
        else
            startActivity(new Intent(StoreContactActivity.this, AddProductTruckActivity.class));
        finish();
    }

    private void loadCountries(){
        if (createStore.getCounriesList() == null)
            return;

        String[] countries = new String[createStore.getCounriesList().size()];
        for (int i = 0; i < createStore.getCounriesList().size(); i++)
            countries[i] = createStore.getCounriesList().get(i).getName_en();

        ArrayAdapter arrayAdapter= new ArrayAdapter<>(StoreContactActivity.this, android.R.layout.simple_dropdown_item_1line, countries);
        editText_country.setAdapter(arrayAdapter);
        editText_country.setInputType(0);

        editText_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_country.showDropDown();
            }
        });

        editText_country.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showCities(parent.getItemAtPosition(position).toString());
            }
        });
    }

    private void showCities(String s) {
        Log.e("MyData", s);
        editText_city.setEnabled(true);
        for (Country country : createStore.getCounriesList()){
            if (s.equals(country.getName_en()))
                countryId = country.getCountry_id();
        }
        Log.e("MyData", ""+countryId);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<CitiesResponse> getCities = apiInterface.getCountryCities(countryId,"");
        getCities.enqueue(new Callback<CitiesResponse>() {
            @Override
            public void onResponse(Call<CitiesResponse> call, Response<CitiesResponse> response) {
                Log.e("MyData", new Gson().toJson(response.body().getData()));
                String[] cities = new String[response.body().getData().size()];
                for (int i = 0; i < response.body().getData().size(); i++)
                    cities[i] = response.body().getData().get(i).getName_en();
                ArrayAdapter arrayAdapter= new ArrayAdapter<>(StoreContactActivity.this, android.R.layout.simple_dropdown_item_1line, cities);
                editText_city.setAdapter(arrayAdapter);
                editText_city.setInputType(0);

                editText_city.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        showCities(parent.getItemAtPosition(position).toString());
                        for (City city : response.body().getData()){
                            if (parent.getItemAtPosition(position).toString().equals(city.getName_en()))
                                cityId = city.getCity_id();
                        }
                    }
                });

                editText_city.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus)
                            editText_city.showDropDown();
                    }
                });
            }

            @Override
            public void onFailure(Call<CitiesResponse> call, Throwable t) {

            }
        });
    }
}
