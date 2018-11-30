package findupproducts.example.com.findup.UI.activities;

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
import findupproducts.example.com.findup.UI.adapters.RadioButtonAdapter;
import findupproducts.example.com.findup.databinding.ActivityCurrencyBinding;
import findupproducts.example.com.findup.models.Currency;
import static findupproducts.example.com.findup.UI.ViewModel.Activites.CurrencyViewModel.currency;

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
                filter(editable.toString());
            }
        });
    }
    private void filter(String s) {
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
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CurrencyActivity.this, StoreSettingsActivity.class));
        finish();

    }
}
