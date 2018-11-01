package findupproducts.example.com.findup.UI.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.f2prateek.rx.preferences2.Preference;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import findupproducts.example.com.findup.Helper.Database.DBHandler;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Store.Stores;
import findupproducts.example.com.findup.Helper.Location.LocationUtility;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.Helper.Utility;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.adapters.NearMeAdapter;
import findupproducts.example.com.findup.UI.adapters.SearchMapAdapter;
import findupproducts.example.com.findup.models.Event;
import findupproducts.example.com.findup.models.Store;

import static findupproducts.example.com.findup.UI.activities.MainActivity.filteredMapDataEvent;
import static findupproducts.example.com.findup.UI.activities.MainActivity.filteredMapDataStore;


public class FilteredDataFragment extends android.support.v4.app.Fragment {

    RecyclerView filtered_recycler;

    public FilteredDataFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.filtered_data, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        filtered_recycler = getActivity().findViewById(R.id.filtered_map_data);
        InitRecyclerView(filtered_recycler);
    }

    public void InitRecyclerView(RecyclerView recyclerView) {
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        SearchMapAdapter adapter = new SearchMapAdapter(getActivity() , filteredMapDataStore , filteredMapDataEvent);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.smoothScrollToPosition(0);
    }
}
