package findupproducts.example.com.findup.UI.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import findupproducts.example.com.findup.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemMsgFragment extends Fragment {


    public SystemMsgFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_system_msg, container, false);
    }

}
