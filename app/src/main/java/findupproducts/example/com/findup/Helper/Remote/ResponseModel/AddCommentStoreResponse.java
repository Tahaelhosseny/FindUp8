package findupproducts.example.com.findup.Helper.Remote.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

import findupproducts.example.com.findup.models.Comment;

public class AddCommentStoreResponse{
    @SerializedName("tag") @Expose
    String tag;
    @SerializedName("success") @Expose
    int success;
    @SerializedName("error") @Expose
    int error;
    @SerializedName("error_msg") @Expose
    String error_msg;
    @SerializedName("data")
    @Expose
    private List<Comment> data;


    public List<Comment> getData() {
        return data;
    }

    public void setData(List<Comment> data) {
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

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }
}
