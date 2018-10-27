package khaled.example.com.findup.UI.ViewModel.Fragments;

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
import khaled.example.com.findup.UI.adapters.CatNameAdapter;
import khaled.example.com.findup.UI.adapters.MainCategoriesAdapter;

public class SortFilterViewModel extends Observable {
    private Context mContect;
    public SortFilterViewModel(Context mContext){
        this.mContect = mContext;
    }

    public void InitCatRecycler(RecyclerView recyclerView){
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        CatNameAdapter adapter = new CatNameAdapter(mContect, new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContect, LinearLayoutManager.VERTICAL, false));
        recyclerView.smoothScrollToPosition(0);
        LoadCatNameFromDatabase(adapter);
    }

    private void LoadCatNameFromDatabase(CatNameAdapter adapter){
        DBHandler.GetAllCategories(mContect, new Category() {
            @Override
            public void onSuccess(Flowable<List<khaled.example.com.findup.models.Category>> listFlowable) {
                listFlowable.subscribe(val->{
                    ((Activity) mContect).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.setCategories(val);
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
