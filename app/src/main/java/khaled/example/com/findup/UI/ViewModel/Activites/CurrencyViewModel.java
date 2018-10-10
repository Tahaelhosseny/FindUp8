package khaled.example.com.findup.UI.ViewModel.Activites;

import android.content.Context;
import android.widget.Toast;

import java.util.Observable;

import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.CurrencyResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyViewModel extends Observable {
    private Context mContext;
    public CurrencyViewModel(Context mContext){
        this.mContext = mContext;
    }

    public void setCurrency(int currency_id , String account_id){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CurrencyResponse> setUserCurrency = apiService.setUserCurrency(currency_id , account_id);
        setUserCurrency.enqueue(new Callback<CurrencyResponse>() {
            @Override
            public void onResponse(Call<CurrencyResponse> call, Response<CurrencyResponse> response) {
                if(response.body().getSuccess() == 1){

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
    public void getAllCurrency(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CurrencyResponse> getAllCurrency = apiService.getAllCurrency();
        getAllCurrency.enqueue(new Callback<CurrencyResponse>() {
            @Override
            public void onResponse(Call<CurrencyResponse> call, Response<CurrencyResponse> response) {
                if(response.body().getSuccess() == 1){

                }else{

                }
            }

            @Override
            public void onFailure(Call<CurrencyResponse> call, Throwable t) {

            }
        });
    }
}
