package khaled.example.com.findup.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.minibugdev.drawablebadge.BadgePosition;
import com.minibugdev.drawablebadge.DrawableBadge;

import khaled.example.com.findup.R;
import khaled.example.com.findup.activities.NotificationsActivity;
import khaled.example.com.findup.activities.SettingsActivity;
import khaled.example.com.findup.activities.StoreChooseCategoryActivity;
import khaled.example.com.findup.activities.UserSavedActivity;

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

        btn_notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), NotificationsActivity.class));
            }
        });
        btn_chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "chats",Toast.LENGTH_SHORT).show();
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