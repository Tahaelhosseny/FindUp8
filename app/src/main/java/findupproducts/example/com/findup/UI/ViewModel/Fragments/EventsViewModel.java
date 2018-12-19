package findupproducts.example.com.findup.UI.ViewModel.Fragments;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import findupproducts.example.com.findup.Helper.Database.DBHandler;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Events;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.Helper.Utility;
import findupproducts.example.com.findup.UI.adapters.EventsAdapter;
import findupproducts.example.com.findup.models.Event;
import io.reactivex.Flowable;

import static findupproducts.example.com.findup.UI.fragments.MainFragment.eventType;

public class EventsViewModel extends Observable {
    Activity mContext;
    List<Event> all_events = new ArrayList<>();
    TextView no_events_txt;
    EventsAdapter adapter;

    public EventsViewModel(Activity mContext, TextView no_events_txt) {
        this.mContext = mContext;
        this.no_events_txt = no_events_txt;
    }

    public void InitRecyclerView(RecyclerView recyclerView,int type){
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new EventsAdapter(mContext, new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, type, false));
        recyclerView.smoothScrollToPosition(0);
        LoadDataFromDataBase(adapter);
    }
    private void LoadDataFromDataBase(EventsAdapter adapter){
        if(eventType == "MainEvents"){
            DBHandler.getAllEvents(mContext, new Events() {
                @Override
                public void onSuccess(Flowable<List<Event>> listFlowable) {
                    listFlowable.subscribe(events -> {
                        ((Activity) mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                all_events.clear();
                                all_events.addAll(events);
                                adapter.setEvents(events);
                                adapter.notifyDataSetChanged();
                            }
                        });
                    });
                }

                @Override
                public void onFail() {

                }
            });
        }else {
            DBHandler.getEventByStoreID(SharedPrefManger.getStore_ID(), mContext, new Events() {
                @Override
                public void onSuccess(Flowable<List<Event>> listFlowable) {
                    listFlowable.subscribe(eventList -> {
                        ((Activity) mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                boolean check = true;
                                List<Event> events = new ArrayList<>();
                                for (int i = 0; i < eventList.size(); i++) {
                                    try {
                                        check = checkDate(eventList.get(i).getEvent_start_date());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    if (!check) {

                                    } else {
                                        events.add(eventList.get(i));
                                    }
                                }
                                if (events.size() > 0) {
                                    adapter.setEvents(events);
                                    adapter.notifyDataSetChanged();
                                    ValidateIsEmpty();
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
    }

    public boolean checkDate(String date) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date datee = simpleDateFormat.parse(date);
        return new Date().after(datee);
    }

    public void FilterAdapter(String search_text) {
        if (!search_text.isEmpty())
            adapter.setEvents(Utility.SearchEvents(all_events, search_text));
        else
            adapter.setEvents(all_events);
        adapter.notifyDataSetChanged();
        ValidateIsEmpty();
    }

    public void ValidateIsEmpty() {
        if (adapter.getItemCount() == 0)
            no_events_txt.setVisibility(View.VISIBLE);
        else
            no_events_txt.setVisibility(View.GONE);
    }


}
