package khaled.example.com.findup.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import khaled.example.com.findup.R;

public class StoreSettingsActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_storeEditProfile, btn_storeNotifications, btn_storeLanguage,
            btn_storeInviteFriend, btn_storeContactUs, btn_storeTerms, btn_storeSettingsBack;
    ImageButton btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_settings);

        btn_storeEditProfile=findViewById(R.id.btn_storeEditProfile);
        btn_storeEditProfile.setOnClickListener(this);

        btn_storeNotifications=findViewById(R.id.btn_storeNotifications);
        btn_storeNotifications.setOnClickListener(this);

        btn_storeLanguage=findViewById(R.id.btn_storeLanguage);
        btn_storeLanguage.setOnClickListener(this);

        btn_storeInviteFriend=findViewById(R.id.btn_storeInviteFriend);
        btn_storeInviteFriend.setOnClickListener(this);

        btn_storeContactUs=findViewById(R.id.btn_storeContactUs);
        btn_storeContactUs.setOnClickListener(this);

        btn_storeTerms=findViewById(R.id.btn_storeTerms);
        btn_storeTerms.setOnClickListener(this);

        btn_storeSettingsBack=findViewById(R.id.btn_storeSettingsBack);
        btn_storeSettingsBack.setOnClickListener(this);

        btn_logout=findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_storeEditProfile:
                startActivity(new Intent(StoreSettingsActivity.this, EditProfileActivity.class));
                break;

            case R.id.btn_storeNotifications:
                startActivity(new Intent(StoreSettingsActivity.this, NotificationsActivity.class));
                break;

            case R.id.btn_storeLanguage:
                startActivity(new Intent(StoreSettingsActivity.this, LanguagesActivity.class));
                break;

            case R.id.btn_storeInviteFriend:
                Toast.makeText(StoreSettingsActivity.this, "invite", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_storeContactUs:
                Toast.makeText(StoreSettingsActivity.this, "contact", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_storeTerms:
                Toast.makeText(StoreSettingsActivity.this, "terms", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_storeSettingsBack:
                startActivity(new Intent(StoreSettingsActivity.this, IntroActivity.class));
                finish();
                break;

            case R.id.btn_logout:
                startActivity(new Intent(StoreSettingsActivity.this, SplashScreenActivity.class));
                finish();
                break;
        }
    }
}