package khaled.example.com.findup.Helper.Database.Interfaces;

import java.util.List;

import io.reactivex.Flowable;
import khaled.example.com.findup.models.Event;
import khaled.example.com.findup.models.Store;

public interface Events {
    void onSuccess(Flowable<List<Event>> listFlowable);
    void onFail();
}
