package findupproducts.example.com.findup.UI.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.fragments.ChatWithStoreFragment;

public class ChatWithStoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_with_genuine);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.chat_fragment_container, new ChatWithStoreFragment()).commit();
    }
}
