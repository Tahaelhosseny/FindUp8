package findupproducts.example.com.findup.UI.adapters;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import findupproducts.example.com.findup.CONST;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.activities.PhotosGalleryActivity;
import findupproducts.example.com.findup.models.StorePhoto;

/**
 * Created by khaled on 7/11/18.
 */

public class StorePhotosAdapter extends RecyclerView.Adapter<StorePhotosAdapter.ViewHolder> {
    private List<StorePhoto> photos;
    private Context context;

    public StorePhotosAdapter(Context context, List<StorePhoto> photos) {
        this.context = context;
        if (photos.size() > 4)
            this.photos = photos.subList(0, 4);
        else
            this.photos = photos;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
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
        String photo = CONST.API_FILE_DOMAIN + "" + CONST.IMAGES_PATH + "" + photos.get(position).getPhoto_name();
        /*if (!TextUtils.isEmpty(photo)){
            Picasso.with(context)
                    .load(photo)
                    .into(holder.photo);
        }*/

        if (!photo.isEmpty()) {
            Transformation transformation = new RoundedTransformationBuilder()
                    .cornerRadiusDp(20)
                    .oval(false)
                    .build();

            Picasso.with(context).load(photo).transform(transformation).placeholder(R.drawable.com_facebook_profile_picture_blank_square).into(holder.photo);
        }
        Log.i("photo_url", photo);

        holder.photo_container.setMinimumWidth(getScreenWidth() / 4);


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
}
