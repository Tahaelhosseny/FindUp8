package khaled.example.com.findup.UI.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.fragments.EventsFragment;
import khaled.example.com.findup.UI.fragments.EventsFragments.PlaceholderFragment;
import khaled.example.com.findup.models.TabEntity;

public class StoreEventsActivity extends AppCompatActivity {

    CommonTabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_events);

        Button btn_createEvent = findViewById(R.id.btn_createEvent);
        btn_createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StoreEventsActivity.this, CreateEventActivity.class));
            }
        });

        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        mTabEntities.add(new TabEntity("Upcoming Events"));
        mTabEntities.add(new TabEntity("Old Events"));
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        EventsFragment eventsFragment = new EventsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("behavior", "V");

        eventsFragment.setArguments(bundle);

        fragmentList.add(new PlaceholderFragment());
        fragmentList.add(eventsFragment);
        tabLayout = findViewById(R.id.storeTabs);
        tabLayout.setTabData(mTabEntities, this, R.id.fl_change, fragmentList);
        tabLayout.setIconHeight(0);
        tabLayout.setIconVisible(false);
        //Typeface mTypeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/sfcompactdisplay_semibold.ttf");
        // mTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/sfcompactdisplay_semibold.ttf");

        tabLayout.getTitleView(0).setTypeface(Typeface.create("sfcompactdisplay_semibold", Typeface.NORMAL));
        tabLayout.getTitleView(1).setTypeface(Typeface.create("sfcompactdisplay_heavy", Typeface.NORMAL));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_events, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}