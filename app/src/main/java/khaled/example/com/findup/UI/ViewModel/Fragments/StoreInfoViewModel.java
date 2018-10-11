package khaled.example.com.findup.UI.ViewModel.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.Flowable;
import khaled.example.com.findup.Helper.Database.DBHandler;
import khaled.example.com.findup.Helper.Database.Interfaces.Store.Stores;
import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.AddCommentStoreResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.RateResponse;
import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.Helper.Utility;
import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.CustomViews.OverlapDecoration;
import khaled.example.com.findup.UI.activities.CommentsActivity;
import khaled.example.com.findup.UI.activities.PhotosGalleryActivity;
import khaled.example.com.findup.UI.adapters.CommentsPhotosAdapter;
import khaled.example.com.findup.UI.adapters.RecyclerTouchListener;
import khaled.example.com.findup.UI.adapters.StorePhotosAdapter;
import khaled.example.com.findup.models.Comment;
import khaled.example.com.findup.models.Store;
import khaled.example.com.findup.models.StorePhoto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreInfoViewModel extends Observable {
    private Context mContext;
    private int store_id;
    private Store MStore;

    public StoreInfoViewModel(Context mContext, int store_id) {
        this.mContext = mContext;
        this.store_id = store_id;
    }



    public void bindCommentsPhotos(RecyclerView recyclerView) {
        List<Comment> commentList = new ArrayList<>();


        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        CommentsPhotosAdapter adapter = new CommentsPhotosAdapter(mContext, commentList);
        recyclerView.setAdapter(adapter);


        DBHandler.getCommentByStoreID(store_id, mContext, new khaled.example.com.findup.Helper.Database.Interfaces.Comment.Comment() {
            @Override
            public void onSuccess(Flowable<List<Comment>> commentFlowable) {
                commentFlowable.subscribe(val -> {
                    ((Activity) mContext).runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            commentList.clear();
                            commentList.addAll(val);
                            recyclerView.setAdapter(adapter);
                        }
                    });
                    //adapter.notifyDataSetChanged();
                });
            }

            @Override
            public void onFail() {

            }
        });

        //recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(mContext
                , recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                mContext.startActivity(new Intent(mContext, CommentsActivity.class));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        recyclerView.addItemDecoration(new OverlapDecoration());
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));

    }

    public void bindPhotos(RecyclerView recyclerView) {
        List<StorePhoto> photos = new ArrayList<>();

        recyclerView.setItemAnimator(new DefaultItemAnimator());

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
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(mContext
                , recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                mContext.startActivity(new Intent(mContext, PhotosGalleryActivity.class));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        DBHandler.getStorePhotosByStoreID(store_id, mContext, new khaled.example.com.findup.Helper.Database.Interfaces.Store.StorePhotos() {
            @Override
            public void onSuccess(Flowable<List<StorePhoto>> listFlowable) {
                listFlowable.subscribe(val -> {
                    ((Activity) mContext).runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            photos.clear();
                            photos.addAll(val);
                            StorePhotosAdapter adapter = new StorePhotosAdapter(mContext, val);
                            recyclerView.setAdapter(adapter);
                        }
                    });
                    //adapter.notifyDataSetChanged();
                });
            }

        });

    }

    public void onMapReadyBind(GoogleMap googleMap, Button get_directions_btn, TextView address) {


        if (MStore == null) {
            DBHandler.getStoreByID(store_id, mContext, new Stores() {
                @Override
                public void onSuccess(Flowable<List<Store>> listFlowable) {

                }

                @Override
                public void onFail() {

                }

                @Override
                public void getStoreID(Flowable<Store> storeFlowable) {
                    storeFlowable.subscribe(store -> {
                        ((Activity) mContext).runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                bind_map_data(store, googleMap, get_directions_btn, address);
                            }

                        });

                    });
                }
            });
        } else
            bind_map_data(MStore, googleMap, get_directions_btn, address);

    }

    private void bind_map_data(Store store, GoogleMap googleMap, Button get_directions_btn, TextView address) {
        MStore = store;
        LatLng sydney = new LatLng(store.getStore_latitude(), store.getStore_longitude());
        googleMap.addMarker(new MarkerOptions().position(sydney).title(store.getStore_name()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(sydney.latitude - ((sydney.latitude * 14) / 1000000), sydney.longitude - ((sydney.longitude * 14) / 400000)), 14));
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        get_directions_btn.setOnClickListener(v -> Utility.OpenGoogleMaps(mContext, store.getStore_latitude(), store.getStore_longitude()));
        address.setText(store.getStore_location_address());
    }


    public void bindStoreData(TextView aboutTxtDetails, TextView workTimeDaysInfoTxt, TextView workTimeInfoTxt,
    ImageView mailImg, ImageView siteImg, ImageView chatImg, ImageView twitterImg, ImageView snapImg,ImageView show_comments
    ) {

        if (MStore == null) {
            DBHandler.getStoreByID(store_id, mContext, new Stores() {
                @Override
                public void onSuccess(Flowable<List<Store>> listFlowable) {

                }

                @Override
                public void onFail() {

                }

                @Override
                public void getStoreID(Flowable<Store> storeFlowable) {
                    storeFlowable.subscribe(store -> {
                        ((Activity) mContext).runOnUiThread(new Runnable() {

                           @Override
                           public void run() {
                                   MStore = store;
                                   aboutTxtDetails.setText(store.getStore_about());
                                                                    //workTimeDaysInfoTxt.setText(UI_Utility.WorkDaysToString(store.getStore_workdays()));
                                                                    //workTimeInfoTxt.setText(UI_Utility.WorkTimeToString(store.getStore_worktime()));
                                                                    //mailImg.setOnClickListener(v -> /*Utility.sendEmail(mContext,store.get); */);
                                   siteImg.setOnClickListener(v -> Utility.OpenWebSite(mContext, store.getStore_website_link()));
                                   chatImg.setOnClickListener(v -> Utility.OpenChatWithStore(mContext, store.getStore_id()));
                                   twitterImg.setOnClickListener(v -> Utility.OpenTwitterAccount(mContext, store.getStore_twitter_link()));
                                                                    //snapImg.setOnClickListener( v -> Utility.OpenSnapChatAccount(mContext,store.gets));
                                   show_comments.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, CommentsActivity.class).putExtra("store_id",store.getStore_id())));
                                   }
                                 }
                        );
                    });
                }
            });
        }
    }


    public void rateStore(float rate, int store_id){
        if(SharedPrefManger.getUser_ID() == 0 || SharedPrefManger.getUser_ID() < 1){
            Toast.makeText(mContext, "Please Login First Before Rate Store", Toast.LENGTH_SHORT).show();
        }else {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<RateResponse> rateStore = apiService.rateStore(SharedPrefManger.getUser_ID() , rate , store_id);
            rateStore.enqueue(new Callback<RateResponse>() {
                @Override
                public void onResponse(Call<RateResponse> call, Response<RateResponse> response) {
                    if (response.body().getError() == 0) {
                        Toast.makeText(mContext, ""+store_id, Toast.LENGTH_SHORT).show();
                        Toast.makeText(mContext, ""+rate, Toast.LENGTH_SHORT).show();
                        Toast.makeText(mContext, "Successfully", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(mContext, ""+response.body().getError_msg(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<RateResponse> call, Throwable t) {
                    Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}


