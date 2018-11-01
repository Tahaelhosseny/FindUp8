package findupproducts.example.com.findup.UI.ViewModel.Fragments;

import android.app.Activity;
import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.Flowable;
import findupproducts.example.com.findup.Helper.Database.DBHandler;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Events;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Store.Stores;
import findupproducts.example.com.findup.models.Event;
import findupproducts.example.com.findup.models.Store;

public class EventDataViewModel {
    private Context mContext;
    String event_id;
    public static Event event;
    public EventDataViewModel(Context mContext , String  event_id){
        this.mContext = mContext; this.event_id = event_id;
    }

    public void BindUI(TextView event_name , TextView store_name , ImageView event_banner , TextView date , TextView location , TextView price ,
                       TextView about){
        DBHandler.getEventByEventID(Integer.parseInt(event_id), mContext, new Events() {
            @Override
            public void onSuccess(Flowable<List<Event>> listFlowable) {
                listFlowable.subscribe(val ->{
                    ((Activity) mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            event = val.get(0);
                            for (int i = 0 ; i < val.size() ; i++){
                                event_name.setText(val.get(i).getEvent_name());
                                DBHandler.getStoreByID(Integer.parseInt(val.get(i).getStore_id()), mContext, new Stores() {
                                    @Override
                                    public void onSuccess(Flowable<List<Store>> listFlowable2) {
                                    }

                                    @Override
                                    public void getStoreID(Flowable<Store> storeFlowable) {
                                        storeFlowable.subscribe(val->{
                                            ((Activity) mContext).runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    store_name.setText(val.getStore_name());
                                                }
                                            });
                                        });
                                    }

                                    @Override
                                    public void onFail() {

                                    }
                                });
                                date.setText(val.get(i).getEvent_start_date());
                                location.setText(val.get(i).getEvent_address());
                                price.setText(val.get(i).getEvent_cost());
                                about.setText(val.get(i).getEvent_desc());
                                Picasso.with(mContext).load(val.get(i).getEvent_photo()).into(event_banner);
                            }
                        }
                    });

                });
            }

            @Override
            public void onFail() {

            }
        });
    }

}
