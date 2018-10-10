package khaled.example.com.findup.Helper.Remote.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import khaled.example.com.findup.models.AskCode;
import khaled.example.com.findup.models.Currency;

public class AskCodeResponse {
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("success")
    @Expose
    private int success;
    @SerializedName("error")
    @Expose
    private int error;
    @SerializedName("error_msg")
    @Expose
    private int error_msg;
    @SerializedName("data")
    @Expose
    private List<AskCode> data;

    public int getError_msg() {
        return error_msg;
    }

    public void setError_msg(int error_msg) {
        this.error_msg = error_msg;
    }

    public List<AskCode> getData() {
        return data;
    }

    public void setData(List<AskCode> data) {
        this.data = data;
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

    public List<AskCode> getUser_data() {
        return data;
    }

    public void setUser_data(List<AskCode> user_data) {
        this.data = user_data;
    }

}
