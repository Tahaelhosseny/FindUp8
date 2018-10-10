package khaled.example.com.findup.Helper.Database;

import android.content.Context;

import java.util.List;

import io.reactivex.Flowable;
import khaled.example.com.findup.Helper.Database.Interfaces.Comment.Comments;
import khaled.example.com.findup.Helper.Database.Interfaces.DataBaseOnChangeApplied;
import khaled.example.com.findup.Helper.Database.Interfaces.Events;
import khaled.example.com.findup.Helper.Database.Interfaces.Product.ProductPhotos;
import khaled.example.com.findup.Helper.Database.Interfaces.Product.Products;
import khaled.example.com.findup.Helper.Database.Interfaces.Store.StorePhotos;
import khaled.example.com.findup.Helper.Database.Interfaces.Store.Stores;
import khaled.example.com.findup.models.Category;
import khaled.example.com.findup.models.Comment;
import khaled.example.com.findup.models.Event;
import khaled.example.com.findup.models.Product;
import khaled.example.com.findup.models.ProductPhoto;
import khaled.example.com.findup.models.Store;
import khaled.example.com.findup.models.StorePhoto;

public class DBHandler {
    // ***************************************************************** //
    // **************************  Categories ************************** //
    // ***************************************************************** //
    public static void InsertCategory(final Category category, final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FindUpDatabase.getAppDatabase(context).daoAccess().insertCategory(category);
            }
        }).start();
    }

    public static void InsertCategories(final List<Category> categoryList, final Context context, final DataBaseOnChangeApplied onChange) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FindUpDatabase.getAppDatabase(context).daoAccess().insertCategories(categoryList);
                onChange.onSuccess();
            }
        }).start();
    }



    /******************************************************************************
     * Stores Section
     * @param store
     * @param context
     */

    public static void InsertStore(final Store store, final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FindUpDatabase.getAppDatabase(context).daoAccess().insertStore(store);
            }
        }).start();
    }

    public static void getAllStores(final Context context, final Stores stores) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Flowable<List<Store>> listFlowable = FindUpDatabase.getAppDatabase(context).daoAccess().getAllStores();
                stores.onSuccess(listFlowable);
            }
        }).start();
    }

    public static void getStoreByID(int store_id, final Context context, final Stores stores) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Flowable<Store> storeFlowable = FindUpDatabase.getAppDatabase(context).daoAccess().getStoreByID(store_id);
                stores.getStoreID(storeFlowable);
            }
        }).start();
    }

    /*******************************************************************************
     *
     * @param event
     * @param context
     */
    public static void InsertEvent(final Event event, final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FindUpDatabase.getAppDatabase(context).daoAccess().insertEvent(event);
            }
        }).start();
    }

    public static void getAllEvents(final Context context, final Events events) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Flowable<List<Event>> listFlowable = FindUpDatabase.getAppDatabase(context).daoAccess().getAllEvents();
                events.onSuccess(listFlowable);
            }
        }).start();
    }

    /*******************************************************************************
     *
     * @param comment
     * @param context
     */
    public static void InsertComment(final Comment comment, final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FindUpDatabase.getAppDatabase(context).daoAccess().insertComment(comment);
            }
        }).start();
    }

    public static void getAllComments(final Context context, final Comments comment) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Flowable<List<Comment>> listFlowable = FindUpDatabase.getAppDatabase(context).daoAccess().getAllComments();
                comment.onSuccess(listFlowable);
            }
        }).start();
    }

    public static void getCommentByStoreID(int store_id, final Context context, final khaled.example.com.findup.Helper.Database.Interfaces.Comment.Comment comment) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Flowable<List<Comment>> listFlowable = FindUpDatabase.getAppDatabase(context).daoAccess().getCommentsByStoreID(store_id);
                comment.onSuccess(listFlowable);
            }
        }).start();
    }

    /*******************************************************************************
     *                                  Products
     * @param product
     * @param context
     *
     ******************************************************************************/
    public static void InsertPrdouct(final Product product, final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FindUpDatabase.getAppDatabase(context).daoAccess().insertProduct(product);
            }
        }).start();
    }

    public static void getAllProducts(final Context context, final Products products) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Flowable<List<Product>> listFlowable = FindUpDatabase.getAppDatabase(context).daoAccess().getAllProducts();
                products.onSuccess(listFlowable);
            }
        }).start();
    }


    public static void getProductByStoreID(int store_id, final Context context, final Products products) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Flowable<List<Product>> productFlowable = FindUpDatabase.getAppDatabase(context).daoAccess().getProductByStoreID(store_id);
                products.onSuccess(productFlowable);
            }
        }).start();
    }

    public static void getProductByID(int prod_id, final Context context, final Products products) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Flowable<Product> productFlowable = FindUpDatabase.getAppDatabase(context).daoAccess().getProductByID(prod_id);
                products.getProduct(productFlowable);
            }
        }).start();
    }


    /*******************************************************************************
     *                                  Product Photos
     * @param productPhoto
     * @param context
     *
     ******************************************************************************/

    public static void InsertProductPhoto(final ProductPhoto productPhoto, final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FindUpDatabase.getAppDatabase(context).daoAccess().insertProductPhoto(productPhoto);
            }
        }).start();
    }


    public static void getProductPhotosByProductID(int product_id, final Context context, final ProductPhotos productPhotos) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Flowable<List<ProductPhoto>> productFlowable = FindUpDatabase.getAppDatabase(context).daoAccess().getProductPhotoByProductID(product_id);
                productPhotos.onSuccess(productFlowable);
            }
        }).start();
    }


    /*******************************************************************************
     *                                  Store Photos
     * @param storePhotos
     * @param context
     *
     ******************************************************************************/

    public static void InsertStorePhoto(final StorePhoto storePhotos, final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FindUpDatabase.getAppDatabase(context).daoAccess().insertStorePhoto(storePhotos);
            }
        }).start();
    }

    public static void getStorePhotosByStoreID(int store_id, final Context context, final StorePhotos storePhotos) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Flowable<List<StorePhoto>> productFlowable = FindUpDatabase.getAppDatabase(context).daoAccess().getStorePhotoByStoreID(store_id);
                storePhotos.onSuccess(productFlowable);
            }
        }).start();
    }


}
