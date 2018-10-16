package khaled.example.com.findup.UI.adapters;

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

import com.like.LikeButton;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import khaled.example.com.findup.Helper.Database.DBHandler;
import khaled.example.com.findup.Helper.Location.LocationUtility;
import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.SaveModelResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.StoresResponse;
import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.activities.StoreDetailsActivity;
import khaled.example.com.findup.models.CurrentLocation;
import khaled.example.com.findup.models.Store;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by khaled on 7/4/18.
 */

public class NearMeAdapter extends RecyclerView.Adapter<NearMeAdapter.ViewHolder> {

    private List<Store> stores;
    private Context context;
    private CurrentLocation currentLocation = new CurrentLocation();

    public NearMeAdapter(Context context, List<Store> stores) {
        this.context = context;
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

    SharedPrefManger sharedPrefManger;
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (sharedPrefManger == null)
            sharedPrefManger = new SharedPrefManger(context);

        holder.store = stores.get(position);
        holder.placeName.setText(holder.store.getStore_name());
        holder.distance.setText(holder.store.getPlaceDistane(context, currentLocation.getLocationModel()));
        holder.review.setText(holder.store.getPlaceReview());
        holder.shortDesc.setText(holder.store.getStore_desc());


        if (!holder.store.getStore_banner().isEmpty())
            Picasso.with(holder.placeImage.getContext()).load(holder.store.getStore_banner()).placeholder(R.color.material_color_grey_500).into(holder.placeImage);

        if (!SharedPrefManger.isIsLoggedIn())
            holder.likeButton.setVisibility(View.INVISIBLE);
        holder.likeButton.setLiked((holder.store.getIf_saved()==0)?false:true);
        holder.likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                SaveStore(context,holder.store,sharedPrefManger.getUser_ID(),likeButton);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                SaveStore(context,holder.store,sharedPrefManger.getUser_ID(),likeButton);
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

        public ViewHolder(View view) {
            super(view);
            placeImage = view.findViewById(R.id.nearItemImg);
            placeName = view.findViewById(R.id.nearItemName);
            distance = view.findViewById(R.id.nearItemDistance);
            review = view.findViewById(R.id.nearItemReview);
            shortDesc = view.findViewById(R.id.nearItemTags);
            likeButton = view.findViewById(R.id.star_button);
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
                store.setIf_saved((response.body().getUser_data().get(0).getSave_case().equals("saved"))?1:0);
                DBHandler.SaveStore(store,store.getIf_saved(),mContext);
            }
            @Override
            public void onFailure(Call<SaveModelResponse> call, Throwable t) {
                likeButton.setLiked((store.getIf_saved()==0)?false:true);
                Log.i("saved_error",t.getMessage());
            }
        });
    }
}
