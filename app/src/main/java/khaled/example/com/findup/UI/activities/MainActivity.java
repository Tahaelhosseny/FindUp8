package khaled.example.com.findup.UI.activities;

import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.patloew.rxlocation.RxLocation;

import java.util.concurrent.TimeUnit;

import khaled.example.com.findup.Helper.Location.LocationUtility;
import khaled.example.com.findup.Helper.Location.LocationView;
import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.fragments.BottomBarFragment;
import khaled.example.com.findup.UI.fragments.MainFragment;
import khaled.example.com.findup.models.CurrentLocation;

public class MainActivity extends AppCompatActivity implements LocationView{

    Toolbar toolbar;
    private RxLocation rxLocation;
    LocationUtility locationUtility;
    SharedPrefManger sharedPrefManger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar =  findViewById(R.id.toolbar_top);
        setSupportActionBar(toolbar);
        sharedPrefManger = new SharedPrefManger(this);


        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();


        rxLocation = new RxLocation(this);
        rxLocation.setDefaultTimeout(15, TimeUnit.SECONDS);
        locationUtility = new LocationUtility(rxLocation);

        transaction.replace(R.id.main_toolbar_container, new MainFragment()).commit();

        BottomBarFragment bottomBarFragment =new BottomBarFragment();
        Bundle bundle = new Bundle();
        getSupportFragmentManager().beginTransaction().replace(R.id.navigation_bottom_container, bottomBarFragment).commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0)
            super.onBackPressed();
        else
            getSupportFragmentManager().popBackStack();
    }

    @Override
    protected void onStart() {
        super.onStart();
        locationUtility.attachView(this);
    }

    @Override
    public void onLocationUpdate(Location location) {
        sharedPrefManger.setCurrentLocation(new CurrentLocation(location.getLatitude(),location.getLongitude()));
    }


    @Override
    public void onLocationSettingsUnsuccessful() {

    }

    @Override
    public void onAddressUpdate(Address address) {

    }
}
