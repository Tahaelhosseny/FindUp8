package findupproducts.example.com.findup.Helper.Database.Interfaces.Notifications;
import java.util.List;
import io.reactivex.Flowable;
import findupproducts.example.com.findup.models.NotificationUser;

public interface NotificationsUserI {
    void onSuccess(Flowable<List<NotificationUser>> listFlowable);
}
