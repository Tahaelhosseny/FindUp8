package khaled.example.com.findup.Helper.Database.Interfaces.Store;

import java.util.List;

import io.reactivex.Flowable;
import khaled.example.com.findup.models.Store;

public interface Stores {
    void onSuccess(Flowable<List<Store>> listFlowable);

    void getStoreID(Flowable<Store> storeFlowable);

    void onFail();
}
