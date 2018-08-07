package khaled.example.com.findup.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import khaled.example.com.findup.Helper.UI_Utility;
import khaled.example.com.findup.R;
import khaled.example.com.findup.fragments.BottomBarFragment;
import khaled.example.com.findup.fragments.CategoryFragment;
import khaled.example.com.findup.fragments.MainFragment;
import khaled.example.com.findup.fragments.MapFragment;
import khaled.example.com.findup.fragments.ProfileFragment;
import khaled.example.com.findup.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    float actionBarSize;
    //BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar =  findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        //bottomNavigationView = findViewById(R.id.navigation_bottom);

        //bottomNavigationView.setOnNavigationItemSelectedListener(navListner);

        /*final BottomNav bottomNav = findViewById(R.id.navigation_bottom);
        bottomNav.addItemNav(new ItemNav(this, R.drawable.home_unselected_0_5x, R.drawable.home_sel_1_5x));
        bottomNav.addItemNav(new ItemNav(this, R.drawable.map_1_5x, R.drawable.map_sel_1_5x));
        bottomNav.addItemNav(new ItemNav(this, R.drawable.search_1_5x, R.drawable.search_sel_0_5x));
        bottomNav.addItemNav(new ItemNav(this, R.drawable.category_1_5x, R.drawable.category_sel_1_5x));
        bottomNav.addItemNav(new ItemNav(this, R.drawable.__1_5x, R.drawable.__1_5x));
        bottomNav.build();

        BottomNav.OnTabSelectedListener listener = new BottomNav.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                Fragment selectedFragment = new MainFragment();
                //UI_Utility.BottomNavigationMenu_icons_change(bottomNavigationView.getMenu(),item);
                switch (position) {
                    case 0:
                        ToolbarSwitch(true);
                        selectedFragment = new MainFragment();
                        break;
                    case 1:
                        ToolbarSwitch(false);
                        selectedFragment = new MapFragment();
                        break;
                    case 2:
                        ToolbarSwitch(false);
                        selectedFragment = new SearchFragment();
                        break;
                    case 3:
                        ToolbarSwitch(false);
                        selectedFragment = new CategoryFragment();
                        break;
                    case 4:
                        ToolbarSwitch(false);
                        selectedFragment = new ProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_toolbar_container, selectedFragment).commit();
            }

            @Override
            public void onTabLongSelected(int position) {
            }
        };
        bottomNav.setTabSelectedListener(listener);*/



        transaction.replace(R.id.main_toolbar_container, new MainFragment()).commit();

        BottomBarFragment bottomBarFragment =new BottomBarFragment();
        Bundle bundle = new Bundle();
        getSupportFragmentManager().beginTransaction().replace(R.id.navigation_bottom_container, bottomBarFragment).commit();
    }







}
