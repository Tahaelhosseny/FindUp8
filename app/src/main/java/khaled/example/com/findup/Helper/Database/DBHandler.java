package khaled.example.com.findup.Helper.Database;

import android.content.Context;

import java.util.List;

import khaled.example.com.findup.Helper.Database.Interfaces.DataBaseOnChangeApplied;
import khaled.example.com.findup.models.Category;

public class DBHandler {
    // ***************************************************************** //
    // **************************  Categories ************************** //
    // ***************************************************************** //
    public static void InsertCategory(final Category category, final Context context, final DataBaseOnChangeApplied onChange){
        new Thread(new Runnable() {
            @Override
            public void run() {
                FindUpDatabase.getAppDatabase(context).daoAccess ().insertCategory(category);
                onChange.onSuccess();
            }
        }) .start();
    }

    public static void InsertCategories(final List<Category> categoryList, final Context context, final DataBaseOnChangeApplied onChange){
        new Thread(new Runnable() {
            @Override
            public void run() {
                FindUpDatabase.getAppDatabase(context).daoAccess().insertCategories(categoryList);
                onChange.onSuccess();
            }
        }) .start();
    }


}
