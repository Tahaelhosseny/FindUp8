package khaled.example.com.findup.UI.ViewModel.Fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.Flowable;
import khaled.example.com.findup.Helper.Database.DBHandler;
import khaled.example.com.findup.Helper.Database.Interfaces.Events;
import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.activities.EventDetailsActivity;
import khaled.example.com.findup.UI.adapters.EventsAdapter;
import khaled.example.com.findup.UI.adapters.RecyclerTouchListener;
import khaled.example.com.findup.models.Event;

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

        DBHandler.getAllEvents(mContext, new Events() {
            @Override
            public void onSuccess(Flowable<List<Event>> listFlowable) {
                listFlowable.subscribe(eventList -> {
                    adapter.setEvents(eventList);
                    adapter.notifyDataSetChanged();
                });
            }

            @Override
            public void onFail() {

            }
        });
    }
}
