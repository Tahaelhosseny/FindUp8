package khaled.example.com.findup.UI.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.willy.ratingbar.RotationRatingBar;

import khaled.example.com.findup.R;

public class RatingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        final RotationRatingBar ratingBar = findViewById(R.id.ratingBar);
        Button btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float currentRating = ratingBar.getRating();
                Toast.makeText(RatingActivity.this, "Current Rating is "+currentRating, Toast.LENGTH_SHORT).show();
            }
        });
    }
}