package khaled.example.com.findup.models;

public class StoreSetting {

    private int sett_id;
    private int store_id;
    private int push_noti_flag;
    private int chat_noti_flag;
    private int like_noti_flag;
    private int comment_noti_flag;
    private String language;
    private int currency_id;

    public StoreSetting(int sett_id, int store_id, int push_noti_flag, int chat_noti_flag, int like_noti_flag, int comment_noti_flag, String language, int currency_id) {
        this.sett_id = sett_id;
        this.store_id = store_id;
        this.push_noti_flag = push_noti_flag;
        this.chat_noti_flag = chat_noti_flag;
        this.like_noti_flag = like_noti_flag;
        this.comment_noti_flag = comment_noti_flag;
        this.language = language;
        this.currency_id = currency_id;
    }

    public int getSett_id() {
        return sett_id;
    }

    public void setSett_id(int sett_id) {
        this.sett_id = sett_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
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

    public int getLike_noti_flag() {
        return like_noti_flag;
    }

    public void setLike_noti_flag(int like_noti_flag) {
        this.like_noti_flag = like_noti_flag;
    }

    public int getComment_noti_flag() {
        return comment_noti_flag;
    }

    public void setComment_noti_flag(int comment_noti_flag) {
        this.comment_noti_flag = comment_noti_flag;
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
