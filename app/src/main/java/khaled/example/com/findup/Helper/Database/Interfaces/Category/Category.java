package khaled.example.com.findup.Helper.Database.Interfaces.Category;

import java.util.List;

import io.reactivex.Flowable;

public interface Category {
        void onSuccess(Flowable<List<khaled.example.com.findup.models.Category>> listFlowable);
        void onFail();
}
