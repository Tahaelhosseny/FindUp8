package findupproducts.example.com.findup.Helper.Database.Interfaces.Store;

import java.util.List;

import findupproducts.example.com.findup.models.Store;
import findupproducts.example.com.findup.models.Store_WorkTime;
import io.reactivex.Flowable;

public interface StoreWorkTimeI {
    void onSuccess(Flowable<List<Store_WorkTime>> listFlowable);

    void getStoreID(Flowable<Store_WorkTime> store_workTimeFlowable);

    void onFail();
}
