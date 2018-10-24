package khaled.example.com.findup.Helper.Database.Interfaces.SavedItem;

import java.util.List;

import io.reactivex.Flowable;
import khaled.example.com.findup.models.UserSavedItem;

public interface SavedItem{
    void onSuccess(Flowable<List<UserSavedItem>> listFlowable);
    void onFail();
}
