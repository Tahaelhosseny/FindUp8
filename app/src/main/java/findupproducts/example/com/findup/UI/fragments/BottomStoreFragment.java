package findupproducts.example.com.findup.UI.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import findupproducts.example.com.findup.Helper.UI_Utility;
import findupproducts.example.com.findup.Helper.Utility;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.adapters.BottomBarStoreAdapter;

public class BottomStoreFragment extends Fragment {

    static float actionBarSize;
    public static Menu menu;
    public static BottomBarStoreAdapter adapter;
    public View.OnClickListener navListner =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConstraintLayout constraintLayout = v.findViewById(R.id.bottom_menu_item_container);
                    int position = (int) constraintLayout.getTag();
                    UI_Utility.BottomNavigationStoreMenu_icons_change(menu, position);
                    adapter.notifyDataSetChanged();
                    Fragment selectedFragment = new StoreAccountHomeFragment();
                    ReplaceFragment(position,v.getContext(),menu);
                }
            };

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        actionBarSize = ((CoordinatorLayout.LayoutParams) (getActivity().findViewById(R.id.store_main_container)).getLayoutParams()).topMargin;

        PopupMenu p = new PopupMenu(getActivity(), null);
        menu = p.getMenu();
        getActivity().getMenuInflater().inflate(R.menu.bottom_store_navigation, menu);
        bindUI(menu);
    }

    private void bindUI(Menu menu) {
        RecyclerView recyclerView = getActivity().findViewById(R.id.BottomBarRecyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new BottomBarStoreAdapter(getActivity(),menu, navListner);
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
    private static void ReplaceFragment(int position, Context mContext, Menu menu) {
        Fragment selectedFragment;
        switch (position) {
            case 0:
                selectedFragment = new StoreAccountHomeFragment();
                Utility.replaceStoreFragment(((FragmentActivity)mContext).getSupportFragmentManager(), new StoreAccountHomeFragment(), R.id.store_main_container, 0,menu);
                break;
            case 1:
                selectedFragment = new ChatWithStoreFragment();
                Utility.replaceStoreFragment(((FragmentActivity)mContext).getSupportFragmentManager(), new StoreChatFragment(), R.id.store_main_container, 0,menu);
                break;
            case 2:
//                ToolbarSwitch(true,mContext);
                selectedFragment = new ProfileStoreFragment();
                Utility.replaceStoreFragment(((FragmentActivity)mContext).getSupportFragmentManager(), new ProfileStoreFragment(), R.id.store_main_container, 0,menu);

                break;
        }
    }


}