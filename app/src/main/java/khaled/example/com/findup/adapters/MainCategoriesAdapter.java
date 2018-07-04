package khaled.example.com.findup.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import khaled.example.com.findup.Helper.Utility;
import khaled.example.com.findup.R;
import khaled.example.com.findup.models.Category;

/**
 * Created by khaled on 7/4/18.
 */

public class MainCategoriesAdapter extends RecyclerView.Adapter<MainCategoriesAdapter.ViewHolder>{

    private List<Category> categoryList;
    private Context context;

    public MainCategoriesAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
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
        holder.catNameText.setText(category.getCat_name());
        holder.cat_bg.setBackgroundColor(Utility.getCategoryBackgroundIDSArray(context)[position%4]);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView catNameText;
        LinearLayout cat_bg;

        public ViewHolder(View view) {
            super(view);

            catNameText = view.findViewById(R.id.mainCatName);
            cat_bg =  view.findViewById(R.id.category_background_layout);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
