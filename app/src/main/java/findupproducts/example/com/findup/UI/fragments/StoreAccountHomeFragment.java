package findupproducts.example.com.findup.UI.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.common.util.DbUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import findupproducts.example.com.findup.Helper.Database.DBHandler;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Notifications.NotificationsUserI;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Product.Products;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.activities.SetLocationActivity;
import findupproducts.example.com.findup.UI.adapters.NotificationsAdapter;
import findupproducts.example.com.findup.UI.adapters.StoreProductsReviewsAdapter;
import findupproducts.example.com.findup.models.NotificationUser;
import findupproducts.example.com.findup.models.Product;
import findupproducts.example.com.findup.models.ReviewStoreItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreAccountHomeFragment extends Fragment {

    StoreProductsReviewsAdapter adapter;

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
        RecyclerView recyclerView = getActivity().findViewById(R.id.reviewsRecyclerView);
        LinearLayout linearLayout = getActivity().findViewById(R.id.location_linear);
        Button setLocBtn = getActivity().findViewById(R.id.setLocBtn);
        setLocBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), SetLocationActivity.class));
            }
        });
        SharedPrefManger.setStoreLocation_type("dynamic");
        SharedPrefManger sharedPrefManger = new SharedPrefManger(getActivity());
        if (!sharedPrefManger.getStoreLocation_type().equals("dynamic"))
            linearLayout.setVisibility(View.GONE);

        adapter = new StoreProductsReviewsAdapter(getActivity(), new ArrayList<Product>());
        LoadProduct(recyclerView);
    }

    private void bindUI(RecyclerView recyclerView) {
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
    private void LoadProduct(RecyclerView recyclerView){
        DBHandler.getProductByStoreID(SharedPrefManger.getStore_ID(), getActivity(), new Products() {
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
                                    bindUI(recyclerView);
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
