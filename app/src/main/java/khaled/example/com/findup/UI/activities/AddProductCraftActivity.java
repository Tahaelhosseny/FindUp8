package khaled.example.com.findup.UI.activities;

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
import khaled.example.com.findup.UI.adapters.TruckProductsAdapter;
import khaled.example.com.findup.models.TruckProduct;

public class AddProductCraftActivity extends AppCompatActivity {

    Button btn_addCraftBack, btn_addCraftDone, btn_addCraftDelete, btn_submit;
    LinearLayout addCraftLayout, addOtherCraftLayout, layoutAddWithDetails;
    RecyclerView recyclerCrafProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_craft);


        btn_addCraftBack=findViewById(R.id.btn_addCraftBack);
        btn_addCraftDone=findViewById(R.id.btn_addCraftDone);
        btn_addCraftDelete=findViewById(R.id.btn_addCraftDelete);
        addCraftLayout=findViewById(R.id.addCraftLayout);
        addOtherCraftLayout=findViewById(R.id.addOtherCraftLayout);
        layoutAddWithDetails=findViewById(R.id.layoutAddWithDetails);
        recyclerCrafProducts=findViewById(R.id.recyclerCraftProducts);
        btn_submit=findViewById(R.id.btn_submit);

        addCraftLayout.setVisibility(View.VISIBLE);
        layoutAddWithDetails.setVisibility(View.GONE);
        addOtherCraftLayout.setVisibility(View.GONE);
        recyclerCrafProducts.setVisibility(View.GONE);
        btn_submit.setVisibility(View.GONE);

        btn_addCraftBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddProductCraftActivity.super.onBackPressed();
                finish();
            }
        });

        addCraftLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCraftLayout.setVisibility(View.GONE);
                layoutAddWithDetails.setVisibility(View.VISIBLE);
            }
        });
        final List<TruckProduct> truckProducts=new ArrayList<>();
        btn_addCraftDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCraftLayout.setVisibility(View.INVISIBLE);
                layoutAddWithDetails.setVisibility(View.GONE);
                addOtherCraftLayout.setVisibility(View.VISIBLE);
                recyclerCrafProducts.setVisibility(View.VISIBLE);
                btn_submit.setVisibility(View.VISIBLE);
                truckProducts.add(new TruckProduct(0, R.drawable.placeholder,
                        R.string.craft_price, R.string.craft_name, R.string.craft_description));
                recyclerCrafProducts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                TruckProductsAdapter adapter = new TruckProductsAdapter(getApplicationContext(),truckProducts);
                recyclerCrafProducts.setAdapter(adapter);
            }
        });
        addOtherCraftLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCraftLayout.setVisibility(View.INVISIBLE);
                layoutAddWithDetails.setVisibility(View.VISIBLE);
                addOtherCraftLayout.setVisibility(View.GONE);
                recyclerCrafProducts.setVisibility(View.VISIBLE);
                btn_submit.setVisibility(View.GONE);
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddProductCraftActivity.this, SuccessfulRegisterationActivity.class));
            }
        });
        btn_addCraftDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AddProductCraftActivity.this, "delete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
