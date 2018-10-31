package findupproducts.example.com.findup.UI.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
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

import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.Presenter.Fragments.NearMePresenter;
import findupproducts.example.com.findup.UI.Presenter.Fragments.UserNotificationPresenter;
import findupproducts.example.com.findup.UI.ViewModel.Fragments.NearMeViewModel;
import findupproducts.example.com.findup.UI.ViewModel.Fragments.UserNotificatonViewModel;
import findupproducts.example.com.findup.UI.activities.EventDetailsActivity;
import findupproducts.example.com.findup.UI.activities.NotificationsActivity;
import findupproducts.example.com.findup.UI.adapters.EventsAdapter;
import findupproducts.example.com.findup.UI.adapters.NotificationsAdapter;
import findupproducts.example.com.findup.UI.adapters.RecyclerTouchListener;
import findupproducts.example.com.findup.databinding.FragmentNotificationsBinding;
import findupproducts.example.com.findup.models.Event;

/**
 * Created by khaled on 8/1/18.
 */

public class NotificationsFragment extends Fragment {

    FragmentNotificationsBinding fragmentNotificationsBinding;
    UserNotificatonViewModel userNotificatonViewModel;

    public NotificationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentNotificationsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_notifications, container, false);
        View view = fragmentNotificationsBinding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        userNotificatonViewModel = new UserNotificatonViewModel(view.getContext());
        fragmentNotificationsBinding.setUserNotifications(userNotificatonViewModel);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userNotificatonViewModel.InitRecycler(fragmentNotificationsBinding.notificationsRecyclerView);



    }
}