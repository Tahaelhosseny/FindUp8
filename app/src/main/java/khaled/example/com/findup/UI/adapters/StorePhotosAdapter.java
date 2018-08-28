package khaled.example.com.findup.UI.adapters;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.activities.PhotosGalleryActivity;

/**
 * Created by khaled on 7/11/18.
 */

public class StorePhotosAdapter extends RecyclerView.Adapter<StorePhotosAdapter.ViewHolder>{
    private List<String> photos;
    private Context context;

    public StorePhotosAdapter(Context context, List<String> photos) {
        this.context = context;
        this.photos = photos.subList(0,4);
    }

    @NonNull
    @Override
    public StorePhotosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_photo_item, parent, false);
        return new StorePhotosAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StorePhotosAdapter.ViewHolder holder, int position) {
        String photo = photos.get(position);
        /*if (TextUtils.isEmpty(photo)){
            Picasso.with(context)
                    .load(photo)
                    .into(holder.photo);
        }*/

        holder.photo_container.setMinimumWidth(getScreenWidth()/4);


        if (position == 3) {
            holder.photo.setVisibility(View.GONE);
            holder.more_txt.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView photo;
        TextView more_txt;
        LinearLayout photo_container;
        public ViewHolder(View view) {
            super(view);

            photo = view.findViewById(R.id.storePhotosImg);
            more_txt = view.findViewById(R.id.storePhotoMoreButton);
            photo_container = view.findViewById(R.id.photo_item_container);
        }

        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, PhotosGalleryActivity.class));
        }
    }
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
}
