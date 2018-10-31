package findupproducts.example.com.findup.UI.ViewModel.Activites;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.EditProfileResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.LoginResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.StoreEditResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.VerifyCodeResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.Helper.UI_Utility;
import findupproducts.example.com.findup.UI.activities.MainActivity;
import findupproducts.example.com.findup.UI.activities.VerifyDeleteActivity;
import findupproducts.example.com.findup.models.StoreInfo;
import findupproducts.example.com.findup.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileViewModel extends Observable {
    Context mContext;
    public EditProfileViewModel(Context mContext) {
        this.mContext = mContext;
    }

    public void sendEditProfileRequest(int account_id ,String user_name , String old_password , String new_password , String mobile ){
        final AlertDialog alertDialog = UI_Utility.ShowProgressDialog(mContext, true);
        alertDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<EditProfileResponse> editProfileCall = apiService.editProfileData(account_id , user_name , old_password , new_password , mobile);
        editProfileCall.enqueue(new Callback<EditProfileResponse>() {
            @Override
            public void onResponse(Call<EditProfileResponse> call, Response<EditProfileResponse> response) {
                alertDialog.dismiss();
                if (response.body().getSuccess() == 1){
                    EditProfileAccepted(response.body().getUser_data().get(0),new_password);
                    Toast.makeText(mContext, ""+response.body().getError_msg(), Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(mContext, ""+response.body().getError_msg(), Toast.LENGTH_SHORT).show();
                }
                Log.e("url",call.request().url().toString());
                alertDialog.dismiss();
            }
            @Override
            public void onFailure(Call<EditProfileResponse> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(mContext,""+t.getMessage(),Toast.LENGTH_LONG).show();
                Log.e("url",call.request().url().toString());
            }
        });
    }

    public void sendEditProfileStoreRequest(int store_id ,String store_name , String old_password , String new_password , String mobile ){
        final AlertDialog alertDialog = UI_Utility.ShowProgressDialog(mContext, true);
        alertDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<StoreEditResponse> editProfileCall = apiService.editStoreInfo(store_id , store_name , mobile , old_password , new_password);
        editProfileCall.enqueue(new Callback<StoreEditResponse>() {
            @Override
            public void onResponse(Call<StoreEditResponse> call, Response<StoreEditResponse> response) {
                alertDialog.dismiss();
                if (response.body().getSuccess() == 1){
                    EditProfileStoreAccepted(response.body().getData().get(0),new_password);
                    Toast.makeText(mContext, ""+response.body().getError_msg(), Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(mContext, ""+response.body().getError_msg(), Toast.LENGTH_SHORT).show();
                }
                Log.e("url",call.request().url().toString());
                alertDialog.dismiss();
            }
            @Override
            public void onFailure(Call<StoreEditResponse> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(mContext,""+t.getMessage(),Toast.LENGTH_LONG).show();
                Log.e("url",call.request().url().toString());
            }
        });
    }

    public void sendCode(String mobile){
        final AlertDialog alertDialog = UI_Utility.ShowProgressDialog(mContext, true);
        alertDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<VerifyCodeResponse> sendCode = apiService.deleAccount(mobile);
        sendCode.enqueue(new Callback<VerifyCodeResponse>() {
            @Override
            public void onResponse(Call<VerifyCodeResponse> call, Response<VerifyCodeResponse> response) {
                alertDialog.dismiss();
                if(response.body().getSuccess() == 1){
                    Intent intent = new Intent(mContext , VerifyDeleteActivity.class);
                    intent.putExtra("mobile" , mobile);
                    mContext.startActivity(intent);
                }else {
                    Toast.makeText(mContext, "Something went error "+response.body().getError_msg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VerifyCodeResponse> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void EditProfileAccepted(User user, String pass){
        SharedPrefManger sharedPrefManger = new SharedPrefManger(mContext);
        sharedPrefManger.setLogin_phone(user.getMobile());
        sharedPrefManger.setLogin_password(pass);
        sharedPrefManger.setUserID(user.getId());
        SharedPrefManger.setUSer_name(user.getName());
    }
    private void EditProfileStoreAccepted(StoreInfo storeInfo, String pass){
        SharedPrefManger sharedPrefManger = new SharedPrefManger(mContext);
        sharedPrefManger.setLogin_phone(storeInfo.getStore_mobile());
        sharedPrefManger.setLogin_password(pass);
        sharedPrefManger.setStoreID(storeInfo.getStore_id());
        SharedPrefManger.setStore_namee(storeInfo.getStore_name());
    }
}
