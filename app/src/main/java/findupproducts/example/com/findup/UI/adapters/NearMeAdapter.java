package findupproducts.example.com.findup.UI.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import findupproducts.example.com.findup.Helper.Database.DBHandler;
import findupproducts.example.com.findup.Helper.Location.LocationUtility;
import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.SaveModelResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.activities.StoreDetailsActivity;
import findupproducts.example.com.findup.models.CurrentLocation;
import findupproducts.example.com.findup.models.Store;
import findupproducts.example.com.findup.netHelper.MakeRequest;
import findupproducts.example.com.findup.netHelper.OnCancelRetry;
import findupproducts.example.com.findup.netHelper.VolleyCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by khaled on 7/4/18.
 */

public class NearMeAdapter extends RecyclerView.Adapter<NearMeAdapter.ViewHolder> {

    private List<Store> permanently_stores;
    private List<Store> stores;
    private Context context;
    private Activity activity ;
    private CurrentLocation currentLocation = new CurrentLocation();
    SharedPrefManger sharedPrefManger;


    public NearMeAdapter(Activity context, List<Store> stores) {
        this.context = context;
        activity = context ;
        this.permanently_stores = LocationUtility.SortStoresByNearest(context, stores, currentLocation.getLocationModel());
        this.stores = LocationUtility.SortStoresByNearest(context, stores, currentLocation.getLocationModel());
    }

    public void setCurrentLocation(CurrentLocation currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setStores(List<Store> stores) {
        this.stores = LocationUtility.SortStoresByNearest(context, stores, currentLocation.getLocationModel());
    }

    @NonNull
    @Override
    public NearMeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.near_me_item, parent, false);
        return new NearMeAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (sharedPrefManger == null)
            sharedPrefManger = new SharedPrefManger(context);

        holder.store = stores.get(position);
        holder.placeName.setText(holder.store.getStore_name());
        holder.distance.setText(holder.store.getPlaceDistane(context, currentLocation.getLocationModel()));
        holder.review.setText(holder.store.getPlaceReview());
        holder.shortDesc.setText(holder.store.getStore_desc());


        if(holder.store.getIf_saved()==0)
        {
            Picasso.with(context).load(R.drawable.like_hert).into(holder.fav);
        }else
            {
                Picasso.with(context).load(R.drawable.likeed).into(holder.fav);
            }

        holder.fav.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(holder.store.getIf_saved()==0)
                    addToSave(holder.store.getStore_id() , holder);
                else
                    {
                        deleteFromSave(holder.store.getStore_id() , holder , position);
                    }
            }
        });

        if (!holder.store.getStore_banner().isEmpty()) {
            Transformation transformation = new RoundedTransformationBuilder()
                    .cornerRadiusDp(0 , 15)
                    .cornerRadiusDp(1 , 15)
                    .oval(false)
                    .build();
            Picasso.with(context).load(stores.get(position).getStore_banner()).transform(transformation).placeholder(R.drawable.near_by_place_holder).into(holder.placeImage);
        }
        if (!SharedPrefManger.isIsLoggedIn())
            holder.likeButton.setVisibility(View.INVISIBLE);
        holder.likeButton.setLiked(holder.store.getIf_saved() != 0);
        holder.likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                SaveStore(context, holder.store, SharedPrefManger.getUser_ID(), likeButton);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                SaveStore(context, holder.store, SharedPrefManger.getUser_ID(), likeButton);
            }
        });

    }

    @Override
    public int getItemCount() {
        return stores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView placeImage;
        TextView placeName;
        TextView distance;
        TextView review;
        TextView shortDesc;
        Store store;
        LikeButton likeButton;

        ImageView fav ;

        public ViewHolder(View view) {
            super(view);
            placeImage = view.findViewById(R.id.nearItemImg);
            placeName = view.findViewById(R.id.nearItemName);
            distance = view.findViewById(R.id.nearItemDistance);
            review = view.findViewById(R.id.nearItemReview);
            shortDesc = view.findViewById(R.id.nearItemTags);
            likeButton = view.findViewById(R.id.star_button);
            fav = view.findViewById(R.id.fav);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            v.getContext().startActivity(new Intent(v.getContext(), StoreDetailsActivity.class).putExtra("store_id", store.getStore_id()));
        }
    }
    private void SaveStore(Context mContext,Store store,int user_id,LikeButton likeButton){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<SaveModelResponse> saveModelResponseCall = apiService.addToSaved(user_id,store.getStore_id(),"Store");
        saveModelResponseCall.enqueue(new Callback<SaveModelResponse>() {
            @Override
            public void onResponse(Call<SaveModelResponse> call, Response<SaveModelResponse> response) {

                if(response.body().getUser_data().get(0).getSave_case().equals("saved")) {
                    store.setIf_saved(1);
                    DBHandler.SaveStore(store,store.getIf_saved(),mContext);
                    likeButton.setLiked(true);
                }else {
                    store.setIf_saved(0);
                    DBHandler.SaveStore(store,store.getIf_saved(),mContext);
                    likeButton.setLiked(false);
                }
            }
            @Override
            public void onFailure(Call<SaveModelResponse> call, Throwable t) {
                likeButton.setLiked(false);
                Log.i("saved_error",t.getMessage());
            }
        });
    }



    private void addToSave(int saved_id  , ViewHolder holder)
    {
        Map<String , String> map = new HashMap<String , String >() ;

        map.put("account_id" ,SharedPrefManger.getUser_ID()+"");
        map.put("saved_id" ,saved_id+"");
        map.put("saved_type" ,SharedPrefManger.getLogin_type());


        Activity activityApi = activity;

        MakeRequest makeRequest = new MakeRequest("http://findupproducts.com/findup_api/user_actions?tag=add_to_save&HashSecure=FindUpSecure_@@01072018" ,"1" , map,activityApi , "addToSave" ,true);
        makeRequest.request(new VolleyCallback() {
            @Override
            public void onSuccess(Map<String, String> result)
            {
                try {
                    JSONObject jsonObject = new JSONObject(result.get("res").toString());
                    if(jsonObject.getInt("success") == 1)
                    {
                        if(new JSONArray(jsonObject.getJSONArray("data").toString()).getJSONObject(0).getString("save_case").equals("saved"))
                        {
                            Picasso.with(activityApi).load(R.drawable.likeed).into(holder.fav);
                            holder.store.setIf_saved(1);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new OnCancelRetry() {
            @Override
            public void OnCacelRetry()
            {

            }
        });

    }


    private void deleteFromSave(int saved_id  , ViewHolder holder , int position)
    {
        Map<String , String> map = new HashMap<String , String >() ;

        map.put("account_id" ,SharedPrefManger.getUser_ID()+"");
        map.put("saved_id" ,saved_id+"");
        map.put("saved_type" ,SharedPrefManger.getLogin_type());


        Activity activityApi = activity;

        MakeRequest makeRequest = new MakeRequest("http://findupproducts.com/findup_api/user_actions?tag=delete_save&HashSecure=FindUpSecure_@@01072018" ,"1" , map,activityApi , "addToSave" ,true);
        makeRequest.request(new VolleyCallback() {
            @Override
            public void onSuccess(Map<String, String> result)
            {
                try {
                    JSONObject jsonObject = new JSONObject(result.get("res").toString());
                    if(jsonObject.getInt("success") == 1)
                    {
                            Picasso.with(activityApi).load(R.drawable.like_hert).into(holder.fav);
                            holder.store.setIf_saved(0);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new OnCancelRetry() {
            @Override
            public void OnCacelRetry()
            {

            }
        });

    }
}
