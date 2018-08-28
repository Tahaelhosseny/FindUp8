package khaled.example.com.findup.UI.ViewModel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Observable;

import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.LoginResponse;
import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.UI.activities.MainActivity;
import khaled.example.com.findup.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends Observable {
    private Context mContext;

    public LoginViewModel(Context mContext) {
        this.mContext = mContext;
    }

    public void sendLoginRequest(String phone, final String password){

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginResponse> userlogincall = apiService.LoginRequest(phone,password);
        userlogincall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body().getSuccess() ==1){
                    LoginAccepted(response.body().getUser_data().get(0),password);
                    mContext.startActivity(new Intent(mContext, MainActivity.class));
                }
                else
                    Toast.makeText(mContext,"phone or password are not correct",Toast.LENGTH_SHORT).show();
                Log.e("url",call.request().url().toString());


            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(mContext,"invalid data",Toast.LENGTH_SHORT).show();
                Log.e("url",call.request().url().toString());
            }
        });
    }


    private void LoginAccepted(User user,String pass){
        SharedPrefManger sharedPrefManger = new SharedPrefManger(mContext);
        sharedPrefManger.setIsLoggedIn(true);
        sharedPrefManger.setLogin_phone(user.getMobile());
        sharedPrefManger.setLogin_password(pass);
        sharedPrefManger.setIsLoggedInAsCustomer(true);
    }
}
