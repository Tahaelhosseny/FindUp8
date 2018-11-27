package findupproducts.example.com.findup.UI.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import findupproducts.example.com.findup.Helper.Database.DBHandler;
import findupproducts.example.com.findup.Helper.FilePath;
import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.CreateProductResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.DeleteStoreProductResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.models.AddProduct;
import findupproducts.example.com.findup.models.Product;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewProductActivity extends AppCompatActivity {

    EditText editText_productName, editText_productDescription,  editText_product_price;
    Button btn_addProductDone, btn_addProductDelete;
    Uri selectedProduct,selectedProduct2,selectedProduct3,selectedProduct4;
    ImageButton pic_product,pic_product2,pic_product3,pic_product4;
    final int appVersion = Build.VERSION.SDK_INT;
    int pro_pos = -1;
    int pro_id = -1;
    ApiInterface apiService;
    SharedPrefManger sharedPrefManger;
    boolean isCraft = false;
    String path = "",path1 = "",path2 = "",path3 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        sharedPrefManger = new SharedPrefManger(this);

        btn_addProductDone = findViewById(R.id.btn_addProductDone);
        btn_addProductDelete = findViewById(R.id.btn_addProductDelete);
        editText_productName = findViewById(R.id.editText_productName);
        editText_productDescription = findViewById(R.id.editText_productDescription);
        editText_product_price = findViewById(R.id.editText_product_price);
        pic_product = findViewById(R.id.pic_product);
        pic_product2 = findViewById(R.id.pic_product2);
        pic_product3 = findViewById(R.id.pic_product3);
        pic_product4 = findViewById(R.id.pic_product4);

        if (appVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (!checkIfAlreadyhavePermission()) {
                ActivityCompat.requestPermissions(NewProductActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }

        if (getIntent() != null){
            pro_pos = getIntent().getIntExtra("pro_pos", -1);
            pro_id = getIntent().getIntExtra("pro_id", -1);
            if (getIntent().hasExtra("isCraft"))
                isCraft = true;
        }

        if (isCraft){
            pic_product2.setVisibility(View.VISIBLE);
            pic_product3.setVisibility(View.VISIBLE);
            pic_product4.setVisibility(View.VISIBLE);
        }

        if (pro_pos != -1){
            editText_productName.setText(getIntent().getStringExtra("pro_name"));
            editText_productDescription.setText(getIntent().getStringExtra("pro_desc"));
            editText_product_price.setText(getIntent().getStringExtra("pro_price"));
            Log.e("Success", getIntent().getStringExtra("pro_img"));
            Bitmap bitmap = BitmapFactory.decodeFile(getIntent().getStringExtra("pro_img"));
            pic_product.setImageBitmap(bitmap);
        }

        apiService = ApiClient.getClient().create(ApiInterface.class);

        pic_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImg(1);
            }
        });
        pic_product2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImg(2);
            }
        });
        pic_product3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImg(3);
            }
        });
        pic_product4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImg(4);
            }
        });

        btn_addProductDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });

        btn_addProductDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProduct();
            }
        });
    }

    private void deleteProduct() {
        if (pro_id != -1 && pro_pos != -1){

            Call<DeleteStoreProductResponse> deleteProduct = apiService.deleteStoreProduct(pro_id, sharedPrefManger.getStore_ID());
            deleteProduct.enqueue(new Callback<DeleteStoreProductResponse>() {
                @Override
                public void onResponse(Call<DeleteStoreProductResponse> call, Response<DeleteStoreProductResponse> response) {
                    Toast.makeText(NewProductActivity.this, "Product Deleted", Toast.LENGTH_LONG).show();
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("opr_type", 2);
                    resultIntent.putExtra("pro_pos", pro_pos);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }

                @Override
                public void onFailure(Call<DeleteStoreProductResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && data != null){
            switch(requestCode) {
                case (1) : {
                    selectedProduct = data.getData();
                    assert selectedProduct != null;
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(NewProductActivity.this.getContentResolver(),selectedProduct);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    pic_product.setImageBitmap(bitmap);
                    path = FilePath.getPath(this, selectedProduct);
                    break;
                }
                case (2) : {
                    selectedProduct2 = data.getData();
                    assert selectedProduct2 != null;
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(NewProductActivity.this.getContentResolver(),selectedProduct2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    pic_product2.setImageBitmap(bitmap);
                    path1 = FilePath.getPath(this, selectedProduct2);
                    break;
                }
                case (3) : {
                    selectedProduct3 = data.getData();
                    assert selectedProduct3 != null;
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(NewProductActivity.this.getContentResolver(),selectedProduct3);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    pic_product3.setImageBitmap(bitmap);
                    path2 = FilePath.getPath(this, selectedProduct3);
                    break;
                }
                case (4) : {
                    selectedProduct4 = data.getData();
                    assert selectedProduct4 != null;
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(NewProductActivity.this.getContentResolver(),selectedProduct4);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    pic_product4.setImageBitmap(bitmap);
                    path3 = FilePath.getPath(this, selectedProduct4);
                    break;
                }
            }
        }
    }

    private void addProduct(){
        /*if (isCraft){
            if (selectedProduct2 == null){
                Toast.makeText(this, "Select Image 2", Toast.LENGTH_LONG).show();
                return;
            } else if (selectedProduct3 == null){
                Toast.makeText(this, "Select Image 3", Toast.LENGTH_LONG).show();
                return;
            } else if (selectedProduct4 == null){
                Toast.makeText(this, "Select Image 4", Toast.LENGTH_LONG).show();
                return;
            }
        }*/

        if (pro_pos != -1){
            Toast.makeText(this, "Product Already Added", Toast.LENGTH_LONG).show();
            return;
        }  else if (TextUtils.isEmpty(editText_productName.getText().toString())){
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

        AddProduct addProduct = new AddProduct(
                -1,
                editText_product_price.getText().toString(),
                editText_productName.getText().toString(),
                editText_productDescription.getText().toString(),
                path);

        File imgFile = new File(path);
        Log.e("MyPath", path);
        RequestBody requestImgFile =
                RequestBody.create(MediaType.parse("image/png"), imgFile);
        MultipartBody.Part product_img =
                MultipartBody.Part.createFormData("product_img", imgFile.getName(), requestImgFile);

        MultipartBody.Part product_img1 = null, product_img2 = null,product_img3 = null;
        if (isCraft){
            if (!path1.isEmpty()){
                File imgFile1 = new File(path1);
                RequestBody requestImgFile1 =
                        RequestBody.create(MediaType.parse("image/png"), imgFile1);
                product_img1 =
                        MultipartBody.Part.createFormData("product_img1", imgFile1.getName(), requestImgFile1);
            }
            if (!path2.isEmpty()){
                File imgFile2 = new File(path2);
                RequestBody requestImgFile2 =
                        RequestBody.create(MediaType.parse("image/png"), imgFile2);
                product_img2 =
                        MultipartBody.Part.createFormData("product_img2", imgFile2.getName(), requestImgFile2);
            }
            if (!path3.isEmpty()){
                File imgFile3 = new File(path3);
                RequestBody requestImgFile3 =
                        RequestBody.create(MediaType.parse("image/png"), imgFile3);
                product_img3 =
                        MultipartBody.Part.createFormData("product_img3", imgFile3.getName(), requestImgFile3);
            }
        }

        MultipartBody.Part store_id = MultipartBody.Part.createFormData("store_id", ""+sharedPrefManger.getStore_ID());
        MultipartBody.Part product_name = MultipartBody.Part.createFormData("product_name", addProduct.getProductName());
        MultipartBody.Part description = MultipartBody.Part.createFormData("product_desc", addProduct.getProductDescription());
        MultipartBody.Part product_price = MultipartBody.Part.createFormData("product_price", addProduct.getProductPrice());

        Call<CreateProductResponse> newProduct = apiService.createStoreProduct(store_id,
                product_name,
                description,
                product_price,
                product_img,
                product_img1,
                product_img2,
                product_img3);

        newProduct.enqueue(new Callback<CreateProductResponse>() {
            @Override
            public void onResponse(Call<CreateProductResponse> call, Response<CreateProductResponse> response) {
                Log.e("Success", new Gson().toJson(response.body()));
                if (response.body().getSuccess() ==1){
                    Product product = new Product(response.body().getData().get(0).getProduct_id(),
                            Double.parseDouble(editText_product_price.getText().toString()),
                            0,
                            0,
                            editText_productName.getText().toString(),
                            editText_productDescription.getText().toString(),
                            "");
                    product.setStore_id(sharedPrefManger.getStore_ID());
                    product.setProduct_rate(0);
                    DBHandler.InsertPrdouct(product,NewProductActivity.this);
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("opr_type", 1);
                    resultIntent.putExtra("pro_id", product.getProduct_id());
                    resultIntent.putExtra("pro_name", product.getProduct_name());
                    resultIntent.putExtra("pro_desc", product.getProduct_desc());
                    resultIntent.putExtra("pro_price", product.getProduct_desc());
                    resultIntent.putExtra("pro_img", path);
                    setResult(Activity.RESULT_OK, resultIntent);
                    Toast.makeText(NewProductActivity.this,"Product Added",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(NewProductActivity.this,"Error adding product",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreateProductResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void pickImg(int requestCode){
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), requestCode);
    }

    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}
