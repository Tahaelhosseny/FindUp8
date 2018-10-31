package findupproducts.example.com.findup.Helper.Database.Interfaces.Comment;

import java.util.List;

import io.reactivex.Flowable;
import findupproducts.example.com.findup.models.Comment;

public interface Comments {
    void onSuccess(Flowable<List<Comment>> listFlowable);

    void onFail();
}
