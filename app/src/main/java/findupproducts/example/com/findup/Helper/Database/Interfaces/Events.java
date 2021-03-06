package findupproducts.example.com.findup.Helper.Database.Interfaces;

import java.util.List;

import io.reactivex.Flowable;
import findupproducts.example.com.findup.models.Event;

public interface Events {
    void onSuccess(Flowable<List<Event>> listFlowable);

    void onFail();
}
