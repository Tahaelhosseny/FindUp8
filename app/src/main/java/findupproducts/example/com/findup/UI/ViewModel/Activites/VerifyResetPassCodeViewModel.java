package findupproducts.example.com.findup.UI.ViewModel.Activites;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.Observable;

import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.AskCodeResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.LoginResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.VerifyCodeResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.Helper.UI_Utility;
import findupproducts.example.com.findup.UI.activities.ForgotPasswordActivity;
import findupproducts.example.com.findup.UI.activities.LoginActivity;
import findupproducts.example.com.findup.UI.activities.VerifyCodeActivity;
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
                alertDialog.dismiss();
                if(response.body().getSuccess() == 1){
                    if(SharedPrefManger.getVerifyType().equals("register")){
                        mContext.startActivity(new Intent(mContext , LoginActivity.class));
                    }else {
                        Intent intent = new Intent(mContext, ForgotPasswordActivity.class);
                        intent.putExtra("phone", mobile);
                        mContext.startActivity(intent);
                    }
                }else {
                    Toast.makeText(mContext, ""+response.body().getError_msg(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<VerifyCodeResponse> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void resend_code(String mobile){
        final AlertDialog alertDialog = UI_Utility.ShowProgressDialog(mContext, true);
        alertDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<AskCodeResponse> sendCode = apiService.getResetPasswordCode(mobile);
        sendCode.enqueue(new Callback<AskCodeResponse>() {
            @Override
            public void onResponse(Call<AskCodeResponse> call, Response<AskCodeResponse> response) {
                alertDialog.dismiss();
                if(response.body().getSuccess() == 1){
                    Toast.makeText(mContext, "Enter Your New Code", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext, "Something went error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AskCodeResponse> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ChangeAccepted(String mobile) {
        SharedPrefManger.setLogin_phone(mobile);
    }
}
