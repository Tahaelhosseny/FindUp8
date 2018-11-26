package findupproducts.example.com.findup.UI.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.R;

public class StoreSettingsActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_storeEditProfile, btn_storeNotifications,  btn_currency,
            btn_storeInviteFriend, btn_storeContactUs, btn_storeTerms, btn_storeSettingsBack,btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_settings);

        btn_storeEditProfile = findViewById(R.id.btn_storeEditProfile);
        btn_storeEditProfile.setOnClickListener(this);

        btn_currency = findViewById(R.id.btn_storeCurrency);
        btn_currency.setOnClickListener(this);

        btn_storeNotifications = findViewById(R.id.btn_storeNotifications);
        btn_storeNotifications.setOnClickListener(this);

        btn_storeInviteFriend = findViewById(R.id.btn_storeInviteFriend);
        btn_storeInviteFriend.setOnClickListener(this);

        btn_storeContactUs = findViewById(R.id.btn_storeContactUs);
        btn_storeContactUs.setOnClickListener(this);

        btn_storeTerms = findViewById(R.id.btn_storeTerms);
        btn_storeTerms.setOnClickListener(this);

        btn_storeSettingsBack = findViewById(R.id.btn_storeSettingsBack);
        btn_storeSettingsBack.setOnClickListener(this);

        btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_storeEditProfile:
                startActivity(new Intent(StoreSettingsActivity.this, EditProfileActivity.class));
                break;

            case R.id.btn_storeCurrency:
                startActivity(new Intent(StoreSettingsActivity.this, CurrencyActivity.class));
                break;

            case R.id.btn_storeNotifications:
                startActivity(new Intent(StoreSettingsActivity.this, StoreNotificationSettActivity.class));
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
//                LogoutAccepted();
                finish();
                break;

            case R.id.btn_logout:
                LogoutAccepted();
                startActivity(new Intent(StoreSettingsActivity.this, IntroActivity.class));
                finish();
                break;
        }
    }

    public void LogoutAccepted(){
        SharedPrefManger.setIsLoggedIn(false);
        SharedPrefManger.setLogin_phone("");
        SharedPrefManger.setLogin_password("");
        SharedPrefManger.setStoreID(0);
        SharedPrefManger.setIsLoggedInAsCustomer(false);
        SharedPrefManger.setLoginType("");
        SharedPrefManger.setStore_namee("");
        SharedPrefManger.setStore_banner("");
        SharedPrefManger.setStore_logo("");
    }
}