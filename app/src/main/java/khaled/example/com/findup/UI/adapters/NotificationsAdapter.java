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

import com.squareup.picasso.Picasso;

import java.util.List;

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.activities.NotificationsActivity;
import khaled.example.com.findup.models.Notification;

/**
 * Created by khaled on 8/1/18.
 */

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {
    private List<Notification> notifications;
    private Context context;

    public NotificationsAdapter(Context context, List<Notification> notifications) {
        this.context = context;
        this.notifications = notifications;
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

        Notification notification = notifications.get(position);
        holder.notificationType.setText(notification.getNotificationType());
        holder.notificationDesc.setText(notification.getNotificationDesc());
        holder.notificationDate.setText(notification.getNotificationDate());
        if (!notification.getNotificationImg().isEmpty())
            Picasso.with(context).load(notification.getNotificationImg()).placeholder(R.drawable.ic_launcher).into(holder.notificationImage);

    }

    @Override
    public int getItemCount() {
        return notifications.size();
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
            v.getContext().startActivity(new Intent(v.getContext(), NotificationsActivity.class).putExtra("sysMSG", 1));
        }
    }
}
