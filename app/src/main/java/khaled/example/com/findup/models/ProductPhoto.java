package khaled.example.com.findup.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import khaled.example.com.findup.CONST;

@Entity/*(foreignKeys = @ForeignKey(entity = Product.class,
        parentColumns = "product_id",
        childColumns = "product_id",
        onDelete = ForeignKey.CASCADE))*/
public class ProductPhoto {
    @NonNull
    @PrimaryKey
    private int photo_id;
    @NonNull
    private String photo_name;
    @NonNull
    private int product_id;

    public ProductPhoto(@NonNull int photo_id, String photo_name, int product_id) {
        this.photo_id = photo_id;
        this.photo_name = photo_name;
        this.product_id = product_id;
    }

    public int getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(int photo_id) {
        this.photo_id = photo_id;
    }

    public String getPhoto_name() {
        return CONST.API_FILE_DOMAIN.concat(CONST.IMAGES_PATH) + "" + photo_name;
    }

    public void setPhoto_name(String photo_name) {
        this.photo_name = photo_name;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
