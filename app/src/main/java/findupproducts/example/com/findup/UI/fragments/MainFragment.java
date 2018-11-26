package findupproducts.example.com.findup.UI.fragments;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import findupproducts.example.com.findup.R;

import static findupproducts.example.com.findup.UI.activities.MainActivity.filterData;

public class MainFragment extends Fragment {

    public static String eventType = "UserMain";

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FrameLayout frameLayout = getActivity().findViewById(R.id.navigation_bottom_container);
        frameLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        ImageButton search_filter = getActivity().findViewById(R.id.search_filter);
        search_filter.setVisibility(View.GONE);

        LinearLayout search_layout = getActivity().findViewById(R.id.search_edit_text_with_filter);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
            search_layout.setElevation(0);
        EditText search = getActivity().findViewById(R.id.search);
        filterData.setSearch_text(search.getText().toString());
        getChildFragmentManager().beginTransaction().replace(R.id.catsContainer, new MainCatsFragment()).commit();
        getChildFragmentManager().beginTransaction().replace(R.id.nearMeContainer, new NearMeFragment()).commit();
        getChildFragmentManager().beginTransaction().replace(R.id.eventsContainer, new EventsFragment()).commit();
    }
}
