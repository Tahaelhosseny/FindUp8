package khaled.example.com.findup.UI.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.fragments.ChatStoreFragment;
import khaled.example.com.findup.UI.fragments.ChatWithStoreFragment;

public class ProfileChatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_chats);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.chat_fragment_container, new ChatStoreFragment()).commit();
    }
}
