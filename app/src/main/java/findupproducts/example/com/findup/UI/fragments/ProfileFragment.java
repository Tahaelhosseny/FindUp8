package findupproducts.example.com.findup.UI.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.activities.AddProductTruckActivity;
import findupproducts.example.com.findup.UI.activities.ChatWithStoreActivity;
import findupproducts.example.com.findup.UI.activities.NotificationsActivity;
import findupproducts.example.com.findup.UI.activities.ProfileChatsActivity;
import findupproducts.example.com.findup.UI.activities.SettingsActivity;
import findupproducts.example.com.findup.UI.activities.StoreAccountHomeActivity;
import findupproducts.example.com.findup.UI.activities.StoreChooseCategoryActivity;
import findupproducts.example.com.findup.UI.activities.StoreContactActivity;
import findupproducts.example.com.findup.UI.activities.UserSavedActivity;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Button btn_notifications = view.findViewById(R.id.btn_notifications);
        Button btn_chats = view.findViewById(R.id.btn_chats);
        Button btn_saved = view.findViewById(R.id.btn_saved);
        Button btn_settings = view.findViewById(R.id.btn_settings);
        Button btn_createStoreAccount = view.findViewById(R.id.btn_createStoreAccount);
        TextView prof_name = view.findViewById(R.id.profile_name);
        TextView prof_phone = view.findViewById(R.id.profile_phone);
        prof_name.setText(SharedPrefManger.getUser_name());
        prof_phone.setText(SharedPrefManger.getLogin_phone());
        btn_notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NotificationsActivity.class));
            }
        });
        btn_chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ProfileChatsActivity.class));
            }
        });
        btn_saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), UserSavedActivity.class));
            }
        });
        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
            }
        });
        btn_createStoreAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), StoreChooseCategoryActivity.class));
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}