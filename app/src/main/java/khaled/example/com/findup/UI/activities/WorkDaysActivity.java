package khaled.example.com.findup.UI.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import khaled.example.com.findup.R;

public class WorkDaysActivity extends Activity {

    Button btn_distanceBack, btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_days_bottom_sheet);

        final RadioButton[] days = new RadioButton[8];
        days[0] = findViewById(R.id.all);
        days[1] = findViewById(R.id.saturday);
        days[2] = findViewById(R.id.sunday);
        days[3] = findViewById(R.id.monday);
        days[4] = findViewById(R.id.tuesday);
        days[5] = findViewById(R.id.wednesday);
        days[6] = findViewById(R.id.thursday);
        days[7] = findViewById(R.id.friday);


        View.OnClickListener radioOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.all:
                        for (RadioButton radioButton : days)
                            radioButton.setChecked(true);
                        break;

                    default:
                        ((RadioButton) v).setChecked(!((RadioButton) v).isChecked());
                        break;
                }
//                mBottomSheetDialog.dismiss();
            }
        };
        for (RadioButton radioButton : days){
            radioButton.setOnClickListener(radioOnClick);
        }

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
                Toast.makeText(WorkDaysActivity.this, "Submit", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
