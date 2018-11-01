package findupproducts.example.com.findup.UI.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
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

import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.activities.EventDetailsActivity;
import findupproducts.example.com.findup.UI.activities.NotificationsActivity;
import findupproducts.example.com.findup.UI.activities.StoreDetailsActivity;
import findupproducts.example.com.findup.models.Event;
import findupproducts.example.com.findup.models.NotificationStore;
import findupproducts.example.com.findup.models.NotificationUser;
import findupproducts.example.com.findup.models.Store;

public class SerachedAdapter extends RecyclerView.Adapter<SerachedAdapter.ViewHolder> {
    private List<Store> storesList;
    private Context context;
    private int state;
    private List<Event> eventList;
    public SerachedAdapter(Context context, List<Store> storesList , int state) {
        this.context = context;
        this.storesList = storesList;
        this.state  = state;
    }
    public SerachedAdapter(List<Event> eventList , int state , Context mContext){
        this.context = mContext;
        this.eventList = eventList;
        this.state = state;
    }

    @NonNull
    @Override
    public SerachedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.searched_item, parent, false);
        return new SerachedAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(state == 1){
            Store store = storesList.get(position);
            holder.searched_name.setText(store.getStore_name());
            holder.searched_desc.setText(store.getStore_desc());
            if (!store.getStore_banner().isEmpty()) {
                Transformation transformation = new RoundedTransformationBuilder()
                        .cornerRadiusDp(50)
                        .oval(false)
                        .build();
                Picasso.with(holder.searched_img.getContext()).load(store.getStore_banner()).transform(transformation).placeholder(R.drawable.near_by_place_holder).into(holder.searched_img);
            holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context , StoreDetailsActivity.class).putExtra("store_id" , store.getStore_id()));
                }
            });
            }
        }else{
            Event event = eventList.get(position);
            holder.searched_name.setText(event.getEvent_name());
            holder.searched_desc.setText(event.getEvent_desc());
            if (!event.getEvent_photo().isEmpty()) {
                Transformation transformation = new RoundedTransformationBuilder()
                        .cornerRadiusDp(50)
                        .oval(false)
                        .build();
                Picasso.with(holder.searched_img.getContext()).load(event.getEvent_photo()).transform(transformation).placeholder(R.drawable.near_by_place_holder).into(holder.searched_img);
            }
            holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context , EventDetailsActivity.class).putExtra("event_id" , event.getEvent_id()));

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(state == 1){
            return storesList.size();
        }else{
            return eventList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView searched_img;
        TextView searched_name;
        TextView searched_desc;
        ConstraintLayout constraintLayout;

        public ViewHolder(View view) {
            super(view);
            searched_desc = view.findViewById(R.id.item_description);
            searched_img = view.findViewById(R.id.item_img);
            searched_name = view.findViewById(R.id.item_name);
            constraintLayout = view.findViewById(R.id.main_searched_item_constraint);


        }

    }
}
