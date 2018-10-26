package khaled.example.com.findup.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class UserSavedItem {
    @PrimaryKey @NonNull
    @SerializedName("saved_id")@Expose
    private int itemId;
    @NonNull
    @SerializedName("saved_name")@Expose
    private String itemName;
    @NonNull
    @SerializedName("saved_description")@Expose
    private String itemDesc;
    @NonNull
    @SerializedName("saved_photo")@Expose
    private String itemImg;
    @NonNull
    @SerializedName("saved_type")@Expose
    private String itemType;


    public UserSavedItem(int itemId, String itemName, String itemDesc, String itemImg, String itemType) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.itemImg = itemImg;
        this.itemType = itemType;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
    }
}
