package khaled.example.com.findup.UI.ViewModel.Activites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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

import static khaled.example.com.findup.UI.activities.StoreInformationActivity.createStore;

public class CreateEventViewModel extends Observable {
    private Context mContext;

    public CreateEventViewModel(Context mContext) {
        this.mContext = mContext;
    }

    public void addNewEvent(String event_name , String event_start , String event_day  , String event_time ,
                            String event_desc , String event_address , int store_id , double longt , double lat , String photo){
        final AlertDialog alertDialog = UI_Utility.ShowProgressDialog(mContext, true);
        alertDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        MultipartBody.Part storeId = MultipartBody.Part.createFormData("store_id", ""+store_id);
        MultipartBody.Part name = MultipartBody.Part.createFormData("event_name", event_name);
        MultipartBody.Part start = MultipartBody.Part.createFormData("event_start_date", event_start);
        MultipartBody.Part day = MultipartBody.Part.createFormData("event_days", event_day);
        MultipartBody.Part time = MultipartBody.Part.createFormData("event_time", event_time);
        MultipartBody.Part desc = MultipartBody.Part.createFormData("event_description", event_desc);
        MultipartBody.Part address = MultipartBody.Part.createFormData("event_address", event_address);
        MultipartBody.Part longtude = MultipartBody.Part.createFormData("event_longitude",""+longt);
        MultipartBody.Part latitude = MultipartBody.Part.createFormData("event_latitude", ""+lat);
        MultipartBody.Part event_photo_base64 = MultipartBody.Part.createFormData("event_photo_base64", "-");
        File bannerFile = new File(photo);
        RequestBody requestbannerFile = RequestBody.create(MediaType.parse("image/png"), bannerFile);
        MultipartBody.Part bodybannerFile = MultipartBody.Part.createFormData("event_photo", bannerFile.getName(), requestbannerFile);
        Call<CreateStoreEventResponse> storeAddEvent = apiService.addNewStoreEvent(
                name,
                bodybannerFile,
                start ,
                day ,
                time ,
                desc ,
                address ,
                storeId ,
                longtude ,
                latitude ,
                event_photo_base64);
        storeAddEvent.enqueue(new Callback<CreateStoreEventResponse>() {
            @Override
            public void onResponse(Call<CreateStoreEventResponse> call, Response<CreateStoreEventResponse> response) {
                alertDialog.dismiss();
                if(response.body().getSuccess() == 1){
                    Toast.makeText(mContext, "Event Added Successfully", Toast.LENGTH_SHORT).show();
                    ((Activity) mContext).finish();
                }else{
                    Toast.makeText(mContext, "Error adding Event", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreateStoreEventResponse> call, Throwable t) {
                alertDialog.dismiss();
                t.printStackTrace();
            }
        });
    }
}
