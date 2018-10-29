package khaled.example.com.findup.Helper.Remote.ResponseModel;

import java.util.List;

import khaled.example.com.findup.models.Search;

public class SearchStoreResponse {
    private String tag;
    private int success;
    private int error;
    private String error_msg;
    private List<Search> data;

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

    public List<Search> getData() {
        return data;
    }

    public void setData(List<Search> data) {
        this.data = data;
    }

}
