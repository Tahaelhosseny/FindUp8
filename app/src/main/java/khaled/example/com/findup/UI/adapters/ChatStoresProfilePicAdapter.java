package khaled.example.com.findup.UI.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.activities.ProductDetailsActivity;
import khaled.example.com.findup.models.Place;
import khaled.example.com.findup.models.Product;

public class ChatStoresProfilePicAdapter extends RecyclerView.Adapter<ChatStoresProfilePicAdapter.ViewHolder> {

    private List<Place> places;
    private Context context;

    private int middle_element_position;

    public ChatStoresProfilePicAdapter(Context context, List<Place> places,int middle_element_position) {
        this.context = context;
        this.places = places;
        this.middle_element_position = middle_element_position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CircularImageView store_img;
        TextView store_name;
        public ViewHolder(View view) {
            super(view);
            store_img = view.findViewById(R.id.store_prof_pic);
            store_name = view.findViewById(R.id.store_name);

        }

        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, ProductDetailsActivity.class));
        }
    }

    @NonNull
    @Override
    public ChatStoresProfilePicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;

        if (0 == viewType)
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_stores_profile_item, parent, false);
        else
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_stores_profile_item_middle, parent, false);

        return new ChatStoresProfilePicAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChatStoresProfilePicAdapter.ViewHolder holder, int position) {

        Place place = places.get(position);

        holder.store_name.setText(place.getPlaceName());
        if (!place.getPlaceImg().isEmpty()) {
            Picasso.with(holder.store_img.getContext()).load(place.getPlaceImg()).placeholder(R.drawable.near_by_place_holder).into(holder.store_img);
        }

    }

    public void setMiddle_element_position(int middle_element_position) {
        this.middle_element_position = middle_element_position;
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == middle_element_position)
            return 100;
        else
            return 0;
    }
}
