package findupproducts.example.com.findup.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("user_id")
    @Expose
    private int id;
    @SerializedName("user_name")
    @Expose
    private String name;
    @SerializedName("user_image")
    @Expose
    private String image;
    @SerializedName("user_mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("store_id")
    @Expose
    private int store_id;
    @SerializedName("store_name")
    @Expose
    private String store_name;
    @SerializedName("store_logo")
    @Expose
    private String store_logo;
    @SerializedName("store_banner")
    @Expose
    private String store_banner;
    @SerializedName("store_mobile")
    @Expose
    private String store_mobile;
    @SerializedName("user_type")
    @Expose
    private String login_type;

    @Override
    public String toString() {
        return "ID : " + getId() + "  Name : "+ getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_logo() {
        return store_logo;
    }

    public void setStore_logo(String store_logo) {
        this.store_logo = store_logo;
    }

    public String getStore_banner() {
        return store_banner;
    }

    public void setStore_banner(String store_banner) {
        this.store_banner = store_banner;
    }

    public String getStore_mobile() {
        return store_mobile;
    }

    public void setStore_mobile(String store_mobile) {
        this.store_mobile = store_mobile;
    }

    public String getLogin_type() {
        return login_type;
    }

    public void setLogin_type(String login_type) {
        this.login_type = login_type;
    }
}
