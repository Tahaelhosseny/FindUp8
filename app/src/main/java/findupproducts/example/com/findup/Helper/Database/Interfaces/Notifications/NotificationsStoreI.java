package findupproducts.example.com.findup.Helper.Database.Interfaces.Notifications;

import java.util.List;

import io.reactivex.Flowable;
import findupproducts.example.com.findup.models.NotificationStore;

public interface NotificationsStoreI {
    void onSuccess(Flowable<List<NotificationStore>> listFlowable);
}
