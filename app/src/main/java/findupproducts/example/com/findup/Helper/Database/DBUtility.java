package findupproducts.example.com.findup.Helper.Database;

import android.content.Context;

import java.util.List;

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

public class DBUtility {
    public static long InsertStores(List<Store> storeList, Context mContext) {
        int sum = 0;
        DBHandler.deleteAllStoreData(mContext);
        for (Store store : storeList) {
            DBHandler.InsertStore(store, mContext);
            InsertComments(store.getStore_comments(), mContext);
            InsertProducts(store.getStore_products(), mContext);
            InsertStoresWorkTimes(store.getStore_worktime() , mContext);
            for (Product product : store.getStore_products()) {
                InsertProductPhotos(product.getProductPhotos(), mContext);
                InsertProductComments(product.getProduct_comments() , mContext);
            }
            InsertStorePhotos(store.getStore_images(), mContext);
            sum++;
        }
        return (sum == storeList.size()) ? 1 : 0;
    }
    public static long InsertUserNotification(List<NotificationUser> notification, Context mContext){
        int sum = 0 ;
        for(NotificationUser notificationUser : notification){
            DBHandler.InsertUserNotifications(notificationUser , mContext);
            sum++;
        }
        return (sum == notification.size()) ? 1 : 0;
    }

    public static long InsertStoresWorkTimes(List<Store_WorkTime> store_workTimes, Context mContext){
        int sum = 0 ;
        for(Store_WorkTime store_workTime : store_workTimes){
            DBHandler.InsertStoreWorkTime(store_workTime , mContext);
            sum++;
        }
        return (sum == store_workTimes.size()) ? 1 : 0;
    }

    public static long InsertStoreNotification(List<NotificationStore> notification, Context mContext){
        int sum = 0 ;
        for(NotificationStore notificationStore : notification){
            DBHandler.InsertStoreNotifications(notificationStore , mContext);
            sum++;
        }
        return (sum == notification.size()) ? 1 : 0;
    }
    public static long InsertAllSavedUserItem(List<UserSavedItem> items , Context mContext){
        int sum = 0;
        for (UserSavedItem userSavedItem : items){
            DBHandler.InsertSavedItem(userSavedItem , mContext);
        }
        return (sum == items.size()) ? 1 : 0;
    }
    public static long InsertComments(List<Comment> commentList, Context mContext) {
        int sum = 0;
        for (Comment comment : commentList) {
            DBHandler.InsertComment(comment, mContext);
            sum++;
        }
        return (sum == commentList.size()) ? 1 : 0;
    }
    public static long InsertProductComments(List<ProductComment> commentList, Context mContext) {
        int sum = 0;
        for (ProductComment comment : commentList) {
            DBHandler.InsertProductComments(comment, mContext);
            sum++;
        }
        return (sum == commentList.size()) ? 1 : 0;
    }
    public static long InsertEvents(List<Event> eventList, Context mContext) {
        int sum = 0;
        for (Event event : eventList) {
            DBHandler.InsertEvent(event, mContext);
            sum++;
        }
        return (sum == eventList.size()) ? 1 : 0;
    }
    public static long InsertCategories(List<Category> categoryList, Context mContext) {
        int sum = 0;
        for (Category category : categoryList) {
            DBHandler.InsertCategory(category, mContext);
            sum++;
        }
        return (sum == categoryList.size()) ? 1 : 0;
    }
    public static long InsertProducts(List<Product> productList, Context mContext) {
        int sum = 0;
        for (Product product : productList) {
            DBHandler.InsertPrdouct(product, mContext);
            sum++;
        }
        return (sum == productList.size()) ? 1 : 0;
    }
    public static long InsertProductPhotos(List<ProductPhoto> productPhotos, Context mContext) {
        int sum = 0;
        for (ProductPhoto productPhoto : productPhotos){
            DBHandler.ProductPhotos(productPhoto, mContext);
            sum++;
        }
        return (sum == productPhotos.size()) ? 1 : 0;
    }
    public static long InsertStorePhotos(List<StorePhoto> storePhotos, Context mContext) {
        int sum = 0;
        for (StorePhoto storePhoto : storePhotos) {
            DBHandler.InsertStorePhoto(storePhoto, mContext);
            sum++;
        }
        return (sum == storePhotos.size()) ? 1 : 0;
    }
}