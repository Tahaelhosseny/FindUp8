package khaled.example.com.findup.UI.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import khaled.example.com.findup.Helper.Utility;
import khaled.example.com.findup.R;
import khaled.example.com.findup.models.Category;
import khaled.example.com.findup.models.Comment;
import khaled.example.com.findup.models.CurrentLocation;

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
