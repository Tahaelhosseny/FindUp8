package khaled.example.com.findup.UI.fragments;

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

import java.util.ArrayList;
import java.util.List;

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.ViewModel.Fragments.NearMeViewModel;
import khaled.example.com.findup.UI.ViewModel.Fragments.ProductsViewModel;
import khaled.example.com.findup.UI.adapters.ProductsAdapter;
import khaled.example.com.findup.databinding.FragmentNearMeBinding;
import khaled.example.com.findup.databinding.FragmentProductsBinding;
import khaled.example.com.findup.models.Product;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {

    public ProductsFragment() {
        // Required empty public constructor
    }

    public static ProductsFragment newInstance() {
        ProductsFragment fragment = new ProductsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    ProductsViewModel productsViewModel;
    FragmentProductsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_products, container, false);

        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        productsViewModel = new ProductsViewModel(view.getContext());
        binding.setProducts(productsViewModel);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Intent i = getActivity().getIntent();
        int store_id = 1;
        if (i.hasExtra("store_id"))
            store_id = i.getIntExtra("store_id",1);


        productsViewModel.bindStoreProducts(binding.productsRecyclerView,store_id);
    }


}