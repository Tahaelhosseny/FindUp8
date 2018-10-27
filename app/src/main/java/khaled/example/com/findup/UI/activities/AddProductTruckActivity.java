package khaled.example.com.findup.UI.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

import khaled.example.com.findup.R;

import khaled.example.com.findup.UI.adapters.AddProductsAdapter;
import khaled.example.com.findup.UI.adapters.RecyclerTouchListener;
import khaled.example.com.findup.models.AddProduct;

public class AddProductTruckActivity extends AppCompatActivity {

    Button btn_addProductBack, btn_submit;
    LinearLayout addProductLayout;
    RecyclerView recyclerTruckProducts;
    List<AddProduct> products;
    AddProductsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_truck);
        products = new ArrayList<>();
        adapter = new AddProductsAdapter(this, products);

        btn_addProductBack = findViewById(R.id.btn_addProductBack);

        addProductLayout = findViewById(R.id.addProductLayout);
        recyclerTruckProducts = findViewById(R.id.recyclerTruckProducts);
        btn_submit = findViewById(R.id.btn_submit);

        bindUI();

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
                startActivityForResult(new Intent(AddProductTruckActivity.this, NewProductActivity.class), 0);
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddProductTruckActivity.this, SuccessfulRegisterationActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && data != null){
            switch(requestCode) {
                case 0: {
                    switch (data.getIntExtra("opr_type", 0)){
                        case 1:
                            Bitmap bitmap = BitmapFactory.decodeFile(data.getStringExtra("pro_img"));
                            products.add(new AddProduct(data.getStringExtra("pro_price"), data.getStringExtra("pro_name"),data.getStringExtra("pro_desc"),bitmap));
                            adapter.notifyDataSetChanged();
                            break;
                        case 2:
                            products.remove(data.getStringExtra("pro_pos"));
                            adapter.notifyDataSetChanged();
                            break;
                    }
                    break;
                }
            }
        }
    }

    private void bindUI(){
        recyclerTruckProducts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerTruckProducts.setAdapter(adapter);
        recyclerTruckProducts.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerTruckProducts, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                startActivityForResult(new Intent(AddProductTruckActivity.this, NewProductActivity.class)
                                .putExtra("pro_pos",position).putExtra("pro_name", products.get(position).getProductName())
                        .putExtra("pro_desc", products.get(position).getProductDescription())
                        .putExtra("pro_price", products.get(position).getProductPrice())
                        .putExtra("pro_img", products.get(position).getProductImgPath())
                        ,0);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}