package khaled.example.com.findup.UI.ViewModel.Fragments;

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
import khaled.example.com.findup.Helper.Database.DBHandler;
import khaled.example.com.findup.Helper.Database.Interfaces.Product.PComment;
import khaled.example.com.findup.Helper.Database.Interfaces.Product.ProductPhotos;
import khaled.example.com.findup.Helper.Database.Interfaces.Product.Products;
import khaled.example.com.findup.Helper.Database.Interfaces.Store.Stores;
import khaled.example.com.findup.Helper.Utility;
import khaled.example.com.findup.UI.CustomViews.OverlapDecoration;
import khaled.example.com.findup.UI.activities.CommentsActivity;
import khaled.example.com.findup.UI.activities.PhotosGalleryActivity;
import khaled.example.com.findup.UI.adapters.CommentsPhotosAdapter;
import khaled.example.com.findup.UI.adapters.CommentsProductPhotoAdater;
import khaled.example.com.findup.UI.adapters.NearMeAdapter;
import khaled.example.com.findup.UI.adapters.ProductPhotosAdapter;
import khaled.example.com.findup.UI.adapters.RecyclerTouchListener;
import khaled.example.com.findup.UI.adapters.StorePhotosAdapter;
import khaled.example.com.findup.models.Comment;
import khaled.example.com.findup.models.Product;
import khaled.example.com.findup.models.ProductComment;
import khaled.example.com.findup.models.ProductPhoto;
import khaled.example.com.findup.models.Store;
import khaled.example.com.findup.models.StorePhoto;

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
                            //TODO Start binding data from variable @v
                            bindCommentsPhotos(commentPhoto);
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

    public void bindCommentsPhotos(RecyclerView recyclerView) {
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
                                    commentList.clear();
                                    commentList.addAll(val);
                                    recyclerView.setAdapter(adapter);
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
