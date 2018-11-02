package findupproducts.example.com.findup.Helper.Remote.ResponseModel;

import java.util.List;

import findupproducts.example.com.findup.models.GetContact;


public class GetChatContactResponse {
    private String tag;
    private int success;
    private int error;
    private List<GetContact> getStoreContacts;

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

    public List<GetContact> getGetStoreContacts() {
        return getStoreContacts;
    }

    public void setGetStoreContacts(List<GetContact> getStoreContacts) {
        this.getStoreContacts = getStoreContacts;
    }
}
