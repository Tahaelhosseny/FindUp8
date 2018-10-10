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
import khaled.example.com.findup.Helper.Remote.ResponseModel.SaveModelResponse;
import khaled.example.com.findup.Helper.UI_Utility;
import khaled.example.com.findup.UI.activities.SettingsActivity;
import khaled.example.com.findup.UI.adapters.NotificationsAdapter;
import khaled.example.com.findup.UI.adapters.UserSavedAdapter;
import khaled.example.com.findup.models.Notification;
import khaled.example.com.findup.models.Product;
import khaled.example.com.findup.models.SaveModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YouSavedViewModel extends Observable {
    private Context mContext;
    public YouSavedViewModel(Context mContext){this.mContext = mContext;}
    public void getUserSaved(RecyclerView recyclerView , int account_id){
        final AlertDialog alertDialog = UI_Utility.ShowProgressDialog(mContext, true);
        alertDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<SaveModelResponse> getUserSaved = apiService.getUserSaved(account_id);
        getUserSaved.enqueue(new Callback<SaveModelResponse>() {
            @Override
            public void onResponse(Call<SaveModelResponse> call, Response<SaveModelResponse> response) {
                alertDialog.dismiss();
                if(response.body().getSuccess() == 1){
                    List<SaveModel> userSaved = response.body().getUser_data();
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    UserSavedAdapter adapter = new UserSavedAdapter(mContext, userSaved);
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
            public void onFailure(Call<SaveModelResponse> call, Throwable t) {
                Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
