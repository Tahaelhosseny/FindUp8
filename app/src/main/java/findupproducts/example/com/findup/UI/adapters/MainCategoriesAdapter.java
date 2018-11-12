package findupproducts.example.com.findup.UI.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import findupproducts.example.com.findup.Helper.UI_Utility;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.activities.CategeoryStoresAcivity;
import findupproducts.example.com.findup.models.Category;

/**
 * Created by khaled on 7/4/18.
 */

public class MainCategoriesAdapter extends RecyclerView.Adapter<MainCategoriesAdapter.ViewHolder> {
    private List<Category> categoryList;
    private Context context;
    public MainCategoriesAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        if (categoryList.size() > 4)
            this.categoryList = categoryList.subList(0, 4);
        else
            this.categoryList = categoryList;
    }
    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
    @NonNull
    @Override
    public MainCategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_cats_item, parent, false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.cat_bg.setCardBackgroundColor(UI_Utility.getCategoryBackgroundIDSArray(context)[position % 4]);
        if (position == 3)
            holder.catNameText.setText(context.getResources().getString(R.string.more));
        else
            holder.catNameText.setText(category.getCat_name());

        holder.category_item_container.setMinimumWidth(getScreenWidth() / 4);
        holder.cat_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context , CategeoryStoresAcivity.class).putExtra("id" , category.getCat_id()));
            }
        });

    }
    @Override
    public int getItemCount() {
        return categoryList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView catNameText;
        CardView cat_bg;
        LinearLayout category_item_container;
        public ViewHolder(View view) {
            super(view);
            catNameText = view.findViewById(R.id.mainCatName);
            cat_bg = view.findViewById(R.id.category_background_layout);
            category_item_container = view.findViewById(R.id.category_item_container);
        }

    }
}
