package findupproducts.example.com.findup.UI.ViewModel.Activites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.Observable;

import findupproducts.example.com.findup.Helper.Database.DBHandler;
import io.reactivex.subjects.PublishSubject;
import findupproducts.example.com.findup.Helper.Database.DBUtility;
import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.EventResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.StoresResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.Helper.UI_Utility;
import findupproducts.example.com.findup.UI.activities.IntroActivity;
import findupproducts.example.com.findup.UI.activities.MainActivity;
import findupproducts.example.com.findup.UI.activities.MainStoreActivity;
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
        SharedPrefManger sharedPrefManger = new SharedPrefManger(mContext);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<StoresResponse> userlogincall = apiService.GetAllStores(String.valueOf(SharedPrefManger.getUser_ID()));
        userlogincall.enqueue(new Callback<StoresResponse>() {
            @Override
            public void onResponse(Call<StoresResponse> call, Response<StoresResponse> response) {
                if (response.body().getSuccess() == 1){
//                    DBHandler.deleteAllStoreData(mContext);
                    if(DBUtility.InsertStores(response.body().getData(),mContext) > 0){
                        loaded.onNext(++defult_load[0]);
                        Toast.makeText(mContext, "Store Added", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(mContext,"App have an error Store "+response.body().getError_msg(),Toast.LENGTH_SHORT).show();
                Log.e("url",call.request().url().toString());
            }

            @Override
            public void onFailure(Call<StoresResponse> call, Throwable t) {
                //Toast.makeText(mContext,"invalid data",Toast.LENGTH_SHORT).show();
                t.printStackTrace();
                UI_Utility.noConnection(mContext,true);
                Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("url",call.request().url().toString());
                Log.e("errmor",t.getMessage());
            }
        });

        Call<EventResponse> eventResponseCall = apiService.GetAllEvents(sharedPrefManger.getUser_ID());
        eventResponseCall.enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                Log.e("url", call.request().url().toString());
                if (response.body().getSuccess() == 1) {
//                    DBHandler.deleteAllEventData(mContext);
                    if (DBUtility.InsertEvents(response.body().getData(), mContext) > 0)
                        loaded.onNext(++defult_load[0]);
                    if (DBUtility.InsertCategories(response.body().getCategories(), mContext) > 0)
                        loaded.onNext(++defult_load[0]);

                    Toast.makeText(mContext, "Event Added", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(mContext, "App have an error Event "+response.body().getError_msg(), Toast.LENGTH_SHORT).show();
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
                    if(sharedPrefManger.getStore_ID() != 0){
                        mContext.startActivity(new Intent(mContext, MainStoreActivity.class));
                        ((Activity) mContext).finish();
                    }
                    else{
                        mContext.startActivity(new Intent(mContext, MainActivity.class));
                        ((Activity) mContext).finish();
                    }
                }else {
                    mContext.startActivity(new Intent(mContext, IntroActivity.class));
                    ((Activity) mContext).finish();
                }
            }
        });
    }
}
