package khaled.example.com.findup.UI.ViewModel;

import android.arch.lifecycle.ViewModel;


import khaled.example.com.findup.Helper.Database.Interfaces.DataSource.CategoryDataSource;
import khaled.example.com.findup.models.Category;

public class CategoryViewModel extends ViewModel {
    private final CategoryDataSource mDataSource;

    private Category mCategory;

    public CategoryViewModel(CategoryDataSource dataSource) {
        mDataSource = dataSource;
    }
}
