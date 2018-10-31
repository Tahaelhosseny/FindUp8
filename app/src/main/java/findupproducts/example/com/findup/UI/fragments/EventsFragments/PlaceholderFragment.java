package findupproducts.example.com.findup.UI.fragments.EventsFragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Flowable;
import findupproducts.example.com.findup.Helper.Database.DBHandler;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Events;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.activities.CreateEventActivity;
import findupproducts.example.com.findup.UI.adapters.EventsAdapter;
import findupproducts.example.com.findup.models.Event;

public class PlaceholderFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    public PlaceholderFragment() {
    }
    RecyclerView recyclerView;
    LinearLayout linearLayout;
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_store_events, container, false);
        Button btn_createNewEvent = rootView.findViewById(R.id.btn_createNewEvent);
        recyclerView = rootView.findViewById(R.id.recyclerEvents);
        linearLayout = rootView.findViewById(R.id.layoutAddEvents);
        btn_createNewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CreateEventActivity.class));
            }
        });
        InitEventByType(recyclerView);
        return rootView;
    }
    Context mContext;
    public void InitEventByType(RecyclerView recyclerView){
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        EventsAdapter adapter = new EventsAdapter(getActivity(), new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, false));
        recyclerView.smoothScrollToPosition(0);
        LoadEventsById(adapter);
    }
    private void LoadEventsById(EventsAdapter adapter){
        DBHandler.getEventByStoreID( 1 , getActivity(), new Events() {
            @Override
            public void onSuccess(Flowable<List<Event>> listFlowable) {
                listFlowable.subscribe(eventList -> {
                    (getActivity()).runOnUiThread(new Runnable() {
                        @Override
                        public void run(){
                            boolean check = true;
                            List<Event> events = new ArrayList<>();
                            for(int i = 0 ; i < eventList.size() ; i++){
                                try {
                                    check = checkDate(eventList.get(i).getEvent_start_date());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                if(check){

                                }else{
                                    events.add(eventList.get(i));
                                }
                            }
                            if(events.size() > 0){
                                recyclerView.setVisibility(View.VISIBLE);linearLayout.setVisibility(View.GONE);
                                adapter.setEvents(events);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    });
                });
            }

            @Override
            public void onFail() {

            }
        });
    }

    public boolean checkDate(String date) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date datee = simpleDateFormat.parse(date);
        if (new Date().after(datee)) {
            return true;
        }
        return false;
    }



}
