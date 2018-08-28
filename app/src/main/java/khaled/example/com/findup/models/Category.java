package khaled.example.com.findup.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by khaled on 7/4/18.
 */
@Entity
public class Category {
    @NonNull
    @PrimaryKey
    private int cat_id;

    @NonNull
    private String cat_name;

    @NonNull
    private String cat_desc;

    @NonNull
    private int show_home_flag;

    @NonNull
    private int block_flag;

    public Category(int cat_id, String cat_name, String cat_desc, int show_home_flag, int block_flag) {
        this.cat_id = cat_id;
        this.cat_name = cat_name;
        this.cat_desc = cat_desc;
        this.show_home_flag = show_home_flag;
        this.block_flag = block_flag;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_desc() {
        return cat_desc;
    }

    public void setCat_desc(String cat_desc) {
        this.cat_desc = cat_desc;
    }

    public int getShow_home_flag() {
        return show_home_flag;
    }

    public void setShow_home_flag(int show_home_flag) {
        this.show_home_flag = show_home_flag;
    }

    public int getBlock_flag() {
        return block_flag;
    }

    public void setBlock_flag(int block_flag) {
        this.block_flag = block_flag;
    }
}
