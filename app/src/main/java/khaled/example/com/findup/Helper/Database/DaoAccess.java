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
import khaled.example.com.findup.models.Comment;
import khaled.example.com.findup.models.Event;
import khaled.example.com.findup.models.PCommentModel;
import khaled.example.com.findup.models.Product;
import khaled.example.com.findup.models.ProductPhoto;
import khaled.example.com.findup.models.Store;
import khaled.example.com.findup.models.StorePhoto;

@Dao
public interface DaoAccess {

    //Categories table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(Category category);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategories(List<Category> categoryList);

    @Query("SELECT * FROM Category WHERE show_home_flag = 1")
    Flowable<List<Category>> getCategoryInHome();


    @Query("SELECT * FROM Category")
    Flowable<List<Category>> getCategories();


    @Update
    void UpdateCategory(Category category);

    @Delete
    void DeleteCategory(Category category);

    @Delete
    void DeleteAllCategories(List<Category> categoryList);


    //Stores table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStore(Store store);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStores(List<Store> storeList);

    @Query("SELECT * FROM Store WHERE store_id = :store_id")
    Flowable<Store> getStoreByID(int store_id);

    @Query("SELECT * FROM store")
    Flowable<List<Store>> getAllStores();


    @Query("UPDATE store set if_saved =:if_saved WHERE store_id = :store_id")
    void SaveStoreOperation(int store_id,int if_saved);

    @Update
    void UpdateStore(Store store);

    @Delete
    void DeleteStore(Store store);


    //Comments table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertComment(Comment comment);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertComment(List<Comment> commentList);

    @Query("SELECT * FROM Comment WHERE comment_id = :comment_id")
    Flowable<Comment> getCommentByID(int comment_id);

    @Query("SELECT * FROM Comment WHERE store_id = :store_id")
    Flowable<List<Comment>> getCommentsByStoreID(int store_id);

    @Query("SELECT * FROM Comment")
    Flowable<List<Comment>> getAllComments();

    @Update
    void UpdateEvent(Comment comment);

    @Delete
    void DeleteEvent(Comment Comment);


    //Events table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEvent(Event event);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEvent(List<Event> eventList);

    @Query("SELECT * FROM Event WHERE store_id = :event_id")
    Flowable<Event> getEventByID(int event_id);

    @Query("SELECT * FROM Event")
    Flowable<List<Event>> getAllEvents();

    @Update
    void UpdateEvent(Event event);

    @Delete
    void DeleteEvent(Event event);


    /*************************************************************************************
     *
     * Products table
     *
     *************************************************************************************/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(Product product);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProducts(List<Product> productList);

    @Query("SELECT * FROM Product WHERE product_id = :prod_id")
    Flowable<Product> getProductByID(int prod_id);

    @Query("SELECT * FROM Product WHERE store_id = :store_id")
    Flowable<List<Product>> getProductByStoreID(int store_id);

    @Query("SELECT * FROM Product")
    Flowable<List<Product>> getAllProducts();

    @Update
    void UpdateProduct(Product product);

    @Delete
    void DeleteProduct(Product product);


    /*************************************************************************************
     *
     * Products Photos table
     *
     *************************************************************************************/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     void insertProductPhoto(ProductPhoto productPhoto);

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//     void insertProductComments(PCommentModel pCommentModel);

    @Query("SELECT * FROM ProductPhoto WHERE product_id = :prod_id")
    Flowable<List<ProductPhoto>> getProductPhotoByProductID(int prod_id);

    @Delete
    void DeleteProductPhoto(ProductPhoto productPhoto);


    /*************************************************************************************
     *
     * Store Photos table
     *
     *************************************************************************************/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStorePhoto(StorePhoto storePhoto);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStorePhotos(List<ProductPhoto> productPhotoList);

    @Query("SELECT * FROM StorePhoto WHERE store_id = :store_id")
    Flowable<List<StorePhoto>> getStorePhotoByStoreID(int store_id);

    @Delete
    void DeleteProductPhoto(StorePhoto storePhoto);



}