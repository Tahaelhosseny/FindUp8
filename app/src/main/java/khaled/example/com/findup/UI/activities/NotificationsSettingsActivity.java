package khaled.example.com.findup.UI.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import khaled.example.com.findup.R;

public class NotificationsSettingsActivity extends AppCompatActivity {

    Button btn_notificationsBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications_settings);

        btn_notificationsBack = findViewById(R.id.btn_notificationsBack);

        btn_notificationsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
