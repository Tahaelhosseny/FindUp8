package khaled.example.com.findup.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import khaled.example.com.findup.R;
import khaled.example.com.findup.models.Product;

/**
 * Created by khaled on 7/16/18.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private List<Product> products;
    private Context context;

    public ProductsAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView productName;
        TextView productPrice;
        TextView productDescription;
        ImageView productImg;

        public ViewHolder(View view) {
            super(view);

            productName = view.findViewById(R.id.productItemNameTxt);
            productDescription = view.findViewById(R.id.productItemDesc);
            productPrice = view.findViewById(R.id.productItemPrice);
            productImg = view.findViewById(R.id.productItemImg);
        }

        @Override
        public void onClick(View v) {
        }
    }

    @NonNull
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);
        return new ProductsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, int position) {

        Product product = products.get(position);
        /*holder.productName.setText(product.getProductName());
        holder.productDescription.setText(product.getProductDescription());
        holder.productPrice.setText(String.valueOf(product.getProductPrice()));*/
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
