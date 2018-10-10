package khaled.example.com.findup.UI.fragments;


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
import khaled.example.com.findup.UI.adapters.MainCategoriesAdapter;
import khaled.example.com.findup.UI.adapters.RecyclerTouchListener;
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
        categories.add(new Category(1, "Food-Trunk", "simple description", 1, 1));
        categories.add(new Category(2, "Home Business", "simple description", 1, 1));
        categories.add(new Category(3, "Crafts", "simple description", 1, 1));
        categories.add(new Category(4, "Food-Trunk", "simple description", 1, 1));
        categories.add(new Category(5, "Home Business", "simple description", 1, 1));
        bindUI(categories);
    }

    private void bindUI(List<Category> categories) {
        RecyclerView recyclerView = getActivity().findViewById(R.id.catsRecyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        MainCategoriesAdapter adapter = new MainCategoriesAdapter(getActivity(), categories);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }

            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        });
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
