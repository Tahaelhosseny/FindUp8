package khaled.example.com.findup.UI.ViewModel.Activites;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.Observable;

import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.CreateStoreEventResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.LoginResponse;
import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.Helper.UI_Utility;
import khaled.example.com.findup.UI.activities.CreateEventActivity;
import khaled.example.com.findup.UI.activities.StoreEventsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class CreateEventViewModel extends Observable {
    private Context mContext;

    public CreateEventViewModel(Context mContext) {
        this.mContext = mContext;
    }

    public void addNewEvent(String event_name , String event_start_date,
                            String event_days , String event_time
                          , String event_description , String event_address,
                            double event_longitude,
                            double event_latitude , String event_photo){


        final AlertDialog alertDialog = UI_Utility.ShowProgressDialog(mContext, true);
        alertDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CreateStoreEventResponse> storeAddEvent = apiService.addNewStoreEvent(event_name,event_start_date ,event_days,event_time,event_description,event_address, SharedPrefManger.getStore_ID(),event_longitude, event_latitude ,event_photo);
        storeAddEvent.enqueue(new Callback<CreateStoreEventResponse>() {
            @Override
            public void onResponse(Call<CreateStoreEventResponse> call, Response<CreateStoreEventResponse> response) {
                alertDialog.dismiss();
                if(response.body().getSuccess() == 1){
                    mContext.startActivity(new Intent(mContext , StoreEventsActivity.class));
                }else{
                    Toast.makeText(mContext, ""+response.body().getError_msg(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<CreateStoreEventResponse> call, Throwable t) {

            }
        });
    }
}
