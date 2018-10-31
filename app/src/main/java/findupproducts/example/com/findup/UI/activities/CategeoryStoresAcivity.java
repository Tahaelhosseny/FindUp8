package findupproducts.example.com.findup.UI.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.ViewModel.Activites.CatStoreViewModel;
import findupproducts.example.com.findup.UI.ViewModel.Activites.EditProfileViewModel;
import findupproducts.example.com.findup.databinding.ActivityCategeoryStoresAcivityBinding;

public class CategeoryStoresAcivity extends AppCompatActivity {
    ActivityCategeoryStoresAcivityBinding binding;
    CatStoreViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new CatStoreViewModel(this);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_categeory_stores_acivity);
        int id = getIntent().getIntExtra("id" , 0);
        binding.btnCatStoreBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        viewModel.InitRecyclerView(binding.storeCatRecycler, id);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
