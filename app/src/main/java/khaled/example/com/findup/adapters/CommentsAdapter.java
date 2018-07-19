package khaled.example.com.findup.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import khaled.example.com.findup.Helper.Utility;
import khaled.example.com.findup.R;
import khaled.example.com.findup.models.Comment;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {


    private Context mContext;
    private List<Comment> commentList;

    public CommentsAdapter(Context mContext, List<Comment> commentList) {
        this.mContext = mContext;
        this.commentList = commentList;
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
        holder.username.setText(comment.getUser_name());
        holder.date.setText(Utility.getDate(comment.getDate()));
        holder.comment_txt.setText(comment.getComment());
        if (!comment.getUser_profile_pic().isEmpty())
            Picasso.with(mContext).load(comment.getUser_profile_pic()).placeholder(R.drawable.com_facebook_profile_picture_blank_square).into(holder.profile_pic);
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
            comment_txt = view.findViewById(R.id.comment_date);
            profile_pic = view.findViewById(R.id.comment_photo);
        }
    }


}
