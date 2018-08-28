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

import khaled.example.com.findup.CustomViews.OverlapDecoration;
import khaled.example.com.findup.R;
import khaled.example.com.findup.activities.ChatWithStoreActivity;
import khaled.example.com.findup.activities.CommentsActivity;
import khaled.example.com.findup.activities.PhotosGalleryActivity;
import khaled.example.com.findup.adapters.CommentsPhotosAdapter;
import khaled.example.com.findup.adapters.RecyclerTouchListener;
import khaled.example.com.findup.adapters.StorePhotosAdapter;
import khaled.example.com.findup.models.Comment;

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

        try {
            MapsInitializer.initialize(this.getActivity());
            mMapView = (MapView) rootView.findViewById(R.id.mapView);
            mMapView.onCreate(savedInstanceState);
            mMapView.getMapAsync(this);
        }catch (Exception e){

        }

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

        ImageView chats = getActivity().findViewById(R.id.chatImg);
        chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ChatWithStoreActivity.class));
            }
        });

        bindCommentsPhotos();
    }



    private void bindCommentsPhotos(){
        List<Comment> commentList = new ArrayList<>();
        commentList.add(new Comment("Nof Ahmed",1532037763,"There are many variations of passages of larem lpsum avaliable, but the mojrity have suffeed alteration in some form, by injected humour.","https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/26167130_1598420403578018_2434073418497810718_n.jpg?_nc_cat=0&oh=fe9593c83468b97c82a5e1623cc99030&oe=5BC620E6"));
        commentList.add(new Comment("Ali Mohamed",1532037763,"There are many variations of passages of larem lpsum avaliable, but the mojrity have suffeed alteration in some form, by injected humour.","https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/23032777_1542237902529602_2168190355513235328_n.jpg?_nc_cat=0&oh=bbc2dce33830def8b69357824a77d8f7&oe=5BE23CBC"));
        commentList.add(new Comment("Mohamed Ahmed",1532037763,"There are many variations of passages of larem lpsum avaliable, but the mojrity have suffeed alteration in some form, by injected humour.","https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/20108103_1445001665586560_8405913558571444834_n.jpg?_nc_cat=0&oh=d1e92942903ee55336709c7e670b95af&oe=5BC99CF4"));
        commentList.add(new Comment("Walid Abd EL-Rahman",1532037763,"There are many variations of passages of larem lpsum avaliable, but the mojrity have suffeed alteration in some form, by injected humour.","https://scontent-cai1-1.xx.fbcdn.net/v/t1.0-9/15337648_1214159182004144_1442355796478199942_n.jpg?_nc_cat=0&oh=9d510e190e24f0d97efcb03db4875f9b&oe=5BDF92C5"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,true);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        RecyclerView recyclerView = getActivity().findViewById(R.id.commentUsersImg);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        CommentsPhotosAdapter adapter = new CommentsPhotosAdapter(getActivity(), commentList);
        recyclerView.setAdapter(adapter);
        //recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new OverlapDecoration());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }
    private void bindPhotos(List<String > photos){
        RecyclerView recyclerView = getActivity().findViewById(R.id.storePhotosRecycler);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        StorePhotosAdapter adapter = new StorePhotosAdapter(getActivity(), photos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)
        {
            @Override
            public boolean canScrollVertically() {
                return false;
            }

            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        });
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
        if (mMapView !=null)
            mMapView.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
