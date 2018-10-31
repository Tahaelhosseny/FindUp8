package findupproducts.example.com.findup.UI.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.f2prateek.rx.preferences2.Preference;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.Flowable;
import findupproducts.example.com.findup.Helper.Database.DBHandler;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Store.Stores;
import findupproducts.example.com.findup.Helper.Location.LocationUtility;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.Helper.Utility;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.activities.CommentsActivity;
import findupproducts.example.com.findup.UI.activities.NotificationsActivity;
import findupproducts.example.com.findup.UI.activities.StoreEventsActivity;
import findupproducts.example.com.findup.UI.activities.StoreSettingsActivity;
import findupproducts.example.com.findup.UI.adapters.NearMeAdapter;
import findupproducts.example.com.findup.models.Store;

public class ProfileStoreFragment extends Fragment {
    private Store MStore;
    public ProfileStoreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_store, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().findViewById(R.id.btn_notifications).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NotificationsActivity.class));
            }
        });

        getActivity().findViewById(R.id.btn_Settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), StoreSettingsActivity.class));
            }
        });

        getActivity().findViewById(R.id.btn_events).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity() , StoreEventsActivity.class));
            }
        });

        TextView store_name = getActivity().findViewById(R.id.storeName);
        ImageView store_logo = getActivity().findViewById(R.id.profileImg);
        ImageView store_Bg = getActivity().findViewById(R.id.profileBg);
        TextView store_desc = getActivity().findViewById(R.id.storeDesc);
        TextView store_review = getActivity().findViewById(R.id.storeReview);
//        store_name.setText(SharedPrefManger.getStore_namee());
        bindStoreData(store_logo , store_review , store_desc , store_Bg , store_name);

    }
    public void bindStoreData(ImageView logo_image , TextView rating , TextView store_desc , ImageView banner , TextView name) {

        if (MStore == null) {
            SharedPrefManger sharedPrefManger = new SharedPrefManger(getActivity());
            DBHandler.getStoreByID(sharedPrefManger.getStore_ID(), getActivity(), new Stores() {
                @Override
                public void onSuccess(Flowable<List<Store>> listFlowable) {
                }
                @Override
                public void onFail() {

                }
                @Override
                public void getStoreID(Flowable<Store> storeFlowable) {
                    storeFlowable.subscribe(store -> {
                        (getActivity()).runOnUiThread(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    MStore = store;
                                                                    Picasso.with(getActivity()).load(store.getStore_logo()).into(logo_image);
                                                                    store_desc.setText(store.getStore_desc());
                                                                    if(store.getStore_rating() == null){
                                                                        rating.setText("0.0");
                                                                    }else{
                                                                        rating.setText(store.getStore_rating());
                                                                    }
                                                                    name.setText(store.getStore_name());
                                                                    Picasso.with(getActivity()).load(store.getStore_banner()).into(banner);
                                                                }
                                                            }
                        );
                    });
                }
            });
        }
    }
}
