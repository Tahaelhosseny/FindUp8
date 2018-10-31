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
import findupproducts.example.com.findup.models.Search;
import findupproducts.example.com.findup.models.Store;

public class SearchMapAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Store> storeList;
    private Context context;
    private List<Event> eventList;
    final int VIEW_TYPE_STORE = 0;
    final int VIEW_TYPE_EVENT = 1;
    public SearchMapAdapter(Context context , List<Store> storeList , List<Event> eventList){
        this.storeList = storeList;
        this.eventList = eventList;
        this.context = context;
    }
    public void setLists(List<Store> storeList , List<Event> eventList) {
        this.storeList = storeList;
        this.eventList = eventList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_map_item, parent, false);
        if(viewType == VIEW_TYPE_STORE){
            return new StoreViewHolder(itemView);
        }

        if(viewType == VIEW_TYPE_EVENT){
            return new EventViewHolder(itemView);
        }

        return null;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof StoreViewHolder){
            ((StoreViewHolder) holder).populateStore(storeList.get(position));
        }

        if(holder instanceof EventViewHolder){
            ((EventViewHolder) holder).populateEvent(eventList.get(position - storeList.size()));
        }
    }
    @Override
    public int getItemViewType(int position){
        if(position < storeList.size()){
            return VIEW_TYPE_STORE;
        }

        if(position - storeList.size() < eventList.size()){
            return VIEW_TYPE_EVENT;
        }

        return -1;
    }
    public class StoreViewHolder extends RecyclerView.ViewHolder {
        ImageView banner;
        TextView name;
        TextView description;
        TextView type;
        ConstraintLayout constraintLayout;

        public StoreViewHolder(View itemView){
            super(itemView);

            banner = itemView.findViewById(R.id.search_map_item_img);
            name = itemView.findViewById(R.id.search_map_item_name);
            description = itemView.findViewById(R.id.search_map_item_desc);
            type = itemView.findViewById(R.id.search_map_item_type);
            constraintLayout = itemView.findViewById(R.id.constraintLayoutSearchMap);
        }

        public void populateStore(Store store){
            Picasso.with(context).load(store.getStore_banner()).into(banner);name.setText(store.getStore_name());
            description.setText(store.getStore_desc());type.setText("Store");
            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context , StoreDetailsActivity.class).putExtra("store_id" , store.getStore_id()));
                }
            });
        }
    }
    public class EventViewHolder extends RecyclerView.ViewHolder {
        ImageView banner;
        TextView name;
        TextView description;
        TextView type;
        ConstraintLayout constraintLayout;

        public EventViewHolder(View itemView){
            super(itemView);

            banner = itemView.findViewById(R.id.search_map_item_img);
            name = itemView.findViewById(R.id.search_map_item_name);
            description = itemView.findViewById(R.id.search_map_item_desc);
            type = itemView.findViewById(R.id.search_map_item_type);
            constraintLayout = itemView.findViewById(R.id.constraintLayoutSearchMap);
        }

        public void populateEvent(Event event){
            Picasso.with(context).load(event.getEvent_photo()).into(banner);name.setText(event.getEvent_name());
            description.setText(event.getEvent_desc());type.setText("Event");
            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context , EventDetailsActivity.class).putExtra("event_id" , event.getEvent_id()));
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return storeList.size() + eventList.size();
    }

}
