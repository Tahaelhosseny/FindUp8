package khaled.example.com.findup.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;

import khaled.example.com.findup.Helper.UI_Utility;
import khaled.example.com.findup.R;
import khaled.example.com.findup.fragments.MainFragment;
import khaled.example.com.findup.fragments.MapFragment;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    float actionBarSize;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar =  findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);

        actionBarSize =((CoordinatorLayout.LayoutParams) (findViewById(R.id.main_toolbar_container)).getLayoutParams()).topMargin;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        bottomNavigationView = findViewById(R.id.navigation_bottom);

        bottomNavigationView.setOnNavigationItemSelectedListener(navListner);


        transaction.replace(R.id.main_toolbar_container, new MainFragment()).commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = new MainFragment();
                    UI_Utility.BottomNavigationMenu_icons_change(bottomNavigationView.getMenu(),item);
                    switch (item.getItemId()) {
                        case R.id.home:
                            ToolbarSwitch(true);
                            selectedFragment = new MainFragment();
                            break;
                        case R.id.map:
                            ToolbarSwitch(false);
                            selectedFragment = new MapFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_toolbar_container, selectedFragment).commit();
                    return true;
                }
            };

    @NonNull
    private void ToolbarSwitch(Boolean sw) {
        if (!sw) {
            toolbar.setVisibility(View.GONE);
            ((CoordinatorLayout.LayoutParams) (findViewById(R.id.main_toolbar_container)).getLayoutParams()).topMargin = 0;
        }else {
            TypedValue tv = new TypedValue();
            toolbar.setVisibility(View.VISIBLE);
            ((CoordinatorLayout.LayoutParams) (findViewById(R.id.main_toolbar_container)).getLayoutParams()).topMargin = (int) actionBarSize;
        }
    }


}
