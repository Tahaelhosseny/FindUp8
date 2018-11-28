package findupproducts.example.com.findup.UI.ViewModel.Activites;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.Observable;

import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.AskCodeResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.VerifyCodeResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.Helper.UI_Utility;
import findupproducts.example.com.findup.UI.activities.ForgotPasswordActivity;
import findupproducts.example.com.findup.UI.activities.IntroActivity;
import findupproducts.example.com.findup.UI.activities.VerifyDeleteActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteAccountViewModel extends Observable {
    private Context mContext;
    public DeleteAccountViewModel(Context mContext){this.mContext=mContext;}
    public void checkCode(String mobile , String code){
        final AlertDialog alertDialog = UI_Utility.ShowProgressDialog(mContext, true);
        alertDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<VerifyCodeResponse> checkCode = apiService.confirmDeleteAccount(mobile , code);
        checkCode.enqueue(new Callback<VerifyCodeResponse>() {
            @Override
            public void onResponse(Call<VerifyCodeResponse> call, Response<VerifyCodeResponse> response) {
                alertDialog.dismiss();
                if(response.body().getSuccess() == 1){
                    ChangeAccepted();
                    Intent intent = new Intent(mContext , IntroActivity.class);
                    mContext.startActivity(intent);
                }else {
                    Toast.makeText(mContext, ""+response.body().getError_msg(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<VerifyCodeResponse> call, Throwable t) {
                alertDialog.dismiss();
                t.printStackTrace();
            }
        });
    }

    public void resend_code(String mobile){
        final AlertDialog alertDialog = UI_Utility.ShowProgressDialog(mContext, true);
        alertDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<VerifyCodeResponse> sendCode = apiService.deleAccount(mobile);
        sendCode.enqueue(new Callback<VerifyCodeResponse>() {
            @Override
            public void onResponse(Call<VerifyCodeResponse> call, Response<VerifyCodeResponse> response) {
                alertDialog.dismiss();
                if(response.body().getSuccess() == 1){
                    Toast.makeText(mContext, "Enter Your New Code", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(mContext, "Something went error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<VerifyCodeResponse> call, Throwable t) {
                alertDialog.dismiss();
                Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void ChangeAccepted() {
        SharedPrefManger.setDistanceText("");
        SharedPrefManger.setDistanceTypeId(1);
        SharedPrefManger.setChatNotiFlag(0);
        SharedPrefManger.setLogin_phone("");
        SharedPrefManger.setPushNotiFlag(0);
        SharedPrefManger.setCurrencyId(0);
        SharedPrefManger.setUserLanguage("");
        SharedPrefManger.setUSer_name("");
        SharedPrefManger.setUserID(0);
        SharedPrefManger.setUserSettingsId(0);
        SharedPrefManger.setIsLoggedInAsCustomer(false);
        SharedPrefManger.setIsLoggedIn(false);
    }
}
