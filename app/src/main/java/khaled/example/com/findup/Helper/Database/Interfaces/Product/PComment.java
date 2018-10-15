package khaled.example.com.findup.Helper.Database.Interfaces.Product;

import java.util.List;

import io.reactivex.Flowable;
import khaled.example.com.findup.models.PCommentModel;

public interface PComment {

    void onSuccess(Flowable<List<PCommentModel>> commentFlowable);

    void onFail();


}
