package khaled.example.com.findup.fragments;


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
import khaled.example.com.findup.adapters.NearMeAdapter;
import khaled.example.com.findup.models.Place;

/**
 * A simple {@link Fragment} subclass.
 */
public class NearMeFragment extends Fragment {


    public NearMeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_near_me, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<Place> places = new ArrayList<>();
        places.add(new Place(1,"Black Cafe", "1.3km", "", "4.5","American Cafe Break $$"));
        places.add(new Place(2,"Genuine Coffee", "2km", "", "4.2","Indian Cafe Break $$"));
        places.add(new Place(1,"Black Cafe", "1.3km", "", "4.5","American Cafe Break $$"));
        places.add(new Place(2,"Genuine Coffee", "2km", "", "4.2","Indian Cafe Break $$"));

        bindUI(places);
    }

    private void bindUI(List<Place> places){
        RecyclerView recyclerView = getActivity().findViewById(R.id.nearMeRecyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        NearMeAdapter adapter = new NearMeAdapter(getActivity(), places);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.smoothScrollToPosition(0);
    }
}
