package khaled.example.com.findup.UI.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.util.DbUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import khaled.example.com.findup.Helper.Database.DBHandler;
import khaled.example.com.findup.Helper.Database.Interfaces.Notifications.NotificationsUserI;
import khaled.example.com.findup.Helper.Database.Interfaces.Product.Products;
import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.adapters.NotificationsAdapter;
import khaled.example.com.findup.UI.adapters.StoreProductsReviewsAdapter;
import khaled.example.com.findup.models.NotificationUser;
import khaled.example.com.findup.models.Product;
import khaled.example.com.findup.models.ReviewStoreItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreAccountHomeFragment extends Fragment {


    public StoreAccountHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store_account_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        bindUI();
    }

    private void bindUI() {
        List<Product> storeProducts = new ArrayList<>();
        RecyclerView recyclerView = getActivity().findViewById(R.id.reviewsRecyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        StoreProductsReviewsAdapter adapter = new StoreProductsReviewsAdapter(getActivity(), storeProducts);
        LoadProduct(adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.smoothScrollToPosition(0);
    }
    private void LoadProduct(StoreProductsReviewsAdapter adapter){
        DBHandler.getStoreProducts(getActivity(), SharedPrefManger.getStore_ID(), new Products() {
            @Override
            public void onSuccess(Flowable<List<Product>> listFlowable) {
                listFlowable.subscribe(
                        val ->{
                            (getActivity()).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.e("Val Pro" , String.valueOf(val.size()));
                                    adapter.setProduct(val);
                                    adapter.notifyDataSetChanged();
                                }
                            });
                        }
                );
            }

            @Override
            public void getProduct(Flowable<Product> productFlowable) {

            }

            @Override
            public void onFail() {

            }
        });
    }


}
