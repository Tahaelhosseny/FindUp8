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

import khaled.example.com.findup.Helper.Location.LocationUtility;
import khaled.example.com.findup.Helper.Utility;
import khaled.example.com.findup.R;
import khaled.example.com.findup.models.Comment;
import khaled.example.com.findup.models.CurrentLocation;
import khaled.example.com.findup.models.Store;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {


    private Context mContext;
    private List<Comment> commentList;
    private CurrentLocation currentLocation = new CurrentLocation();

    public CommentsAdapter(Context mContext, List<Comment> commentList) {
        this.mContext = mContext;
        this.commentList = commentList;
    }

    public void setComments(List<Comment> comment) {
        this.commentList = comment;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.username.setText(comment.getAccount_name());
        holder.date.setText(Utility.getDate(comment.getDate()));
        holder.comment_txt.setText(comment.getComment());
        if (!comment.getAccount_image().isEmpty()) {
            Transformation transformation = new RoundedTransformationBuilder()
                    .cornerRadiusDp(20)
                    .oval(false)
                    .build();

            Picasso.with(mContext).load(comment.getAccount_image()).transform(transformation).placeholder(R.drawable.com_facebook_profile_picture_blank_square).into(holder.profile_pic);
        }
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView username;
        TextView date;
        TextView comment_txt;
        ImageView profile_pic;

        public ViewHolder(View view) {
            super(view);
            username = view.findViewById(R.id.comment_username);
            date = view.findViewById(R.id.comment_date);
            comment_txt = view.findViewById(R.id.comment_txt);
            profile_pic = view.findViewById(R.id.user_photo);
        }
    }


}
