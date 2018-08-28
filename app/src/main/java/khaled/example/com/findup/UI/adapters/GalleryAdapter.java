package khaled.example.com.findup.UI.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsibbold.zoomage.ZoomageView;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import khaled.example.com.findup.Helper.Utility;
import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.activities.StoreDetailsActivity;
import khaled.example.com.findup.models.Comment;
import khaled.example.com.findup.models.Place;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private Context mContext;
    private List<String> url;
    private Place place;


    public GalleryAdapter(Context mContext, List<String> url, Place place) {
        this.mContext = mContext;
        this.url = url;
        this.place = place;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_gallery_item, parent, false);
        return new GalleryAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String url = this.url.get(position);
        if (!url.isEmpty()) {
            Transformation transformation = new RoundedTransformationBuilder()
                    .cornerRadiusDp(10)
                    .oval(false)
                    .build();

            Picasso.with(mContext).load(url).transform(transformation).placeholder(R.drawable.com_facebook_profile_picture_blank_square).into(holder.photo);
        }
    }

    @Override
    public int getItemCount() {
        return url.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ZoomageView photo;

        public ViewHolder(View view) {
            super(view);
            photo = view.findViewById(R.id.photo_gallery_item);
        }

    }
}
