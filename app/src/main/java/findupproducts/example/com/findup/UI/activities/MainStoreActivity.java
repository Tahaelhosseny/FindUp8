package findupproducts.example.com.findup.UI.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import findupproducts.example.com.findup.Helper.Database.DBHandler;
import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.NotificationResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.StoreNotificationResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.Helper.UI_Utility;
import findupproducts.example.com.findup.Helper.Utility;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.fragments.BottomBarFragment;
import findupproducts.example.com.findup.UI.fragments.BottomStoreFragment;
import findupproducts.example.com.findup.UI.fragments.MainFragment;
import findupproducts.example.com.findup.UI.fragments.MainStoreFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainStoreActivity extends AppCompatActivity {
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_store);

        /*getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);*/
        insertSoreNotification();
        FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.store_main_container, new MainStoreFragment(), new MainStoreFragment().getClass().getName()).commit();

        BottomStoreFragment bottomStoreFragment = new BottomStoreFragment();
        Bundle bundle = new Bundle();
        getSupportFragmentManager().beginTransaction().replace(R.id.store_bottom_container, bottomStoreFragment).commit();
    }

    private void insertSoreNotification(){
        SharedPrefManger sharedPrefManger = new SharedPrefManger(this);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<StoreNotificationResponse> userNotification = apiService.getStoreNotification(sharedPrefManger.getStore_ID());
        userNotification.enqueue(new Callback<StoreNotificationResponse>() {
            @Override
            public void onResponse(Call<StoreNotificationResponse> call, Response<StoreNotificationResponse> response) {
                if(response.body().getSuccess() == 1){
                    if (response.body().getData().size()  >  0){
                        for (int i = 0 ; i < response.body().getData().size() ; i++){
                            DBHandler.InsertStoreNotifications(response.body().getData().get(i) , MainStoreActivity.this);
                        }
                    }
                    Log.e("Notification Store " , "Insertion Success");
                }else{
                    Log.e("Noti Store " , "There is Not Notification to this account yet Account ID : "+ SharedPrefManger.getStore_ID());
                }
            }

            @Override

            public void onFailure(Call<StoreNotificationResponse> call, Throwable t) {
                Log.e("Noti Store Failler " , t.getMessage());

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0)
            super.onBackPressed();
        else {
            try {
                getSupportFragmentManager().popBackStackImmediate();
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.store_main_container);
                UI_Utility.BottomNavigationStoreMenu_icons_change(BottomStoreFragment.menu, Utility.storeFragmentTagsList().indexOf(fragment.getClass().getName()));
                Log.i("CurrentFragment",fragment.getClass().getName());
                BottomStoreFragment.adapter.notifyDataSetChanged();
            }catch (Exception e){
                transaction.replace(R.id.store_main_container, new MainFragment(), new MainStoreFragment().getClass().getName()).commit();
            }

        }
    }
}
