package findupproducts.example.com.findup.UI.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.Presenter.Activities.CurrencyPresenter;
import findupproducts.example.com.findup.UI.ViewModel.Activites.CurrencyViewModel;
import findupproducts.example.com.findup.databinding.ActivityCurrencyBinding;

public class CurrencyActivity extends AppCompatActivity {

    ActivityCurrencyBinding activityCurrencyBinding;
    CurrencyViewModel currencyViewModel;
    Button btn_currencyBack, btn_submit;
    int type;
    int countryId = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currencyViewModel = new CurrencyViewModel(this);
        activityCurrencyBinding= DataBindingUtil.setContentView(this,R.layout.activity_currency);
        activityCurrencyBinding.setCurrencyOperation(currencyViewModel);

        if (getIntent().hasExtra("type"))
            type = getIntent().getIntExtra("type",0);

        if (getIntent().hasExtra("country_id"))
            countryId = getIntent().getIntExtra("country_id",-1);

        btn_submit=findViewById(R.id.btn_submit);
        btn_currencyBack=findViewById(R.id.btn_currencyBack);
        btn_currencyBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(CurrencyActivity.this, StoreSettingsActivity.class));
                finish();
            }
        });

        switch (type){
            case 1:{
                currencyViewModel.getAllCurrency(activityCurrencyBinding.currencyListt);
                /*activityCurrencyBinding.setPresenter(new CurrencyPresenter() {
                    @Override
                    public void setUserCurrency() {
                        int currency_id = SharedPrefManger.getPermantCurrency();
                        currencyViewModel.setCurrency(currency_id , SharedPrefManger.getStore_ID() );
                    }
                    @Override
                    public void LoadAllCurrency() {

                    }
                });*/
                activityCurrencyBinding.btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int currency_id = SharedPrefManger.getPermantCurrency();
                        currencyViewModel.setCurrency(currency_id , SharedPrefManger.getStore_ID() );
                    }
                });
                break;
            }
            case 2:{
                currencyViewModel.getAllCountry(activityCurrencyBinding.currencyListt);
                activityCurrencyBinding.btnCurrencyBack.setText("Select Country");
                activityCurrencyBinding.searchCurrency.setHint("Search on Country");
                activityCurrencyBinding.btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("country_id", currencyViewModel.countryId);
                        resultIntent.putExtra("country_name", currencyViewModel.countryName);
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                    }
                });
                break;
            }
            case 3:{
                currencyViewModel.getAllCity(activityCurrencyBinding.currencyListt, countryId);
                activityCurrencyBinding.btnCurrencyBack.setText("Select City");
                activityCurrencyBinding.searchCurrency.setHint("Search on City");
                activityCurrencyBinding.btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("city_id", currencyViewModel.cityId);
                        resultIntent.putExtra("city_name", currencyViewModel.cityName);
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                    }
                });
                break;
            }
        }

        EditText searchCurrency = findViewById(R.id.searchCurrency);
        searchCurrency.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                currencyViewModel.FilterAdapter(editable.toString(), type);
            }
        });
    }
    /*private void filter(String s) {
        List<Currency> currencyList = new ArrayList<>();
        for (Currency currency : currency){
            if (currency.getCurrency_title().toLowerCase().contains(s.toLowerCase())){
                currencyList.add(currency);
            }
        }
        activityCurrencyBinding.currencyAllAdapter.setItemAnimator(new DefaultItemAnimator());
        RadioButtonAdapter adapter = new RadioButtonAdapter(CurrencyActivity.this, currencyList);
        activityCurrencyBinding.currencyAllAdapter.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        activityCurrencyBinding.currencyAllAdapter.setLayoutManager(new LinearLayoutManager(CurrencyActivity.this, LinearLayoutManager.VERTICAL, false));
        activityCurrencyBinding.currencyAllAdapter.smoothScrollToPosition(0);
    }*/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }
}
