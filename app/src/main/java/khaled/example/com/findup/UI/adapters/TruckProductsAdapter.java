package khaled.example.com.findup.UI.adapters;

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
import khaled.example.com.findup.models.TruckProduct;

public class TruckProductsAdapter extends RecyclerView.Adapter<TruckProductsAdapter.ViewHolder> {
    private List<TruckProduct> truckProducts;
    private Context context;

    public TruckProductsAdapter(Context context, List<TruckProduct> truckProducts) {
        this.context = context;
        this.truckProducts = truckProducts;
    }


    @NonNull
    @Override
    public TruckProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_truck_item, parent, false);
        return new TruckProductsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TruckProduct truckProduct = truckProducts.get(position);
    }

    @Override
    public int getItemCount() {
        return truckProducts.size();
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
