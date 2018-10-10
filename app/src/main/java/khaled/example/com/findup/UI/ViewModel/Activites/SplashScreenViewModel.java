package khaled.example.com.findup.UI.ViewModel.Activites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import java.util.Observable;
import io.reactivex.subjects.PublishSubject;
import khaled.example.com.findup.Helper.Database.DBUtility;
import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.StoresResponse;
import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.Helper.UI_Utility;
import khaled.example.com.findup.UI.activities.IntroActivity;
import khaled.example.com.findup.UI.activities.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenViewModel  extends Observable {
    private Context mContext;

    public SplashScreenViewModel(Context mContext) {
        this.mContext = mContext;
    }

    public void UpdateStoresData(){
        final int[] defult_load = {0};
        PublishSubject<Integer> loaded = PublishSubject.create();

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<StoresResponse> userlogincall = apiService.GetAllStores();
        userlogincall.enqueue(new Callback<StoresResponse>() {
            @Override
            public void onResponse(Call<StoresResponse> call, Response<StoresResponse> response) {
                if (response.body().getSuccess() ==1){
                    if(DBUtility.InsertStores(response.body().getData(),mContext) > 0){
                       // loaded.onNext(++defult_load[0]);
                        StartApp();
                    }

                }
                else
                    Toast.makeText(mContext,"App have an error",Toast.LENGTH_SHORT).show();
                Log.e("url",call.request().url().toString());
            }

            @Override
            public void onFailure(Call<StoresResponse> call, Throwable t) {
                //Toast.makeText(mContext,"invalid data",Toast.LENGTH_SHORT).show();
                UI_Utility.noConnection(mContext,true);
                Log.e("url",call.request().url().toString());
                Log.e("error",t.getMessage());

            }
        });
    }

    public void StartApp(){
        SharedPrefManger sharedPrefManger = new SharedPrefManger(mContext);

        ((Activity) mContext).runOnUiThread(new Runnable() {
            public void run() {
                if(sharedPrefManger.isIsLoggedIn()){
                    mContext.startActivity(new Intent(mContext, MainActivity.class));
                    ((Activity) mContext).finish();
                }else {
                    mContext.startActivity(new Intent(mContext, IntroActivity.class));
                    ((Activity) mContext).finish();
                }
            }
        });
    }
}