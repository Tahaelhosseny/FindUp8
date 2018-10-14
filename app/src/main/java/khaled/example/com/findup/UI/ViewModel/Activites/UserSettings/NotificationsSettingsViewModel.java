package khaled.example.com.findup.UI.ViewModel.Activites.UserSettings;

import android.content.Context;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.Observable;

import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.NotificationFlagResponse;
import khaled.example.com.findup.Helper.SharedPrefManger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsSettingsViewModel extends Observable {
    Context mContext;

    public NotificationsSettingsViewModel(Context mContext) {
        this.mContext = mContext;
    }

    public void BindCheckBox(CheckBox checkBox_pushNotifications, CheckBox checkBox_chatsNotifications){
        checkBox_pushNotifications.setOnCheckedChangeListener((v,b)-> ChangeNotificationSettings(checkBox_pushNotifications,checkBox_chatsNotifications));
        checkBox_chatsNotifications.setOnCheckedChangeListener((v,b)-> ChangeNotificationSettings(checkBox_pushNotifications,checkBox_chatsNotifications));
    }

    public void ChangeNotificationSettings(CheckBox checkBox_pushNotifications, CheckBox checkBox_chatsNotifications){
        SharedPrefManger sharedPrefManger = new SharedPrefManger(mContext);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<NotificationFlagResponse> call = apiService.setNotificationFlags(sharedPrefManger.getUser_ID(),((checkBox_pushNotifications.isChecked())?1:0),((checkBox_pushNotifications.isChecked())?1:0));
        call.enqueue(new Callback<NotificationFlagResponse>() {
            @Override
            public void onResponse(Call<NotificationFlagResponse> call, Response<NotificationFlagResponse> response) {
                int chat = 0;int push = 0;
                if(response.body().getSuccess() == 1){
                    if(checkBox_chatsNotifications.isChecked()){
                        SharedPrefManger.setChatNotiFlag(1);
                        chat = 1;
                    }
                    if(checkBox_pushNotifications.isChecked()){
                        SharedPrefManger.setPushNotiFlag(1);
                        push = 1;
                    }
//                SharedPrefManger.setPushNotiFlag(push);
//                SharedPrefManger.setChatNotiFlag(chat);
                    Toast.makeText(mContext, "Push "+push, Toast.LENGTH_SHORT).show();
                    Toast.makeText(mContext, "Chat "+chat, Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(mContext, ""+response.body().getError_msg(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<NotificationFlagResponse> call, Throwable t) {
                Log.e("url",call.request().url().toString());
                Log.e("Passed",sharedPrefManger.getUser_ID()+" - "+((checkBox_pushNotifications.isChecked())?1:0)+" - "+((checkBox_pushNotifications.isChecked())?1:0));
            }
        });
    }
}
