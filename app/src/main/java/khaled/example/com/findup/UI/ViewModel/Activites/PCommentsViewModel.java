package khaled.example.com.findup.UI.ViewModel.Activites;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.Flowable;
import khaled.example.com.findup.Helper.Database.DBHandler;
import khaled.example.com.findup.Helper.Database.Interfaces.Product.PComment;
import khaled.example.com.findup.Helper.Utility;

import khaled.example.com.findup.UI.adapters.PCommentAdapter;
import khaled.example.com.findup.models.PCommentModel;

class PCommentsViewModel extends Observable {
    private Context mContext;
    private List<PCommentModel> commentList = new ArrayList<>();

    public PCommentsViewModel(Context mContext) {
        this.mContext = mContext;
    }

    public void InitRecyclerView(RecyclerView recyclerView , int store_id){
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        PCommentAdapter adapter = new PCommentAdapter(mContext, commentList);
        //LoadCommentsFromDatabase(adapter , store_id);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.smoothScrollToPosition(0);
    }


//  public void LoadCommentsFromDatabase(PCommentAdapter adapter , int product_id){
//        Utility.UpdateCurrentLocation((Activity) mContext,mContext);
//        DBHandler.getProductCommentID(product_id, mContext, new PComment() {
//            @Override
//            public void onSuccess(Flowable<List<PCommentModel>> commentFlowable) {
//                commentFlowable.subscribe(
//                        val-> {
//                            ((Activity) mContext).runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    adapter.setComments(val);
//                                    adapter.notifyDataSetChanged();
//                                }
//                            });
//                        },
//                        err-> Log.i("database err","return data database error : "+err.getMessage())
//                );
//            }
//
//            @Override
//            public void onFail() {
//
//            }
//        });

//  }
}