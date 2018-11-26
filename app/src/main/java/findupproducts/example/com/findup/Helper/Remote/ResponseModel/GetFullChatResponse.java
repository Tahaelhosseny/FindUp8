package findupproducts.example.com.findup.Helper.Remote.ResponseModel;

import java.util.ArrayList;
import java.util.List;

import findupproducts.example.com.findup.models.GetChat;

public class GetFullChatResponse {
    private String tag;
    private int success;
    private int error;
    private List<GetChat> data = new ArrayList<GetChat>();

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

    public List<GetChat> getGetChatMessage() {
        return data;
    }

    public void setGetChatMessage(List<GetChat> getChatMessage) {
        this.data = getChatMessage;
    }
}
