package khaled.example.com.findup.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserSetting {
    @SerializedName("sett_id") @Expose
    private int setting_id;
    @SerializedName("account_id") @Expose
    private int account_id;
    @SerializedName("push_noti_flag") @Expose
    private int pust_noti_flag;
    @SerializedName("chat_noti_flag") @Expose
    private int chat_noti_flag;
    @SerializedName("distance_type_id") @Expose
    private int distance_type_id;
    @SerializedName("language") @Expose
    private String language;
    @SerializedName("currency_id") @Expose
    private int currency_id;

    public int getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(int setting_id) {
        this.setting_id = setting_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getPust_noti_flag() {
        return pust_noti_flag;
    }

    public void setPust_noti_flag(int pust_noti_flag) {
        this.pust_noti_flag = pust_noti_flag;
    }

    public int getChat_noti_flag() {
        return chat_noti_flag;
    }

    public void setChat_noti_flag(int chat_noti_flag) {
        this.chat_noti_flag = chat_noti_flag;
    }

    public int getDistance_type_id() {
        return distance_type_id;
    }

    public void setDistance_type_id(int distance_type_id) {
        this.distance_type_id = distance_type_id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(int currency_id) {
        this.currency_id = currency_id;
    }
}
