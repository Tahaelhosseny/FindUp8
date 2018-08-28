package khaled.example.com.findup.Helper.Remote.ResponseModel;

import java.util.List;

import khaled.example.com.findup.models.User;

public class LoginResponse {

    private String tag;
    private int success;
    private int error;
    private String error_msg;
    private List<User> user_data;

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public List<User> getUser_data() {
        return user_data;
    }

    public void setUser_data(List<User> user_data) {
        this.user_data = user_data;
    }


}
