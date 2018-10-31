package findupproducts.example.com.findup.UI.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;

import com.f2prateek.rx.preferences2.Preference;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import findupproducts.example.com.findup.Helper.Location.LocationUtility;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.Helper.Utility;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.activities.FilterActivity;
import findupproducts.example.com.findup.models.CurrentLocation;

import static findupproducts.example.com.findup.UI.activities.MainActivity.filterData;

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

            // Add a marker in Sydney and move the camera
//            for (int i = 0 ; i < filteredMapDataEvent.size() ; i++){
//               LatLng m = new LatLng(Double.parseDouble(filteredMapDataEvent.get(i).getEvent_latitude()) , Double.parseDouble(filteredMapDataEvent.get(i).getEvent_longitude())) ;
//               googleMap.addMarker(new MarkerOptions().position(m).icon(BitmapDescriptorFactory.fromResource(R.drawable.current_location_marker))
//               .title(filteredMapDataEvent.get(i).getEvent_name()));
//                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(m.latitude + ((m.latitude * 14) / 100000), m.longitude), 14));
//                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//            }
            LatLng sydney = new LatLng(currentLocation.getLocation().latitude, currentLocation.getLocation().longitude);
            googleMap.addMarker(new MarkerOptions().position(sydney).icon(
                    BitmapDescriptorFactory.fromResource(R.drawable.current_location_marker)
            ).title("Your Location"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(sydney.latitude + ((sydney.latitude * 14) / 100000), sydney.longitude), 14));
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageButton view_fillter = getActivity().findViewById(R.id.search_filter);
        filter = getActivity().findViewById(R.id.filter);
        view_fillter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterData.setSearch_from("FromMap");
                startActivity(new Intent(getActivity(), FilterActivity.class));
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}