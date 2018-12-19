package findupproducts.example.com.findup.UI.ViewModel.Activites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import findupproducts.example.com.findup.Helper.Database.DBHandler;
import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.LoginResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.NotificationResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.StoreSettingsGetResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.UserSettingsResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.Helper.UI_Utility;
import findupproducts.example.com.findup.UI.activities.MainActivity;
import findupproducts.example.com.findup.UI.activities.MainStoreActivity;
import findupproducts.example.com.findup.UI.activities.VerifyCodeActivity;
import findupproducts.example.com.findup.models.StoreSetting;
import findupproducts.example.com.findup.models.User;
import findupproducts.example.com.findup.models.UserSetting;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends Observable {
    private Context mContext;
    private Activity activity;
    public LoginViewModel(Activity mContext) {
        this.mContext = mContext;
        activity = mContext ;
    }
    public void sendLoginRequest(String phone, final String password){

        final AlertDialog alertDialog = UI_Utility.ShowProgressDialog(mContext, true);
        alertDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginResponse> userlogincall = apiService.LoginRequest(phone,password);
        userlogincall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.e("MyData", new Gson().toJson(response.body()));
                if (response.body().getSuccess() == 1 && response.body().getData().get(0).getLogin_type().equals("User"))
                {
                    User user = response.body().getUser_data().get(0);
                    LoginAccepted(user , phone , password);
                    saveUserSettings(user.getId());
                    mContext.startActivity(new Intent(mContext, MainActivity.class));
                    activity.finish();
                } else if (response.body().getSuccess() == 1 && response.body().getData().get(0).getLogin_type().equals("Store")) {
                    LoginStoreAccepted(response.body().getUser_data().get(0), password);
                    saveStoreSetting(response.body().getUser_data().get(0).getId());
                    mContext.startActivity(new Intent(mContext, MainStoreActivity.class));
                }else if(response.body().getSuccess() == 0 && response.body().getError_msg().equals("Account not verified") )
                {
                    mContext.startActivity(new Intent(mContext , VerifyCodeActivity.class).putExtra("email" , phone));
                }
                else {
                    Toast.makeText(mContext, "phone or password are not correct", Toast.LENGTH_SHORT).show();
                }
                Log.e("url",call.request().url().toString());
                alertDialog.dismiss();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                t.printStackTrace();
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
                t.printStackTrace();
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
    private void LoginAccepted(User user,String email ,String pass){
        SharedPrefManger.setIsLoggedIn(true);
        SharedPrefManger.setLogin_phone(user.getMobile());
        SharedPrefManger.setLogin_password(pass);
        SharedPrefManger.setUser_mail(email);
        SharedPrefManger.setUserID(user.getId());
        SharedPrefManger.setIsLoggedInAsCustomer(true);
        SharedPrefManger.setUSer_name(user.getName());
        SharedPrefManger.setLoginType("user");
    }
    private void LoginStoreAccepted(User user,String pass){
        SharedPrefManger.setIsLoggedIn(true);
        SharedPrefManger.setLogin_phone(user.getMobile());
        SharedPrefManger.setLogin_password(pass);
        SharedPrefManger.setStoreID(user.getId());
        SharedPrefManger.setIsLoggedInAsCustomer(false);
        SharedPrefManger.setStore_banner(user.getImage());
        SharedPrefManger.setStore_logo(user.getStore_logo());
        SharedPrefManger.setStore_namee(user.getName());
        SharedPrefManger.setLoginType("store");
        SharedPrefManger.setStoreLocation_type(user.getLocation_type());
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
