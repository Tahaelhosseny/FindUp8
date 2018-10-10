package khaled.example.com.findup.UI.ViewModel.Activites;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import java.util.Observable;

import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.UI_Utility;

public class EnterPhoneResetViewModel extends Observable {
    private Context mContext;
    public EnterPhoneResetViewModel(Context mContext){this.mContext=mContext;}
    public void setCodeToPhone(String mobile){
        final AlertDialog alertDialog = UI_Utility.ShowProgressDialog(mContext, true);
        alertDialog.show();
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
//        Call<> sendCode =

    }
}
