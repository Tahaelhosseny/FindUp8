package findupproducts.example.com.findup.UI.ViewModel.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.f2prateek.rx.preferences2.Preference;

import java.util.ArrayList;
import java.util.List;

import findupproducts.example.com.findup.Helper.Database.DBHandler;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Store.Stores;
import findupproducts.example.com.findup.Helper.Location.LocationUtility;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.Helper.Utility;
import findupproducts.example.com.findup.UI.adapters.NearMeAdapter;
import findupproducts.example.com.findup.models.Store;
import io.reactivex.Flowable;

public class NearMeViewModel extends java.util.Observable {
    private Context mContext;
    NearMeAdapter adapter;
    List<Store> all_stores = new ArrayList<>();
    private TextView no_stores_txt;

    public NearMeViewModel(Context mContext, TextView no_stores_txt) {
        this.mContext = mContext;
        this.no_stores_txt = no_stores_txt;
    }

    public void InitRecyclerView(Bundle bundle, RecyclerView recyclerView) {
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new NearMeAdapter(mContext, new ArrayList<>());

        LoadStoresFromDatabase(adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.smoothScrollToPosition(0);
    }


    public void LoadStoresFromDatabase(NearMeAdapter adapter) {
        Utility.UpdateCurrentLocation((Activity) mContext, mContext);

        SharedPrefManger sharedPrefManger = new SharedPrefManger(mContext);

        Preference<Float> Latitude = SharedPrefManger.getLatitude();
        Latitude.asObservable().subscribe(val -> LocationUtility.LatitudeToAdapter(val, adapter));

        Preference<Float> Longitude = SharedPrefManger.getLongitude();
        Longitude.asObservable().subscribe(val -> LocationUtility.LongitudeToAdapter(val, adapter));

        DBHandler.getAllStores(mContext, new Stores() {
            @Override
            public void onSuccess(Flowable<List<Store>> listFlowable) {

                listFlowable.subscribe(
                        val -> {
                            ((Activity) mContext).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    all_stores.clear();
                                    all_stores.addAll(val);
                                    //all_stores = val;
                                    adapter.setStores(val);
                                    adapter.notifyDataSetChanged();
                                    ValidateIsEmpty();
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

    public void FilterAdapter(String search_text) {
        if (!search_text.isEmpty())
            adapter.setStores(Utility.SearchStores(all_stores, search_text));
        else
            adapter.setStores(all_stores);
        adapter.notifyDataSetChanged();
        ValidateIsEmpty();
    }

    public void ValidateIsEmpty() {
        if (adapter.getItemCount() == 0)
            no_stores_txt.setVisibility(View.VISIBLE);
        else
            no_stores_txt.setVisibility(View.GONE);
    }
}
