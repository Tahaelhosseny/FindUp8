package khaled.example.com.findup.UI.fragments;


import android.app.AlertDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.ViewModel.Fragments.StoreInfoViewModel;
import khaled.example.com.findup.UI.activities.CommentsActivity;
import khaled.example.com.findup.databinding.FragmentStoreInfoBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreInfoFragment extends Fragment implements OnMapReadyCallback {

    MapView mMapView;
    StoreInfoViewModel storeInfoViewModel;
    FragmentStoreInfoBinding binding;

    public StoreInfoFragment() {
        // Required empty public constructor
    }

    public static StoreInfoFragment newInstance() {
        StoreInfoFragment fragment = new StoreInfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store_info, container, false);
        View rootView = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        storeInfoViewModel = new StoreInfoViewModel(getContext(), getArguments().getInt("store_id"));
        binding.setInfo(storeInfoViewModel);
        try {
            MapsInitializer.initialize(this.getActivity());
            mMapView = (MapView) rootView.findViewById(R.id.mapView);
            mMapView.onCreate(savedInstanceState);
            mMapView.getMapAsync(this);
        } catch (Exception e) {

        }

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ImageView show_comments = getActivity().findViewById(R.id.show_comments);
        show_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CommentsActivity.class));
            }
        });

        TextView add_rating = getActivity().findViewById(R.id.addRateTxt);
        add_rating.setOnClickListener(getRatingDialog());

        storeInfoViewModel.bindCommentsPhotos(binding.commentUsersImg);
        storeInfoViewModel.bindPhotos(binding.storePhotosRecycler);
        storeInfoViewModel.bindStoreData(binding.aboutTxtDetails, binding.workTimeDaysInfoTxt, binding.workTimeInfoTxt,
                binding.mailImg, binding.siteImg, binding.chatImg, binding.twitterImg, binding.snapImg);

    }


    private View.OnClickListener getRatingDialog() {
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
        storeInfoViewModel.onMapReadyBind(googleMap, binding.button2, binding.streetAddress);
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mMapView != null)
            mMapView.onLowMemory();
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
    public void onResume() {
        super.onResume();
        if (mMapView != null)
            mMapView.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
