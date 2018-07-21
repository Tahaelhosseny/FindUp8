package com.android.findup;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MeasureDistanceActivity extends Activity {

    Button btn_distanceBack, btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure_distance);

        btn_submit=findViewById(R.id.btn_submit);
        btn_distanceBack=findViewById(R.id.btn_distanceBack);

        btn_distanceBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MeasureDistanceActivity.this, "Submit", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return super.onKeyDown(keyCode, event);
        }
        return true;
    }
}
