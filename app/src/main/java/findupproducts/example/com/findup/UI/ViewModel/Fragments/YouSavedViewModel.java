package findupproducts.example.com.findup.UI.ViewModel.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import findupproducts.example.com.findup.Helper.Database.DBHandler;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Store.Stores;
import findupproducts.example.com.findup.UI.adapters.UserSavedAdapter;
import findupproducts.example.com.findup.models.Store;
import io.reactivex.Flowable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YouSavedViewModel extends Observable {
    private Context mContext;
    List<Store> savedItems = new ArrayList<>();
    public YouSavedViewModel(Context mContext){this.mContext = mContext;}
    public void InitRecycler(RecyclerView recyclerView){
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        UserSavedAdapter adapter = new UserSavedAdapter(mContext, savedItems);
        LoadSavedFromDatabase(adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.smoothScrollToPosition(0);
    }
    private void LoadSavedFromDatabase(UserSavedAdapter adapter){
        DBHandler.getAllSavedItem(mContext, new Stores() {
            @Override
            public void onSuccess(Flowable<List<Store>> listFlowable) {
                listFlowable.subscribe(val->{
                    ((Activity) mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.setSavedList(val);
                            adapter.notifyDataSetChanged();
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
}
