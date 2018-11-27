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

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.activities.NotificationsActivity;
import findupproducts.example.com.findup.models.NotificationStore;
import findupproducts.example.com.findup.models.NotificationUser;
import findupproducts.example.com.findup.models.UserSavedItem;

/**
 * Created by khaled on 8/1/18.
 */

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {
    private List<NotificationUser> notifications;
    private Context context;
    private List<NotificationStore> notificationStoreList;
    public NotificationsAdapter(Context context, List<NotificationUser> notifications) {
        this.context = context;
        this.notifications = notifications;
    }
    public NotificationsAdapter(List<NotificationStore> notificationStoreList , Context context){
        this.context = context;
        this.notificationStoreList = notificationStoreList;
    }
    public void setNotificationUser(List<NotificationUser> notificationUser) {
        this.notifications = notificationUser;
        notifyDataSetChanged();
    }
    public void setNotificationStore(List<NotificationStore> notificationStore) {
        this.notificationStoreList = notificationStore;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotificationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_item, parent, false);
        return new NotificationsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsAdapter.ViewHolder holder, int position) {
        if(SharedPrefManger.getUser_ID() != 0 && SharedPrefManger.getStore_ID() == 0){
            NotificationUser notification = notifications.get(position);
            holder.notificationType.setText(notification.getNotificationTitle());
            holder.notificationDesc.setText(notification.getNotificationDesc());
            holder.notificationDate.setText(notification.getNotificationDate());
            if (!notification.getNotificationImg().isEmpty()) {
                Transformation transformation = new RoundedTransformationBuilder()
                        .cornerRadiusDp(80)
                        .oval(false)
                        .build();
                Picasso.with(holder.notificationImage.getContext()).load(notification.getNotificationImg()).transform(transformation).placeholder(R.drawable.near_by_place_holder).into(holder.notificationImage);
            }
        }else{
            NotificationStore notification = notificationStoreList.get(position);
            holder.notificationType.setText(notification.getNotificationTitle());
            holder.notificationDesc.setText(notification.getNotificationDesc());
            holder.notificationDate.setText(notification.getNotificationDate());
            if (!notification.getNotificationImg().isEmpty())
                Picasso.with(context).load(notification.getNotificationImg()).placeholder(R.mipmap.ic_launcher).into(holder.notificationImage);
        }

    }

    @Override
    public int getItemCount() {
        if(SharedPrefManger.getStore_ID() != 0){
            return notificationStoreList.size();
        }else{
            return notifications.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView notificationImage;
        TextView notificationType;
        TextView notificationDesc;
        TextView notificationDate;
        ImageView delNotificationBtn;

        public ViewHolder(View view) {
            super(view);
            notificationImage = view.findViewById(R.id.notificationItemImg);
            notificationType = view.findViewById(R.id.notificationItemTypeTxt);
            notificationDesc = view.findViewById(R.id.notificationItemDesc);
            notificationDate = view.findViewById(R.id.notificationItemDate);
            delNotificationBtn = view.findViewById(R.id.delNotificationBtn);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
//            v.getContext().startActivity(new Intent(v.getContext(), NotificationsActivity.class).putExtra("sysMSG", 1));
        }
    }
}
