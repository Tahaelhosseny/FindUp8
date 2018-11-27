package findupproducts.example.com.findup.UI.ViewModel.Activites;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.CurrencyResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.UI.activities.CurrencyActivity;
import findupproducts.example.com.findup.UI.adapters.RadioButtonAdapter;
import findupproducts.example.com.findup.models.Currency;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyViewModel extends Observable {
    public static List<Currency> currency = new ArrayList<>();
    private Context mContext;
    private List<Currency> currencyList = new ArrayList<>();
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
                Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
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
                    for (int i = 0 ; i < response.body().getUser_data().size() ; i ++){
                        currency.add(response.body().getUser_data().get(i));
                    }
                    if(currency.size() < 1){

                    }else{
                        InitRecyclerView(recyclerView , currency);
                    }
                }else{

                }
            }

            @Override
            public void onFailure(Call<CurrencyResponse> call, Throwable t) {

            }
        });
    }
    public void InitRecyclerView(RecyclerView recyclerView , List<Currency> currency){
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RadioButtonAdapter adapter = new RadioButtonAdapter(mContext, currency);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.smoothScrollToPosition(0);
    }
}
