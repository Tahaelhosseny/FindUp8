package khaled.example.com.findup.UI.fragments;


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

import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.Presenter.Fragments.UserNotificationPresenter;
import khaled.example.com.findup.UI.Presenter.Fragments.UserSavedPresenter;
import khaled.example.com.findup.UI.ViewModel.Fragments.UserNotificatonViewModel;
import khaled.example.com.findup.UI.ViewModel.Fragments.YouSavedViewModel;
import khaled.example.com.findup.UI.adapters.NotificationsAdapter;
import khaled.example.com.findup.UI.adapters.UserSavedAdapter;
import khaled.example.com.findup.databinding.FragmentUserSavedBinding;
import khaled.example.com.findup.models.Product;
import khaled.example.com.findup.models.UserSavedItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserSavedFragment extends Fragment {

    FragmentUserSavedBinding fragmentUserSavedBinding;
    YouSavedViewModel userSavedViewModel;


    public UserSavedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentUserSavedBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_saved, container, false);
        View view = fragmentUserSavedBinding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        userSavedViewModel = new YouSavedViewModel(view.getContext());
        fragmentUserSavedBinding.setUserSaved(userSavedViewModel);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userSavedViewModel.InitRecycler(fragmentUserSavedBinding.userSavedRecyclerView);


    }
}
