package khaled.example.com.findup.Helper.Database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import khaled.example.com.findup.models.Category;

@Dao
public interface DaoAccess {

    //Categories table
    @Insert
    void insertCategory(Category category);

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertCategories(List<Category> categoryList);

    @Query ("SELECT * FROM Category WHERE cat_id = :cat_id")
    Flowable<Category> getCategoryByID(int cat_id);

    @Update
    void UpdateCategory(Category category);

    @Delete
    void DeleteCategory(Category category);

    @Delete
    void DeleteAllCategories(List<Category> categoryList);

}