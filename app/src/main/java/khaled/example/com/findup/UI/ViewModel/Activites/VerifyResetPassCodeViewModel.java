package khaled.example.com.findup.UI.ViewModel.Activites;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.Observable;

import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.LoginResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.VerifyCodeResponse;
import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.Helper.UI_Utility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyResetPassCodeViewModel extends Observable {
    private Context mContext;
    public VerifyResetPassCodeViewModel(Context mContext){this.mContext=mContext;}
    public void checkCode(String mobile , String code){
        final AlertDialog alertDialog = UI_Utility.ShowProgressDialog(mContext, true);
        alertDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<VerifyCodeResponse> checkCode = apiService.checkVerifyCode(mobile,code);
        checkCode.enqueue(new Callback<VerifyCodeResponse>() {
            @Override
            public void onResponse(Call<VerifyCodeResponse> call, Response<VerifyCodeResponse> response) {
                if(response.body().getSuccess() == 1){
                    ChangeAccepted(mobile);
                    //start to change password
                }else{
                    Toast.makeText(mContext, "Code is Wrong", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<VerifyCodeResponse> call, Throwable t) {
                Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void resend_code(){

    }

    private void ChangeAccepted(String mobile) {
        SharedPrefManger.setLogin_phone(mobile);
    }
}
