package findupproducts.example.com.findup.Helper.Database.Interfaces.DataSource;

import java.util.List;

import io.reactivex.Flowable;
import findupproducts.example.com.findup.models.Store;

public interface StoreDataSource {


    /**
     * Gets the category from the data source.
     *
     * @return the category from the data source.
     */
    Flowable<Store> getStore(int store_id);

    Flowable<List<Store>> getAllStores();


    /**
     * Inserts the category into the data source, or, if this is an existing category, updates it.
     *
     * @param store the category to be inserted or updated.
     */
    void insertOrUpdateStore(Store store);


    /**
     * Inserts the category into the data source, or, if this is an existing category, updates it.
     *
     * @param stores the category to be inserted or updated.
     */
    void insertOrUpdateStores(List<Store> stores);

    /**
     * Deletes all categories from the data source.
     */
    void deleteAllStores();
}
