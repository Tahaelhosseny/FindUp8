package khaled.example.com.findup.UI.ViewModel.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import khaled.example.com.findup.Helper.Database.Interfaces.Product.ProductPhotos;
import khaled.example.com.findup.Helper.Database.Interfaces.Product.Products;
import khaled.example.com.findup.Helper.Database.Interfaces.Store.Stores;
import khaled.example.com.findup.Helper.Utility;
import khaled.example.com.findup.UI.CustomViews.OverlapDecoration;
import khaled.example.com.findup.UI.activities.CommentsActivity;
import khaled.example.com.findup.UI.activities.PhotosGalleryActivity;
import khaled.example.com.findup.UI.adapters.CommentsPhotosAdapter;
import khaled.example.com.findup.UI.adapters.ProductPhotosAdapter;
import khaled.example.com.findup.UI.adapters.RecyclerTouchListener;
import khaled.example.com.findup.UI.adapters.StorePhotosAdapter;
import khaled.example.com.findup.models.Comment;
import khaled.example.com.findup.models.Product;
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
                                ,RecyclerView productPhotosRecycler,TextView commentUsersTxt,TextView commentUsersNumTxt){
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
                            product_name.setText(v.getProduct_name());
                        }
                    });
                    });
            }

            @Override
            public void onFail() {

            }
        });

    }
}
