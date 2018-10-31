package findupproducts.example.com.findup.UI.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.models.AddProduct;

public class AddProductsAdapter extends RecyclerView.Adapter<AddProductsAdapter.ViewHolder> {
    private List<AddProduct> addProducts;
    private Context context;

    public AddProductsAdapter(Context context, List<AddProduct> addProducts) {
        this.context = context;
        this.addProducts = addProducts;
    }


    @NonNull
    @Override
    public AddProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_truck_item, parent, false);
        return new AddProductsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AddProduct addProduct = addProducts.get(position);

        holder.productName.setText(addProduct.getProductName());
        holder.productDescription.setText(addProduct.getProductDescription());
        holder.productPrice.setText(addProduct.getProductPrice());

        Bitmap bitmap = BitmapFactory.decodeFile(addProduct.getProductImgPath());
        holder.productImg.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return addProducts.size();
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
        public void onClick(View view) {

        }
    }
}
