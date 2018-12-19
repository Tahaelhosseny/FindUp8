package findupproducts.example.com.findup.UI.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.activities.EventDetailsActivity;
import findupproducts.example.com.findup.models.Event;
import findupproducts.example.com.findup.netHelper.MakeRequest;
import findupproducts.example.com.findup.netHelper.OnCancelRetry;
import findupproducts.example.com.findup.netHelper.VolleyCallback;

/**
 * Created by khaled on 7/4/18.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {
    private List<Event> events;
    private Context context;
    private Activity activity ;
    public EventsAdapter(Activity context, List<Event> events) {
        this.context = context;
        this.events = events;
        activity = context ;
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


        if(holder.event.getIf_saved().equals("0"))
            holder.addToFavorite.setImageResource(R.drawable.like_hert);
        else
            holder.addToFavorite.setImageResource(R.drawable.likeed);

        holder.addToFavorite.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(holder.event.getIf_saved().equals("1"))
                    deleteFromSave(holder.event.getEvent_id() , holder , position);
                else addToSave(holder.event.getEvent_id() ,holder);
            }
        });


        if (!holder.event.getEvent_photo().isEmpty()) {
            Transformation transformation = new RoundedTransformationBuilder().cornerRadiusDp(0, 5)
                    .cornerRadiusDp(1, 5)
                    .oval(false)
                    .build();
            Picasso.with(context).load(events.get(position).getEvent_photo()).transform(transformation).placeholder(R.drawable.near_by_place_holder).into(holder.eventsItemImg);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context , EventDetailsActivity.class).putExtra("event_id" , events.get(position).getEvent_id()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        Event event;
        TextView eventName;
        TextView eventDate;
        TextView eventDescription;
        ImageView eventsItemImg;
        CardView cardView;
        ImageView addToFavorite ;


        public ViewHolder(View view) {
            super(view);
            eventName = view.findViewById(R.id.event_name);
            eventDescription = view.findViewById(R.id.eventDesc);
            eventDate = view.findViewById(R.id.eventDate);
            eventsItemImg = view.findViewById(R.id.eventsItemImg);
            cardView = view.findViewById(R.id.main_event_constarint);
            addToFavorite = view.findViewById(R.id.addToFavorite);
        }


    }





    private void addToSave(String saved_id  , ViewHolder holder)
    {
        Map<String , String> map = new HashMap<String , String >() ;

        map.put("account_id" ,SharedPrefManger.getUser_ID()+"");
        map.put("saved_id" ,saved_id+"");
        map.put("saved_type" ,SharedPrefManger.getLogin_type());


        Activity activityApi = activity;

        MakeRequest makeRequest = new MakeRequest("http://findupproducts.com/findup_api/user_actions?tag=add_to_save&HashSecure=FindUpSecure_@@01072018" ,"1" , map,activityApi , "addToSave" ,true);
        makeRequest.request(new VolleyCallback() {
            @Override
            public void onSuccess(Map<String, String> result)
            {
                try {
                    JSONObject jsonObject = new JSONObject(result.get("res").toString());
                    if(jsonObject.getInt("success") == 1)
                    {
                        if(new JSONArray(jsonObject.getJSONArray("data").toString()).getJSONObject(0).getString("save_case").equals("saved"))
                        {
                            Picasso.with(activityApi).load(R.drawable.likeed).into(holder.addToFavorite);
                            holder.event.setIf_saved("1");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new OnCancelRetry() {
            @Override
            public void OnCacelRetry()
            {

            }
        });

    }


    private void deleteFromSave(String saved_id  , ViewHolder holder , int position)
    {
        Map<String , String> map = new HashMap<String , String >() ;

        map.put("account_id" ,SharedPrefManger.getUser_ID()+"");
        map.put("saved_id" ,saved_id+"");
        map.put("saved_type" ,SharedPrefManger.getLogin_type());


        Activity activityApi = activity;

        MakeRequest makeRequest = new MakeRequest("http://findupproducts.com/findup_api/user_actions?tag=delete_save&HashSecure=FindUpSecure_@@01072018" ,"1" , map,activityApi , "addToSave" ,true);
        makeRequest.request(new VolleyCallback() {
            @Override
            public void onSuccess(Map<String, String> result)
            {
                try {
                    JSONObject jsonObject = new JSONObject(result.get("res").toString());
                    if(jsonObject.getInt("success") == 1)
                    {
                        Picasso.with(activityApi).load(R.drawable.like_hert).into(holder.addToFavorite);
                        holder.event.setIf_saved("0");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new OnCancelRetry() {
            @Override
            public void OnCacelRetry()
            {

            }
        });

    }

}
