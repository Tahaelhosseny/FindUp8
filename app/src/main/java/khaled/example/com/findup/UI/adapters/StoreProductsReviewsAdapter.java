package khaled.example.com.findup.UI.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import io.reactivex.Flowable;
import khaled.example.com.findup.Helper.Database.DBHandler;
import khaled.example.com.findup.Helper.Database.Interfaces.Product.PComment;
import khaled.example.com.findup.R;
import khaled.example.com.findup.models.Product;
import khaled.example.com.findup.models.ProductComment;
import khaled.example.com.findup.models.ReviewStoreItem;

public class StoreProductsReviewsAdapter extends RecyclerView.Adapter<StoreProductsReviewsAdapter.ViewHolder> {

    private List<Product> reviewStoreItems;
    private Context context;

    public StoreProductsReviewsAdapter(Context context, List<Product> reviewStoreItems) {
        this.context = context;
        this.reviewStoreItems = reviewStoreItems;
    }

    public void setProduct(List<Product> reviewStoreItems){
        this.reviewStoreItems = reviewStoreItems;
    }


    @NonNull
    @Override
    public StoreProductsReviewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_product_review_item, parent, false);
        return new StoreProductsReviewsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreProductsReviewsAdapter.ViewHolder holder, int position) {
        Product product = reviewStoreItems.get(position);
        if (!product.getProduct_banner().isEmpty()) {
            Transformation transformation = new RoundedTransformationBuilder()
                    .cornerRadiusDp(20)
                    .oval(false)
                    .build();

            Picasso.with(context).load("http://findupproducts.com/findup_api/imgs/"+product.getProduct_banner()).transform(transformation).placeholder(R.drawable.com_facebook_profile_picture_blank_square).into(holder.productImage);
        }
        holder.productDesc.setText(product.getProduct_desc());
        holder.productName.setText(product.getProduct_name());
        loadComments(holder.commentUserImg , holder.commentBody , holder.commentTime , holder.comment_user ,  product.getProduct_id());

    }

    @Override
    public int getItemCount() {
        return reviewStoreItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView productImage;
        TextView productName;
        TextView productDesc;
        TextView commentBody;
        TextView comment_user;
        TextView commentTime;
        TextView viewPrevtxt;
        ImageView commentUserImg;

        public ViewHolder(View view) {
            super(view);
            productImage = view.findViewById(R.id.productItemImg);
            productName = view.findViewById(R.id.productItemNameTxt);
            productDesc = view.findViewById(R.id.productItemDesc);
            commentBody = view.findViewById(R.id.comment_txt);
            comment_user = view.findViewById(R.id.comment_username);
            commentTime = view.findViewById(R.id.timeTxt);
            commentUserImg = view.findViewById(R.id.user_photo);
            viewPrevtxt = view.findViewById(R.id.viewPreTxt);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
        }
    }

    private void loadComments(ImageView img , TextView coment , TextView date , TextView name, int id){
        DBHandler.getSpecificComment(context, id, new PComment() {
            @Override
            public void onSuccess(Flowable<List<ProductComment>> commentFlowable) {
                commentFlowable.subscribe(val ->
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("Val" , String.valueOf(val.size()));
                                for (int i = 0 ; i < val.size() ; i++) {
                                    name.setText(val.get(i).getAccount_name());
                                    coment.setText(val.get(i).getComment());
                                    date.setText(val.get(i).getComment_date());
                                    if (!val.get(i).getAccount_image().isEmpty()) {
                                        Transformation transformation = new RoundedTransformationBuilder()
                                                .cornerRadiusDp(20)
                                                .oval(false)
                                                .build();

                                        Picasso.with(context).load(val.get(i).getAccount_image()).transform(transformation).placeholder(R.drawable.com_facebook_profile_picture_blank_square).into(img);
                                    }

                                }
                                if(val.size()  == 0){
                                    date.setVisibility(View.GONE);
                                    coment.setText("There is No Comment Until now Fro this product");
                                    img.setVisibility(View.GONE);
                                    name.setVisibility(View.GONE);
                                }
                            }
                        })
                );
            }
            @Override
            public void onFail() {

            }
        });
    }
}
