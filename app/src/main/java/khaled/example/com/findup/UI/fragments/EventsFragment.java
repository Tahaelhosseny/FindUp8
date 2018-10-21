package khaled.example.com.findup.UI.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
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
import khaled.example.com.findup.UI.ViewModel.Fragments.EventsViewModel;
import khaled.example.com.findup.UI.ViewModel.Fragments.NearMeViewModel;
import khaled.example.com.findup.UI.activities.EventDetailsActivity;
import khaled.example.com.findup.UI.adapters.EventsAdapter;
import khaled.example.com.findup.UI.adapters.RecyclerTouchListener;
import khaled.example.com.findup.databinding.FragmentEventsBinding;
import khaled.example.com.findup.models.Event;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {


    int type;

    public EventsFragment() {
        // Required empty public constructor
    }

    EventsViewModel eventsViewModel;
    FragmentEventsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_events, container, false);//11842 H
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        eventsViewModel = new EventsViewModel(view.getContext());
        binding.setEvents(eventsViewModel);

        String behavior;
        if (getArguments() != null && getArguments().containsKey("behavior")) {
            behavior = getArguments().getString("behavior");
            if (behavior.equals("V"))
                type = LinearLayoutManager.VERTICAL;
            else
                type = LinearLayoutManager.HORIZONTAL;
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        eventsViewModel.InitRecyclerView(binding.eventsRecyclerView,type);
    }



}
