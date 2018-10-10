package khaled.example.com.findup.UI.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import khaled.example.com.findup.Helper.UI_Utility;
import khaled.example.com.findup.Helper.Utility;
import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.adapters.BottomBarAdapter;

public class BottomBarFragment extends Fragment {

    static float actionBarSize;
    public static Menu menu;
    BottomBarAdapter adapter;
    public View.OnClickListener navListner =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConstraintLayout constraintLayout = v.findViewById(R.id.bottom_menu_item_container);
                    int position = (int) constraintLayout.getTag();
                    UI_Utility.BottomNavigationMenu_icons_change(menu, position);
                    adapter.notifyDataSetChanged();
                    Fragment selectedFragment = new MainFragment();
                    ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
                    ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Category");
                    ReplaceFragment(position,v.getContext(),menu);
                    //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_toolbar_container, selectedFragment).commit();
                }
            };

    private static void ReplaceFragment(int position,Context mContext,Menu menu) {
        Fragment selectedFragment;
        switch (position) {
            case 0:
                ToolbarSwitch(true,mContext);
                selectedFragment = new MainFragment();
                Utility.replaceFragment(((FragmentActivity)mContext).getSupportFragmentManager(), new MainFragment(), R.id.main_toolbar_container, 0,menu);
                ((AppCompatActivity) mContext).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                ((AppCompatActivity) mContext).getSupportActionBar().setDisplayShowHomeEnabled(false);
                ((AppCompatActivity) mContext).getSupportActionBar().setTitle(mContext.getString(R.string.find_things));
                break;
            case 1:
                ToolbarSwitch(false,mContext);
                selectedFragment = new MapFragment();
                Utility.replaceFragment(((FragmentActivity)mContext).getSupportFragmentManager(), new MapFragment(), R.id.main_toolbar_container, 0,menu);
                break;
            case 2:
                ToolbarSwitch(true,mContext);
                selectedFragment = new SearchFragment();
                Utility.replaceFragment(((FragmentActivity)mContext).getSupportFragmentManager(), new SearchFragment(), R.id.main_toolbar_container, 0,menu);
                ((AppCompatActivity) mContext).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                ((AppCompatActivity) mContext).getSupportActionBar().setDisplayShowHomeEnabled(false);
                ((AppCompatActivity) mContext).getSupportActionBar().setTitle(mContext.getString(R.string.find_things));
                break;
            case 3:
                ToolbarSwitch(true,mContext);
                selectedFragment = new CategoryFragment();
                Utility.replaceFragment(((FragmentActivity)mContext).getSupportFragmentManager(), new CategoryFragment(), R.id.main_toolbar_container, 0,menu);
                ((AppCompatActivity) mContext).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                ((AppCompatActivity) mContext).getSupportActionBar().setDisplayShowHomeEnabled(false);
                ((AppCompatActivity) mContext).getSupportActionBar().setTitle(mContext.getString(R.string.category));
                break;
            case 4:
                ToolbarSwitch(true,mContext);
                selectedFragment = new ProfileFragment();
                Utility.replaceFragment(((FragmentActivity)mContext).getSupportFragmentManager(), new ProfileFragment(), R.id.main_toolbar_container, 0,menu);
                ((AppCompatActivity) mContext).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                ((AppCompatActivity) mContext).getSupportActionBar().setDisplayShowHomeEnabled(false);
                ((AppCompatActivity) mContext).getSupportActionBar().setTitle(mContext.getString(R.string.profile));
                break;
        }
    }

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        actionBarSize = ((CoordinatorLayout.LayoutParams) (getActivity().findViewById(R.id.main_toolbar_container)).getLayoutParams()).topMargin;

        PopupMenu p = new PopupMenu(getActivity(), null);
        menu = p.getMenu();
        getActivity().getMenuInflater().inflate(R.menu.bottom_navigation_items, menu);
        bindUI(menu);

    }

    private void bindUI(Menu menu) {
        RecyclerView recyclerView = getActivity().findViewById(R.id.BottomBarRecyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new BottomBarAdapter(menu, navListner);
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

    public static void ToolbarSwitch(Boolean sw, Context mContext) {
        Toolbar toolbar = ((Activity) mContext).findViewById(R.id.toolbar_top);
        if (!sw) {
            toolbar.setVisibility(View.GONE);
            ((CoordinatorLayout.LayoutParams) (((Activity) mContext).findViewById(R.id.main_toolbar_container)).getLayoutParams()).topMargin = 0;
        } else {
            TypedValue tv = new TypedValue();
            toolbar.setVisibility(View.VISIBLE);
            ((CoordinatorLayout.LayoutParams) (((Activity) mContext).findViewById(R.id.main_toolbar_container)).getLayoutParams()).topMargin = (int) actionBarSize;
        }
    }
}
