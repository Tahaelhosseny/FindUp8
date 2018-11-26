package findupproducts.example.com.findup.UI.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.f2prateek.rx.preferences2.Preference;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import findupproducts.example.com.findup.Helper.Location.LocationUtility;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.Helper.Utility;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.activities.FilterActivity;
import findupproducts.example.com.findup.models.CurrentLocation;
import findupproducts.example.com.findup.models.Event;
import findupproducts.example.com.findup.models.Store;

import static findupproducts.example.com.findup.UI.activities.MainActivity.filterData;
import static findupproducts.example.com.findup.UI.activities.MainActivity.filteredMapDataEvent;
import static findupproducts.example.com.findup.UI.activities.MainActivity.filteredMapDataStore;

public class MapFragment extends Fragment implements OnMapReadyCallback {
    GoogleMap myMap;
    MapView mMapView;
    String TAG = getTag();
    HorizontalScrollView filter;

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            rootView = inflater.inflate(R.layout.fragment_find, container, false);
            FrameLayout frameLayout = getActivity().findViewById(R.id.navigation_bottom_container);
            frameLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            MapsInitializer.initialize(this.getActivity());
            mMapView = (MapView) rootView.findViewById(R.id.map);
            mMapView.onCreate(savedInstanceState);
            mMapView.getMapAsync(this);
            getChildFragmentManager().beginTransaction().replace(R.id.nearMeContainer, new FilteredDataFragment()).commit();
        } catch (InflateException e) {
            Log.e(TAG, "Inflate exception");
        }
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMapView != null)
            mMapView.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        {
            CurrentLocation currentLocation = new CurrentLocation();
            Utility.UpdateCurrentLocation((Activity) getActivity(), getActivity());

            SharedPrefManger sharedPrefManger = new SharedPrefManger(getActivity());
            Preference<Float> Latitude = sharedPrefManger.getLatitude();
            Latitude.asObservable().subscribe(val -> LocationUtility.LatitudeToCurrentLocationModel(val, currentLocation));
            Preference<Float> Longitude = sharedPrefManger.getLongitude();
            Longitude.asObservable().subscribe(val -> LocationUtility.LongitudeToCurrentLocationModel(val, currentLocation));

            LatLng sydney = new LatLng(currentLocation.getLocation().latitude, currentLocation.getLocation().longitude);
            googleMap.addMarker(new MarkerOptions().position(sydney).icon(
                    BitmapDescriptorFactory.fromResource(R.drawable.current_location_marker)
            ).title("Your Location"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(sydney.latitude + ((sydney.latitude * 14) / 100000), sydney.longitude), 14));
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            for (Store store : filteredMapDataStore){
                LatLng storeLoc = new LatLng(store.getStore_latitude(),store.getStore_longitude());
                googleMap.addMarker(new MarkerOptions().position(storeLoc).icon(
                        BitmapDescriptorFactory.fromResource(R.drawable.current_location_marker)
                ).title(store.getStore_name()));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(storeLoc.latitude + ((storeLoc.latitude * 14) / 100000), storeLoc.longitude), 14));
            }

            for (Event event : filteredMapDataEvent){
                double latitude = Double.valueOf(event.getEvent_latitude());
                double longitude = Double.valueOf(event.getEvent_longitude().trim());
                googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude,longitude)).icon(
                        BitmapDescriptorFactory.fromResource(R.drawable.current_location_marker)
                ).title(event.getEvent_name()));
            }
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageButton view_fillter = getActivity().findViewById(R.id.search_filter);
        filter = getActivity().findViewById(R.id.filter);
        CardView distace = filter.findViewById(R.id.distance_card);
        CardView price = filter.findViewById(R.id.price_card);
        CardView saved = filter.findViewById(R.id.saved_card);
        CardView like = filter.findViewById(R.id.liked_card);
        if(filterData.getFilter_distance() != null ){
            distace.setVisibility(View.VISIBLE);
        }
        if(filterData.getFilter_price() != null ){
            price.setVisibility(View.VISIBLE);
        }
        if(filterData.getFilter_by() != null){
            if(filterData.getFilter_by() == "liked"){
                like.setVisibility(View.VISIBLE);
            }
            if(filterData.getFilter_by() == "saved"){
                saved.setVisibility(View.VISIBLE);
            }
        }
        view_fillter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterData.setSearch_from("FromMap");
                EditText search = getActivity().findViewById(R.id.search);
                filterData.setSearch_text(search.getText().toString());
                startActivity(new Intent(getActivity(), FilterActivity.class));
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}