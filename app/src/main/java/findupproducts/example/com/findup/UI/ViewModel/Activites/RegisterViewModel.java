package findupproducts.example.com.findup.UI.ViewModel.Activites;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.Observable;

import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.RegisterResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.Helper.UI_Utility;
import findupproducts.example.com.findup.UI.activities.MainActivity;
import findupproducts.example.com.findup.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends Observable {
    private Context mContext;
    List<User> userData;

    public RegisterViewModel(Context mContext) {
        this.mContext = mContext;
    }

    public void sendRegisterRequest(String name,  String password , String mobile , String email){

        final AlertDialog alertDialog = UI_Utility.ShowProgressDialog(mContext, true);
        alertDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<RegisterResponse> userRegisterCall = apiService.registerNewUser("user",name , password , mobile , "normal" , email);
        userRegisterCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.body().getSuccess() == 1){
                    userData = response.body().getData();
                    RegisterAccepted(mobile , password , userData.get(0).getId());
                    mContext.startActivity(new Intent(mContext, MainActivity.class));
                }
                else
                    Toast.makeText(mContext,""+response.body().getError_msg(),Toast.LENGTH_SHORT).show();
                Log.e("url",call.request().url().toString());
                alertDialog.dismiss();
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(mContext,"invalid data",Toast.LENGTH_SHORT).show();
                Log.e("url",call.request().url().toString());
                alertDialog.dismiss();
            }
        });
    }

    private void RegisterAccepted(String mobile , String password , int user_id){
        SharedPrefManger sharedPrefManger = new SharedPrefManger(mContext);
        sharedPrefManger.setIsLoggedIn(true);
        sharedPrefManger.setLogin_phone(mobile);
        sharedPrefManger.setLogin_password(password);
        sharedPrefManger.setUserID(user_id);
        sharedPrefManger.setIsLoggedInAsCustomer(true);
    }
}
