package khaled.example.com.findup.UI.ViewModel.Fragments;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.f2prateek.rx.preferences2.Preference;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.Flowable;
import khaled.example.com.findup.Helper.Database.DBHandler;

import khaled.example.com.findup.Helper.Database.Interfaces.Comment.Comments;
import khaled.example.com.findup.Helper.Utility;
import khaled.example.com.findup.UI.adapters.CommentsAdapter;
import khaled.example.com.findup.models.Comment;

public class ProductCommentsViewModel extends Observable {
    private Context mContext;
    private List<Comment> commentList = new ArrayList<>();

    public ProductCommentsViewModel(Context mContext) {
        this.mContext = mContext;
    }

    public void InitRecyclerView(RecyclerView recyclerView , int store_id){
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        CommentsAdapter adapter = new CommentsAdapter(mContext, commentList);
        LoadCommentsFromDatabase(adapter , store_id);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.smoothScrollToPosition(0);
    }


    public void LoadCommentsFromDatabase(CommentsAdapter adapter , int store_id){
        Utility.UpdateCurrentLocation((Activity) mContext,mContext);
        DBHandler.getCommentByStoreID(store_id, mContext, new khaled.example.com.findup.Helper.Database.Interfaces.Comment.Comment() {
            @Override
            public void onSuccess(Flowable<List<Comment>> commentFlowable) {
                commentFlowable.subscribe(
                        val-> {
                            ((Activity) mContext).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.setComments(val);
                                    adapter.notifyDataSetChanged();
                                }
                            });
                        },
                        err-> Log.i("database err","return data database error : "+err.getMessage())
                );
//                Toast.makeText(mContext, "Loaded", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFail() {
                Toast.makeText(mContext, "Failed to Load", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
