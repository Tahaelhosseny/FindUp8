package khaled.example.com.findup.UI.activities;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new MessureDistanceViewModel(this);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_measure_distance);
//        setContentView(R.layout.activity_measure_distance);

        binding.setMessureDistance(viewModel);
        binding.setPresenter(new MessureDistancePresenter() {
            @Override
            public void setMessureDistance() {
                // Your Method
            }
        });
        String distance = SharedPrefManger.getDistanceText();
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
