package khaled.example.com.findup.UI.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import khaled.example.com.findup.R;

public class StoreNotificationSettActivity extends AppCompatActivity {
    Button backToSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_notification_sett);
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
