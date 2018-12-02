package findupproducts.example.com.findup.UI.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.fragments.CommentsFragment;

public class CommentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.comments_framelayout, new CommentsFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CommentsActivity.this , MainActivity.class));
        finish();
    }
}
