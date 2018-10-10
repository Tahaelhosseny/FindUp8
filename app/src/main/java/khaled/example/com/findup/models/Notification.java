package khaled.example.com.findup.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by khaled on 8/1/18.
 */

public class Notification {
    @SerializedName("noti_id")
    @Expose
    private int notificationId;
    @SerializedName("noti_title")
    @Expose
    private String notificationType;
    @SerializedName("noti_desc")
    @Expose
    private String notificationDate;
    @SerializedName("noti_date")
    @Expose
    private String notificationDesc;
    @SerializedName("noti_image")
    @Expose
    private String notificationImg;
    @SerializedName("noti_type")
    @Expose
    private String notificationTitle;
    @SerializedName("read_flag")
    @Expose
    private int read_flag;

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
