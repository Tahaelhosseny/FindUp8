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
import khaled.example.com.findup.UI.adapters.NotificationsAdapter;
import khaled.example.com.findup.UI.adapters.StoreProductsReviewsAdapter;
import khaled.example.com.findup.models.Notification;
import khaled.example.com.findup.models.ReviewStoreItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoreAccountHomeFragment extends Fragment {


    public StoreAccountHomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store_account_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<ReviewStoreItem> reviewStoreItems = new ArrayList<>();
        reviewStoreItems.add(new ReviewStoreItem());
        reviewStoreItems.add(new ReviewStoreItem());
        reviewStoreItems.add(new ReviewStoreItem());
        bindUI(reviewStoreItems);
    }

    private void bindUI(List<ReviewStoreItem> reviewStoreItems){
        RecyclerView recyclerView = getActivity().findViewById(R.id.reviewsRecyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        StoreProductsReviewsAdapter adapter = new StoreProductsReviewsAdapter(getActivity(), reviewStoreItems);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.smoothScrollToPosition(0);
    }
}
