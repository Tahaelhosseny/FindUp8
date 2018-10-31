package findupproducts.example.com.findup.UI.ViewModel.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.Flowable;
import findupproducts.example.com.findup.Helper.Database.DBHandler;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Notifications.NotificationsStoreI;
import findupproducts.example.com.findup.Helper.Database.Interfaces.Notifications.NotificationsUserI;
import findupproducts.example.com.findup.Helper.Database.Interfaces.SavedItem.SavedItem;
import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.NotificationResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.Helper.UI_Utility;
import findupproducts.example.com.findup.UI.activities.SettingsActivity;
import findupproducts.example.com.findup.UI.adapters.CommentsAdapter;
import findupproducts.example.com.findup.UI.adapters.NotificationsAdapter;
import findupproducts.example.com.findup.UI.adapters.UserSavedAdapter;
import findupproducts.example.com.findup.models.NotificationStore;
import findupproducts.example.com.findup.models.NotificationUser;
import findupproducts.example.com.findup.models.UserSavedItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserNotificatonViewModel extends Observable {
    private Context mContext;
    List<NotificationUser> notificationUsers = new ArrayList<>();
    List<NotificationStore> notificationStore = new ArrayList<>();

    public UserNotificatonViewModel(Context mContext){this.mContext = mContext;}
    public void InitRecycler(RecyclerView recyclerView){
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        NotificationsAdapter adapter = new NotificationsAdapter(mContext, notificationUsers);
        NotificationsAdapter adapterStore = new NotificationsAdapter(notificationStore , mContext);
        if(SharedPrefManger.getStore_ID() != 0 && SharedPrefManger.getUser_ID() == 0){
            LoadNotificationStoreFromDatabase(adapterStore);
            recyclerView.setAdapter(adapterStore);

        }else{
            LoadNotificationUserFromDatabase(adapter);
            recyclerView.setAdapter(adapter);

        }
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerView.smoothScrollToPosition(0);
    }
    private void LoadNotificationUserFromDatabase(NotificationsAdapter adapter){
        DBHandler.getAllUserNotification(mContext, new NotificationsUserI() {
            @Override
            public void onSuccess(Flowable<List<NotificationUser>> listFlowable) {
                listFlowable.subscribe(
                        val -> {
                            ((Activity) mContext).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.setNotificationUser(val);
                                    adapter.notifyDataSetChanged();
                                }
                            });
                        },
                        err -> Log.i("database err", "store database error : " + err.getMessage())
                );
            }
        });
    }

    private void LoadNotificationStoreFromDatabase(NotificationsAdapter adapter){
        DBHandler.getAllStoreNotification(mContext, new NotificationsStoreI() {
            @Override
            public void onSuccess(Flowable<List<NotificationStore>> listFlowable) {
                listFlowable.subscribe(
                        val -> {
                            ((Activity) mContext).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.setNotificationStore(val);
                                    adapter.notifyDataSetChanged();
                                }
                            });
                        },
                        err -> Log.i("database err", "store database error : " + err.getMessage())
                );
            }
        });
    }
}
