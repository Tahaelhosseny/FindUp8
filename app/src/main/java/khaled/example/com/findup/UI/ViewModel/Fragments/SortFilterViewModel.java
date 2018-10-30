package khaled.example.com.findup.UI.ViewModel.Fragments;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.Flowable;
import khaled.example.com.findup.Helper.Database.DBHandler;
import khaled.example.com.findup.Helper.Database.Interfaces.Category.Category;
import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.SearchStoreResponse;
import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.UI.adapters.CatNameAdapter;
import khaled.example.com.findup.UI.adapters.MainCategoriesAdapter;
import khaled.example.com.findup.models.Event;
import khaled.example.com.findup.models.Store;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static khaled.example.com.findup.UI.activities.MainActivity.filterData;
import static khaled.example.com.findup.UI.activities.MainActivity.filteredMapDataEvent;
import static khaled.example.com.findup.UI.activities.MainActivity.filteredMapDataStore;


public class SortFilterViewModel extends Observable {
    private Context mContext;
    public SortFilterViewModel(Context mContext){
        this.mContext = mContext;
    }

    public void InitCatRecycler(RecyclerView recyclerView){
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        CatNameAdapter adapter = new CatNameAdapter(mContext, new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.smoothScrollToPosition(0);
        LoadCatNameFromDatabase(adapter);
    }
    private void LoadCatNameFromDatabase(CatNameAdapter adapter){
        DBHandler.GetAllCategories(mContext, new Category() {
            @Override
            public void onSuccess(Flowable<List<khaled.example.com.findup.models.Category>> listFlowable) {
                listFlowable.subscribe(val->{
                    ((Activity) mContext).runOnUiThread(new Runnable() {
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
    public void getFilteredData(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<SearchStoreResponse> getFiltered = apiService.getFilteredStores(SharedPrefManger.getUser_ID(),
                filterData.getSearch_text(), filterData.getFilter_price()
                ,filterData.getFilter_rate(),filterData.getFilter_opennow(),filterData.getFilter_distance()
                ,filterData.getSearch_from(),"0.0",
                "0.0",filterData.getFilter_by(),filterData.getFilter_byid());
        getFiltered.enqueue(new Callback<SearchStoreResponse>() {
            @Override
            public void onResponse(Call<SearchStoreResponse> call, Response<SearchStoreResponse> response) {
                if(response.body().getSuccess() == 1){
                    filteredMapDataStore.clear();
                    filteredMapDataStore.clear();
                    List<Store> stores = response.body().getData().get(0).getStores();
                    List<Event> events = response.body().getData().get(0).getEvents();
                    for (int i = 0 ; i < stores.size() ; i++){
                        filteredMapDataStore.add(stores.get(i));
                    }
                    for (int i = 0 ; i < events.size() ; i++){
                        filteredMapDataEvent.add(events.get(i));
                    }
                    Toast.makeText(mContext, "Your Data Updated Store " +filteredMapDataStore.size() + " Event " + filteredMapDataEvent.size() , Toast.LENGTH_SHORT).show();
                    ((Activity) mContext).finish();
                }else{
                    Toast.makeText(mContext, ""+response.body().getError_msg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchStoreResponse> call, Throwable t) {
                Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
