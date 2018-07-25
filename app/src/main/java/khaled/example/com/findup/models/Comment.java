package khaled.example.com.findup.models;

public class Comment {
    private int cm_id;
    private String user_name;
    private int user_id;
    private int prod_id;
    private long date;
    private String comment;
    private String user_profile_pic;

    public Comment() {
    }

    public Comment(String user_name, long date, String comment, String user_profile_pic) {
        this.user_name = user_name;
        this.date = date;
        this.comment = comment;
        this.user_profile_pic = user_profile_pic;
    }

    public int getCm_id() {
        return cm_id;
    }

    public void setCm_id(int cm_id) {
        this.cm_id = cm_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUser_profile_pic() {
        return user_profile_pic;
    }

    public void setUser_profile_pic(String user_profile_pic) {
        this.user_profile_pic = user_profile_pic;
    }
}
