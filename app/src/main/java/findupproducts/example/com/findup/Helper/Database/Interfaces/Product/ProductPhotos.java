package findupproducts.example.com.findup.Helper.Database.Interfaces.Product;

import java.util.List;

import io.reactivex.Flowable;
import findupproducts.example.com.findup.models.ProductPhoto;

public interface ProductPhotos {
    void onSuccess(Flowable<List<ProductPhoto>> prListFlowable);
}
