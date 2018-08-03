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
import khaled.example.com.findup.adapters.NotificationsAdapter;
import khaled.example.com.findup.adapters.UserSavedAdapter;
import khaled.example.com.findup.models.Notification;
import khaled.example.com.findup.models.UserSavedItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserSavedFragment extends Fragment {


    public UserSavedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_saved, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<UserSavedItem> userSavedItems = new ArrayList<>();
        userSavedItems.add(new UserSavedItem());
        userSavedItems.add(new UserSavedItem());
        userSavedItems.add(new UserSavedItem());
        userSavedItems.add(new UserSavedItem());
        userSavedItems.add(new UserSavedItem());
        userSavedItems.add(new UserSavedItem());
        bindUI(userSavedItems);
    }

    private void bindUI(List<UserSavedItem> userSavedItems){
        RecyclerView recyclerView = getActivity().findViewById(R.id.userSavedRecyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        UserSavedAdapter adapter = new UserSavedAdapter(getActivity(), userSavedItems);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.smoothScrollToPosition(0);
    }
}
