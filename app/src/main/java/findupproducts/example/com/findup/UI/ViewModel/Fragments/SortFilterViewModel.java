package findupproducts.example.com.findup.UI.ViewModel.Fragments;

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
import findupproducts.example.com.findup.Helper.Database.DBHandler;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Category.Category;
import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.SearchStoreResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.UI.adapters.CatNameAdapter;
import findupproducts.example.com.findup.UI.adapters.MainCategoriesAdapter;
import findupproducts.example.com.findup.models.Event;
import findupproducts.example.com.findup.models.Product;
import findupproducts.example.com.findup.models.Store;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static findupproducts.example.com.findup.UI.activities.MainActivity.filterData;
import static findupproducts.example.com.findup.UI.activities.MainActivity.filteredMapDataEvent;
import static findupproducts.example.com.findup.UI.activities.MainActivity.filteredMapDataStore;
import static findupproducts.example.com.findup.UI.activities.MainActivity.searchedEvents;
import static findupproducts.example.com.findup.UI.activities.MainActivity.searchedProducts;
import static findupproducts.example.com.findup.UI.activities.MainActivity.searchedStore;


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
            public void onSuccess(Flowable<List<findupproducts.example.com.findup.models.Category>> listFlowable) {
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
                if (response.body().getSuccess() == 1) {
                    if(filterData.getSearch_from().equals("FromMap")) {
                        List<Store> stores = response.body().getData().get(0).getStores();
                        List<Event> events = response.body().getData().get(0).getEvents();
                        if (events.size() == 0) {
                            Toast.makeText(mContext, "There is no data match this search from events", Toast.LENGTH_SHORT).show();
                        } else {
                            filteredMapDataEvent.clear();
                            Toast.makeText(mContext, "We found " + events.size() + " event that match your search", Toast.LENGTH_SHORT).show();
                        }
                        if (stores.size() == 0) {
                            Toast.makeText(mContext, "There is no data match this search from stores", Toast.LENGTH_SHORT).show();
                        } else {
                            filteredMapDataStore.clear();
                            Toast.makeText(mContext, "We found " + stores.size() + " store that match your search", Toast.LENGTH_SHORT).show();
                        }

                        for (int i = 0; i < stores.size(); i++) {
                            filteredMapDataStore.add(stores.get(i));
                        }
                        for (int i = 0; i < events.size(); i++) {
                            filteredMapDataEvent.add(events.get(i));
                        }
                    }else{
                        List<Store> stores = response.body().getData().get(0).getStores();
                        List<Event> events = response.body().getData().get(0).getEvents();
                        List<Product> products = response.body().getData().get(0).getProducts();
                        if(products.size() == 0){
                            Toast.makeText(mContext, "There is no data match this search from products", Toast.LENGTH_SHORT).show();
                        }else {
                            searchedProducts.clear();
                            Toast.makeText(mContext, "We found " + products.size() + " products that match your search", Toast.LENGTH_SHORT).show();
                        }
                        if (events.size() == 0) {
                            Toast.makeText(mContext, "There is no data match this search from events", Toast.LENGTH_SHORT).show();
                        } else {
                            searchedEvents.clear();
                            Toast.makeText(mContext, "We found " + events.size() + " event that match your search", Toast.LENGTH_SHORT).show();
                        }
                        if (stores.size() == 0) {
                            Toast.makeText(mContext, "There is no data match this search from stores", Toast.LENGTH_SHORT).show();
                        } else {
                            searchedStore.clear();
                            Toast.makeText(mContext, "We found " + stores.size() + " store that match your search", Toast.LENGTH_SHORT).show();
                        }
                        for (int i = 0; i < stores.size(); i++) {
                            searchedStore.add(stores.get(i));
                        }
                        for (int i = 0; i < events.size(); i++) {
                            searchedEvents.add(events.get(i));
                        }
                        for (int i = 0; i < products.size(); i++) {
                            searchedProducts.add(products.get(i));
                        }
                    }

                }
            }
            @Override
            public void onFailure(Call<SearchStoreResponse> call, Throwable t) {
                Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
