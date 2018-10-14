package khaled.example.com.findup.UI.ViewModel.Activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.LoginResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.UserSettingsResponse;
import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.Helper.UI_Utility;
import khaled.example.com.findup.UI.activities.MainActivity;
import khaled.example.com.findup.models.User;
import khaled.example.com.findup.models.UserSetting;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends Observable {
    private Context mContext;

    public LoginViewModel(Context mContext) {
        this.mContext = mContext;
    }

    public void sendLoginRequest(String phone, final String password){

        final AlertDialog alertDialog = UI_Utility.ShowProgressDialog(mContext, true);
        alertDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginResponse> userlogincall = apiService.LoginRequest(phone,password);
        userlogincall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body().getSuccess() == 1){
                    LoginAccepted(response.body().getUser_data().get(0),password);
                    saveUserSettings(response.body().getUser_data().get(0).getId());
                    mContext.startActivity(new Intent(mContext, MainActivity.class));

                }
                else
                    Toast.makeText(mContext,"phone or password are not correct",Toast.LENGTH_SHORT).show();
                Log.e("url",call.request().url().toString());
                alertDialog.dismiss();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(mContext,"invalid data",Toast.LENGTH_SHORT).show();
                Log.e("url",call.request().url().toString());
                alertDialog.dismiss();
            }
        });
    }
    //check

    public void saveUserSettings(int account_id){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserSettingsResponse> userSettings = apiService.getUserSetting(account_id);
        userSettings.enqueue(new Callback<UserSettingsResponse>() {
            @Override
            public void onResponse(Call<UserSettingsResponse> call, Response<UserSettingsResponse> response) {
                if(response.body().getSuccess() == 1){
                    saveUserSettingSuccess(response.body().getUser_data().get(0));
                }else{
                    UserSetting userSetting = new UserSetting(0 , account_id ,0,0,0,"en",0 );
                    saveUserSettingSuccess(userSetting);
                }
            }

            @Override
            public void onFailure(Call<UserSettingsResponse> call, Throwable t) {
                Toast.makeText(mContext, "Undefined Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveUserSettingSuccess(UserSetting userSetting){
        SharedPrefManger.setChatNotiFlag(userSetting.getChat_noti_flag());
        SharedPrefManger.setCurrencyId(userSetting.getCurrency_id());
        SharedPrefManger.setDistanceTypeId(userSetting.getDistance_type_id());
        SharedPrefManger.setPushNotiFlag(userSetting.getPust_noti_flag());
        SharedPrefManger.setUserLanguage(userSetting.getLanguage());
        SharedPrefManger.setUserSettingsId(userSetting.getSetting_id());
    }
    private void LoginAccepted(User user,String pass){
        SharedPrefManger sharedPrefManger = new SharedPrefManger(mContext);
        sharedPrefManger.setIsLoggedIn(true);
        sharedPrefManger.setLogin_phone(user.getMobile());
        sharedPrefManger.setLogin_password(pass);
        sharedPrefManger.setUserID(user.getId());
        sharedPrefManger.setIsLoggedInAsCustomer(true);
        SharedPrefManger.setUSer_name(user.getName());
    }
}
