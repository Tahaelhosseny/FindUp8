package khaled.example.com.findup.Helper.Database;

import android.content.Context;

import khaled.example.com.findup.Helper.Database.Interfaces.DataSource.CategoryDataSource;
import khaled.example.com.findup.persistence.LocalCategoryDataSource;

public class injection {

    public static CategoryDataSource provideCategoryDataSource(Context mContext){
        FindUpDatabase findUpDatabase = FindUpDatabase.getAppDatabase(mContext);
        return new LocalCategoryDataSource(findUpDatabase.daoAccess());
    }

    /*public static ViewModelFactory provideViewModelFactory(Context mContext){
        CategoryDataSource dataSource = provideCategoryDataSource(mContext);
        return new ViewModelFactory(dataSource);
    }*/
}
