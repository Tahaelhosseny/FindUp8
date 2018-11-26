package findupproducts.example.com.findup.Helper.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

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
import findupproducts.example.com.findup.models.Store_WorkTime;
import findupproducts.example.com.findup.models.UserSavedItem;

@Database(entities = {Category.class, Store.class,
        Store_WorkTime.class,
        Comment.class, Event.class, Product.class,
        StorePhoto.class, ProductPhoto.class ,
        NotificationUser.class , NotificationStore.class ,
        UserSavedItem.class , ProductComment.class}, version = 2, exportSchema = false)
public abstract class FindUpDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "findup.db";
    private static FindUpDatabase INSTANCE;

    public static FindUpDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (FindUpDatabase.class) {
                if (INSTANCE == null)
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(), FindUpDatabase.class, DATABASE_NAME)
                                    .build();
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract DaoAccess daoAccess();
}
