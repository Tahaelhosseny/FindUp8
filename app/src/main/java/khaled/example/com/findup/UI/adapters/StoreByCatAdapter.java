package khaled.example.com.findup.UI.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Transformation;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;
import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.activities.StoreDetailsActivity;
import khaled.example.com.findup.models.Store;

public class StoreByCatAdapter extends RecyclerView.Adapter<StoreByCatAdapter.ViewHolder> {
    private List<Store> stores;
    private Context context;

    public StoreByCatAdapter(Context context, List<Store> stores) {
        this.context = context;
        this.stores = stores;
    }

    public void setSavedList(List<Store> stores) {
        this.stores = stores;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_store_logo;
        TextView txt_name;
        TextView txt_desc;
        TextView txt_ratee;
        LinearLayout linearLayout;

        public ViewHolder(View view) {
            super(view);
            img_store_logo = view.findViewById(R.id.img_store_img);
            txt_name = view.findViewById(R.id.txt_store_name);
            txt_ratee = view.findViewById(R.id.txt_store_rate);
            txt_desc = view.findViewById(R.id.txt_store_desc);
            linearLayout = view.findViewById(R.id.main_cat_store_constraint);
        }

}

    @NonNull
    @Override
    public StoreByCatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_cat_item_search, parent, false);
        return new StoreByCatAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreByCatAdapter.ViewHolder holder, int position) {
        Store store = stores.get(position);
        holder.txt_name.setText(store.getStore_name());
        holder.txt_desc.setText(store.getStore_desc());
        if(store.getStore_rating() == ""){
            holder.txt_ratee.setText("0.0");
        }else{
            holder.txt_ratee.setText(store.getStore_rating());
        }
        if (!store.getStore_logo().isEmpty()) {
            Transformation transformation = new RoundedTransformationBuilder()
                    .cornerRadiusDp(80)
                    .oval(false)
                    .build();
            Picasso.with(holder.img_store_logo.getContext()).load(store.getStore_banner()).transform(transformation).into(holder.img_store_logo);
        }else {
            Transformation transformation = new RoundedTransformationBuilder()
                    .cornerRadiusDp(80)
                    .oval(false)
                    .build();
            Picasso.with(holder.img_store_logo.getContext()).load(store.getStore_banner()).transform(transformation).into(holder.img_store_logo);
        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, StoreDetailsActivity.class).putExtra("store_id", store.getStore_id()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return stores.size();
    }
}