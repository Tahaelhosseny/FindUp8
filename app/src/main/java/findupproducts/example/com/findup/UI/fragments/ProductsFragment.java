package findupproducts.example.com.findup.UI.fragments;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.ViewModel.Fragments.ProductsViewModel;
import findupproducts.example.com.findup.databinding.FragmentProductsBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {

    ProductsViewModel productsViewModel;
    FragmentProductsBinding binding;

    public ProductsFragment() {
        // Required empty public constructor
    }

    public static ProductsFragment newInstance() {
        ProductsFragment fragment = new ProductsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

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
            store_id = i.getIntExtra("store_id", 1);


        productsViewModel.bindStoreProducts(binding.productsRecyclerView, store_id);
    }


}