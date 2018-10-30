package khaled.example.com.findup.UI.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.adapters.TabPagerAdapter;
import khaled.example.com.findup.UI.fragments.FilterFragment;
import khaled.example.com.findup.models.CurrentLocation;

/**
 * Created by khaled on 8/1/18.
 */

public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        TabLayout tabLayout =  findViewById(R.id.welcome_slide_tab);
        tabLayout.addTab(tabLayout.newTab().setText("Sort"));
        tabLayout.addTab(tabLayout.newTab().setText("Filter"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager =  findViewById(R.id.welcome_view_pager);
        final TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tabb) {
                viewPager.setCurrentItem(tabb.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        CurrentLocation currentLocation = SharedPrefManger.getCurrentLocation();
        Log.e("Long" , String.valueOf(currentLocation.getLocationModel().getLongitude()));
        Log.e("Lat" , String.valueOf(currentLocation.getLocationModel().getLatitude()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
