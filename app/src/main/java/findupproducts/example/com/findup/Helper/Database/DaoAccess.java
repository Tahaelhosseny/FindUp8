package findupproducts.example.com.findup.Helper.Database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import org.intellij.lang.annotations.Flow;

import java.util.List;

import findupproducts.example.com.findup.models.Store_WorkTime;
import io.reactivex.Flowable;
import findupproducts.example.com.findup.models.Category;
import findupproducts.example.com.findup.models.Comment;
import findupproducts.example.com.findup.models.Event;
import findupproducts.example.com.findup.models.NotificationStore;
import findupproducts.example.com.findup.models.NotificationUser;
import findupproducts.example.com.findup.models.Product;
import findupproducts.example.com.findup.models.ProductComment;
import findupproducts.example.com.findup.models.ProductPhoto;
import findupproducts.example.com.findup.models.Store;
import findupproducts.example.com.findup.models.StorePhoto;
import findupproducts.example.com.findup.models.UserSavedItem;

@Dao
public interface DaoAccess {


    //Categories table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(Category category);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStoreWorkDays(Store_WorkTime store_workTime);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserNotification(NotificationUser notificationUser);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStoreNotification(NotificationStore notificationStore);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSavedItem(UserSavedItem userSavedItem);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategories(List<Category> categoryList);

    @Query("SELECT * FROM Category")
    Flowable<List<Category>> getCategories();

    @Query("SELECT * FROM Category WHERE show_home_flag = 1")
    Flowable<List<Category>> getCategoryInHome();


    @Update
    void UpdateCategory(Category category);

    @Delete
    void DeleteCategory(Category category);

    @Delete
    void DeleteAllCategories(List<Category> categoryList);



    //Product comments

    @Query("DELETE FROM ProductComment")
    void deleteAllProductComments();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProductComment(ProductComment commentModel);

    @Query("SELECT * FROM ProductComment WHERE comment_id = (SELECT MAX(comment_id) FROM ProductComment WHERE product_id = :product_id)")
    Flowable<List<ProductComment>> getSpecificComment(int product_id);


    //Stores table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStore(Store store);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStores(List<Store> storeList);

    @Query("SELECT * FROM Store WHERE store_id = :store_id")
    Flowable<Store> getStoreByID(int store_id);

    @Query("SELECT * FROM Store_WorkTime WHERE store_id = :store_id")
    Flowable<Store_WorkTime> getStoreWorkById(int store_id);

    @Query("SELECT * FROM store")
    Flowable<List<Store>> getAllStores();

    @Query("SELECT * FROM Store WHERE store_cat_id = :cat_id")
    Flowable<List<Store>> getStoreByCat(int cat_id);

    @Query("UPDATE Product set if_liked = :if_liked AND product_likes_count = :count WHERE product_id = :product_id")
    void likeProduct(int count , int product_id , int if_liked);

    @Query("UPDATE store set if_saved =:if_saved WHERE store_id = :store_id")
    void SaveStoreOperation(int store_id,int if_saved);

    @Query("UPDATE store set store_name = :store_name AND store_mobile = :store_mobile WHERE store_id = :store_id")
    void updateStoreData(String store_name , int store_id , String store_mobile);

    @Update
    void UpdateStore(Store store);
    @Delete
    void DeleteStore(Store store);
    @Query("SELECT * FROM UserSavedItem")
    Flowable<List<UserSavedItem>> getAllSaved();
    @Delete
    void deleteSavedItem(UserSavedItem userSavedItem);
    @Query("SELECT * FROM NotificationUser")
    Flowable<List<NotificationUser>> getAllUserNotification();

    @Query("SELECT * FROM NotificationStore")
    Flowable<List<NotificationStore>> getAllStoreNotification();


    //Comments table
    @Query("DELETE FROM Comment")
    void deleteAllComments();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertComment(Comment comment);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertComment(List<Comment> commentList);

    @Query("SELECT * FROM Comment WHERE comment_id = :comment_id")
    Flowable<Comment> getCommentByID(int comment_id);

    @Query("SELECT * FROM Comment WHERE store_id = :store_id")
    Flowable<List<Comment>> getCommentsByStoreID(int store_id);

    @Query("SELECT * FROM Product WHERE store_id = :store_id")
    Flowable<List<Product>> getStoreProducts(int store_id);

    @Query("SELECT * FROM ProductComment WHERE product_id = :product_id")
    Flowable<List<ProductComment>> getCommentsByProductID(int product_id);

    @Query("SELECT * FROM Comment")
    Flowable<List<Comment>> getAllComments();

    @Update
    void UpdateEvent(Comment comment);

    @Query("SELECT * FROM Event WHERE event_id = :event_id")
    Flowable<List<Event>> getEventByEventID(int event_id);

    @Query("DELETE FROM Store")
    void deleteAllStoreData();

    @Query("DELETE FROM Event")
    void deleteAllEventData();

    @Query("DELETE FROM UserSavedItem WHERE itemId = :id")
    void deleteSavedbyID(int id);

    @Query("SELECT * FROM UserSavedItem WHERE itemDesc = :desc AND itemName = :name")
    Flowable<List<UserSavedItem>> getSavedID(String desc  , String name);

    @Query("SELECT * FROM Store WHERE if_saved = 1")
    Flowable<List<Store>> getSavedStore();

    @Delete
    void DeleteSaved(UserSavedItem userSavedItem);

    //Events table
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEvent(Event event);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEvent(List<Event> eventList);

    @Query("SELECT * FROM Event WHERE store_id = :store_id")
    Flowable<List<Event>> getEventByID(int store_id);

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

    @Query("DELETE FROM Product")
    void deleteAllProductsData();

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

    @Query("DELETE FROM ProductPhoto")
    void deleteAllProductsPhotosData();

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

    @Query("DELETE FROM StorePhoto")
    void deleteAllStorePhotoData();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStorePhoto(StorePhoto storePhoto);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStorePhotos(List<ProductPhoto> productPhotoList);

    @Query("SELECT * FROM StorePhoto WHERE store_id = :store_id")
    Flowable<List<StorePhoto>> getStorePhotoByStoreID(int store_id);

    @Delete
    void DeleteProductPhoto(StorePhoto storePhoto);




}