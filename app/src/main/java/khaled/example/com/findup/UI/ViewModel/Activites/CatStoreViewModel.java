package khaled.example.com.findup.UI.ViewModel.Activites;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.Flowable;
import khaled.example.com.findup.Helper.Database.DBHandler;
import khaled.example.com.findup.Helper.Database.Interfaces.Category.Category;
import khaled.example.com.findup.Helper.Database.Interfaces.Store.Stores;
import khaled.example.com.findup.UI.adapters.CatNameAdapter;
import khaled.example.com.findup.UI.adapters.MainCategoriesAdapter;
import khaled.example.com.findup.UI.adapters.StoreByCatAdapter;
import khaled.example.com.findup.models.Store;

public class CatStoreViewModel extends Observable{
    private Context mContext;
    public CatStoreViewModel(Context mContext){
        this.mContext = mContext;
    }

    public void InitRecyclerView(RecyclerView recyclerView , int id){
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        StoreByCatAdapter adapter = new StoreByCatAdapter(mContext, new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }

            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        });
        recyclerView.smoothScrollToPosition(0);
        LoadDataFromDataBase(adapter , id);
    }

    public void LoadDataFromDataBase(StoreByCatAdapter adapter , int id){
        DBHandler.getStoreByCatid(id, mContext, new Stores() {
            @Override
            public void onSuccess(Flowable<List<Store>> listFlowable) {
                listFlowable.subscribe(val ->{
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
//adapter.setCategoryList(val);
//        adapter.notifyDataSetChanged();