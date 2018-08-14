package khaled.example.com.findup.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import khaled.example.com.findup.R;
import khaled.example.com.findup.adapters.TruckProductsAdapter;
import khaled.example.com.findup.models.TruckProduct;

public class AddProductTruckActivity extends AppCompatActivity {

    Button btn_addProductBack, btn_addProductDone, btn_addProductDelete, btn_submit;
    LinearLayout addProductLayout, addOtherProductLayout, layoutAddWithDetails;
    RecyclerView recyclerTruckProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_truck);

        btn_addProductBack=findViewById(R.id.btn_addProductBack);
        btn_addProductDone=findViewById(R.id.btn_addProductDone);
        btn_addProductDelete=findViewById(R.id.btn_addProductDelete);
        addProductLayout=findViewById(R.id.addProductLayout);
        addOtherProductLayout=findViewById(R.id.addOtherProductLayout);
        layoutAddWithDetails=findViewById(R.id.layoutAddWithDetails);
        recyclerTruckProducts=findViewById(R.id.recyclerTruckProducts);
        btn_submit=findViewById(R.id.btn_submit);

        addProductLayout.setVisibility(View.VISIBLE);
        layoutAddWithDetails.setVisibility(View.GONE);
        addOtherProductLayout.setVisibility(View.GONE);
        recyclerTruckProducts.setVisibility(View.GONE);
        btn_submit.setVisibility(View.GONE);

        btn_addProductBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddProductTruckActivity.super.onBackPressed();
                finish();
            }
        });

        addProductLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProductLayout.setVisibility(View.GONE);
                layoutAddWithDetails.setVisibility(View.VISIBLE);
            }
        });
        final List<TruckProduct> truckProducts=new ArrayList<>();
        btn_addProductDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProductLayout.setVisibility(View.INVISIBLE);
                layoutAddWithDetails.setVisibility(View.GONE);
                addOtherProductLayout.setVisibility(View.VISIBLE);
                recyclerTruckProducts.setVisibility(View.VISIBLE);
                btn_submit.setVisibility(View.VISIBLE);
                truckProducts.add(new TruckProduct(0, R.drawable.placeholder,
                        R.string.product_price, R.string.product_name, R.string.product_description));
                recyclerTruckProducts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                TruckProductsAdapter adapter = new TruckProductsAdapter(getApplicationContext(),truckProducts);
                recyclerTruckProducts.setAdapter(adapter);
            }
        });
        addOtherProductLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProductLayout.setVisibility(View.INVISIBLE);
                layoutAddWithDetails.setVisibility(View.VISIBLE);
                addOtherProductLayout.setVisibility(View.GONE);
                recyclerTruckProducts.setVisibility(View.VISIBLE);
                btn_submit.setVisibility(View.GONE);
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddProductTruckActivity.this, SuccessfulRegisterationActivity.class));
            }
        });
        btn_addProductDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddProductTruckActivity.this, "delete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}