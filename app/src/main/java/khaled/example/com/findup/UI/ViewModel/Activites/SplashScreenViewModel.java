package khaled.example.com.findup.UI.ViewModel.Activites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonParser;

import java.util.List;
import java.util.Observable;
import io.reactivex.subjects.PublishSubject;
import khaled.example.com.findup.Helper.Database.DBUtility;
import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.EventResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.StoresResponse;
import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.Helper.UI_Utility;
import khaled.example.com.findup.UI.activities.IntroActivity;
import khaled.example.com.findup.UI.activities.MainActivity;
import khaled.example.com.findup.models.Product;
import khaled.example.com.findup.models.ProductPhoto;
import khaled.example.com.findup.models.Store;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

public class SplashScreenViewModel  extends Observable {
    private Context mContext;

    public SplashScreenViewModel(Context mContext) {
        this.mContext = mContext;
    }

    public void UpdateStoresData(){
        final int[] defult_load = {0};
        PublishSubject<Integer> loaded = PublishSubject.create();
        SharedPrefManger sharedPrefManger = new SharedPrefManger(mContext);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<StoresResponse> userlogincall = apiService.GetAllStores(SharedPrefManger.getUser_ID());
        userlogincall.enqueue(new Callback<StoresResponse>() {
            @Override
            public void onResponse(Call<StoresResponse> call, Response<StoresResponse> response) {
                if (response.body().getSuccess() ==1){
                    if(DBUtility.InsertStores(response.body().getData(),mContext) > 0)
                       loaded.onNext(++defult_load[0]);
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

        Call<EventResponse> eventResponseCall = apiService.GetAllEvents(sharedPrefManger.getUser_ID());
        eventResponseCall.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                Log.e("url", call.request().url().toString());
                if (response.body().getSuccess() == 1) {
                    if (DBUtility.InsertEvents(response.body().getData(), mContext) > 0)
                        loaded.onNext(++defult_load[0]);
                    if (DBUtility.InsertCategories(response.body().getCategories(), mContext) > 0)
                        loaded.onNext(++defult_load[0]);
                } else
                    Toast.makeText(mContext, "App have an error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                //Toast.makeText(mContext,"invalid data",Toast.LENGTH_SHORT).show();
                UI_Utility.noConnection(mContext, true);
                Log.e("event_url", call.request().url().toString());
                Log.e("event_error", t.getMessage());
                loaded.onNext(--defult_load[0]);


            }
        });


        loaded.subscribe(v->{
            if (v==2){
                StartApp();
            }
            Log.i("load",v+"");
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
