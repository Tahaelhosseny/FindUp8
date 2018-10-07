package khaled.example.com.findup.UI.adapters;

import android.content.Context;
import android.content.Intent;
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

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.activities.ProductDetailsActivity;
import khaled.example.com.findup.models.Product;

/**
 * Created by khaled on 7/16/18.
 */

public class SendChatProductsAdapter extends RecyclerView.Adapter<SendChatProductsAdapter.ViewHolder> {

    private List<Product> products;
    private Context context;

    public SendChatProductsAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView productName;
        TextView productPrice;
        TextView productDescription;
        ImageView productImg;
        ImageView plus,minus;
        TextView amount;
        int amout_val;

        public ViewHolder(View view) {
            super(view);

            productName = view.findViewById(R.id.product_title);
            productDescription = view.findViewById(R.id.product_desc);
            productPrice = view.findViewById(R.id.productItemPrice);
            productImg = view.findViewById(R.id.product_image);

            plus = view.findViewById(R.id.plus);
            minus = view.findViewById(R.id.minus);
            amount = view.findViewById(R.id.prod_amount);
            amout_val = 0;
        }

        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, ProductDetailsActivity.class));
        }
    }

    @NonNull
    @Override
    public SendChatProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.send_chat_products_item, parent, false);
        return new SendChatProductsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final SendChatProductsAdapter.ViewHolder holder, int position) {

        Product product = products.get(position);

        holder.productName.setText(product.getProduct_name());
        holder.productDescription.setText(product.getProduct_desc());
        holder.productPrice.setText(String.valueOf(product.getProduct_price()));

        if (!product.getProduct_banner().isEmpty()) {
            Transformation transformation = new RoundedTransformationBuilder()
                    .cornerRadiusDp(80)
                    .oval(false)
                    .build();

            Picasso.with(holder.productImg.getContext()).load(product.getProduct_banner()).transform(transformation).placeholder(R.drawable.near_by_place_holder).into(holder.productImg);
        }

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.amout_val +=1;
                holder.amount.setText(holder.amout_val+"");
            }
        });

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.amout_val < 0) {
                    holder.amout_val += 1;
                    holder.amount.setText(holder.amout_val + "");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
