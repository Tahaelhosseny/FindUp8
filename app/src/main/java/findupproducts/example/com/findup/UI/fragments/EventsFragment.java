package findupproducts.example.com.findup.UI.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.ViewModel.Fragments.EventsViewModel;
import findupproducts.example.com.findup.databinding.FragmentEventsBinding;

import static findupproducts.example.com.findup.UI.fragments.MainFragment.eventType;

import static findupproducts.example.com.findup.UI.fragments.MainFragment.eventType;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_events, container, false);//11842 H
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        eventsViewModel = new EventsViewModel(getActivity(), binding.noEventsFound);
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
        if (eventType.equals("MainEvents")) {
            eventsViewModel.InitRecyclerView(binding.eventsMRecyclerView, LinearLayoutManager.HORIZONTAL);
        }else {
            eventsViewModel.InitRecyclerView(binding.eventsMRecyclerView, LinearLayoutManager.VERTICAL);
        }
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        if (args.containsKey("search_text"))
            eventsViewModel.FilterAdapter(args.getString("search_text"));

    }
}
