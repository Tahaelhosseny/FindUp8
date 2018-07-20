package khaled.example.com.findup.fragments;


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
import android.widget.ImageView;

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
public class StoreInfoFragment extends Fragment {


    public StoreInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store_info, container, false);
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
}
