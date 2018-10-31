package findupproducts.example.com.findup.Helper.Database.Interfaces.Product;

import java.util.List;

import io.reactivex.Flowable;
import findupproducts.example.com.findup.models.ProductComment;

public interface PComment {

    void onSuccess(Flowable<List<ProductComment>> commentFlowable);

    void onFail();


}
