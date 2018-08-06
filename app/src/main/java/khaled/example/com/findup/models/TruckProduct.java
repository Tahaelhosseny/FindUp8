package khaled.example.com.findup.models;

public class TruckProduct {
    private int productId, productPic, productPrice, productName, productDescription;

    public TruckProduct(int productId,int productPic, int productPrice, int productName, int productDescription) {
        this.productId = productId;
        this.productPic=productPic;
        this.productPrice = productPrice;
        this.productName = productName;
        this.productDescription = productDescription;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductName() {
        return productName;
    }

    public void setProductName(int productName) {
        this.productName = productName;
    }

    public int getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(int productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductPic() {
        return productPic;
    }

    public void setProductPic(int productPic) {
        this.productPic = productPic;
    }
}
