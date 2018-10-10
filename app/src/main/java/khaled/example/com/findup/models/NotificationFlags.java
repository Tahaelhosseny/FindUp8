package khaled.example.com.findup.models;

public class NotificationFlags {
    private int sett_id;
    private int account_id;
    private int push_noti_flag;
    private int chat_noti_flag;
    private int distance_type_id;
    private String language;
    private int currency_id;

    public int getSett_id() {
        return sett_id;
    }

    public void setSett_id(int sett_id) {
        this.sett_id = sett_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getPush_noti_flag() {
        return push_noti_flag;
    }

    public void setPush_noti_flag(int push_noti_flag) {
        this.push_noti_flag = push_noti_flag;
    }

    public int getChat_noti_flag() {
        return chat_noti_flag;
    }

    public void setChat_noti_flag(int chat_noti_flag) {
        this.chat_noti_flag = chat_noti_flag;
    }

    public int getDistance_type_id() {
        return distance_type_id;
    }

    public void setDistance_type_id(int distance_type_id) {
        this.distance_type_id = distance_type_id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(int currency_id) {
        this.currency_id = currency_id;
    }
}
