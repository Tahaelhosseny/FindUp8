package khaled.example.com.findup.Helper.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import khaled.example.com.findup.models.Category;

@Database(entities = {Category.class}, version = 1, exportSchema = false)
public abstract class FindUpDatabase extends RoomDatabase{
    public abstract DaoAccess daoAccess() ;
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
}
