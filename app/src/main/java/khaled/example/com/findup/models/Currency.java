package khaled.example.com.findup.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Currency {
    @SerializedName("currency_id")
    @Expose
    private int currency_id;
    @SerializedName("currency_title")
    @Expose
    private String currency_title;
    @SerializedName("user_currency")
    @Expose
    private int user_currency;
    boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(int currency_id) {
        this.currency_id = currency_id;
    }

    public String getCurrency_title() {
        return currency_title;
    }

    public void setCurrency_title(String currency_title) {
        this.currency_title = currency_title;
    }

    public int getUser_currency() {
        return user_currency;
    }

    public void setUser_currency(int user_currency) {
        this.user_currency = user_currency;
    }
}
