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
import khaled.example.com.findup.models.Product;
import khaled.example.com.findup.models.ReviewStoreItem;

public class StoreProductsReviewsAdapter extends RecyclerView.Adapter<StoreProductsReviewsAdapter.ViewHolder> {

    private List<ReviewStoreItem> reviewStoreItems;
    private Context context;

    public StoreProductsReviewsAdapter(Context context, List<ReviewStoreItem> reviewStoreItems) {
        this.context = context;
        this.reviewStoreItems = reviewStoreItems;
    }

    public void setProducts(List<ReviewStoreItem> reviewStoreItems) {
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
        TextView commentTime;
        ImageView commentUserImg;

        public ViewHolder(View view) {
            super(view);
            productImage = view.findViewById(R.id.productItemImg);
            productName = view.findViewById(R.id.productItemNameTxt);
            productDesc = view.findViewById(R.id.productItemDesc);
            commentBody = view.findViewById(R.id.comment_txt);
            commentTime = view.findViewById(R.id.timeTxt);
            commentUserImg = view.findViewById(R.id.user_photo);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
        }
    }
}
