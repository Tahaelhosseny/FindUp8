package khaled.example.com.findup.models;

/**
 * Created by khaled on 7/4/18.
 */

public class Category {
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
