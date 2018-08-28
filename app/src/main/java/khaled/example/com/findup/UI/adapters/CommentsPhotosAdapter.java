package khaled.example.com.findup.UI.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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

import khaled.example.com.findup.Helper.UI_Utility;
import khaled.example.com.findup.R;
import khaled.example.com.findup.models.Category;
import khaled.example.com.findup.models.Comment;

/**
 * Created by khaled on 7/4/18.
 */

public class CommentsPhotosAdapter extends RecyclerView.Adapter<CommentsPhotosAdapter.ViewHolder>{

    private List<Comment> CommentsList;
    private Context context;

    public CommentsPhotosAdapter(Context context, List<Comment> CommentsList) {
        this.context = context;
        this.CommentsList = CommentsList.subList(0,3);
    }

    @NonNull
    @Override
    public CommentsPhotosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comments_photos_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = CommentsList.get(position);
        if (!comment.getUser_profile_pic().isEmpty()) {
            Transformation transformation = new RoundedTransformationBuilder()
                    .cornerRadiusDp(90)
                    .oval(false)
                    .borderColor(Color.WHITE)
                    .borderWidthDp(10)
                    .build();

            Picasso.with(holder.photoImg.getContext()).load(comment.getUser_profile_pic()).transform(transformation).placeholder(R.drawable.near_by_place_holder).into(holder.photoImg);

        }
    }
    @Override
    public int getItemCount() {
        return CommentsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView photoImg;

        public ViewHolder(View view) {
            super(view);

            photoImg = view.findViewById(R.id.CommentPhotoImg);
        }

        @Override
        public void onClick(View v) {
        }
    }

}
