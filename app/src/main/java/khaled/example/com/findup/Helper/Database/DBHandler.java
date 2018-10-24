package khaled.example.com.findup.Helper.Database;

import android.content.Context;
import android.util.Log;

import java.util.List;

import io.reactivex.Flowable;
import khaled.example.com.findup.Helper.Database.Interfaces.Comment.Comments;
import khaled.example.com.findup.Helper.Database.Interfaces.DataBaseOnChangeApplied;
import khaled.example.com.findup.Helper.Database.Interfaces.Events;
import khaled.example.com.findup.Helper.Database.Interfaces.Notifications.NotificationsStoreI;
import khaled.example.com.findup.Helper.Database.Interfaces.Notifications.NotificationsUserI;
import khaled.example.com.findup.Helper.Database.Interfaces.Product.PComment;
import khaled.example.com.findup.Helper.Database.Interfaces.Product.ProductPhotos;
import khaled.example.com.findup.Helper.Database.Interfaces.Product.Products;
import khaled.example.com.findup.Helper.Database.Interfaces.SavedItem.SavedItem;
import khaled.example.com.findup.Helper.Database.Interfaces.Store.StorePhotos;
import khaled.example.com.findup.Helper.Database.Interfaces.Store.Stores;
import khaled.example.com.findup.models.Category;
import khaled.example.com.findup.models.Comment;
import khaled.example.com.findup.models.Event;
import khaled.example.com.findup.models.NotificationStore;
import khaled.example.com.findup.models.NotificationUser;
import khaled.example.com.findup.models.Product;
import khaled.example.com.findup.models.ProductComment;
import khaled.example.com.findup.models.ProductPhoto;
import khaled.example.com.findup.models.Store;
import khaled.example.com.findup.models.StorePhoto;
import khaled.example.com.findup.models.UserSavedItem;
public class DBHandler {

    //*****************************Notifications************************************* //
    public static void InsertUserNotifications(final NotificationUser notificationUser, final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FindUpDatabase.getAppDatabase(context).daoAccess().insertUserNotification(notificationUser);
            }
        }).start();
    }
    public static void InsertStoreNotifications(final NotificationStore notificationStore, final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FindUpDatabase.getAppDatabase(context).daoAccess().insertStoreNotification(notificationStore);
            }
        }).start();
    }

    public static void InsertSavedItem(final UserSavedItem userSavedItem, final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FindUpDatabase.getAppDatabase(context).daoAccess().insertSavedItem(userSavedItem);
            }
        }).start();
    }
//
    public static void InsertProductComments(final ProductComment commentModel, final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FindUpDatabase.getAppDatabase(context).daoAccess().insertProductComment(commentModel);
            }
        }).start();
    }

    public static void DeleteSavedItem(final UserSavedItem userSavedItem, final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FindUpDatabase.getAppDatabase(context).daoAccess().deleteSavedItem(userSavedItem);
            }
        }).start();
    }

    public static void getAllSavedItem(final Context context, final SavedItem savedItem) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Flowable<List<UserSavedItem>> listFlowable = FindUpDatabase.getAppDatabase(context).daoAccess().getAllSaved();
                savedItem.onSuccess(listFlowable);
            }
        }).start();
    }

    public static void getAllUserNotification(final Context context, final NotificationsUserI notificationsUserI) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Flowable<List<NotificationUser>> listFlowable = FindUpDatabase.getAppDatabase(context).daoAccess().getAllUserNotification();
                notificationsUserI.onSuccess(listFlowable);
            }
        }).start();
    }

    public static void getAllStoreNotification(final Context context, final NotificationsStoreI notificationsStoreI) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Flowable<List<NotificationStore>> listFlowable = FindUpDatabase.getAppDatabase(context).daoAccess().getAllStoreNotification();
                notificationsStoreI.onSuccess(listFlowable);
            }
        }).start();
    }
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




    public static void GetHomeCategories(final Context context, final khaled.example.com.findup.Helper.Database.Interfaces.Category.Category category) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Flowable<List<Category>> listFlowable = FindUpDatabase.getAppDatabase(context).daoAccess().getCategoryInHome();
                category.onSuccess(listFlowable);
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

    public static void getEventByStoreID(int store_id , final Context context, final Events events) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Flowable<List<Event>> listFlowable = FindUpDatabase.getAppDatabase(context).daoAccess().getEventByID(store_id);
                events.onSuccess(listFlowable);
            }
        }).start();
    }

    /*******************************************************************************
     *
     * @param commentList
     * @param context
     */
    public static void InsertComments(final List<Comment> commentList, final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < commentList.size();i++)
                    FindUpDatabase.getAppDatabase(context).daoAccess().insertComment(commentList.get(i));
            }
        }).start();
    }

    public static void InsertProductsComments(final List<ProductComment> commentList, final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i<commentList.size();i++)
                    FindUpDatabase.getAppDatabase(context).daoAccess().insertProductComment(commentList.get(i));
            }
        }).start();
    }

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

    public static void getCommentByProductID(int product_id , final Context context, final PComment comment) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Flowable<List<ProductComment>> listFlowable = FindUpDatabase.getAppDatabase(context).daoAccess().getCommentsByProductID(product_id);
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



    public static void SaveStore(Store store,int if_saved, final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FindUpDatabase.getAppDatabase(context).daoAccess().SaveStoreOperation(store.getStore_id(),if_saved);
                //products.onSuccess(null);
            }
        }).start();
    }


    /*******************************************************************************
     *                                  Product Photos
     * @param productPhotos
     * @param context
     *
     ******************************************************************************/

    public static void InsertProductPhoto(final List<ProductPhoto> productPhotos, final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e("productPhotos", ""+productPhotos.size());
                for (int i = 0;i<productPhotos.size();i++)
                    FindUpDatabase.getAppDatabase(context).daoAccess().insertProductPhoto(productPhotos.get(0));
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

    public static void ProductPhotos(final ProductPhoto productPhoto, final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
               FindUpDatabase.getAppDatabase(context).daoAccess().insertProductPhoto(productPhoto);
            }
        }).start();
    }
}
