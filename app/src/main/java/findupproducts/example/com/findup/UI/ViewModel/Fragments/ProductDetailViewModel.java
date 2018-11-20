package findupproducts.example.com.findup.UI.ViewModel.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.Flowable;
import findupproducts.example.com.findup.Helper.Database.DBHandler;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Product.PComment;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Product.ProductPhotos;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Product.Products;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Store.Stores;
import findupproducts.example.com.findup.Helper.Utility;
import findupproducts.example.com.findup.UI.CustomViews.OverlapDecoration;
import findupproducts.example.com.findup.UI.activities.CommentsActivity;
import findupproducts.example.com.findup.UI.activities.PhotosGalleryActivity;
import findupproducts.example.com.findup.UI.adapters.CommentsPhotosAdapter;
import findupproducts.example.com.findup.UI.adapters.CommentsProductPhotoAdater;
import findupproducts.example.com.findup.UI.adapters.NearMeAdapter;
import findupproducts.example.com.findup.UI.adapters.ProductPhotosAdapter;
import findupproducts.example.com.findup.UI.adapters.RecyclerTouchListener;
import findupproducts.example.com.findup.UI.adapters.StorePhotosAdapter;
import findupproducts.example.com.findup.models.Comment;
import findupproducts.example.com.findup.models.Product;
import findupproducts.example.com.findup.models.ProductComment;
import findupproducts.example.com.findup.models.ProductPhoto;
import findupproducts.example.com.findup.models.Store;
import findupproducts.example.com.findup.models.StorePhoto;

public class ProductDetailViewModel extends Observable {
    private Context mContext;
    private int product_id;
    private Product MProduct;

    public  ProductDetailViewModel(Context mContext , int product_id){
        this.mContext = mContext;
        this.product_id = product_id;
    }

    public void bindProductData(ImageView product_banner, TextView product_name,TextView product_price,TextView productStoreTxt,TextView product_like_count, TextView aboutProduct
                                ,RecyclerView productPhotosRecycler,TextView commentUsersTxt,TextView commentUsersNumTxt
     , ImageView pComments , RecyclerView commentPhoto){
        DBHandler.getProductByID(product_id, mContext, new Products() {
            @Override
            public void onSuccess(Flowable<List<Product>> listFlowable) {

            }

            @Override
            public void getProduct(Flowable<Product> productFlowable) {
                productFlowable.subscribe(v->{
                    ((Activity) mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {



                            bindCommentsPhotos(commentPhoto , commentUsersNumTxt , commentUsersTxt , pComments);
                            bindPhotos(productPhotosRecycler);
                            commentUsersNumTxt.setText(""+v.getProduct_comments_count());
                            product_price.setText(""+v.getProduct_price());
                            productStoreTxt.setText(v.getProduct_name());
                            product_like_count.setText(""+v.getProduct_likes_count());
                            Picasso.with(mContext).load(v.getProduct_banner()).into(product_banner);
                            aboutProduct.setText(v.getProduct_name());
                            product_name.setText(v.getProduct_name());
                            pComments.setOnClickListener(v ->
                                    mContext.startActivity(new Intent(mContext, CommentsActivity.class).putExtra("product_id",product_id)));
                        }
                    });
                    });
            }

            @Override
            public void onFail() {

            }
        });

    }
    public void showProductComments(int id){
        Intent intent = new Intent(mContext , CommentsActivity.class).putExtra("product_id" , id);

    }
    public void bindPhotos(RecyclerView recyclerView) {
        List<ProductPhoto> photos = new ArrayList<>();

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }

            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        });
        recyclerView.smoothScrollToPosition(0);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(mContext
                , recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                mContext.startActivity(new Intent(mContext, PhotosGalleryActivity.class));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        DBHandler.getProductPhotosByProductID(product_id, mContext, new ProductPhotos() {
            @Override
            public void onSuccess(Flowable<List<ProductPhoto>> prListFlowable) {
                prListFlowable.subscribe(val -> {
                    ((Activity) mContext).runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            photos.clear();
                            photos.addAll(val);
                            ProductPhotosAdapter adapter = new ProductPhotosAdapter(mContext, val);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    });
                    //adapter.notifyDataSetChanged();
                });
            }
        });
        }

    public void bindCommentsPhotos(RecyclerView recyclerView , TextView commentUserCount , TextView commentText , ImageView toComments) {
        List<ProductComment> commentList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        CommentsProductPhotoAdater adapter = new CommentsProductPhotoAdater(mContext, commentList);
        recyclerView.setAdapter(adapter);


        DBHandler.getCommentByProductID(product_id, mContext, new PComment() {
                    @Override
                    public void onSuccess(Flowable<List<ProductComment>> commentFlowable) {
                        commentFlowable.subscribe(val -> {
                            ((Activity) mContext).runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    int num = val.size();
                                    if(num == 0){
                                        commentText.setText("No Comments Yet");
                                        commentUserCount.setText("No One Comment on this");
                                        toComments.setVisibility(View.GONE);
                                    }else  if (num > 0 && num < 3){
                                        String stat = "";
                                        for (int i = 0 ; i < num ; i++){
                                            if (num == 1) {
                                                stat = stat + val.get(i).getAccount_name();
                                            }else {
                                                if((i+1) == num){
                                                    stat = stat + val.get(i).getAccount_name();
                                                }else{
                                                    stat = stat + val.get(i).getAccount_name() + " and ";
                                                }
                                            }
                                        }
                                        stat += " commented";
                                        commentText.setText(stat);
                                        commentUserCount.setText("No more Comments");
                                    }else if(num >= 3){
                                        String stat = "";
                                        for (int i = 0 ; i < 3 ; i++){
                                            if((i+1) == 3){
                                                stat = stat + val.get(i).getAccount_name();
                                            }else{
                                                stat = stat + val.get(i).getAccount_name() + " and ";
                                            }
                                        }
                                        commentText.setText(stat);
                                        if ((val.size()-3) == 0){
                                            commentUserCount.setText("And no more commented in this");
                                        }else{
                                            commentUserCount.setText("And "+(val.size() - 3)+" commented in this");
                                        }
                                    }

                                    //---------------------------------------------------------------------------------------------
                                    if (num == 0){
                                        recyclerView.setVisibility(View.INVISIBLE);
                                    }else {
                                        commentList.clear();
                                        commentList.addAll(val);
                                        recyclerView.setAdapter(adapter);
                                    }
                                }
                            });
                            //adapter.notifyDataSetChanged();
                        });
                    }

                    @Override
                    public void onFail() {

                    }
                });

                //recyclerView.setLayoutManager(layoutManager);

                recyclerView.addOnItemTouchListener(new RecyclerTouchListener(mContext
                        , recyclerView, new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        mContext.startActivity(new Intent(mContext, CommentsActivity.class).putExtra("product_id" ,product_id ));
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));

        recyclerView.addItemDecoration(new OverlapDecoration());
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));

    }

}
