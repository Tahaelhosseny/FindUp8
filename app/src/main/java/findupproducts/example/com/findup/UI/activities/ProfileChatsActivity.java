package findupproducts.example.com.findup.UI.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.fragments.ChatStoreFragment;

public class ProfileChatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_chats);

        findViewById(R.id.btn_chatBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.chat_fragment_container, new ChatStoreFragment()).commit();
    }
}
