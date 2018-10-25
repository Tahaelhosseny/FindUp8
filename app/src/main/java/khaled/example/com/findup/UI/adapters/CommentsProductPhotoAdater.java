package khaled.example.com.findup.UI.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import khaled.example.com.findup.R;
import khaled.example.com.findup.models.Comment;
import khaled.example.com.findup.models.ProductComment;

public class CommentsProductPhotoAdater extends RecyclerView.Adapter<CommentsProductPhotoAdater.ViewHolder> {

    private List<ProductComment> CommentsList;
    private Context context;

    public CommentsProductPhotoAdater(Context context, List<ProductComment> CommentsList) {
        this.context = context;
        if (CommentsList.size() > 3)
            this.CommentsList = CommentsList.subList(0, 3);
        else
            this.CommentsList = CommentsList;
    }

    @NonNull
    @Override
    public CommentsProductPhotoAdater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comments_photos_item, parent, false);
        return new CommentsProductPhotoAdater.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsProductPhotoAdater.ViewHolder holder, int position) {
        ProductComment comment = CommentsList.get(position);
        if (!comment.getAccount_image().isEmpty()) {
            Transformation transformation = new RoundedTransformationBuilder()
                    .cornerRadiusDp(90)
                    .oval(true)
                    .borderColor(Color.WHITE)
                    .borderWidthDp(10)
                    .build();
            Picasso.with(holder.photoImg.getContext()).load(comment.getAccount_image()).transform(transformation).placeholder(R.drawable.near_by_place_holder).into(holder.photoImg);

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
