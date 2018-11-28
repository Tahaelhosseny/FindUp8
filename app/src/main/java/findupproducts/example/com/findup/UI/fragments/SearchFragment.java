package findupproducts.example.com.findup.UI.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.activities.FilterActivity;
import findupproducts.example.com.findup.UI.fragments.SearchedFragments.EventSearchedFragment;
import findupproducts.example.com.findup.UI.fragments.SearchedFragments.ProductSearchedFragment;
import findupproducts.example.com.findup.UI.fragments.SearchedFragments.StoreSearchedFragment;

import static findupproducts.example.com.findup.UI.activities.MainActivity.filterData;

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
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FrameLayout frameLayout = getActivity().findViewById(R.id.navigation_bottom_container);
        frameLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        getActivity().overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_top);
        getChildFragmentManager().beginTransaction().replace(R.id.catsContainer, new MainCatsFragment()).commit();
        getChildFragmentManager().beginTransaction().replace(R.id.store_container, new StoreSearchedFragment()).commit();
        getChildFragmentManager().beginTransaction().replace(R.id.event_container, new EventSearchedFragment()).commit();
        getChildFragmentManager().beginTransaction().replace(R.id.product_container, new ProductSearchedFragment()).commit();
        ImageButton view_fillter = getActivity().findViewById(R.id.search_filter);
        view_fillter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterData.setSearch_from("");
                EditText search = getActivity().findViewById(R.id.search);
                filterData.setSearch_text(search.getText().toString());
                startActivity(new Intent(getActivity(), FilterActivity.class));
            }
        });

    }
}
