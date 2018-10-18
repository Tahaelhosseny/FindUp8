package khaled.example.com.findup.Helper.Database;

import android.content.Context;

import java.util.List;

import khaled.example.com.findup.models.Category;
import khaled.example.com.findup.models.Comment;
import khaled.example.com.findup.models.Event;
import khaled.example.com.findup.models.PCommentModel;
import khaled.example.com.findup.models.Product;
import khaled.example.com.findup.models.ProductPhoto;
import khaled.example.com.findup.models.Store;
import khaled.example.com.findup.models.StorePhoto;

public class DBUtility {
    public static long InsertStores(List<Store> storeList, Context mContext) {
        int sum = 0;
        for (Store store : storeList) {
            DBHandler.InsertStore(store, mContext);
            InsertComments(store.getStore_comments(), mContext);
            InsertProducts(store.getStore_products(), mContext);
            InsertStorePhotos(store.getStore_images(), mContext);
            sum++;
        }
        return (sum == storeList.size()) ? 1 : 0;
    }


    public static long InsertComments(List<Comment> commentList, Context mContext) {
        int sum = 0;
        for (Comment comment : commentList) {
            DBHandler.InsertComment(comment, mContext);
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
//            DBHandler.InsertProductPhoto((ProductPhoto) productList.get(sum).getProductPhotos(), mContext);
            sum++;
        }
        return (sum == productList.size()) ? 1 : 0;
    }

    public static long InsertProductPhotos(List<ProductPhoto> productPhotos, Context mContext) {
        int sum = 0;
        for (ProductPhoto productPhoto : productPhotos) {
            DBHandler.InsertProductPhoto(productPhoto, mContext);
            sum++;
        }
        return (sum == productPhotos.size()) ? 1 : 0;
    }
//
//        public static long InsertProductComment(List<PCommentModel> pCommentModels, Context mContext) {
//        int sum = 0;
//        for (PCommentModel pCommentModel : pCommentModels) {
//            DBHandler.InsertProductCommens(pCommentModel, mContext);
//            sum++;
//        }
//        return (sum == pCommentModels.size()) ? 1 : 0;
//    }

    public static long InsertStorePhotos(List<StorePhoto> storePhotos, Context mContext) {
        int sum = 0;
        for (StorePhoto storePhoto : storePhotos) {
            DBHandler.InsertStorePhoto(storePhoto, mContext);
            sum++;
        }
        return (sum == storePhotos.size()) ? 1 : 0;
    }
}