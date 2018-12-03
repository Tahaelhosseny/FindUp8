package findupproducts.example.com.findup.UI.ViewModel.Activites;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.exoplayer.C;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.CitiesResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.CountriesResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.CurrencyResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.Helper.Utility;
import findupproducts.example.com.findup.UI.activities.CurrencyActivity;
import findupproducts.example.com.findup.UI.activities.StoreContactActivity;
import findupproducts.example.com.findup.UI.adapters.RadioButtonAdapter;
import findupproducts.example.com.findup.UI.adapters.RecyclerTouchListener;
import findupproducts.example.com.findup.models.City;
import findupproducts.example.com.findup.models.Country;
import findupproducts.example.com.findup.models.Currency;
import findupproducts.example.com.findup.models.Event;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyViewModel extends Observable {
    public static List<Currency> currency = new ArrayList<>();
    private List<Country> countryList = new ArrayList<>();
    private List<City> cityList = new ArrayList<>();
    private Context mContext;
    public RadioButtonAdapter adapter;
    public int countryId = -1;
    public int cityId = -1;
    public String countryName = "";
    public String cityName = "";

    public CurrencyViewModel(Context mContext){
        this.mContext = mContext;
    }

    public void setCurrency(int currency_id , int store_id){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CurrencyResponse> setUserCurrency = apiService.setUserCurrency(currency_id , store_id);
        setUserCurrency.enqueue(new Callback<CurrencyResponse>() {
            @Override
            public void onResponse(Call<CurrencyResponse> call, Response<CurrencyResponse> response) {
                if(response.body().getSuccess() == 1){
                    SharedPrefManger.setCurrencyIdStore(currency_id);
                }else{
                    Toast.makeText(mContext, ""+response.body().getError_msg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CurrencyResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    public void getAllCurrency(RecyclerView recyclerView){
        currency.clear();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CurrencyResponse> getAllCurrency = apiService.getAllCurrency();
        getAllCurrency.enqueue(new Callback<CurrencyResponse>() {
            @Override
            public void onResponse(Call<CurrencyResponse> call, Response<CurrencyResponse> response) {
                if(response.body().getSuccess() == 1){
                    InitRecyclerViewCurrency(recyclerView , response.body().getUser_data());
                }
            }

            @Override
            public void onFailure(Call<CurrencyResponse> call, Throwable t) {

            }
        });
    }

    public void getAllCountry(RecyclerView recyclerView){
        countryList.clear();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CountriesResponse> getCountries = apiService.getCountries("");
        getCountries.enqueue(new Callback<CountriesResponse>() {
            @Override
            public void onResponse(Call<CountriesResponse> call, Response<CountriesResponse> response) {
                Log.e("MyData", new Gson().toJson(response.body().getData()));
                if(response.body().getSuccess() == 1){
                    countryList = response.body().getData();
                    InitRecyclerViewCountry(recyclerView);
                } else
                    Log.e("MyData", response.body().getError_msg());
            }

            @Override
            public void onFailure(Call<CountriesResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getAllCity(RecyclerView recyclerView, int countryId){
        cityList.clear();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CitiesResponse> getCities = apiService.getCountryCities(countryId,"");
        getCities.enqueue(new Callback<CitiesResponse>() {
            @Override
            public void onResponse(Call<CitiesResponse> call, Response<CitiesResponse> response) {
                Log.e("MyData", new Gson().toJson(response.body().getData()));
                if(response.body().getSuccess() == 1){
                    InitRecyclerViewCity(recyclerView);
                }
            }

            @Override
            public void onFailure(Call<CitiesResponse> call, Throwable t) {

            }
        });
    }

    private void InitRecyclerViewCurrency(RecyclerView recyclerView, List<Currency> currency){
        adapter = new RadioButtonAdapter(mContext, 1);
        recyclerView.setAdapter(adapter);
        adapter.setCurrencyList(currency);
        bindUI(recyclerView);
    }

    private void InitRecyclerViewCountry(RecyclerView recyclerView){
        adapter = new RadioButtonAdapter(mContext, 2);
        recyclerView.setAdapter(adapter);
        adapter.setCountryList(countryList);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(mContext, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                countryId = countryList.get(position).getCountry_id();
                countryName = countryList.get(position).getName_en();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        bindUI(recyclerView);
    }

    private void InitRecyclerViewCity(RecyclerView recyclerView){
        adapter = new RadioButtonAdapter(mContext, 3);
        recyclerView.setAdapter(adapter);
        adapter.setCityList(cityList);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(mContext, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                cityId = cityList.get(position).getCity_id();
                cityName = cityList.get(position).getName_en();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        bindUI(recyclerView);
    }

    private void bindUI(RecyclerView recyclerView){
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.smoothScrollToPosition(0);
    }

    public void FilterAdapter(String search_text, int type) {
        switch (type){
            case 1:{
                if (!search_text.isEmpty())
                    adapter.setCurrencyList(searchCurrencies(currency,search_text));
                else
                    adapter.setCurrencyList(currency);
                break;
            }
            case 2:{
                if (!search_text.isEmpty())
                    adapter.setCountryList(searchCountries(search_text));
                else
                    adapter.setCountryList(countryList);
                break;
            }
            case 3:{
                if (!search_text.isEmpty())
                    adapter.setCityList(searchCities(search_text));
                else
                    adapter.setCityList(cityList);
                break;
            }
        }
    }

    private List<Currency> searchCurrencies(List<Currency> currencies, String search_text) {
        List<Currency> filtered = new ArrayList<>();
        for (Currency currency : currencies) {
            if (currency.getCurrency_title().toLowerCase().startsWith(search_text.toLowerCase())) {
                filtered.add(currency);
            }
        }
        return filtered;
    }

    private List<Country> searchCountries(String search_text) {
        List<Country> filtered = new ArrayList<>();
        for (Country country : countryList) {
            if (country.getName_en().toLowerCase().startsWith(search_text.toLowerCase())) {
                filtered.add(country);
            }
        }
        return filtered;
    }

    private List<City> searchCities(String search_text) {
        List<City> filtered = new ArrayList<>();
        for (City city : cityList) {
            if (city.getName_en().toLowerCase().startsWith(search_text.toLowerCase())) {
                filtered.add(city);
            }
        }
        return filtered;
    }
}
