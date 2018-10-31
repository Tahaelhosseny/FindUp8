package findupproducts.example.com.findup.UI.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import findupproducts.example.com.findup.UI.fragments.FilterFragment;
import findupproducts.example.com.findup.UI.fragments.SortFragment;


public class TabPagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public TabPagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    FilterFragment filer = new FilterFragment();
                    return filer;
                case 1:
                    SortFragment sort = new SortFragment();
                    return sort;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
}
