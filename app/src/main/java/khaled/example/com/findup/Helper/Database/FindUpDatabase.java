package khaled.example.com.findup.Helper.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import khaled.example.com.findup.models.Category;
import khaled.example.com.findup.models.Comment;
import khaled.example.com.findup.models.Event;
import khaled.example.com.findup.models.NotificationStore;
import khaled.example.com.findup.models.NotificationUser;
import khaled.example.com.findup.models.Product;
import khaled.example.com.findup.models.ProductPhoto;
import khaled.example.com.findup.models.Store;
import khaled.example.com.findup.models.StorePhoto;
import khaled.example.com.findup.models.UserSavedItem;

@Database(entities = {Category.class, Store.class,
        Comment.class, Event.class, Product.class,
        StorePhoto.class, ProductPhoto.class ,
        NotificationUser.class , NotificationStore.class , UserSavedItem.class}, version = 2, exportSchema = false)
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
