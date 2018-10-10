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
import khaled.example.com.findup.UI.activities.EventDetailsActivity;
import khaled.example.com.findup.models.Event;

/**
 * Created by khaled on 7/4/18.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private List<Event> events;
    private Context context;

    public EventsAdapter(Context context, List<Event> events) {
        this.context = context;
        this.events = events;
    }

    @NonNull
    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_item, parent, false);
        return new EventsAdapter.ViewHolder(itemView);
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public void onBindViewHolder(@NonNull EventsAdapter.ViewHolder holder, int position) {

        holder.event = events.get(position);
        holder.eventName.setText(holder.event.getEvent_name());
        holder.eventDescription.setText(holder.event.getEvent_desc());
        holder.eventDate.setText(holder.event.getEvent_start_date());
        if (!holder.event.getEvent_photo().isEmpty())
            Picasso.with(holder.eventsItemImg.getContext()).load(holder.event.getEvent_photo()).placeholder(R.color.material_color_grey_500).into(holder.eventsItemImg);

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Event event;
        TextView eventName;
        TextView eventDate;
        TextView eventDescription;
        ImageView eventsItemImg;

        public ViewHolder(View view) {
            super(view);
            eventName = view.findViewById(R.id.event_name);
            eventDescription = view.findViewById(R.id.eventDesc);
            eventDate = view.findViewById(R.id.eventDate);
            eventsItemImg = view.findViewById(R.id.eventsItemImg);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(context, EventDetailsActivity.class);
            i.putExtra("event_id",event.getEvent_id());
            context.startActivity(i);
        }
    }
}
