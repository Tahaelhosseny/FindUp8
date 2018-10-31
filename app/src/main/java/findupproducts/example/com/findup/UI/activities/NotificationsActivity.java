package findupproducts.example.com.findup.UI.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.fragments.NotificationsFragment;
import findupproducts.example.com.findup.UI.fragments.SystemMsgFragment;

/**
 * Created by khaled on 8/1/18.
 */

public class NotificationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        Button back_btn = findViewById(R.id.btn_notificationsBack);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (getIntent().hasExtra("sysMSG")) {
            transaction.replace(R.id.notification_fragment_container, new SystemMsgFragment()).commit();
            //back_btn.setBackground(this.getResources().getDrawable(R.drawable.baseline_close_black_36));
            back_btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_close_black_36, 0, 0, 0);

            back_btn.setText(this.getResources().getString(R.string.system_message));
        } else {
            transaction.replace(R.id.notification_fragment_container, new NotificationsFragment()).commit();
            //back_btn.setBackground(this.getResources().getDrawable(R.drawable.baseline_keyboard_arrow_left_black_48dp));
            back_btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_keyboard_arrow_left_black_48dp, 0, 0, 0);
            back_btn.setText(this.getResources().getString(R.string.profile_notification));
        }


    }
}
