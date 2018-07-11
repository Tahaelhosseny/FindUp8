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
import khaled.example.com.findup.adapters.EventsAdapter;
import khaled.example.com.findup.adapters.MainCategoriesAdapter;
import khaled.example.com.findup.adapters.NearMeAdapter;
import khaled.example.com.findup.adapters.RecyclerTouchListener;
import khaled.example.com.findup.models.Category;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainCatsFragment extends Fragment {


    public MainCatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_cats, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Food-Trunk"));
        categories.add(new Category("Home Business"));
        categories.add(new Category("Crafts"));
        categories.add(new Category("Food-Trunk"));
        categories.add(new Category("Home Business"));
        bindUI(categories);
    }

    private void bindUI(List<Category> categories){
        RecyclerView recyclerView = getActivity().findViewById(R.id.catsRecyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        MainCategoriesAdapter adapter = new MainCategoriesAdapter(getActivity(), categories);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.smoothScrollToPosition(0);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity()
                , recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

}
