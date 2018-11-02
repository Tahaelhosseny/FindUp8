package findupproducts.example.com.findup.Helper.Remote.ResponseModel;

import java.util.List;

import findupproducts.example.com.findup.models.SendMessage;

public class SendChatResponse {
    private String tag;
    private int success;
    private int error;
    private List<SendMessage> data;

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

    public List<SendMessage> getGetChatMessage() {
        return data;
    }

    public void setGetChatMessage(List<SendMessage> getChatMessage) {
        this.data = getChatMessage;
    }
}
