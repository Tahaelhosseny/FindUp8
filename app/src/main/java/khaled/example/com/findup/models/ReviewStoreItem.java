package khaled.example.com.findup.models;

import java.util.List;

public class ReviewStoreItem {

    private String productName, productDesc, productImg;
    private List<Comment> productComments;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public List<Comment> getProductComments() {
        return productComments;
    }

    public void setProductComments(List<Comment> productComments) {
        this.productComments = productComments;
    }
}
