package khaled.example.com.findup.UI.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import khaled.example.com.findup.R;
import khaled.example.com.findup.models.Product;

/**
 * Created by khaled on 8/1/18.
 */

public class UserSavedAdapter extends RecyclerView.Adapter<UserSavedAdapter.ViewHolder>{
    private List<Product> userSavedItems;
    private Context context;

    public UserSavedAdapter(Context context, List<Product> userSavedItems) {
        this.context = context;
        this.userSavedItems = userSavedItems;
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
        Product product = userSavedItems.get(position);
        /*UserSavedItem userSavedItem = userSavedItems.get(position);
        holder.userSavedName.setText(userSavedItem.getItemName());
        holder.userSavedDesc.setText(userSavedItem.getItemDesc());
        if (!userSavedItem.getItemImg().isEmpty())
            Picasso.with(context).load(userSavedItem.getItemImg()).placeholder(R.drawable.placeholder).into(holder.userSavedImage);*/

        holder.userSavedName.setText(product.getProduct_name());
        holder.userSavedDesc.setText(product.getProduct_desc());

        if (!product.getProduct_banner().isEmpty()) {
            Transformation transformation = new RoundedTransformationBuilder()
                    .cornerRadiusDp(80)
                    .oval(false)
                    .build();

            Picasso.with(holder.userSavedImage.getContext()).load(product.getProduct_banner()).transform(transformation).placeholder(R.drawable.near_by_place_holder).into(holder.userSavedImage);


        }
    }
    @Override
    public int getItemCount() {
        return userSavedItems.size();
    }
}
