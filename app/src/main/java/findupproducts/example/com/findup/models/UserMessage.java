package findupproducts.example.com.findup.models;

public class UserMessage {
    private int userType;
    private String msgBody;

    public UserMessage(int userType, String msgBody) {
        this.userType = userType;
        this.msgBody = msgBody;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }
}
