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
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.util.DbUtils;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.ArrayList;
import java.util.List;

import findupproducts.example.com.findup.Helper.Database.Interfaces.Store.StoreWorkTimeI;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Store.Stores;
import findupproducts.example.com.findup.Helper.Utility;
import findupproducts.example.com.findup.UI.activities.CommentsActivity;
import findupproducts.example.com.findup.models.Store;
import findupproducts.example.com.findup.models.Store_WorkTime;
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
    private static final int PLACE_PICKER_REQUEST = 2;
    SharedPrefManger sharedPrefManger;

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
        TextView location = getActivity().findViewById(R.id.txt_location);
        TextView days = getActivity().findViewById(R.id.txt_days);
        TextView time = getActivity().findViewById(R.id.txt_time);
        TextView textView = getActivity().findViewById(R.id.product_txt_validation);
        Button setLocBtn = getActivity().findViewById(R.id.setLocBtn);
        setLocBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getActivity().startActivity(new Intent(getActivity(), SetLocationActivity.class));
                try {
                    pickAddress();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                }
            }
        });
        sharedPrefManger = new SharedPrefManger(getActivity());
        if (!sharedPrefManger.getStoreLocation_type().contains("Dynamic"))
            setLocBtn.setVisibility(View.GONE);

        TextView locationTypeTxt = getActivity().findViewById(R.id.locationTypeTxt);
        if (sharedPrefManger.getStoreLocation_type().contains("Static"))
            locationTypeTxt.setText("your Location is Static");
        else
            locationTypeTxt.setVisibility(View.GONE);


        SharedPrefManger sharedPrefManger = new SharedPrefManger(getActivity());
        if (!sharedPrefManger.getStoreLocation_type().equals("Dynamic")) {
            linearLayout.setVisibility(View.GONE);
        }else{
            DBHandler.getStoreByID(SharedPrefManger.getStore_ID(), getActivity(), new Stores() {
                @Override
                public void onSuccess(Flowable<List<Store>> listFlowable) {

                }
                @Override
                public void onFail() {

                }
                @Override
                public void getStoreID(Flowable<Store> storeFlowable) {
                    storeFlowable.subscribe(store -> {
                         getActivity().runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                if(store.getStore_location_address().equals("")){
                                    location.setText("No Address Specified Until Now");
                                }else {
                                    location.setText(store.getStore_location_address());
                                }
                                  getTimeDay(days , time);
                               }
                            }
                        );
                    });
                }
            });
        }
        adapter = new StoreProductsReviewsAdapter(getActivity(), new ArrayList<Product>());
        LoadProduct(recyclerView , textView);
    }

    private void getTimeDay(TextView days, TextView time) {
        DBHandler.getStoreWorkByID(SharedPrefManger.getStore_ID(), getActivity(), new StoreWorkTimeI() {
            @Override
            public void onSuccess(Flowable<List<Store_WorkTime>> listFlowable) {

            }

            @Override
            public void getStoreID(Flowable<Store_WorkTime> store_workTimeFlowable) {
                store_workTimeFlowable.subscribe(store -> {
                    getActivity().runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        if(store.getStore_workdays().equals("")){
                                                            days.setText("No Days Specified Until Now");
                                                        }else{
                                                            days.setText(store.getStore_workdays());
                                                        }
                                                        if(store.getWork_to_time().equals("") || store.getWork_from_time().equals("")){
                                                            days.setText("No Time Specified Until Now");
                                                        }else {
                                                            time.setText("From " + store.getWork_from_time() + " To " + store.getWork_to_time());
                                                        }
                                                    }
                                                }
                    );
                });
            }

            @Override
            public void onFail() {

            }
        });
    }

    private void bindUI(RecyclerView recyclerView) {
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
    private void LoadProduct(RecyclerView recyclerView , TextView textView){
        DBHandler.getProductByStoreID(SharedPrefManger.getStore_ID(), getActivity(), new Products() {
            @Override
            public void onSuccess(Flowable<List<Product>> listFlowable) {
                listFlowable.subscribe(
                        val ->{
                            (getActivity()).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.e("Val Pro" , String.valueOf(val.size()));
                                    List<Product> products = new ArrayList<>();
                                    for (int i = 0 ; i  < val.size() ; i++){
                                        products.add(val.get(i));
                                    }
                                    if(products.size() > 0) {
                                        adapter.setProduct(products);
                                        adapter.notifyDataSetChanged();
                                        bindUI(recyclerView);
                                    }else{
                                        textView.setVisibility(View.VISIBLE);
                                    }
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

    private void pickAddress() throws GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
    }
}
