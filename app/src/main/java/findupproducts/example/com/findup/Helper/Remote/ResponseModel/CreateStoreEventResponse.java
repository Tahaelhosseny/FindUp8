package findupproducts.example.com.findup.Helper.Remote.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import findupproducts.example.com.findup.models.Comment;
import findupproducts.example.com.findup.models.Event;

public class CreateStoreEventResponse {
    @SerializedName("tag") @Expose
    String tag;
    @SerializedName("success") @Expose
    int success;
    @SerializedName("error") @Expose
    int error;
    @SerializedName("error_msg") @Expose
    String error_msg;
    @SerializedName("error_photo") @Expose
    String error_photo;
    @SerializedName("data")
    @Expose
    private List<Event> data;
    @SerializedName("id") @Expose
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getError_photo() {
        return error_photo;
    }

    public void setError_photo(String error_photo) {
        this.error_photo = error_photo;
    }

    public List<Event> getData() {
        return data;
    }

    public void setData(List<Event> data) {
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
