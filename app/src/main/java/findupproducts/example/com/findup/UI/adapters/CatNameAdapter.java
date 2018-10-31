package findupproducts.example.com.findup.UI.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import findupproducts.example.com.findup.Helper.Utility;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.models.Category;
import findupproducts.example.com.findup.models.Comment;
import findupproducts.example.com.findup.models.CurrentLocation;

import static findupproducts.example.com.findup.UI.activities.MainActivity.filterData;

public class CatNameAdapter  extends RecyclerView.Adapter<CatNameAdapter.ViewHolder> {


    private Context mContext;
    private List<Category> categories;

    public CatNameAdapter(Context mContext, List<Category> categories) {
        this.mContext = mContext;
        this.categories = categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CatNameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_cat_item, parent, false);
        return new CatNameAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CatNameAdapter.ViewHolder holder, int position) {
        Category category = categories.get(position);

        holder.cat_name.setText(category.getCat_name());
        holder.cat_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterData.setFilter_by("Category");filterData.setFilter_byid(String.valueOf(category.getCat_id()));
                if (TextUtils.equals(holder.cat_name.getTag().toString(),"inactive")){
                    holder.cat_name.setTextColor(Color.parseColor("#F24E8E"));
                    holder.cat_name.setTag("active");
                } else{
                    holder.cat_name.setTextColor(mContext.getResources().getColor(R.color.tw__composer_deep_gray));
                    holder.cat_name.setTag("inactive");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cat_name;


        public ViewHolder(View view) {
            super(view);
            cat_name = view.findViewById(R.id.single_cat_txt);

        }
    }


}
