package khaled.example.com.findup.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import khaled.example.com.findup.R;
import khaled.example.com.findup.adapters.EventsAdapter;
import khaled.example.com.findup.adapters.MainCategoriesAdapter;
import khaled.example.com.findup.adapters.NearMeAdapter;
import khaled.example.com.findup.models.Category;
import khaled.example.com.findup.models.Event;
import khaled.example.com.findup.models.Place;


public class EventDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_details, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bindUI();
    }

    private void bindUI() {

    }

}
