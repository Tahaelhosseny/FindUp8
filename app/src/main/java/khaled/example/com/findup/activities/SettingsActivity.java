package khaled.example.com.findup.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import khaled.example.com.findup.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    
    Button btn_editProfile, btn_notifications, btn_currency, btn_measureDistance, btn_language,
            btn_inviteFriend, btn_contactUs, btn_terms, btn_settingsBack;
    ImageButton btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btn_editProfile=findViewById(R.id.btn_editProfile);
        btn_editProfile.setOnClickListener(this);

        btn_notifications=findViewById(R.id.btn_notifications);
        btn_notifications.setOnClickListener(this);

        btn_currency=findViewById(R.id.btn_currency);
        btn_currency.setOnClickListener(this);

        btn_measureDistance=findViewById(R.id.btn_measureDistance);
        btn_measureDistance.setOnClickListener(this);

        btn_language=findViewById(R.id.btn_language);
        btn_language.setOnClickListener(this);

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
                startActivity(new Intent(SettingsActivity.this, EditProfileActivity.class));
                break;

            case R.id.btn_notifications:
                startActivity(new Intent(SettingsActivity.this, NotificationsSettingsActivity.class));
                break;

            case R.id.btn_currency:
                startActivity(new Intent(SettingsActivity.this, CurrencyActivity.class));
                break;

            case R.id.btn_measureDistance:
                startActivity(new Intent(SettingsActivity.this, MeasureDistanceActivity.class));
                break;

            case R.id.btn_language:
                startActivity(new Intent(SettingsActivity.this, LanguagesActivity.class));
                break;

            case R.id.btn_inviteFriend:
                Toast.makeText(SettingsActivity.this, "invite", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_contactUs:
                Toast.makeText(SettingsActivity.this, "contact", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_terms:
                Toast.makeText(SettingsActivity.this, "terms", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_settingsBack:
                super.onBackPressed();
                finish();
                break;

            case R.id.btn_logout:
                startActivity(new Intent(SettingsActivity.this, SplashScreenActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}