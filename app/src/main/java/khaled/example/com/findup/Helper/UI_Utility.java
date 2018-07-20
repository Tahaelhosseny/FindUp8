package khaled.example.com.findup.Helper;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import khaled.example.com.findup.R;

public class UI_Utility {
    public static int[] getCategoryBackgroundIDSArray(Context mContext){
        TypedArray ta = mContext.getResources().obtainTypedArray(R.array.colors);
        int[] colors = new int[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            colors[i] = ta.getColor(i, 0);
        }
        return colors;
    }

    public static void BottomNavigationMenu_icons_change(Menu menu , MenuItem Selected){
        menu.findItem(R.id.home).setIcon(R.drawable.home_unselected_0_5x);
        menu.findItem(R.id.map).setIcon(R.drawable.map_1_5x);
        menu.findItem(R.id.search).setIcon(R.drawable.search_1_5x);
        menu.findItem(R.id.category).setIcon(R.drawable.category_1_5x);
        menu.findItem(R.id.myCart).setIcon(R.drawable.__1_5x);

        if (Selected.getItemId() == R.id.home)
            Selected.setIcon(R.drawable.home_sel_1_5x);
        if (Selected.getItemId() == R.id.map)
            Selected.setIcon(R.drawable.map_sel_1_5x);
        if (Selected.getItemId() == R.id.search)
            Selected.setIcon(R.drawable.search_sel_0_5x);
        if (Selected.getItemId() == R.id.category)
            Selected.setIcon(R.drawable.category_sel_1_5x);
        if (Selected.getItemId() == R.id.myCart)
            Selected.setIcon(R.drawable.__1_5x);
    }

    public static void switchVisibility(View view){
        if (view.getVisibility() == View.GONE)
            view.setVisibility(View.VISIBLE);
        else if (view.getVisibility() == View.VISIBLE)
            view.setVisibility(View.GONE);
    }
}
