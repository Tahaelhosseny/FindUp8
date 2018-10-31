package findupproducts.example.com.findup.UI.ViewModel.Fragments;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.f2prateek.rx.preferences2.Preference;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import findupproducts.example.com.findup.Helper.Database.DBHandler;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Store.Stores;
import findupproducts.example.com.findup.Helper.Location.LocationUtility;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.Helper.Utility;
import findupproducts.example.com.findup.UI.adapters.NearMeAdapter;
import findupproducts.example.com.findup.models.Store;

public class NearMeViewModel extends java.util.Observable {
    private Context mContext;

    public NearMeViewModel(Context mContext) {
        this.mContext = mContext;
    }

    public void InitRecyclerView(RecyclerView recyclerView) {
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        NearMeAdapter adapter = new NearMeAdapter(mContext, new ArrayList<>());
        LoadStoresFromDatabase(adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.smoothScrollToPosition(0);
    }


    public void LoadStoresFromDatabase(NearMeAdapter adapter) {
        Utility.UpdateCurrentLocation((Activity) mContext, mContext);

        SharedPrefManger sharedPrefManger = new SharedPrefManger(mContext);

        Preference<Float> Latitude = sharedPrefManger.getLatitude();
        Latitude.asObservable().subscribe(val -> LocationUtility.LatitudeToAdapter(val, adapter));

        Preference<Float> Longitude = sharedPrefManger.getLongitude();
        Longitude.asObservable().subscribe(val -> LocationUtility.LongitudeToAdapter(val, adapter));

        DBHandler.getAllStores(mContext, new Stores() {
            @Override
            public void onSuccess(Flowable<List<Store>> listFlowable) {

                listFlowable.subscribe(
                        val -> {
                            ((Activity) mContext).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.setStores(val);
                                    adapter.notifyDataSetChanged();
                                }
                            });
                        },
                        err -> Log.i("database err", "store database error : " + err.getMessage())
                );

            }

            @Override
            public void onFail() {

            }

            @Override
            public void getStoreID(Flowable<Store> storeFlowable) {

            }
        });

    }
}
