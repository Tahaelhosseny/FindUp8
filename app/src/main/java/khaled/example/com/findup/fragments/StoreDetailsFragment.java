package khaled.example.com.findup.fragments;


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
import khaled.example.com.findup.models.TabEntity;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreDetailsFragment extends Fragment {


    public StoreDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store_details, container, false);
    }

    CommonTabLayout tabLayout;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        mTabEntities.add(new TabEntity("Information"));
        mTabEntities.add(new TabEntity("Products"));
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new StoreInfoFragment());
        fragmentList.add(new ProductsFragment());
        tabLayout = getActivity().findViewById(R.id.storeTabs);
        tabLayout.setTabData(mTabEntities,getActivity(),R.id.fl_change,fragmentList);
        tabLayout.setIconHeight(0);
        tabLayout.setIconVisible(false);
        //Typeface mTypeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/sfcompactdisplay_semibold.ttf");
       // mTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/sfcompactdisplay_semibold.ttf");

        tabLayout.getTitleView(0).setTypeface(Typeface.create("sfcompactdisplay_semibold", Typeface.NORMAL));
        tabLayout.getTitleView(1).setTypeface(Typeface.create("sfcompactdisplay_heavy", Typeface.NORMAL));

        //tabLayout.setupWithViewPager(viewPager);
        //tabLayout.setSelectedTabIndicatorColor(getActivity().getResources().getColor(R.color.material_color_deep_orange_accent));
        /*tabLayout.clearFocus();
        tabLayout.setFocusableInTouchMode(false);
        tabLayout.setFocusable(false);
        tabLayout.dispatchWindowFocusChanged(false);*/

    }

    @Override
    public void onPause() {
        super.onPause();
        tabLayout.getTitleView(0).setTypeface(Typeface.create("sfcompactdisplay_semibold", Typeface.NORMAL));
        tabLayout.getTitleView(1).setTypeface(Typeface.create("sfcompactdisplay_heavy", Typeface.NORMAL));

    }
}
