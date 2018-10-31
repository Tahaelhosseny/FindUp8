package findupproducts.example.com.findup.UI.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;

import java.util.List;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.models.Category;
import findupproducts.example.com.findup.models.Product;
import findupproducts.example.com.findup.models.SaveModel;
import findupproducts.example.com.findup.models.UserSavedItem;

/**
 * Created by khaled on 8/1/18.
 */

public class UserSavedAdapter extends RecyclerView.Adapter<UserSavedAdapter.ViewHolder>{
    private List<UserSavedItem> userSavedItems;
    private Context context;

    public UserSavedAdapter(Context context, List<UserSavedItem> userSavedItems) {
        this.context = context;
        this.userSavedItems = userSavedItems;
    }

    public void setSavedList(List<UserSavedItem> userSavedItems) {
        this.userSavedItems = userSavedItems;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView userSavedImage;
        TextView userSavedName;
        TextView userSavedDesc;

        public ViewHolder(View view) {
            super(view);
            userSavedImage = view.findViewById(R.id.userSavedItemImg);
            userSavedDesc = view.findViewById(R.id.userSavedItemDesc);
            userSavedName = view.findViewById(R.id.userSavedItemName);
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
        holder.userSavedName.setText(userSavedItems.get(position).getItemName());
        holder.userSavedDesc.setText(userSavedItems.get(position).getItemDesc());
        if (!userSavedItems.get(position).getItemImg().isEmpty()) {
//            Picasso.with(context).load(userSavedItems.get(position).getItemImg()).placeholder(R.drawable.placeholder).into(holder.userSavedImage);
                Transformation transformation = new RoundedTransformationBuilder()
                        .cornerRadiusDp(80)
                        .oval(false)
                        .build();
                Picasso.with(holder.userSavedImage.getContext()).load(userSavedItems.get(position).getItemImg()).transform(transformation).placeholder(R.drawable.near_by_place_holder).into(holder.userSavedImage);
        }
/*
        holder.userSavedName.setText(saveModel.getSaved_name());
        holder.userSavedDesc.setText(saveModel.getSaved_description());

        if (!saveModel.getSaved_photo().isEmpty()) {
            Transformation transformation = new RoundedTransformationBuilder()
                    .cornerRadiusDp(80)
                    .oval(false)
                    .build();
            Picasso.with(holder.userSavedImage.getContext()).load(saveModel.getSaved_photo()).transform(transformation).placeholder(R.drawable.near_by_place_holder).into(holder.userSavedImage);
//            Picasso.with(holder.userSavedImage.getContext()).load(saveModel.getSaved_photo()).into(holder.userSavedImage);

        }*/
    }
    @Override
    public int getItemCount() {
        return userSavedItems.size();
    }
}
