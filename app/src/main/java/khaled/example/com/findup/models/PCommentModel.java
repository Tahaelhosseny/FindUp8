package khaled.example.com.findup.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class PCommentModel {
    @PrimaryKey
    private int comment_id;
    @NonNull
    private String account_name;
    @NonNull
    @SerializedName("account_id")
    @Expose
    private int acc_id;
    @NonNull
    private int product_id;
    private String comment_date;
    private long comment_date_timestamp;
    @NonNull
    private String comment;
    @NonNull
    private String account_image;
    @NonNull
    private String block_flag;



    public PCommentModel() {
    }

    public PCommentModel(String account_name, long date, String comment, String account_image) {
        this.account_name = account_name;
        this.comment_date_timestamp = date;
        this.comment = comment;
        this.account_image = account_image;
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

    public int getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(int acc_id) {
        this.acc_id = acc_id;
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

    @NonNull
    public String getBlock_flag() {
        return block_flag;
    }

    public void setBlock_flag(@NonNull String block_flag) {
        this.block_flag = block_flag;
    }
}
