package khaled.example.com.findup.UI.ViewModel.Activites;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.Observable;

import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.AskCodeResponse;
import khaled.example.com.findup.Helper.UI_Utility;
import khaled.example.com.findup.UI.activities.VerifyCodeActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterPhoneResetViewModel extends Observable {
    private Context mContext;
    public EnterPhoneResetViewModel(Context mContext){this.mContext=mContext;}
    public void setCodeToPhone(String mobile){
        final AlertDialog alertDialog = UI_Utility.ShowProgressDialog(mContext, true);
        alertDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<AskCodeResponse> sendCode = apiService.getResetPasswordCode(mobile);
        sendCode.enqueue(new Callback<AskCodeResponse>() {
            @Override
            public void onResponse(Call<AskCodeResponse> call, Response<AskCodeResponse> response) {
                if(response.body().getSuccess() == 1){
                    Intent newIntent = new Intent(mContext , VerifyCodeActivity.class);
                    newIntent.putExtra("mobile" , mobile);
                    mContext.startActivity(newIntent);
                }else {
                    Toast.makeText(mContext, "Something went error "+response.body().getError(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AskCodeResponse> call, Throwable t) {
                Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
