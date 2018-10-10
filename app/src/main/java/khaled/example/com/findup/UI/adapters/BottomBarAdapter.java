package khaled.example.com.findup.UI.adapters;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import khaled.example.com.findup.R;

/**
 * Created by khaled on 8/6/18.
 */

public class BottomBarAdapter extends RecyclerView.Adapter<BottomBarAdapter.ViewHolder> {

    int selected = 0;

    private List<MenuItem> menuItemList;
    private View.OnClickListener onClickListener;
    private static Menu menu;

    public BottomBarAdapter(Menu menuItemList, View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.menu = menuItemList;
        this.menuItemList = new ArrayList<>();
        for (int i = 0; i < menuItemList.size(); i++) {
            this.menuItemList.add(menuItemList.getItem(i));
        }
    }

    public static Menu getMenu() {
        return menu;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    @NonNull
    @Override
    public BottomBarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bottom_bar_item, parent, false);
        return new BottomBarAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BottomBarAdapter.ViewHolder holder, int position) {
        holder.itemImage.setImageDrawable(menuItemList.get(position).getIcon());
        holder.itemImage.setMinimumHeight(ConstraintLayout.MarginLayoutParams.MATCH_PARENT);
        holder.itemImage.setMinimumWidth(getScreenWidth() / getItemCount() + 1);
        holder.container.setTag(position);
    }

    @Override
    public int getItemCount() {
        return menuItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        ConstraintLayout container;

        public ViewHolder(View view) {
            super(view);
            itemImage = view.findViewById(R.id.bottomBarItemImg);
            container = view.findViewById(R.id.bottom_menu_item_container);
            view.setOnClickListener(onClickListener);

        }

    }
}
