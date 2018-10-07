package khaled.example.com.findup.Helper.Database.Interfaces.Product;

import java.util.List;

import io.reactivex.Flowable;
import khaled.example.com.findup.models.Product;

public interface Products {
    void onSuccess(Flowable<List<Product>> listFlowable);
    void getProduct(Flowable<Product> productFlowable);
    void onFail();
}
