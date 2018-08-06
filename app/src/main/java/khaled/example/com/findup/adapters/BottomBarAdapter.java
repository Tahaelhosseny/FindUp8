package khaled.example.com.findup.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import khaled.example.com.findup.R;

/**
 * Created by khaled on 8/6/18.
 */

public class BottomBarAdapter extends RecyclerView.Adapter<BottomBarAdapter.ViewHolder>{
    private List<int[]> itemsImgIds;

    public BottomBarAdapter(List<int[]> itemsImgIds) {
        this.itemsImgIds= itemsImgIds;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView itemImage;

        public ViewHolder(View view) {
            super(view);
            itemImage = view.findViewById(R.id.bottomBarItemImg);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }
    }

    @NonNull
    @Override
    public BottomBarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bottom_bar_item, parent, false);
        return new BottomBarAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BottomBarAdapter.ViewHolder holder, int position) {
        holder.itemImage.setImageResource(itemsImgIds.get(position)[0]);
    }

    @Override
    public int getItemCount() {
        return itemsImgIds.size();
    }
}
