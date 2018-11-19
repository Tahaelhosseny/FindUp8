package findupproducts.example.com.findup.Helper.Remote.ResponseModel;

import java.util.List;

import findupproducts.example.com.findup.models.GetContact;
import findupproducts.example.com.findup.models.Store;

public class StoreForChat {
    private String tag;
    private int success;
    private int error;
    private List<Store> data;

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

    public List<Store> getGetStoreContacts() {
        return data;
    }

    public void setGetStoreContacts(List<Store> getStoreContacts) {
        this.data = getStoreContacts;
    }
}
