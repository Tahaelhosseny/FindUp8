package khaled.example.com.findup.Helper.Database.Interfaces.Store;

import java.util.List;

import io.reactivex.Flowable;
import khaled.example.com.findup.models.StorePhoto;

public interface StorePhotos {
    void onSuccess(Flowable<List<StorePhoto>> prListFlowable);
}
