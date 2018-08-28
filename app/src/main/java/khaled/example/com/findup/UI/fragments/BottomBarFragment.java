package khaled.example.com.findup.UI.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import khaled.example.com.findup.Helper.UI_Utility;
import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.activities.EventDetailsActivity;
import khaled.example.com.findup.UI.adapters.BottomBarAdapter;
import khaled.example.com.findup.UI.adapters.EventsAdapter;
import khaled.example.com.findup.UI.adapters.RecyclerTouchListener;
import khaled.example.com.findup.models.Event;

public class BottomBarFragment extends Fragment {

    public static BottomBarFragment newInstance() {
        BottomBarFragment fragment = new BottomBarFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_bar, container, false);
    }

    float actionBarSize;
    Menu menu;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        actionBarSize =((CoordinatorLayout.LayoutParams) (getActivity().findViewById(R.id.main_toolbar_container)).getLayoutParams()).topMargin;

        PopupMenu p  = new PopupMenu(getActivity(), null);
        menu = p.getMenu();
        getActivity().getMenuInflater().inflate(R.menu.bottom_navigation_items, menu);
        bindUI(menu);

    }

    BottomBarAdapter adapter;
    private void bindUI(Menu menu){
        RecyclerView recyclerView = getActivity().findViewById(R.id.BottomBarRecyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
         adapter = new BottomBarAdapter(menu,navListner);
        recyclerView.setAdapter(adapter);
        recyclerView.stopNestedScroll();
        recyclerView.stopScroll();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }

            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        });
        recyclerView.smoothScrollToPosition(0);
    }


    public View.OnClickListener navListner =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConstraintLayout constraintLayout = v.findViewById(R.id.bottom_menu_item_container);
                    int position =(int) constraintLayout.getTag();
                    UI_Utility.BottomNavigationMenu_icons_change(menu,position);
                    adapter.notifyDataSetChanged();
                    Fragment selectedFragment = new MainFragment();
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Category");
                    switch (position) {
                        case 0:
                            ToolbarSwitch(true);
                            selectedFragment = new MainFragment();
                            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
                            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getActivity().getString(R.string.find_things));
                            break;
                        case 1:
                            ToolbarSwitch(false);
                            selectedFragment = new MapFragment();
                            break;
                        case 2:
                            ToolbarSwitch(true);
                            selectedFragment = new SearchFragment();
                            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
                            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getActivity().getString(R.string.find_things));
                            break;
                        case 3:
                            ToolbarSwitch(true);
                            selectedFragment = new CategoryFragment();
                            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
                            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getActivity().getString(R.string.category));
                            break;
                        case 4:
                            ToolbarSwitch(true);
                            selectedFragment = new ProfileFragment();
                            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
                            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getActivity().getString(R.string.profile));
                            break;
                    }
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_toolbar_container, selectedFragment).commit();
                }
            };

    public void ToolbarSwitch(Boolean sw) {
        Toolbar toolbar =  getActivity().findViewById(R.id.toolbar_top);
        if (!sw) {
            toolbar.setVisibility(View.GONE);
            ((CoordinatorLayout.LayoutParams) (getActivity().findViewById(R.id.main_toolbar_container)).getLayoutParams()).topMargin = 0;
        }else {
            TypedValue tv = new TypedValue();
            toolbar.setVisibility(View.VISIBLE);
            ((CoordinatorLayout.LayoutParams) (getActivity().findViewById(R.id.main_toolbar_container)).getLayoutParams()).topMargin = (int) actionBarSize;
        }
    }
}
