package khaled.example.com.findup.UI.ViewModel.Fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Observable;

import khaled.example.com.findup.Helper.Database.DBHandler;
import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.AddCommentStoreResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.LoginResponse;
import khaled.example.com.findup.Helper.UI_Utility;
import khaled.example.com.findup.UI.activities.MainActivity;
import khaled.example.com.findup.UI.activities.StoreDetailsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCommentStoreViewModel extends Observable {
    private Context mContext;
    public AddCommentStoreViewModel(Context mContext) {
        this.mContext = mContext;
    }

    public void addCommentToStore(int account_id , String comment , int store_id){
        final AlertDialog alertDialog = UI_Utility.ShowProgressDialog(mContext, true);
        alertDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<AddCommentStoreResponse> addCommentCall = apiService.addNewStoreComment(account_id , store_id , comment);
        addCommentCall.enqueue(new Callback<AddCommentStoreResponse>() {
            @Override
            public void onResponse(Call<AddCommentStoreResponse> call, Response<AddCommentStoreResponse> response) {
                alertDialog.dismiss();
                if (response.body().getError() == 0) {
                    Toast.makeText(mContext, "Comment Added To Store Successfully", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext, ""+response.body().getError_msg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddCommentStoreResponse> call, Throwable t) {

            }
        });

    }

}

