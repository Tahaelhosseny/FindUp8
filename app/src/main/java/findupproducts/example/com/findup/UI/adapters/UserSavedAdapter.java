package findupproducts.example.com.findup.UI.adapters;


import android.content.Context;
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
import com.squareup.picasso.Transformation;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;

import java.util.List;

import findupproducts.example.com.findup.Helper.Database.DBHandler;
import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.SaveModelResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.models.Category;
import findupproducts.example.com.findup.models.Product;
import findupproducts.example.com.findup.models.SaveModel;
import findupproducts.example.com.findup.models.Search;
import findupproducts.example.com.findup.models.Store;
import findupproducts.example.com.findup.models.UserSavedItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by khaled on 8/1/18.
 */

public class UserSavedAdapter extends RecyclerView.Adapter<UserSavedAdapter.ViewHolder>{
    private List<Store> userSavedItems;
    private Context context;
    public UserSavedAdapter(Context context, List<Store> userSavedItems) {
        this.context = context;
        this.userSavedItems = userSavedItems;
    }
    public void setSavedList(List<Store> userSavedItems) {
        this.userSavedItems = userSavedItems;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView userSavedImage;
        TextView userSavedName;
        TextView userSavedDesc;
        LikeButton savedItem;

        public ViewHolder(View view) {
            super(view);
            userSavedImage = view.findViewById(R.id.userSavedItemImg);
            userSavedDesc = view.findViewById(R.id.userSavedItemDesc);
            userSavedName = view.findViewById(R.id.userSavedItemName);
            savedItem = view.findViewById(R.id.savedItemStarBtn);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
        }
    }
    @NonNull
    @Override
    public UserSavedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_saved_item, parent, false);
        return new UserSavedAdapter.ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull UserSavedAdapter.ViewHolder holder, int position) {
        holder.userSavedName.setText(userSavedItems.get(position).getStore_name());
        holder.userSavedDesc.setText(userSavedItems.get(position).getStore_desc());
        if (!userSavedItems.get(position).getStore_banner().isEmpty()) {
                Transformation transformation = new RoundedTransformationBuilder()
                        .cornerRadiusDp(40)
                        .oval(false)
                        .build();
                Picasso.with(holder.userSavedImage.getContext()).load(userSavedItems.get(position).getStore_banner()).transform(transformation).placeholder(R.drawable.near_by_place_holder).into(holder.userSavedImage);
        }
        holder.savedItem.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {

            }

            @Override
            public void unLiked(LikeButton likeButton) {
                SaveStore(context , userSavedItems.get(position), SharedPrefManger.getUser_ID(),likeButton);
            }
        });

    }
    @Override
    public int getItemCount() {
        return userSavedItems.size();
    }
    private void SaveStore(Context mContext,Store store,int user_id,LikeButton likeButton){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<SaveModelResponse> saveModelResponseCall = apiService.addToSaved(user_id,store.getStore_id(),"Store");
        saveModelResponseCall.enqueue(new Callback<SaveModelResponse>() {
            @Override
            public void onResponse(Call<SaveModelResponse> call, Response<SaveModelResponse> response) {

                if(!response.body().getUser_data().get(0).getSave_case().equals("saved")) {
                    store.setIf_saved(0);
                    DBHandler.SaveStore(store,store.getIf_saved(),mContext);
                    likeButton.setLiked(false);
                }
            }
            @Override
            public void onFailure(Call<SaveModelResponse> call, Throwable t) {
                likeButton.setLiked(true);
                Log.i("saved_error",t.getMessage());
            }
        });
    }

}
