package findupproducts.example.com.findup.UI.ViewModel.Activites;

import android.arch.lifecycle.ViewModel;

import findupproducts.example.com.findup.Helper.Database.Interfaces.DataSource.CategoryDataSource;
import findupproducts.example.com.findup.models.Category;

public class CategoryViewModel extends ViewModel {
    private final CategoryDataSource mDataSource;

    private Category mCategory;

    public CategoryViewModel(CategoryDataSource dataSource) {
        mDataSource = dataSource;
    }
}
