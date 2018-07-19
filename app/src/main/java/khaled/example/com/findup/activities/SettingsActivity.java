package khaled.example.com.findup.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import khaled.example.com.findup.R;

public class SettingsActivity extends AppCompatActivity {
    
    Button btn_editProfile, btn_notifications, btn_currency, btn_measureDistance, btn_langauge,
            btn_inviteFriend, btn_contactUs, btn_terms, btn_settingsBack;
    ImageButton btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setSupportActionBar((android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_settings));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        btn_editProfile=findViewById(R.id.btn_editProfile);
        btn_editProfile.setOnClickListener(this);

        btn_notifications=findViewById(R.id.btn_notifications);
        btn_notifications.setOnClickListener(this);

        btn_currency=findViewById(R.id.btn_currency);
        btn_currency.setOnClickListener(this);

        btn_measureDistance=findViewById(R.id.btn_measureDistance);
        btn_measureDistance.setOnClickListener(this);

        btn_langauge=findViewById(R.id.btn_langauge);
        btn_langauge.setOnClickListener(this);

        btn_inviteFriend=findViewById(R.id.btn_inviteFriend);
        btn_inviteFriend.setOnClickListener(this);

        btn_contactUs=findViewById(R.id.btn_contactUs);
        btn_contactUs.setOnClickListener(this);

        btn_terms=findViewById(R.id.btn_terms);
        btn_terms.setOnClickListener(this);

        btn_settingsBack=findViewById(R.id.btn_settingsBack);
        btn_settingsBack.setOnClickListener(this);

        btn_logout=findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_editProfile:
                startActivity(new Intent(Settings.this, EditProfileActivity.class));
                break;

            case R.id.btn_notifications:
                startActivity(new Intent(Settings.this, NotificationsActivity.class));
                break;

            case R.id.btn_currency:
                startActivity(new Intent(Settings.this, CurrencyActivity.class));
                break;

            case R.id.btn_measureDistance:
                startActivity(new Intent(Settings.this, MeasureDistanceActivity.class));
                break;

            case R.id.btn_langauge:
                Toast.makeText(Settings.this, "langauge", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_inviteFriend:
                Toast.makeText(Settings.this, "invite", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_contactUs:
                Toast.makeText(Settings.this, "contact", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_terms:
                Toast.makeText(Settings.this, "terms", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_settingsBack:
                startActivity(new Intent(Settings.this, Intro.class));
                finish();
                break;

            case R.id.btn_logout:
                startActivity(new Intent(Settings.this, SplashScreen.class));
                finish();
                break;
    }
}
