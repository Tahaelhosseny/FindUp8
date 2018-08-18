package khaled.example.com.findup.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import khaled.example.com.findup.R;
import khaled.example.com.findup.activities.ProductDetailsActivity;
import khaled.example.com.findup.adapters.ProductsAdapter;
import khaled.example.com.findup.adapters.RecyclerTouchListener;
import khaled.example.com.findup.models.Product;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {

    public ProductsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<Product> products = new ArrayList<>();
        products.add(new Product(0,36,206,566,"Name", "Description","http://i.imgur.com/2KQVKw0.jpg"));
        products.add(new Product(0,36,206,566,"Name", "Description","https://media-cdn.tripadvisor.com/media/photo-s/0e/40/f2/34/delicious-cofe.jpg"));
        products.add(new Product(0,36,206,566,"Name", "Description","https://cdn2.stylecraze.com/wp-content/uploads/2013/09/1557_5-Black-Tea-Side-Effects-You-Should-Be-Aware-Of.jpg"));
        products.add(new Product(0,36,206,566,"Name", "Description","http://i.imgur.com/2KQVKw0.jpg"));
        products.add(new Product(0,36,206,566,"Name", "Description","https://i2-prod.mirror.co.uk/incoming/article6201545.ece/ALTERNATES/s615/Cup-of-tea.jpg"));
        products.add(new Product(0,36,206,566,"Name", "Description","http://cdn.shopify.com/s/files/1/0653/8213/products/Review_1_1_595e822f-7ad5-42f2-8f04-d16c923614dd_grande.jpg?v=1520387592"));
        bindUI(products);
    }

    private void bindUI(List<Product> products){
        RecyclerView recyclerView = getActivity().findViewById(R.id.productsRecyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        ProductsAdapter adapter = new ProductsAdapter(getActivity(), products);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity()
                , recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                startActivity(new Intent(getActivity(), ProductDetailsActivity.class));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}
