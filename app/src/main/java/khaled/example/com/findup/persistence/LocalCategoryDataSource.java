package khaled.example.com.findup.persistence;

import android.arch.persistence.room.Query;

import io.reactivex.Flowable;
import khaled.example.com.findup.Helper.Database.DaoAccess;
import khaled.example.com.findup.Helper.Database.Interfaces.DataSource.CategoryDataSource;
import khaled.example.com.findup.models.Category;

public class LocalCategoryDataSource implements CategoryDataSource {
    private DaoAccess daoAccess;

    public LocalCategoryDataSource(DaoAccess daoAccess) {
        this.daoAccess = daoAccess;
    }

    @Override
    public Flowable<Category> getCategory(int cat_id) {
        return daoAccess.getCategoryByID(cat_id);
    }

    @Override
    public void insertOrUpdateUser(Category user) {

    }

    @Query("")
    @Override
    public void deleteAllCategories() {

    }
}
