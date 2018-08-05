package khaled.example.com.findup.fragments;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import khaled.example.com.findup.R;
import khaled.example.com.findup.activities.CommentsActivity;
import khaled.example.com.findup.activities.PhotosGalleryActivity;
import khaled.example.com.findup.activities.StoreDetailsActivity;
import khaled.example.com.findup.adapters.RecyclerTouchListener;
import khaled.example.com.findup.adapters.StorePhotosAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreInfoFragment extends Fragment implements OnMapReadyCallback {

    MapView mMapView;

    public StoreInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_store_info, container, false);

        MapsInitializer.initialize(this.getActivity());
        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<String> photos = new ArrayList<>();
        photos.add("");
        photos.add("");
        photos.add("");
        photos.add("");
        photos.add("");
        bindPhotos(photos);

        ImageView show_comments = getActivity().findViewById(R.id.show_comments);
        show_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CommentsActivity.class));
            }
        });

        TextView add_rating = getActivity().findViewById(R.id.addRateTxt);
        add_rating.setOnClickListener(getRatingDialog());

    }

    private void bindPhotos(List<String > photos){
        RecyclerView recyclerView = getActivity().findViewById(R.id.storePhotosRecycler);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        StorePhotosAdapter adapter = new StorePhotosAdapter(getActivity(), photos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.smoothScrollToPosition(0);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity()
                , recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                startActivity(new Intent(getActivity(), PhotosGalleryActivity.class));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }


    private View.OnClickListener getRatingDialog(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
                final View dialogView = inflater.inflate(R.layout.rating_custom_dialog, null);
                dialogBuilder.setView(dialogView);
                dialogBuilder.setCancelable(true);
                RatingBar ratingBar = dialogView.findViewById(R.id.ratingBar);
                Button submit = dialogView.findViewById(R.id.submit_rating);
                final AlertDialog b = dialogBuilder.create();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        b.dismiss();
                    }
                });

                b.show();
                b.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        };
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(sydney.latitude - ((sydney.latitude * 14) / 1000000), sydney.longitude- ((sydney.longitude * 14) / 400000)) , 14));
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
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
        if (mMapView !=null)
            mMapView.onResume();
    }


}
