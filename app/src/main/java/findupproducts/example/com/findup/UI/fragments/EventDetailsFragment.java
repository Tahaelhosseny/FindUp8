package findupproducts.example.com.findup.UI.fragments;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.ViewModel.Fragments.EventDataViewModel;
import findupproducts.example.com.findup.UI.ViewModel.Fragments.EventsViewModel;
import findupproducts.example.com.findup.databinding.FragmentEventDetailsBinding;

import static findupproducts.example.com.findup.UI.ViewModel.Fragments.EventDataViewModel.event;


public class EventDetailsFragment extends Fragment implements OnMapReadyCallback {
    FragmentEventDetailsBinding binding;
    EventDataViewModel viewModel;
    private MapView mMapView;
    String event_id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_details, container, false);//11842 H
        View view = binding.getRoot();
        Intent i = ((Activity)view.getContext()).getIntent();
        event_id = i.getStringExtra("event_id");
        //here data must be an instance of the class MarsDataProvider
        viewModel = new EventDataViewModel(view.getContext() , event_id);
        binding.setEventDetails(viewModel);

        try {
            MapsInitializer.initialize(this.getActivity());
            mMapView = (MapView) view.findViewById(R.id.mapView);
            mMapView.onCreate(savedInstanceState);
            mMapView.getMapAsync(this);
        } catch (Exception e) {

        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel.BindUI(binding.eventName , binding.storeName , binding.imageView2 , binding.dateTxt , binding.locationTxt , binding.ticketPriceTxt , binding.aboutTxtDetails);
    }

    private void LoadEventData(String event_id) {
    }

    private void bindUI() {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(-34, 151);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(sydney.latitude - ((sydney.latitude * 14) / 1000000), sydney.longitude - ((sydney.longitude * 14) / 400000)), 14));
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        /*LatLng eventLoc = new LatLng(Double.parseDouble(event.getEvent_latitude()),Double.parseDouble(event.getEvent_latitude()));
        googleMap.addMarker(new MarkerOptions().position(eventLoc).icon(
                BitmapDescriptorFactory.fromResource(R.drawable.map_marker)
        ).title(event.getEvent_name()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eventLoc,4));*/
    }
}
