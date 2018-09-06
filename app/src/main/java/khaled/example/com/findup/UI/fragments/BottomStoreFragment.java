package khaled.example.com.findup.UI.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import khaled.example.com.findup.Helper.UI_Utility;
import khaled.example.com.findup.Helper.Utility;
import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.adapters.BottomBarAdapter;

public class BottomStoreFragment extends Fragment {

    public static BottomStoreFragment newInstance() {
        BottomStoreFragment fragment = new BottomStoreFragment();
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

        actionBarSize =((CoordinatorLayout.LayoutParams) (getActivity().findViewById(R.id.store_main_container)).getLayoutParams()).topMargin;

        PopupMenu p  = new PopupMenu(getActivity(), null);
        menu = p.getMenu();
        getActivity().getMenuInflater().inflate(R.menu.bottom_store_navigation, menu);
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
                    UI_Utility.BottomNavigationStoreMenu_icons_change(menu,position);
                    adapter.notifyDataSetChanged();
                    //android.support.v4.app.Fragment selectedFragment = new MainStoreFragment();
                    Utility.replaceFragment(getActivity().getSupportFragmentManager(), new MainStoreFragment(), R.id.main_toolbar_container, 0);
                    switch (position) {
                        case 0:
                            //selectedFragment = new MainStoreFragment();
                            Utility.replaceFragment(getActivity().getSupportFragmentManager(), new MainStoreFragment(), R.id.main_toolbar_container, 0);
                            //((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                            //((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
                            break;
                        case 1:
                            //selectedFragment = new ChatStoreFragment();
                            Utility.replaceFragment(getActivity().getSupportFragmentManager(), new ChatStoreFragment(), R.id.main_toolbar_container, 0);
                            //((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                            //((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
                            break;
                        case 2:
                            //selectedFragment = new ProfileStoreFragment();
                            Utility.replaceFragment(getActivity().getSupportFragmentManager(), new ProfileStoreFragment(), R.id.main_toolbar_container, 0);
                            //((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                            //((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
                            break;
                    }
                    //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_toolbar_container, selectedFragment).commit();
                }
            };

}