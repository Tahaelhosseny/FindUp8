package khaled.example.com.findup.UI.ViewModel.Fragments;

import android.content.Context;
import android.provider.ContactsContract;
import android.widget.ImageView;
import android.widget.TextView;

public class EventDataViewModel {
    private Context mContext;
    int event_id;
    public EventDataViewModel(Context mContext , int event_id){
        this.mContext = mContext; this.event_id = event_id;
    }

    public void BindUI(TextView event_name , TextView store_name , ImageView event_banner ){


    }

}
