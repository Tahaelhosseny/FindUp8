package khaled.example.com.findup.UI.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.ViewModel.Fragments.SortFilterViewModel;
import khaled.example.com.findup.UI.ViewModel.Fragments.UserNotificatonViewModel;
import khaled.example.com.findup.databinding.FragmentSortBinding;

public class SortFragment extends Fragment {

    FragmentSortBinding binding;
    SortFilterViewModel viewModel;

    public SortFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sort, container, false);
        View view = binding.getRoot();
        viewModel = new SortFilterViewModel(view.getContext());
        binding.setSortFrag(viewModel);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.InitCatRecycler(binding.categoryFilterRecycler);
    }
}