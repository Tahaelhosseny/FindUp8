package findupproducts.example.com.findup.UI.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import findupproducts.example.com.findup.Helper.Database.DBHandler;
import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.LikeProductResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.Helper.UI_Utility;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.activities.ProductDetailsActivity;
import findupproducts.example.com.findup.models.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);
        return new ProductsAdapter.ViewHolder(itemView);
    }

//    txtVw.setCompoundDrawablesWithIntrinsicBounds( R.drawable.smiley, 0, 0, 0);

    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, int position) {

        holder.product = products.get(position);

        holder.productName.setText(holder.product.getProduct_name());
        holder.productDescription.setText(holder.product.getProduct_desc());
        holder.productPrice.setText(String.valueOf(holder.product.getProduct_price()));
        holder.productNumComments.setText(UI_Utility.CountValueToString(holder.product.getProduct_comments_count(),context));
        holder.productNumLikes.setText(UI_Utility.CountValueToString(holder.product.getProduct_likes_count(),context));
        holder.productNumLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                likeProduct(holder.product , holder.productNumLikes);
            }
        });
        if (!holder.product.getProduct_banner().isEmpty()) {
            Transformation transformation = new RoundedTransformationBuilder()
                    .cornerRadiusDp(50)
                    .oval(false)
                    .build();
            Picasso.with(holder.productImg.getContext()).load(holder.product.getProduct_banner()).transform(transformation).placeholder(R.drawable.near_by_place_holder).into(holder.productImg);
        }
        if(holder.product.getIf_liked() == 1){
            holder.productNumLikes.setCompoundDrawablesWithIntrinsicBounds(R.drawable.like ,0,0,0);
        }

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView productName;
        TextView productPrice;
        TextView productDescription;
        ImageView productImg;
        TextView productNumLikes;
        TextView productNumComments;
        Product product;

        public ViewHolder(View view) {
            super(view);

            productName = view.findViewById(R.id.productItemNameTxt);
            productDescription = view.findViewById(R.id.productItemDesc);
            productPrice = view.findViewById(R.id.productItemPrice);
            productImg = view.findViewById(R.id.productItemImg);
            productNumLikes =  view.findViewById(R.id.productNumLikes);
            productNumComments = view.findViewById(R.id.productNumComments);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, ProductDetailsActivity.class);
            intent.putExtra("prod_id", product.getProduct_id());
            v.getContext().startActivity(intent);
        }
    }
    private void likeProduct(Product product , TextView textView){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<LikeProductResponse> likeProduct = apiService.likeProduct(SharedPrefManger.getUser_ID() , product.getProduct_id());
        likeProduct.enqueue(new Callback<LikeProductResponse>() {
            @Override
            public void onResponse(Call<LikeProductResponse> call, Response<LikeProductResponse> response) {
                if (response.body().getSuccess() == 1){
                    if(response.body().getData().get(0).getLike_case().equals("like")){
                        product.setIf_liked(1);
                        DBHandler.likeProduct(product.getProduct_likes_count()+1 , product,1,context);
                        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.like ,0,0,0);
                        textView.setText(String.valueOf(product.getProduct_likes_count()+1));
                    }
                    if(response.body().getData().get(0).getLike_case().equals("unlike")){
                        product.setIf_liked(0);
                        DBHandler.likeProduct(product.getProduct_comments_count()-1, product,0,context);
                        textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.like ,0,0,0);
                        textView.setText(String.valueOf(product.getProduct_likes_count()-1));
                    }
                }
            }
            @Override
            public void onFailure(Call<LikeProductResponse> call, Throwable t) {
                Toast.makeText(context, "Something went error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
