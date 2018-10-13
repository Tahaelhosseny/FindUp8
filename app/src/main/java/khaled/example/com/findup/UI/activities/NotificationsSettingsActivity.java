package khaled.example.com.findup.UI.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import khaled.example.com.findup.R;
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
