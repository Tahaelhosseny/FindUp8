package khaled.example.com.findup.fragments;


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
import khaled.example.com.findup.activities.EventDetailsActivity;
import khaled.example.com.findup.activities.NotificationsActivity;
import khaled.example.com.findup.adapters.EventsAdapter;
import khaled.example.com.findup.adapters.NotificationsAdapter;
import khaled.example.com.findup.adapters.RecyclerTouchListener;
import khaled.example.com.findup.models.Event;
import khaled.example.com.findup.models.Notification;

/**
 * Created by khaled on 8/1/18.
 */

public class NotificationsFragment extends Fragment {


    public NotificationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification());
        notifications.add(new Notification());
        notifications.add(new Notification());
        notifications.add(new Notification());
        notifications.add(new Notification());
        notifications.add(new Notification());
        notifications.add(new Notification());
        bindUI(notifications);
    }

    private void bindUI(List<Notification> notifications){
        RecyclerView recyclerView = getActivity().findViewById(R.id.notificationsRecyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        NotificationsAdapter adapter = new NotificationsAdapter(getActivity(), notifications);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.smoothScrollToPosition(0);
    }
}
