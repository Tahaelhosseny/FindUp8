package findupproducts.example.com.findup.UI.fragments;


import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.CustomViews.OverlapDecoration;
import findupproducts.example.com.findup.UI.ViewModel.Fragments.ProductDetailViewModel;
import findupproducts.example.com.findup.UI.ViewModel.Fragments.StoreDetailsViewModel;
import findupproducts.example.com.findup.UI.activities.CommentsActivity;
import findupproducts.example.com.findup.UI.adapters.CommentsPhotosAdapter;
import findupproducts.example.com.findup.UI.adapters.RecyclerTouchListener;
import findupproducts.example.com.findup.databinding.FragmentProductDetailsBinding;
import findupproducts.example.com.findup.models.Comment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailsFragment extends Fragment {


    public ProductDetailsFragment() {
        // Required empty public constructor
    }


    FragmentProductDetailsBinding binding;
    ProductDetailViewModel productDetailViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_details, container, false);
        View view = binding.getRoot();
        Intent i = ((Activity) view.getContext()).getIntent();
        int store_id = 1;
        if (i.hasExtra("prod_id"))
            store_id = i.getIntExtra("prod_id", 1);
        productDetailViewModel = new ProductDetailViewModel(view.getContext(), store_id);
        Toast.makeText(getActivity(), ""+store_id, Toast.LENGTH_SHORT).show();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        productDetailViewModel.bindProductData(binding.productBanner,binding.productName,binding.productPrice,binding.productStoreTxt,binding.productLikeCount,binding.aboutProduct,
                binding.productPhotosRecycler,binding.commentUsersTxt,binding.commentUsersNumTxt,
            binding.showProductComment , binding.commentUsersImg);

    }
}
