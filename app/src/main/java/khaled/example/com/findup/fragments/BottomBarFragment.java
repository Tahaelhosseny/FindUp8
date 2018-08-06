package khaled.example.com.findup.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import khaled.example.com.findup.R;
import khaled.example.com.findup.activities.EventDetailsActivity;
import khaled.example.com.findup.adapters.BottomBarAdapter;
import khaled.example.com.findup.adapters.EventsAdapter;
import khaled.example.com.findup.adapters.RecyclerTouchListener;
import khaled.example.com.findup.models.Event;

public class BottomBarFragment extends Fragment {

    public BottomBarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_bar, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<int[]> itemsImgIds = new ArrayList<>();
        itemsImgIds.add(new int[]{R.drawable.home_unselected_0_5x, R.drawable.home_sel_1_5x});
        itemsImgIds.add(new int[]{R.drawable.map_1_5x, R.drawable.map_sel_1_5x});
        itemsImgIds.add(new int[]{R.drawable.search_1_5x, R.drawable.search_sel_0_5x});
        itemsImgIds.add(new int[]{R.drawable.category_1_5x, R.drawable.category_sel_1_5x});
        itemsImgIds.add(new int[]{R.drawable.__1_5x, R.drawable.__1_5x});
        bindUI(itemsImgIds);
    }

    private void bindUI(List<int[]> itemsImgIds){
        RecyclerView recyclerView = getActivity().findViewById(R.id.BottomBarRecyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        BottomBarAdapter adapter = new BottomBarAdapter(itemsImgIds);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.smoothScrollToPosition(0);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity()
                , recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}
