package findupproducts.example.com.findup.UI.ViewModel.Fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.f2prateek.rx.preferences2.Preference;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import findupproducts.example.com.findup.Helper.Database.DBHandler;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Store.Stores;
import findupproducts.example.com.findup.Helper.Location.LocationUtility;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.fragments.ProductsFragment;
import findupproducts.example.com.findup.UI.fragments.StoreInfoFragment;
import findupproducts.example.com.findup.models.CurrentLocation;
import findupproducts.example.com.findup.models.Store;
import findupproducts.example.com.findup.models.TabEntity;

public class StoreDetailsViewModel extends java.util.Observable {
    private Context mContext;
    private int StoreID;
    private Store store;

    public StoreDetailsViewModel(Context mContext, int storeID) {
        this.mContext = mContext;
        this.StoreID = storeID;
    }

    public void AssignDataToFields(ImageView storeImg, TextView storeName, TextView storeDis, TextView storeBriefTxt, TextView store_rating) {
        DBHandler.getStoreByID(StoreID, mContext, new Stores() {
            @Override
            public void onSuccess(Flowable<List<Store>> listFlowable) {

            }

            @Override
            public void getStoreID(Flowable<Store> storeFlowable) {
                //Utility.UpdateCurrentLocation((Activity) mContext,mContext);
                SharedPrefManger sharedPrefManger = new SharedPrefManger(mContext);
                CurrentLocation location = new CurrentLocation();
                Preference<Float> Latitude = sharedPrefManger.getLatitude();
                Latitude.asObservable().subscribe(latitude -> LocationUtility.LatitudeToCurrentLocationModel(latitude, location));

                Preference<Float> Longitude = sharedPrefManger.getLongitude();
                Longitude.asObservable().subscribe(longitude -> LocationUtility.LongitudeToCurrentLocationModel(longitude, location));

                Flowable<CurrentLocation> currentLocationFlowable = Flowable.fromCallable(new Callable<CurrentLocation>() {
                    @Override
                    public CurrentLocation call() throws Exception {
                        return location;
                    }
                });

                storeFlowable.subscribe(
                        val -> {
                            ((Activity) mContext).runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    store = val;
                                    if (!val.getStore_banner().isEmpty())
                                        Picasso.with(mContext).load(val.getStore_banner()).placeholder(R.drawable.near_by_place_holder).into(storeImg);
                                    storeName.setText(val.getStore_name());
                                    storeDis.setText(val.getStore_name());
                                    storeBriefTxt.setText(val.getStore_desc());
                                    store_rating.setText(val.getStore_rating());
                                    currentLocationFlowable.subscribe(loca -> storeDis.setText(val.getPlaceDistane(mContext, loca.getLocationModel())));

                                }
                            });
                        },
                        err -> Log.i("database err", "store database error : " + err.getMessage())


                );
            }

            @Override
            public void onFail() {

            }
        });
    }


    public void InitTabs(CommonTabLayout tabLayout) {
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        mTabEntities.add(new TabEntity("Information"));
        mTabEntities.add(new TabEntity("Products"));
        ArrayList<Fragment> fragmentList = new ArrayList<>();


        Bundle bundle = new Bundle();
        DBHandler.getStoreByID(StoreID, mContext, new Stores() {
            @Override
            public void onSuccess(Flowable<List<Store>> listFlowable) {

            }

            @Override
            public void getStoreID(Flowable<Store> storeFlowable) {
                storeFlowable.subscribe(val -> {
                    ((Activity) mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            StoreInfoFragment storeInfoFragment = new StoreInfoFragment();
                            storeInfoFragment.setArguments(bundle);

                            ProductsFragment productsFragment = new ProductsFragment();
                            bundle.putInt("store_id", val.getStore_id());
                            productsFragment.setArguments(bundle);

                            fragmentList.add(storeInfoFragment);
                            fragmentList.add(productsFragment);
                            tabLayout.notifyDataSetChanged();
                            tabLayout.setTabData(mTabEntities, (FragmentActivity) mContext, R.id.fl_change, fragmentList);
                            tabLayout.setIconHeight(0);
                            tabLayout.setIconVisible(false);
                            tabLayout.getTitleView(0).setTypeface(Typeface.create("sfcompactdisplay_semibold", Typeface.NORMAL));
                            tabLayout.getTitleView(1).setTypeface(Typeface.create("sfcompactdisplay_heavy", Typeface.NORMAL));
                        }
                    });
                });
            }

            @Override
            public void onFail() {

            }
        });


        //Typeface mTypeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/sfcompactdisplay_semibold.ttf");
        // mTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/sfcompactdisplay_semibold.ttf");


        //tabLayout.setupWithViewPager(viewPager);
        //tabLayout.setSelectedTabIndicatorColor(getActivity().getResources().getColor(R.color.material_color_deep_orange_accent));
        /*tabLayout.clearFocus();
        tabLayout.setFocusableInTouchMode(false);
        tabLayout.setFocusable(false);
        tabLayout.dispatchWindowFocusChanged(false);*/

    }
}
