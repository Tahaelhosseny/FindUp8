package findupproducts.example.com.findup.UI.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;
import com.patloew.rxlocation.RxLocation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import findupproducts.example.com.findup.Helper.Database.DBHandler;
import findupproducts.example.com.findup.Helper.Database.DBUtility;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Events;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Product.Products;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Store.Stores;
import findupproducts.example.com.findup.Helper.Location.LocationUtility;
import findupproducts.example.com.findup.Helper.Location.LocationView;
import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.GetAllSavedResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.NotificationResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.SaveModelResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.Helper.UI_Utility;
import findupproducts.example.com.findup.Helper.Utility;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.adapters.UserSavedAdapter;
import findupproducts.example.com.findup.UI.fragments.BottomBarFragment;
import findupproducts.example.com.findup.UI.fragments.MainFragment;
import findupproducts.example.com.findup.UI.fragments.MapFragment;
import findupproducts.example.com.findup.models.CurrentLocation;
import findupproducts.example.com.findup.models.Event;
import findupproducts.example.com.findup.models.FilterQueries;
import findupproducts.example.com.findup.models.Product;
import findupproducts.example.com.findup.models.SaveModel;
import findupproducts.example.com.findup.models.Store;
import findupproducts.example.com.findup.models.UserSavedItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements LocationView{
    public static FilterQueries filterData;
    public static List<Store>filteredMapDataStore;
    public static List<Event>filteredMapDataEvent;
    public static List<Product> searchedProducts;
    public static List<Event> searchedEvents;
    public static List<Store> searchedStore;

    Context context;
    Toolbar toolbar;
    private RxLocation rxLocation;
    FragmentTransaction transaction;
    LocationUtility locationUtility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filterData = new FilterQueries();
        filteredMapDataEvent = new ArrayList<>();
        filteredMapDataStore = new ArrayList<>();
        searchedEvents = new ArrayList<>(); searchedProducts = new ArrayList<>(); searchedStore = new ArrayList<>();
        toolbar =  findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        insertUserNotification();
        getUserSaved();
        FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        rxLocation = new RxLocation(this);
        rxLocation.setDefaultTimeout(15, TimeUnit.SECONDS);
        locationUtility = new LocationUtility(rxLocation);
        if(filteredMapDataEvent.size() == 0){
            DBHandler.getAllEvents(this, new Events() {
                @Override
                public void onSuccess(Flowable<List<Event>> listFlowable) {
                    listFlowable.subscribe(val->{
                        (MainActivity.this).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                filteredMapDataEvent.clear();
                                for (int i = 0 ; i < val.size() ; i++){
                                    filteredMapDataEvent.add(val.get(i));
                                }
                                Log.e("F Event Size" , String.valueOf(filteredMapDataEvent.size()));
                            }
                        });
                    });
                }

                @Override
                public void onFail() {

                }
            });
        }
        if (filteredMapDataStore.size() == 0){
            DBHandler.getAllStores(this, new Stores() {
                @Override
                public void onSuccess(Flowable<List<Store>> listFlowable) {
                    listFlowable.subscribe(val->{
                        (MainActivity.this).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                filteredMapDataStore.clear();
                                for (int i = 0 ; i < val.size() ; i++){
                                    filteredMapDataStore.add(val.get(i));
                                }
                                Log.e("Store Size " , String.valueOf(filteredMapDataStore.size()));
                            }
                        });
                    });
                }
                @Override
                public void getStoreID(Flowable<Store> storeFlowable) {

                }
                @Override
                public void onFail() {

                }
            });
        }
        if(searchedEvents.size() == 0){
            DBHandler.getAllEvents(this, new Events() {
                @Override
                public void onSuccess(Flowable<List<Event>> listFlowable) {
                    listFlowable.subscribe(val->{
                        (MainActivity.this).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                searchedEvents.clear();
                                for (int i = 0 ; i < val.size() ; i++){
                                    searchedEvents.add(val.get(i));
                                }
                                Log.e("F Event Size" , String.valueOf(searchedEvents.size()));
                            }
                        });
                    });
                }

                @Override
                public void onFail() {

                }
            });
        }
        if (searchedProducts.size() == 0){
            DBHandler.getAllProducts(this, new Products() {
                @Override
                public void onSuccess(Flowable<List<Product>> listFlowable) {
                    listFlowable.subscribe(val->{
                        (MainActivity.this).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                searchedProducts.clear();
                                for (int i = 0 ; i < val.size() ; i++){
                                    searchedProducts.add(val.get(i));
                                }
                                Log.e("F Event Size" , String.valueOf(searchedProducts.size()));
                            }
                        });
                    });
                }
                @Override
                public void getProduct(Flowable<Product> productFlowable) {

                }
                @Override
                public void onFail() {

                }
            });
        }
        if(searchedStore.size() == 0){
            DBHandler.getAllStores(this, new Stores() {
                @Override
                public void onSuccess(Flowable<List<Store>> listFlowable) {
                    listFlowable.subscribe(val->{
                        (MainActivity.this).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                searchedStore.clear();
                                for (int i = 0 ; i < val.size() ; i++){
                                    searchedStore.add(val.get(i));
                                }
                                Log.e("Store Size " , String.valueOf(searchedStore.size()));
                            }
                        });
                    });
                }
                @Override
                public void getStoreID(Flowable<Store> storeFlowable) {

                }
                @Override
                public void onFail() {

                }
            });
        }
        transaction.replace(R.id.main_toolbar_container, new MainFragment(), new MainFragment().getClass().getName()).commit();
        BottomBarFragment bottomBarFragment =new BottomBarFragment();
        Bundle bundle = new Bundle();
        getSupportFragmentManager().beginTransaction().replace(R.id.navigation_bottom_container, bottomBarFragment).commit();
    }
    public void getUserSaved(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<GetAllSavedResponse> getUserSaved = apiService.getUserSaved(SharedPrefManger.getUser_ID());
        getUserSaved.enqueue(new Callback<GetAllSavedResponse>() {
            @Override
            public void onResponse(Call<GetAllSavedResponse> call, Response<GetAllSavedResponse> response) {
                if(response.body().getSuccess() == 1 && response.body().getUser_data().size() > 0){
                    List<UserSavedItem> userSaved = response.body().getUser_data();
                    for (int i = 0 ; i < userSaved.size() ; i++){
                        DBHandler.InsertSavedItem(response.body().getUser_data().get(i) , MainActivity.this);
                    }
                    Log.e("Saved Item" , "Insertion Success");
                }else{
                    Log.e("Saved Item Else" ,response.body().getError_msg());
                }
            }
            @Override
            public void onFailure(Call<GetAllSavedResponse> call, Throwable t) {
                Log.e("Saved Item Fail " , t.getMessage());
            }
        });

    }
    private void insertUserNotification(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        SharedPrefManger sharedPrefManger = new SharedPrefManger(this);
        Call<NotificationResponse> userNotification = apiService.getUserNotification(String.valueOf(sharedPrefManger.getUser_ID()));
        userNotification.enqueue(new Callback<NotificationResponse>() {
            @Override
            public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
                if(response.body().getSuccess() == 1){
                    if (response.body().getUser_data().size()  >  0){
                        for (int i = 0 ; i < response.body().getUser_data().size() ; i++){
                            DBHandler.InsertUserNotifications(response.body().getUser_data().get(i) , MainActivity.this);
                        }
                    }
                    Log.e("Notification User " , "Insertion Success");
                }else{
                    Log.e("Noti User " , "There is Not Notification to this account yet Account ID : "+ SharedPrefManger.getUser_ID());
                }
            }

            @Override
            public void onFailure(Call<NotificationResponse> call, Throwable t) {
                Log.e("Noti User Failler " , t.getMessage());

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
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_toolbar_container);
                UI_Utility.BottomNavigationMenu_icons_change(BottomBarFragment.menu, Utility.fragmentTagsList().indexOf(fragment.getClass().getName()));
                Log.i("CurrentFragment",fragment.getClass().getName());
                BottomBarFragment.adapter.notifyDataSetChanged();
            }catch (Exception e){
                transaction.replace(R.id.main_toolbar_container, new MainFragment(), new MainFragment().getClass().getName()).commit();
            }

        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        locationUtility.attachView(this);
    }
    @Override
    public void onLocationUpdate(Location location) {
        SharedPrefManger.setCurrentLocation(new CurrentLocation(location.getLatitude(),location.getLongitude()));
    }
    @Override
    public void onLocationSettingsUnsuccessful() {

    }
    @Override
    public void onAddressUpdate(Address address) {

    }
}
