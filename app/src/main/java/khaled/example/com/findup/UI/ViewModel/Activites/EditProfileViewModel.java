package khaled.example.com.findup.UI.ViewModel.Activites;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.EditProfileResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.LoginResponse;
import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.Helper.UI_Utility;
import khaled.example.com.findup.UI.activities.MainActivity;
import khaled.example.com.findup.models.User;
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

    private void EditProfileAccepted(User user, String pass){
        SharedPrefManger sharedPrefManger = new SharedPrefManger(mContext);
        sharedPrefManger.setLogin_phone(user.getMobile());
        sharedPrefManger.setLogin_password(pass);
        sharedPrefManger.setUserID(user.getId());
        SharedPrefManger.setUSer_name(user.getName());
    }
}
