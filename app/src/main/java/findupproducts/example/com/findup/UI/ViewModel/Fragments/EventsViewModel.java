package findupproducts.example.com.findup.UI.ViewModel.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import findupproducts.example.com.findup.Helper.SharedPrefManger;
import io.reactivex.Flowable;
import findupproducts.example.com.findup.Helper.Database.DBHandler;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Events;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.activities.EventDetailsActivity;
import findupproducts.example.com.findup.UI.adapters.EventsAdapter;
import findupproducts.example.com.findup.UI.adapters.RecyclerTouchListener;
import findupproducts.example.com.findup.models.Event;

import static findupproducts.example.com.findup.UI.fragments.MainFragment.eventType;

public class EventsViewModel extends Observable {
    Context mContext;

    public EventsViewModel(Context mContext) {
        this.mContext = mContext;
    }

    public void InitRecyclerView(RecyclerView recyclerView,int type){
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        EventsAdapter adapter = new EventsAdapter(mContext, new ArrayList<>());
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
        if (new Date().after(datee)) {
            return true;
        }
        return false;
    }


}
