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

    public void addNewEvent(Event eventToCreate){
        File banner = new File(eventToCreate.getEvent_photo());
        Toast.makeText(mContext, "stage 1", Toast.LENGTH_SHORT).show();

        MultipartBody.Part event_name = MultipartBody.Part.createFormData("event_name", eventToCreate.getEvent_name());
        Toast.makeText(mContext, "stage 2", Toast.LENGTH_SHORT).show();

        MultipartBody.Part event_start_date = MultipartBody.Part.createFormData("event_start_date", eventToCreate.getEvent_start_date());
        Toast.makeText(mContext, "stage 3", Toast.LENGTH_SHORT).show();

        MultipartBody.Part event_days = MultipartBody.Part.createFormData("event_days", eventToCreate.getEvent_days());
        Toast.makeText(mContext, "stage 4", Toast.LENGTH_SHORT).show();

        MultipartBody.Part event_time = MultipartBody.Part.createFormData("event_time", eventToCreate.getEvent_time());
        Toast.makeText(mContext, "stage 5", Toast.LENGTH_SHORT).show();

        MultipartBody.Part event_description = MultipartBody.Part.createFormData("event_description", eventToCreate.getEvent_desc());
        Toast.makeText(mContext, "stage 6", Toast.LENGTH_SHORT).show();

        MultipartBody.Part event_address = MultipartBody.Part.createFormData("event_address", eventToCreate.getEvent_address());
        Toast.makeText(mContext, "stage 7", Toast.LENGTH_SHORT).show();

        MultipartBody.Part store_id = MultipartBody.Part.createFormData("store_id", eventToCreate.getStore_id());
        Toast.makeText(mContext, "stage 8", Toast.LENGTH_SHORT).show();

        MultipartBody.Part event_longitude = MultipartBody.Part.createFormData("event_longitude", eventToCreate.getEvent_longitude());
        Toast.makeText(mContext, "stage 9", Toast.LENGTH_SHORT).show();

        MultipartBody.Part event_latitude = MultipartBody.Part.createFormData("event_latitude", eventToCreate.getEvent_latitude());
        Toast.makeText(mContext, "stage 10", Toast.LENGTH_SHORT).show();

        RequestBody requestbannerFile = RequestBody.create(MediaType.parse("image/png"), banner);
        Toast.makeText(mContext, "stage 11", Toast.LENGTH_SHORT).show();

        MultipartBody.Part event_photo = MultipartBody.Part.createFormData("event_photo", banner.getName(), requestbannerFile);
        Toast.makeText(mContext, "stage 12", Toast.LENGTH_SHORT).show();

        final AlertDialog alertDialog = UI_Utility.ShowProgressDialog(mContext, true);
        alertDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CreateStoreEventResponse> storeAddEvent = apiService.addNewStoreEvent(event_name,event_start_date,event_days,event_time,event_description,event_address,store_id,event_longitude,event_latitude,event_photo);
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
                Toast.makeText(mContext, "Error Failler : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
