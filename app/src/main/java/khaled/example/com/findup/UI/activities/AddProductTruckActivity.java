package khaled.example.com.findup.UI.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mopub.common.util.Json;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.CreateProductResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.CreateStoreResponse;
import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.adapters.AddProductsAdapter;
import khaled.example.com.findup.models.AddProduct;
import khaled.example.com.findup.models.Product;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static khaled.example.com.findup.UI.activities.StoreInformationActivity.createStore;

public class AddProductTruckActivity extends AppCompatActivity {

    EditText editText_productName, editText_productDescription,  editText_product_price;
    Button btn_addProductBack, btn_addProductDone, btn_addProductDelete, btn_submit;
    LinearLayout addProductLayout, addOtherProductLayout, layoutAddWithDetails;
    ImageButton pic_product;
    RecyclerView recyclerTruckProducts;
    List<AddProduct> products;
    Uri selectedProduct;
    final int appVersion = Build.VERSION.SDK_INT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_truck);
        products = new ArrayList<>();

        if (appVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (!checkIfAlreadyhavePermission()) {
                ActivityCompat.requestPermissions(AddProductTruckActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }

        btn_addProductBack = findViewById(R.id.btn_addProductBack);
        btn_addProductDone = findViewById(R.id.btn_addProductDone);
        btn_addProductDelete = findViewById(R.id.btn_addProductDelete);
        addProductLayout = findViewById(R.id.addProductLayout);
        addOtherProductLayout = findViewById(R.id.addOtherProductLayout);
        layoutAddWithDetails = findViewById(R.id.layoutAddWithDetails);
        recyclerTruckProducts = findViewById(R.id.recyclerTruckProducts);
        editText_productName = findViewById(R.id.editText_productName);
        editText_productDescription = findViewById(R.id.editText_productDescription);
        editText_product_price = findViewById(R.id.product_price);
        pic_product = findViewById(R.id.pic_product);
        btn_submit = findViewById(R.id.btn_submit);

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
        btn_addProductDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProduct();
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
        pic_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImg();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && data != null){
            switch(requestCode) {
                case (1) : {
                    selectedProduct = data.getData();
                    assert selectedProduct != null;
                    Bitmap bitmap = BitmapFactory.decodeFile(selectedProduct.getPath());
                    pic_product.setImageBitmap(bitmap);
                    break;
                }
            }
        }
    }

    private void addProduct(){
        if (TextUtils.isEmpty(editText_productName.getText().toString())){
            Toast.makeText(this, "Enter Product Name", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(editText_productDescription.getText().toString())){
            Toast.makeText(this, "Enter Description", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(editText_product_price.getText().toString())){
            Toast.makeText(this, "Enter Price", Toast.LENGTH_LONG).show();
            return;
        } else if (selectedProduct == null){
            Toast.makeText(this, "Select Image", Toast.LENGTH_LONG).show();
            return;
        }

        Bitmap bitmap = BitmapFactory.decodeFile(selectedProduct.getPath());

        AddProduct addProduct = new AddProduct(editText_product_price.getText().toString(),
                editText_productName.getText().toString(),
                editText_productDescription.getText().toString(),
                bitmap);
        addProductLayout.setVisibility(View.INVISIBLE);
        layoutAddWithDetails.setVisibility(View.GONE);
        addOtherProductLayout.setVisibility(View.VISIBLE);
        recyclerTruckProducts.setVisibility(View.VISIBLE);
        btn_submit.setVisibility(View.VISIBLE);
        products.add(addProduct);
        recyclerTruckProducts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        AddProductsAdapter adapter = new AddProductsAdapter(getApplicationContext(), products);
        recyclerTruckProducts.setAdapter(adapter);

        File imgFile = new File(selectedProduct.getPath());
        RequestBody requestImgFile =
                RequestBody.create(MediaType.parse("image/png"), imgFile);
        MultipartBody.Part product_img =
                MultipartBody.Part.createFormData("product_img", imgFile.getName(), requestImgFile);

        MultipartBody.Part store_id = MultipartBody.Part.createFormData("store_id", "1");
        MultipartBody.Part product_name = MultipartBody.Part.createFormData("product_name", addProduct.getProductName());
        MultipartBody.Part description = MultipartBody.Part.createFormData("product_desc", addProduct.getProductDescription());
        MultipartBody.Part product_price = MultipartBody.Part.createFormData("product_price", addProduct.getProductPrice());
        MultipartBody.Part product_img_base64 = MultipartBody.Part.createFormData("product_img_base64", "-");

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CreateProductResponse> newProduct = apiService.createStoreProduct(store_id,
                product_name,
                description,
                product_price,
                product_img,
                product_img_base64
        );
        newProduct.enqueue(new Callback<CreateProductResponse>() {
            @Override
            public void onResponse(Call<CreateProductResponse> call, Response<CreateProductResponse> response) {
                Log.e("Success", new Gson().toJson(response.body()));
                if (response.body().getSuccess() ==1){
                    Toast.makeText(AddProductTruckActivity.this,"Product Added",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AddProductTruckActivity.this,"Error adding product",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreateProductResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void pickImg(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}