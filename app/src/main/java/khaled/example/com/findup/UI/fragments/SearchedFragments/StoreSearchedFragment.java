package khaled.example.com.findup.UI.fragments.SearchedFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.adapters.ProductsAdapter;
import khaled.example.com.findup.UI.adapters.SerachedAdapter;

import static khaled.example.com.findup.UI.activities.MainActivity.searchedProducts;
import static khaled.example.com.findup.UI.activities.MainActivity.searchedStore;

public class StoreSearchedFragment extends Fragment {
    RecyclerView recyclerView;
    public StoreSearchedFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.store_search_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = getActivity().findViewById(R.id.store_searched_data);
        InitRecycler(recyclerView);
    }
    private void InitRecycler(RecyclerView recyclerView){
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        SerachedAdapter adapter = new SerachedAdapter(getActivity(), searchedStore , 1);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.smoothScrollToPosition(0);
    }
}
