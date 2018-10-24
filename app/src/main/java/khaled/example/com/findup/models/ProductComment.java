package khaled.example.com.findup.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity
public class ProductComment {
    @PrimaryKey
    private int comment_id;
    @NonNull
    private String account_name;
    @NonNull
    private int account_id;
    @NonNull
    private int product_id;
    @NonNull
    private String comment_date;
    @NonNull
    private long comment_date_timestamp;
    @NonNull
    private String comment;
    @NonNull
    private String account_image;
    @NonNull
    private String block_flag;



    public ProductComment() {
    }


    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public int getAccount_id() {
        return account_id;
    }
    public void setAccount_id( int account_id) {
        this.account_id = account_id;
    }
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id( int product_id) {
        this.product_id = product_id;
    }

    public int getStore_id() {
        return product_id;
    }

    public void setStore_id(int store_id) {
        this.product_id = store_id;
    }

    public long getDate() {
        return comment_date_timestamp;
    }

    public void setDate(long date) {
        this.comment_date_timestamp = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAccount_image() {
        return account_image;
    }

    public void setAccount_image(String account_image) {
        this.account_image = account_image;
    }

    @NonNull
    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(@NonNull String comment_date) {
        this.comment_date = comment_date;
    }

    @NonNull
    public long getComment_date_timestamp() {
        return comment_date_timestamp;
    }

    public void setComment_date_timestamp(@NonNull long comment_date_timestamp) {
        this.comment_date_timestamp = comment_date_timestamp;
    }

    public String getBlock_flag() {
        return "0";
    }

    public void setBlock_flag( String block_flag) {
        this.block_flag = "0";
    }
}
