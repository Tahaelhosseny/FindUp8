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

    @Override
    public void onBindViewHolder(@NonNull EventsAdapter.ViewHolder holder, int position) {

        /*Event event = events.get(position);
        holder.eventName.setText(event.getEvent_name());
        holder.eventDescription.setText(event.getEvent_desc());
        holder.eventDate.setText(event.getEvent_start_date());
        if (!event.getEvent_photo().isEmpty())
            Picasso.with(holder.eventsItemImg.getContext()).load(event.getEvent_photo()).placeholder(R.drawable.events_place_holder).into(holder.eventsItemImg);*/

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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
        }
    }
}
