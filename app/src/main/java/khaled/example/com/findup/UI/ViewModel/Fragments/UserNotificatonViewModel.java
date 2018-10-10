package khaled.example.com.findup.UI.ViewModel.Fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;
import java.util.Observable;

import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.NotificationResponse;
import khaled.example.com.findup.Helper.UI_Utility;
import khaled.example.com.findup.UI.activities.SettingsActivity;
import khaled.example.com.findup.UI.adapters.CommentsAdapter;
import khaled.example.com.findup.UI.adapters.NotificationsAdapter;
import khaled.example.com.findup.models.Notification;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserNotificatonViewModel extends Observable {
    private Context mContext;
    public UserNotificatonViewModel(Context mContext){this.mContext = mContext;}
    public void getUserNotification(RecyclerView recyclerView , String account_id){
        final AlertDialog alertDialog = UI_Utility.ShowProgressDialog(mContext, true);
        alertDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<NotificationResponse> getUserNotification = apiService.getUserNotification(account_id);
        getUserNotification.enqueue(new Callback<NotificationResponse>() {
            @Override
            public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
                alertDialog.dismiss();
                if(response.body().getSuccess() == 1){
                    List<Notification> userNotification = response.body().getUser_data();
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    NotificationsAdapter adapter = new NotificationsAdapter(mContext, userNotification);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                    recyclerView.smoothScrollToPosition(0);
                }else{
                    Toast.makeText(mContext, ""+response.body().getError_msg(), Toast.LENGTH_SHORT).show();
                    mContext.startActivity(new Intent(mContext , SettingsActivity.class));
                }
            }

            @Override
            public void onFailure(Call<NotificationResponse> call, Throwable t) {
                Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
