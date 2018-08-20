package khaled.example.com.findup.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import khaled.example.com.findup.R;
import khaled.example.com.findup.activities.FilterActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_search, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_top);
        getChildFragmentManager().beginTransaction().replace(R.id.catsContainer, new MainCatsFragment()).commit();
        getChildFragmentManager().beginTransaction().replace(R.id.popularProductsContainer, new ProductsFragment()).commit();
        getChildFragmentManager().beginTransaction().replace(R.id.suggestedProductsContainer, new SuggestedProductsFragment()).commit();

        ImageButton view_fillter = getActivity().findViewById(R.id.search_filter);
        view_fillter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //UI_Utility.switchVisibility(filter);
                //getChildFragmentManager().beginTransaction().replace(R.id.nearMeContainer, new NearMeFragment()).commit();
                startActivity(new Intent(getActivity(), FilterActivity.class));
            }
        });

    }
}
