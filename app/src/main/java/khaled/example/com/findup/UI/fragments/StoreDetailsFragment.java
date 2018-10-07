package khaled.example.com.findup.UI.fragments;


import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;
import java.util.List;

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.ViewModel.Fragments.NearMeViewModel;
import khaled.example.com.findup.UI.ViewModel.Fragments.StoreDetailsViewModel;
import khaled.example.com.findup.databinding.FragmentNearMeBinding;
import khaled.example.com.findup.databinding.FragmentStoreDetailsBinding;
import khaled.example.com.findup.models.TabEntity;
import khaled.example.com.findup.UI.fragments.ProductsFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreDetailsFragment extends Fragment  {


    public StoreDetailsFragment() {
        // Required empty public constructor
    }

    StoreDetailsViewModel storeDetailsViewModel;
    FragmentStoreDetailsBinding binding;
    int store_id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store_details, container, false);
        View view = binding.getRoot();
        Intent i = ((Activity)view.getContext()).getIntent();
        store_id=0;
        if (i.hasExtra("store_id"))
            store_id = i.getIntExtra("store_id",0);
        storeDetailsViewModel = new StoreDetailsViewModel(view.getContext(),store_id);
        binding.setNearMe(storeDetailsViewModel);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        storeDetailsViewModel.AssignDataToFields(binding.storeImg,binding.storeName,binding.storeDis,binding.storeBriefTxt,binding.storeRating);
        storeDetailsViewModel.InitTabs(binding.storeTabs);
    }

}
