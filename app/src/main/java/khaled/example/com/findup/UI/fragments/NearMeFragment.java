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

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.Presenter.Fragments.NearMePresenter;
import khaled.example.com.findup.UI.ViewModel.Fragments.NearMeViewModel;
import khaled.example.com.findup.UI.adapters.NearMeAdapter;
import khaled.example.com.findup.databinding.FragmentNearMeBinding;
import khaled.example.com.findup.models.Store;

/**
 * A simple {@link Fragment} subclass.
 */
public class NearMeFragment extends Fragment {


    public NearMeFragment() {
        // Required empty public constructor
    }

    NearMeViewModel nearMeViewModel;
    FragmentNearMeBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_near_me, container, false);
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        nearMeViewModel = new NearMeViewModel(view.getContext());
        binding.setNearMe(nearMeViewModel);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        nearMeViewModel.InitRecyclerView(binding.nearMeRecyclerView);

        binding.setPresenter(new NearMePresenter() {
            @Override
            public void InitRecyclerView() {
                nearMeViewModel.InitRecyclerView(binding.nearMeRecyclerView);
            }
        });
    }

}
