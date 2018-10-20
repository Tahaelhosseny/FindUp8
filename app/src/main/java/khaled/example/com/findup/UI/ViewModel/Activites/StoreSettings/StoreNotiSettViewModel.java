package khaled.example.com.findup.UI.ViewModel.Activites.StoreSettings;

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

public class StoreNotiSettViewModel extends Observable {
    private Context mContext;

    public StoreNotiSettViewModel(Context mContext) {
        this.mContext = mContext;
    }

    public void BindCheckBox(CheckBox checkBox_pushNotifications, CheckBox checkBox_chatsNotifications ,CheckBox checkBox_likeNotifications, CheckBox checkBox_commentNotifications){
        checkBox_pushNotifications.setOnCheckedChangeListener((v,b)-> ChangeNotificationSettings(checkBox_pushNotifications,checkBox_chatsNotifications , checkBox_likeNotifications , checkBox_commentNotifications));
        checkBox_chatsNotifications.setOnCheckedChangeListener((v,b)-> ChangeNotificationSettings(checkBox_pushNotifications,checkBox_chatsNotifications , checkBox_likeNotifications , checkBox_commentNotifications));
        checkBox_likeNotifications.setOnCheckedChangeListener((v,b)-> ChangeNotificationSettings(checkBox_pushNotifications,checkBox_chatsNotifications , checkBox_likeNotifications , checkBox_commentNotifications));
        checkBox_commentNotifications.setOnCheckedChangeListener((v,b)-> ChangeNotificationSettings(checkBox_pushNotifications,checkBox_chatsNotifications , checkBox_likeNotifications , checkBox_commentNotifications));
    }
    public void ChangeNotificationSettings(CheckBox checkBox_pushNotifications, CheckBox checkBox_chatsNotifications
            , CheckBox checkBox_likeNotifications, CheckBox checkBox_commentNotifications){
        SharedPrefManger sharedPrefManger = new SharedPrefManger(mContext);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<NotificationFlagResponse> call = apiService.setStoreNotificationFlag(((checkBox_pushNotifications.isChecked())?1:0),((checkBox_chatsNotifications.isChecked())?1:0)
          , ((checkBox_likeNotifications.isChecked())?1:0)  ,((checkBox_commentNotifications.isChecked())?1:0) , SharedPrefManger.getStore_ID());
        call.enqueue(new Callback<NotificationFlagResponse>() {
            @Override
            public void onResponse(Call<NotificationFlagResponse> call, Response<NotificationFlagResponse> response) {
                if(response.body().getSuccess() == 1){
                    int noti = (checkBox_pushNotifications.isChecked())?1:0;
                    SharedPrefManger.setPushNotiFlag(noti);
                    int chat = (checkBox_chatsNotifications.isChecked())?1:0;
                    SharedPrefManger.setChatNotiFlag(chat);
                    int like = (checkBox_likeNotifications.isChecked())?1:0;
                    SharedPrefManger.setLikesStoreNoti(like);
                    int comment = (checkBox_commentNotifications.isChecked())?1:0;
                    SharedPrefManger.setStoreCommentsNoti(comment);
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
