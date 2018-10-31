package findupproducts.example.com.findup.UI.fragments;


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

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.ViewModel.Fragments.EventsViewModel;
import findupproducts.example.com.findup.UI.ViewModel.Fragments.MainCatsViewModel;
import findupproducts.example.com.findup.UI.adapters.MainCategoriesAdapter;
import findupproducts.example.com.findup.UI.adapters.RecyclerTouchListener;
import findupproducts.example.com.findup.databinding.FragmentMainCatsBinding;
import findupproducts.example.com.findup.models.Category;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainCatsFragment extends Fragment {


    public MainCatsFragment() {
        // Required empty public constructor
    }


    MainCatsViewModel mainCatsViewModel;
    FragmentMainCatsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_cats, container, false);
        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        mainCatsViewModel = new MainCatsViewModel(view.getContext());
        binding.setEvents(mainCatsViewModel);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainCatsViewModel.InitRecyclerView(binding.catsRecyclerView);
    }

}
