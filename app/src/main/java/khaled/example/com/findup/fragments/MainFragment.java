package khaled.example.com.findup.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import khaled.example.com.findup.adapters.EventsAdapter;
import khaled.example.com.findup.adapters.MainCategoriesAdapter;
import khaled.example.com.findup.adapters.NearMeAdapter;
import khaled.example.com.findup.adapters.RecyclerTouchListener;
import khaled.example.com.findup.models.Category;
import khaled.example.com.findup.models.Event;
import khaled.example.com.findup.models.Place;


public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<Category> categories = new ArrayList<>();
        categories.add(new Category("food"));
        categories.add(new Category("food"));
        categories.add(new Category("food"));
        categories.add(new Category("food"));
        categories.add(new Category("food"));

        List<Place> places = new ArrayList<>();
        places.add(new Place("place", "4 KM", "", "5.0"));
        places.add(new Place("place", "4 KM", "", "5.0"));
        places.add(new Place("place", "4 KM", "", "5.0"));
        places.add(new Place("place", "4 KM", "", "5.0"));

        List<Event> events = new ArrayList<>();
        events.add(new Event("Event", "Event Description","6 May",""));
        events.add(new Event("Event", "Event Description","6 May",""));
        events.add(new Event("Event", "Event Description","6 May",""));
        events.add(new Event("Event", "Event Description","6 May",""));
        events.add(new Event("Event", "Event Description","6 May",""));

        RecyclerView catsRecyclerView = getActivity().findViewById(R.id.catsRecyclerView);
        MainCategoriesAdapter mainCategoriesAdapter = new MainCategoriesAdapter(getActivity(), categories);
        catsRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity()
                , catsRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        RecyclerView nearRecyclerView = getActivity().findViewById(R.id.nearMeRecyclerView);
        NearMeAdapter nearMeAdapter = new NearMeAdapter(getActivity(), places);
        nearRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity()
                , catsRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        RecyclerView eventsRecyclerView = getActivity().findViewById(R.id.eventsRecyclerView);
        EventsAdapter eventsAdapter = new EventsAdapter(getActivity(), events);
        eventsRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity()
                , catsRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                startActivity(new Intent(getActivity(), EventDetailsActivity.class));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        bindUI(catsRecyclerView, mainCategoriesAdapter, 1);
        bindUI(nearRecyclerView, nearMeAdapter, 2);
        bindUI(eventsRecyclerView, eventsAdapter, 3);
    }

    private void bindUI(RecyclerView recyclerView, Object adapter, int switchAdapter){
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        switch (switchAdapter){
            case 1:
                recyclerView.setAdapter((MainCategoriesAdapter) adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
                break;
            case 2:
                recyclerView.setAdapter((NearMeAdapter) adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
                break;
            case 3:
                recyclerView.setAdapter((EventsAdapter) adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));
                break;
        }

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
