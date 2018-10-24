package khaled.example.com.findup.UI.ViewModel.Fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.Observable;

import khaled.example.com.findup.Helper.Database.DBHandler;
import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.AddCommentProductResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.AddCommentStoreResponse;
import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.Helper.UI_Utility;
import khaled.example.com.findup.UI.activities.CommentsActivity;
import khaled.example.com.findup.models.Comment;
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
                if (response.body().getSuccess() == 1) {
                    Toast.makeText(mContext, "Comment Added To Store Successfully", Toast.LENGTH_SHORT).show();
                    DBHandler.InsertComments(response.body().getData(),mContext);
                    mContext.startActivity(new Intent(mContext, CommentsActivity.class).putExtra("store_id",store_id));
                 //   ((Activity)mContext).finish();
                }else {
                    Toast.makeText(mContext, ""+response.body().getError_msg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddCommentStoreResponse> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addCommentToProduct(String comment , int product_id){
        final AlertDialog alertDialog = UI_Utility.ShowProgressDialog(mContext, true);
        alertDialog.show();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<AddCommentProductResponse> addCommentProduct = apiInterface.addNewProductComment(SharedPrefManger.getUser_ID() , product_id , comment);
        addCommentProduct.enqueue(new Callback<AddCommentProductResponse>() {
            @Override
            public void onResponse(Call<AddCommentProductResponse> call, Response<AddCommentProductResponse> response) {
                alertDialog.dismiss();
                if(response.body().getSuccess() == 1){
                    Toast.makeText(mContext, "Comment Added To Product Successfully", Toast.LENGTH_SHORT).show();
                    DBHandler.InsertProductsComments(response.body().getData() , mContext);
                    mContext.startActivity(new Intent(mContext , CommentsActivity.class).putExtra("product_id" , product_id));
                }else{
                    Toast.makeText(mContext, ""+response.body().getError_msg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddCommentProductResponse> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

