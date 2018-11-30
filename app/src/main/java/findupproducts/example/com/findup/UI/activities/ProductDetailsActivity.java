package findupproducts.example.com.findup.UI.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.UI.fragments.ProductDetailsFragment;
import findupproducts.example.com.findup.models.Product;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ProductDetailsFragment productDetailsFragment = new ProductDetailsFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.product_fragment_container, productDetailsFragment).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
