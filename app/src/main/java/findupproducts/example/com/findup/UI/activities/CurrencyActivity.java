package findupproducts.example.com.findup.UI.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.Presenter.Activities.CurrencyPresenter;
import findupproducts.example.com.findup.UI.ViewModel.Activites.CurrencyViewModel;
import findupproducts.example.com.findup.databinding.ActivityCurrencyBinding;

public class CurrencyActivity extends AppCompatActivity {

    ActivityCurrencyBinding activityCurrencyBinding;
    CurrencyViewModel currencyViewModel;
    Button btn_currencyBack, btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currencyViewModel = new CurrencyViewModel(this);
        activityCurrencyBinding= DataBindingUtil.setContentView(this,R.layout.activity_currency);

        activityCurrencyBinding.setCurrencyOperation(currencyViewModel);
        btn_submit=findViewById(R.id.btn_submit);
        btn_currencyBack=findViewById(R.id.btn_currencyBack);

        btn_currencyBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CurrencyActivity.this, StoreSettingsActivity.class));
                finish();
            }
        });
        currencyViewModel.getAllCurrency(activityCurrencyBinding.currencyAllAdapter);
        activityCurrencyBinding.setPresenter(new CurrencyPresenter() {
            @Override
            public void setUserCurrency() {
                int currency_id = SharedPrefManger.getPermantCurrency();
                currencyViewModel.setCurrency(currency_id , SharedPrefManger.getStore_ID() );
            }
            @Override
            public void LoadAllCurrency() {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CurrencyActivity.this, StoreSettingsActivity.class));
        finish();

    }
}
