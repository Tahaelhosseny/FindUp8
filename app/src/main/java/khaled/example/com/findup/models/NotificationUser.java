package khaled.example.com.findup.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by khaled on 8/1/18.
 */
@Entity
public class NotificationUser {
    @SerializedName("noti_id")
    @Expose
    @PrimaryKey @NonNull
    private int notificationId;
    @SerializedName("noti_title")
    @Expose @NonNull
    private String notificationTitle;
    @SerializedName("noti_date")
    @Expose @NonNull
    private String notificationDate;
    @SerializedName("noti_desc")
    @Expose @NonNull
    private String notificationDesc;
    @SerializedName("noti_image")
    @Expose @NonNull
    private String notificationImg;
    @SerializedName("noti_type")
    @Expose @NonNull
    private String notificationType;
    @SerializedName("read_flag")
    @Expose @NonNull
    private int read_flag;
    @SerializedName("acc_id")@Expose @NonNull
    private int account_id;

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(String notificationDate) {
        this.notificationDate = notificationDate;
    }

    public String getNotificationDesc() {
        return notificationDesc;
    }

    public void setNotificationDesc(String notificationDesc) {
        this.notificationDesc = notificationDesc;
    }

    public String getNotificationImg() {
        return notificationImg;
    }

    public void setNotificationImg(String notificationImg) {
        this.notificationImg = notificationImg;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public int getRead_flag() {
        return read_flag;
    }

    public void setRead_flag(int read_flag) {
        this.read_flag = read_flag;
    }
}
