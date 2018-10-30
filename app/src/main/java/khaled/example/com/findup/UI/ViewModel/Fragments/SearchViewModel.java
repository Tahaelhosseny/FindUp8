package khaled.example.com.findup.UI.ViewModel.Fragments;

import android.content.Context;
import android.widget.Toast;

import java.util.ConcurrentModificationException;
import java.util.Observable;

import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.LoginResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.SearchStoreResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewModel extends Observable {
    private Context mContext;
    public  SearchViewModel(Context mContext){
        this.mContext = mContext;
    }

    public void getFilteredData(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<SearchStoreResponse> getFiltered = apiService.getFilteredStores(0 , "", "","","","","","","","","");
        getFiltered.enqueue(new Callback<SearchStoreResponse>() {
            @Override
            public void onResponse(Call<SearchStoreResponse> call, Response<SearchStoreResponse> response) {
                if(response.body().getSuccess() == 1){

                }else{
                    Toast.makeText(mContext, ""+response.body().getError_msg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchStoreResponse> call, Throwable t) {
                Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
