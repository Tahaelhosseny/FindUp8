package khaled.example.com.findup.Helper.Database.Interfaces.DataSource;

import io.reactivex.Flowable;
import khaled.example.com.findup.models.Category;

public interface CategoryDataSource {


    /**
     * Gets the category from the data source.
     *
     * @return the category from the data source.
     */
    Flowable<Category> getCategory(int cat_id);

    /**
     * Inserts the category into the data source, or, if this is an existing category, updates it.
     *
     * @param user the category to be inserted or updated.
     */
    void insertOrUpdateUser(Category user);

    /**
     * Deletes all categories from the data source.
     */
    void deleteAllCategories();
}
