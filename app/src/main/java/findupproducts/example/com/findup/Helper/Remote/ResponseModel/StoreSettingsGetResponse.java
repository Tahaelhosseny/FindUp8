package findupproducts.example.com.findup.Helper.Remote.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import findupproducts.example.com.findup.models.StoreSetting;

public class StoreSettingsGetResponse {
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
    private String error_msg;
    @SerializedName("data")
    @Expose
    private List<StoreSetting> data;

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

    public List<StoreSetting> getData() {
        return data;
    }

    public void setData(List<StoreSetting> data) {
        this.data = data;
    }
}
