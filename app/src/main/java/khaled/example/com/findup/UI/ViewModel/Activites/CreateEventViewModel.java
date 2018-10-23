package khaled.example.com.findup.UI.ViewModel.Activites;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.io.File;
import java.util.Observable;

import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.CreateStoreEventResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.LoginResponse;
import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.Helper.UI_Utility;
import khaled.example.com.findup.UI.activities.CreateEventActivity;
import khaled.example.com.findup.UI.activities.StoreEventsActivity;
import khaled.example.com.findup.models.Event;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateEventViewModel extends Observable {
    private Context mContext;

    public CreateEventViewModel(Context mContext) {
        this.mContext = mContext;
    }

    public void addNewEvent(String event_name , String wvent_start , String event_day  , String event_time ,
                            String event_desc , String event_address , int store_id , double longt , double lat , String photo){
        Toast.makeText(mContext, "Tmam Success", Toast.LENGTH_SHORT).show();
        final AlertDialog alertDialog = UI_Utility.ShowProgressDialog(mContext, true);
        alertDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Toast.makeText(mContext, "Tmam 1 ", Toast.LENGTH_SHORT).show();
        Call<CreateStoreEventResponse> storeAddEvent = apiService.addNewStoreEvent(
                event_name , wvent_start , event_day , event_time , event_desc , event_address , store_id , longt , lat , photo);
        storeAddEvent.enqueue(new Callback<CreateStoreEventResponse>() {
            @Override
            public void onResponse(Call<CreateStoreEventResponse> call, Response<CreateStoreEventResponse> response) {
                alertDialog.dismiss();
                if(response.body().getSuccess() == 1){
                    Toast.makeText(mContext, "Event Added Successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "Error Success : "+response.body().getError_msg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreateStoreEventResponse> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(mContext, "Error Fail : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
