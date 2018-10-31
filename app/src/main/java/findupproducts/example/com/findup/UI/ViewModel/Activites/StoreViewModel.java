package findupproducts.example.com.findup.UI.ViewModel.Activites;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.Observable;

import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.CreateStoreResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreViewModel extends Observable {
    private Context mContext;
    public StoreViewModel(Context mContext){this.mContext = mContext;}
    public void CreateStore(){
        /*Bitmap bitmap = null;Bitmap bitmap1 = null;
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CreateStoreResponse> createStore = apiService.createNewStore("", 0 , 0 , "" , ""
                , "" , "" , "" , "" , 0 , bitmap, bitmap1 , ""
                , "" , "" , "" , "");
        createStore.enqueue(new Callback<CreateStoreResponse>() {
            @Override
            public void onResponse(Call<CreateStoreResponse> call, Response<CreateStoreResponse> response) {

            }
            @Override
            public void onFailure(Call<CreateStoreResponse> call, Throwable t) {

            }
        });*/
    }
}
