package khaled.example.com.findup.UI.ViewModel.Fragments;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.Flowable;
import khaled.example.com.findup.Helper.Database.DBHandler;
import khaled.example.com.findup.Helper.Database.Interfaces.Category.Category;
import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.adapters.MainCategoriesAdapter;
import khaled.example.com.findup.UI.adapters.RecyclerTouchListener;

public class MainCatsViewModel extends Observable {
    Context mContext;

    public MainCatsViewModel(Context mContext) {
        this.mContext = mContext;
    }

    public void InitRecyclerView(RecyclerView recyclerView){
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        MainCategoriesAdapter adapter = new MainCategoriesAdapter(mContext, new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false) {
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
        LoadDataFromDataBase(adapter);
    }

    public void LoadDataFromDataBase(MainCategoriesAdapter adapter){
        DBHandler.GetAllCategories(mContext, new Category() {
            @Override
            public void onSuccess(Flowable<List<khaled.example.com.findup.models.Category>> listFlowable) {
                listFlowable.subscribe(val->{
                    ((Activity) mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.setCategoryList(val);
                            adapter.notifyDataSetChanged();
                        }
                    });
                });
            }
            @Override
            public void onFail() {

            }
        });
    }

}