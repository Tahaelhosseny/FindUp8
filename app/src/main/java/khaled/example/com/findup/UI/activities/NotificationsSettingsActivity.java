package khaled.example.com.findup.UI.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.Presenter.Activities.UserSettings.NotificationsSettingsPresenter;
import khaled.example.com.findup.UI.ViewModel.Activites.UserSettings.NotificationsSettingsViewModel;
import khaled.example.com.findup.databinding.ActivityNotificationsSettingsBinding;

public class NotificationsSettingsActivity extends AppCompatActivity {

    Button btn_notificationsBack;
    ActivityNotificationsSettingsBinding binding;
    NotificationsSettingsViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new NotificationsSettingsViewModel(this);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_notifications_settings);
        int chatnoti= SharedPrefManger.getChatNotiFlag();
        int notiPust = SharedPrefManger.getPushNotiFlag();
        if(chatnoti != 0){
            binding.checkBoxChatsNotifications.setChecked(true);
        }
        if(notiPust != 0){
            binding.checkBoxPushNotifications.setChecked(true);
        }

        viewModel.BindCheckBox(binding.checkBoxPushNotifications,binding.checkBoxChatsNotifications);
        btn_notificationsBack = findViewById(R.id.btn_notificationsBack);
        binding.btnNotificationsBack.setOnClickListener(v -> this.onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
