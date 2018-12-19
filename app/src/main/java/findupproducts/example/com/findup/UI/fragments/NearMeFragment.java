package findupproducts.example.com.findup.UI.fragments;


import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.Presenter.Fragments.NearMePresenter;
import findupproducts.example.com.findup.UI.ViewModel.Fragments.NearMeViewModel;
import findupproducts.example.com.findup.databinding.FragmentNearMeBinding;
import findupproducts.example.com.findup.netHelper.MakeRequest;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_near_me, container, false);
        View view = binding.getRoot();
        nearMeViewModel = new NearMeViewModel(getActivity(), binding.noStoresFound);
        binding.setNearMe(nearMeViewModel);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        nearMeViewModel.InitRecyclerView(bundle, binding.nearMeRecyclerView);
        binding.setPresenter(new NearMePresenter() {
            @Override
            public void InitRecyclerView() {
                nearMeViewModel.InitRecyclerView(bundle, binding.nearMeRecyclerView);
            }
        });
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        if (args.containsKey("search_text"))
            nearMeViewModel.FilterAdapter(args.getString("search_text"));
    }






}
