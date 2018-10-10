package khaled.example.com.findup.UI.ViewModel.Activites;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.Observable;

import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.EditProfileResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.ResetPasswordResponse;
import khaled.example.com.findup.Helper.UI_Utility;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ForgetPasswordViewModel extends Observable {
    private Context mContext;
    public ForgetPasswordViewModel(Context mContext){this.mContext = mContext;}

    public void updateNewPassword(String mobile , String new_password){
        final AlertDialog alertDialog = UI_Utility.ShowProgressDialog(mContext, true);
        alertDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResetPasswordResponse> updateNewPassword = apiService.resetNewPassword(mobile , new_password);
        updateNewPassword.enqueue(new Callback<ResetPasswordResponse>() {
            @Override
            public void onResponse(Call<ResetPasswordResponse> call, Response<ResetPasswordResponse> response) {
                if(response.body().getSuccess() == 1){
                    Toast.makeText(mContext, "Your Password Changed Successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "An Error Happened", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResetPasswordResponse> call, Throwable t) {
                Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
