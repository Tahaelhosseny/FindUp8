package findupproducts.example.com.findup.UI.ViewModel.Fragments;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import findupproducts.example.com.findup.Helper.Database.DBHandler;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Product.Products;
import findupproducts.example.com.findup.UI.adapters.ProductsAdapter;
import findupproducts.example.com.findup.models.Product;

public class ProductsViewModel extends java.util.Observable {
    private Context mContext;
    private List<Product> productList;

    public ProductsViewModel(Context mContext) {
        this.mContext = mContext;
    }


    public void bindStoreProducts(RecyclerView recyclerView, int store_id) {
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ProductsAdapter adapter = new ProductsAdapter(mContext, new ArrayList<>());
        LoadProductsFromDatabase(adapter, store_id);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    private void LoadProductsFromDatabase(ProductsAdapter adapter, int store_id) {
        DBHandler.getProductByStoreID(store_id, mContext, new Products() {
            @Override
            public void onSuccess(Flowable<List<Product>> listFlowable) {
                listFlowable.subscribe(val -> {
                    ((Activity) mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.setProducts(val);
                            adapter.notifyDataSetChanged();
                        }
                    });
                });
            }

            @Override
            public void getProduct(Flowable<Product> productFlowable) {

            }

            @Override
            public void onFail() {

            }
        });
    }
}
