package khaled.example.com.findup.UI.fragments;


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

import java.util.ArrayList;
import java.util.List;

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.activities.EventDetailsActivity;
import khaled.example.com.findup.UI.adapters.EventsAdapter;
import khaled.example.com.findup.UI.adapters.RecyclerTouchListener;
import khaled.example.com.findup.models.Event;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {


    int type;

    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        String behavior;
        if (getArguments() != null && getArguments().containsKey("behavior")) {
            behavior = getArguments().getString("behavior");
            if (behavior.equals("V"))
                type = LinearLayoutManager.VERTICAL;
            else
                type = LinearLayoutManager.HORIZONTAL;
        }
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<Event> events = new ArrayList<>();
        /*events.add(new Event("Event", "Event Description","6 May",""));
        events.add(new Event("Event", "Event Description","6 May",""));
        events.add(new Event("Event", "Event Description","6 May",""));
        events.add(new Event("Event", "Event Description","6 May",""));
        events.add(new Event("Event", "Event Description","6 May",""));*/
        bindUI(events);

    }

    private void bindUI(List<Event> events) {
        RecyclerView recyclerView = getActivity().findViewById(R.id.eventsRecyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        EventsAdapter adapter = new EventsAdapter(getActivity(), events);
        recyclerView.setAdapter(adapter);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), type, false));
        recyclerView.smoothScrollToPosition(0);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity()
                , recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                startActivity(new Intent(getActivity(), EventDetailsActivity.class));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }
}
