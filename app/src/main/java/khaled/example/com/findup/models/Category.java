package khaled.example.com.findup.models;

/**
 * Created by khaled on 7/4/18.
 */

public class Category {
    private int cat_id;
    private String cat_name;

    public Category(String cat_name) {
        this.cat_name = cat_name;
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
}
