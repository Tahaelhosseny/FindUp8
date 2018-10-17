package khaled.example.com.findup.UI.activities;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;

import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.Presenter.Activities.UserSettings.MessureDistancePresenter;
import khaled.example.com.findup.UI.ViewModel.Activites.EditProfileViewModel;
import khaled.example.com.findup.UI.ViewModel.Activites.MessureDistanceViewModel;
import khaled.example.com.findup.databinding.ActivityMeasureDistanceBinding;

public class MeasureDistanceActivity extends Activity {
    ActivityMeasureDistanceBinding binding;
    MessureDistanceViewModel viewModel;
    Button btn_distanceBack, btn_submit;
    int distance_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new MessureDistanceViewModel(this);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_measure_distance);
        RadioButton mile = findViewById(R.id.mile_radio);
        RadioButton km = findViewById(R.id.km_radio);
        if(SharedPrefManger.getDistanceTypeId() == 1){
            km.setChecked(true);mile.setChecked(false);
        }else if(SharedPrefManger.getDistanceTypeId() == 2){
            km.setChecked(false);mile.setChecked(true);
        }else{
            km.setChecked(false);mile.setChecked(false);
        }
        binding.setMessureDistance(viewModel);
        binding.radioDistance.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.mile_radio:
                        distance_id = 2;
                        break;
                    case R.id.km_radio:
                        distance_id = 1;
                        break;
                }
            }
        });
        binding.setPresenter(new MessureDistancePresenter() {
            @Override
            public void setMessureDistance() {
                    if(distance_id == 0){
                        Toast.makeText(MeasureDistanceActivity.this, "Please Select Measure type", Toast.LENGTH_SHORT).show();
                    }else{
                        if((distance_id == 1 && SharedPrefManger.getDistanceTypeId() == 1) || (distance_id == 2 && SharedPrefManger.getDistanceTypeId() == 2)){
                            Toast.makeText(MeasureDistanceActivity.this, "Already Selected this type", Toast.LENGTH_SHORT).show();
                        }else{
                            viewModel.setUserMessureDistance(SharedPrefManger.getUser_ID() , distance_id);
                        }
                    }

            }
        });
        btn_distanceBack = findViewById(R.id.btn_distanceBack);
        btn_distanceBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
