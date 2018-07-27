package khaled.example.com.findup.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import khaled.example.com.findup.R;

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
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getChildFragmentManager().beginTransaction().replace(R.id.catsContainer, new MainCatsFragment()).commit();
        getChildFragmentManager().beginTransaction().replace(R.id.popularProductsContainer, new ProductsFragment()).commit();
        getChildFragmentManager().beginTransaction().replace(R.id.suggestedProductsContainer, new ProductsFragment()).commit();
    }
}
