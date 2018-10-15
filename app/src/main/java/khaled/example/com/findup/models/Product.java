package khaled.example.com.findup.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.List;

@Entity
public class Product {
    @NonNull
    @PrimaryKey
    private int product_id;
    @NonNull
    private int product_likes_count;
    @NonNull
    private int product_comments_count;
    @NonNull
    private double product_price;
    @NonNull
    private int store_id;
    @NonNull
    private float product_rate;
    @NonNull
    private String product_name;
    @NonNull
    private String product_desc;
    @NonNull
    private String product_banner;
    @Ignore
    private List<PCommentModel> product_comments;
    @Ignore
    private List<ProductPhoto> productPhotos;
    public List<ProductPhoto> getProductPhotos() {
        return productPhotos;
    }
    public void setProductPhotos(List<ProductPhoto> productPhotos) {
        this.productPhotos = productPhotos;
    }
    public Product(int product_id, double product_price, int product_likes_count, int product_comments_count, String product_name, String product_desc, String product_banner ) {
        this.product_id = product_id;
        this.product_price = product_price;
        this.product_likes_count = product_likes_count;
        this.product_comments_count = product_comments_count;
        this.product_name = product_name;
        this.product_desc = product_desc;
        this.product_banner = product_banner;

            }
    public int getProduct_id() {
        return product_id;
    }
    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
    public double getProduct_price() {
        return product_price;
    }
    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }
    public int getProduct_likes_count() {
        return product_likes_count;
    }
    public void setProduct_likes_count(int product_likes_count) {
        this.product_likes_count = product_likes_count;
    }
    public int getProduct_comments_count() {
        return product_comments_count;
    }
    public void setProduct_comments_count(int product_comments_count) {
        this.product_comments_count = product_comments_count;
    }
    public String getProduct_name() {
        return product_name;
    }
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }
    public String getProduct_desc() {
        return product_desc;
    }
    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }
    public String getProduct_banner() {
        return product_banner;
    }
    public void setProduct_banner(String product_banner) {
        this.product_banner = product_banner;
    }
    public int getStore_id() {
        return store_id;
    }
    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }
    public float getProduct_rate() {
        return product_rate;
    }
    public void setProduct_rate(float product_rate) {
        this.product_rate = product_rate;
    }
    public List<PCommentModel> getProduct_comments() {
        return product_comments;
    }
    public void setProduct_comments(List<PCommentModel> product_comments) {
        this.product_comments = product_comments;
    }
}
