package khaled.example.com.findup.UI.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.Presenter.Activities.SplashScreenPresenter;
import khaled.example.com.findup.UI.ViewModel.Activites.SplashScreenViewModel;
import khaled.example.com.findup.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {

    ActivitySplashScreenBinding binding;
    SplashScreenViewModel splashScreenViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash_screen);
        splashScreenViewModel = new SplashScreenViewModel(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);
        binding.setLoginview(splashScreenViewModel);
        splashScreenViewModel.UpdateStoresData();

        binding.setPresenter(new SplashScreenPresenter() {
            @Override
            public void LoadStoreData() {
                splashScreenViewModel.UpdateStoresData();
            }
        });

    }
}
