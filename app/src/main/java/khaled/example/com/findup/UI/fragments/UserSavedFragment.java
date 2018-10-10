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
import khaled.example.com.findup.UI.adapters.UserSavedAdapter;
import khaled.example.com.findup.models.Product;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserSavedFragment extends Fragment {


    public UserSavedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_saved, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<Product> products = new ArrayList<>();
        products.add(new Product(0, 36, 206, 566, "T.A.C.O Truck", "Description", "http://storage-cube.quebecormedia.com/v1/cl_prod/canadian_living/61d1cc29b4c9649a0c22a0297909203b3bb18dc1/vegan-chocolate-cake"));
        products.add(new Product(0, 36, 206, 566, "Chocolate Cake", "Description", "https://www.tasteofhome.com/wp-content/uploads/2018/01/exps957_TH143195C09_04_4b-2.jpg"));
        products.add(new Product(0, 36, 206, 566, "Name", "Description", "https://img.buzzfeed.com/thumbnailer-prod-us-east-1/video-api/assets/109778.jpg"));
        products.add(new Product(0, 36, 206, 566, "T.A.C.O Truck", "Description", "http://i.imgur.com/2KQVKw0.jpg"));
        products.add(new Product(0, 36, 206, 566, "Chocolate Cake", "Description", "https://i2-prod.mirror.co.uk/incoming/article6201545.ece/ALTERNATES/s615/Cup-of-tea.jpg"));
        products.add(new Product(0, 36, 206, 566, "Name", "Description", "http://cdn.shopify.com/s/files/1/0653/8213/products/Review_1_1_595e822f-7ad5-42f2-8f04-d16c923614dd_grande.jpg?v=1520387592"));

        bindUI(products);
    }

    private void bindUI(List<Product> userSavedItems) {
        RecyclerView recyclerView = getActivity().findViewById(R.id.userSavedRecyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        UserSavedAdapter adapter = new UserSavedAdapter(getActivity(), userSavedItems);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.smoothScrollToPosition(0);
    }
}
