package findupproducts.example.com.findup.UI.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.R;

public class WorkDaysActivity extends Activity {

    Button btn_distanceBack, btn_submit;
    List<String> listOfDays;
    CheckBox[] days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.week_days_bottom_sheet);

        listOfDays = new ArrayList<>();

        days = new CheckBox[8];
        days[0] = findViewById(R.id.all);
        days[1] = findViewById(R.id.saturday);
        days[2] = findViewById(R.id.sunday);
        days[3] = findViewById(R.id.monday);
        days[4] = findViewById(R.id.tuesday);
        days[5] = findViewById(R.id.wednesday);
        days[6] = findViewById(R.id.thursday);
        days[7] = findViewById(R.id.friday);

        days[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (days[0].isChecked()){
                    for (CheckBox checkBox : days){
                        checkBox.setChecked(true);
                    }
                } else {
                    for (CheckBox checkBox : days){
                        checkBox.setChecked(false);
                    }
                }
            }
        });

        btn_submit = findViewById(R.id.btn_submit);
        btn_distanceBack = findViewById(R.id.btn_distanceBack);

        btn_distanceBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listOfDays.clear();
                for (int i = 0;i < days.length;i++){
                    if (days[i].isChecked())
                        listOfDays.add(days[i].getText().toString());
                }
                Intent resultIntent = new Intent();
                resultIntent.putExtra("days", listOfDays.toArray(new String[0]));
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
