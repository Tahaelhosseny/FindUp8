package khaled.example.com.findup.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import khaled.example.com.findup.CONST;

@Entity(foreignKeys = @ForeignKey(entity = Store.class,
        parentColumns = "store_id",
        childColumns = "store_id",
        onDelete = ForeignKey.CASCADE))
public class StorePhoto {
    @NonNull
    @PrimaryKey
    private int photo_id;
    @NonNull
    private String photo_name;
    @NonNull
    private int store_id;

    public StorePhoto(int photo_id, String photo_name, int store_id) {
        this.photo_id = photo_id;
        this.photo_name = photo_name;
        this.store_id = store_id;
    }

    public int getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(int photo_id) {
        this.photo_id = photo_id;
    }

    public String getPhoto_name() {
        return photo_name;
    }

    public void setPhoto_name(String photo_name) {
        this.photo_name = photo_name;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }
}
