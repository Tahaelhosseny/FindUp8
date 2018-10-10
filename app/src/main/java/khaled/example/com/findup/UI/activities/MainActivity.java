package khaled.example.com.findup.UI.activities;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;
import com.patloew.rxlocation.RxLocation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import khaled.example.com.findup.Helper.Location.LocationUtility;
import khaled.example.com.findup.Helper.Location.LocationView;
import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.Helper.UI_Utility;
import khaled.example.com.findup.Helper.Utility;
import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.fragments.BottomBarFragment;
import khaled.example.com.findup.UI.fragments.MainFragment;
import khaled.example.com.findup.UI.fragments.MapFragment;
import khaled.example.com.findup.models.CurrentLocation;

public class MainActivity extends AppCompatActivity implements LocationView{
    Context context;
    Toolbar toolbar;
    private RxLocation rxLocation;
    LocationUtility locationUtility;
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


        rxLocation = new RxLocation(this);
        rxLocation.setDefaultTimeout(15, TimeUnit.SECONDS);
        locationUtility = new LocationUtility(rxLocation);

        transaction.replace(R.id.main_toolbar_container, new MainFragment(), new MainFragment().getClass().getName()).commit();

        BottomBarFragment bottomBarFragment =new BottomBarFragment();
        Bundle bundle = new Bundle();
        getSupportFragmentManager().beginTransaction().replace(R.id.navigation_bottom_container, bottomBarFragment).commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0)
            super.onBackPressed();
        else {
            getSupportFragmentManager().popBackStackImmediate();
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_toolbar_container);
            UI_Utility.BottomNavigationMenu_icons_change(BottomBarFragment.menu, Utility.fragmentTagsList().indexOf(fragment.getClass().getName()));
            Log.i("CurrentFragment",fragment.getClass().getName());
            BottomBarFragment.adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        locationUtility.attachView(this);
    }

    @Override
    public void onLocationUpdate(Location location) {
        SharedPrefManger.setCurrentLocation(new CurrentLocation(location.getLatitude(),location.getLongitude()));
    }


    @Override
    public void onLocationSettingsUnsuccessful() {

    }

    @Override
    public void onAddressUpdate(Address address) {

    }

}
