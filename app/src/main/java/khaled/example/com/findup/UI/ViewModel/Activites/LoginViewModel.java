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

import khaled.example.com.findup.Helper.Database.DBHandler;
import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.LoginResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.NotificationResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.StoreSettingsGetResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.UserSettingsResponse;
import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.Helper.UI_Utility;
import khaled.example.com.findup.UI.activities.MainActivity;
import khaled.example.com.findup.UI.activities.MainStoreActivity;
import khaled.example.com.findup.models.StoreSetting;
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
                if (response.body().getSuccess() == 1 && response.body().getData().get(0).getLogin_type().equals("User")){
                    Toast.makeText(mContext, "User Id " + response.body().getData().get(0).getId(), Toast.LENGTH_SHORT).show();
                    LoginAccepted(response.body().getUser_data().get(0),password);
                    saveUserSettings(response.body().getUser_data().get(0).getId());
                    mContext.startActivity(new Intent(mContext, MainActivity.class));

                    }else if(response.body().getSuccess() ==1 && response.body().getData().get(0).getLogin_type().equals("Store")){
                    LoginStoreAccepted(response.body().getUser_data().get(0),password);
                    saveStoreSetting(response.body().getUser_data().get(0).getStore_id());
                    mContext.startActivity(new Intent(mContext, MainStoreActivity.class));
                } else{
                    Toast.makeText(mContext,"phone or password are not correct",Toast.LENGTH_SHORT).show();
                }
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
    private void saveStoreSetting(int store_id) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<StoreSettingsGetResponse> storeSetting = apiService.getStoreSetting(store_id);
        storeSetting.enqueue(new Callback<StoreSettingsGetResponse>() {
            @Override
            public void onResponse(Call<StoreSettingsGetResponse> call, Response<StoreSettingsGetResponse> response) {
                if(response.body().getSuccess() == 1){
                    saveStoreSettingSuccess(response.body().getData().get(0));
                }else{
                    StoreSetting store = new StoreSetting(0,store_id,0,0,0,0,"en" , 0);
                    saveStoreSettingSuccess(store);
                }
            }

            @Override
            public void onFailure(Call<StoreSettingsGetResponse> call, Throwable t) {
                Log.e("Save Store Setting Fail" , t.getMessage());
            }
        });
    }
    public void saveUserSettings(int account_id){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserSettingsResponse> userSettings = apiService.getUserSetting(account_id);
        userSettings.enqueue(new Callback<UserSettingsResponse>() {
            @Override
            public void onResponse(Call<UserSettingsResponse> call, Response<UserSettingsResponse> response) {
                if(response.body().getSuccess() == 1){
                    saveUserSettingSuccess(response.body().getUser_data().get(0));
                }else{
                    UserSetting userSetting = new UserSetting(0 , account_id ,0,0,1,"en",0 );
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
        Toast.makeText(mContext, ""+user.getId(), Toast.LENGTH_SHORT).show();
        sharedPrefManger.setIsLoggedInAsCustomer(true);
        sharedPrefManger.setUSer_name(user.getName());
        sharedPrefManger.setLoginType(user.getLogin_type());
    }
    private void LoginStoreAccepted(User user,String pass){
        SharedPrefManger sharedPrefManger = new SharedPrefManger(mContext);
        sharedPrefManger.setIsLoggedIn(true);
        sharedPrefManger.setLogin_phone(user.getStore_mobile());
        sharedPrefManger.setLogin_password(pass);
        sharedPrefManger.setStoreID(user.getStore_id());
        sharedPrefManger.setIsLoggedInAsCustomer(false);
        sharedPrefManger.setStore_banner(user.getStore_banner());
        sharedPrefManger.setStore_logo(user.getStore_logo());
        sharedPrefManger.setStore_namee(user.getStore_name());
        sharedPrefManger.setLoginType(user.getLogin_type());
    }
    private void saveStoreSettingSuccess(StoreSetting storeSetting){
        SharedPrefManger.setChatNotiFlagStore(storeSetting.getChat_noti_flag());
        SharedPrefManger.setCurrencyIdStore(storeSetting.getCurrency_id());
        SharedPrefManger.setPushNotiFlagStore(storeSetting.getPush_noti_flag());
        SharedPrefManger.setLanguageStore(storeSetting.getLanguage());
        SharedPrefManger.setStoreSettingsId(storeSetting.getSett_id());
        SharedPrefManger.setStoreCommentsNoti(storeSetting.getComment_noti_flag());
        SharedPrefManger.setLikesStoreNoti(storeSetting.getLike_noti_flag());
    }
}
