package findupproducts.example.com.findup.models;

import android.graphics.Bitmap;

public class AddProduct {
    private int productId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public AddProduct(int productId, String productPrice, String productName, String productDescription, String productImgPath) {
        this.productId = productId;
        this.productPrice = productPrice;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productImgPath = productImgPath;
    }

    private String productPrice, productName, productDescription,productImgPath;

    public String getProductImgPath() {
        return productImgPath;
    }

    public void setProductImgPath(String productImgPath) {
        this.productImgPath = productImgPath;
    }

    private Bitmap productPic;

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Bitmap getProductPic() {
        return productPic;
    }

    public void setProductPic(Bitmap productPic) {
        this.productPic = productPic;
    }
}
