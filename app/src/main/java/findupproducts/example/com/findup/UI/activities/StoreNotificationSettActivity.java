package findupproducts.example.com.findup.UI.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.ViewModel.Activites.StoreSettings.StoreNotiSettViewModel;
import findupproducts.example.com.findup.UI.ViewModel.Activites.UserSettings.NotificationsSettingsViewModel;
import findupproducts.example.com.findup.databinding.ActivityStoreNotificationSettBinding;

public class StoreNotificationSettActivity extends AppCompatActivity {
    Button backToSetting;
    ActivityStoreNotificationSettBinding binding;
    StoreNotiSettViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new StoreNotiSettViewModel(this);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_store_notification_sett);
        int chatnoti= SharedPrefManger.getChatNotiFlagStore();
        int notiPust = SharedPrefManger.getPushNotiFlagStore();
        int likeNotiFlag = SharedPrefManger.getLikeStoreNoti();
        int comment = SharedPrefManger.getStoreCommentsNoti();
        if(chatnoti != 0){
            binding.checkBoxChatsNotifications.setChecked(true);
        }
        if(notiPust != 0){
            binding.checkBoxPushNotifications.setChecked(true);
        }
        if(likeNotiFlag != 0){
            binding.checkBoxLikesNotifications.setChecked(true);
        }
        if(comment != 0){
            binding.checkBoxCommentsNotifications.setChecked(true);
        }
        Toast.makeText(this, ""+SharedPrefManger.getStore_ID(), Toast.LENGTH_SHORT).show();
        viewModel.BindCheckBox(
                binding.checkBoxPushNotifications ,
                binding.checkBoxChatsNotifications,
                binding.checkBoxLikesNotifications,
                binding.checkBoxCommentsNotifications
        );
        backToSetting = findViewById(R.id.btn_notificationsBack);
        backToSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StoreNotificationSettActivity.this , StoreSettingsActivity.class));finish();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(StoreNotificationSettActivity.this , StoreSettingsActivity.class));finish();
    }
}
