package khaled.example.com.findup.Helper.Database.Interfaces.Comment;

import java.util.List;

import io.reactivex.Flowable;

public interface Comment {
    void onSuccess(Flowable<List<khaled.example.com.findup.models.Comment>> commentFlowable);

    void onFail();
}
