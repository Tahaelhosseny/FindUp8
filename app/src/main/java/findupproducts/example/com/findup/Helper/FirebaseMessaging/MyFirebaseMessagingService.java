package findupproducts.example.com.findup.Helper.FirebaseMessaging;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import findupproducts.example.com.findup.UI.activities.SplashScreenActivity;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if(remoteMessage.getData().size() > 1){
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            try {
                JSONObject jsonObject = new JSONObject(remoteMessage.getData().toString());
                sendPushNotification(jsonObject);
            }catch (Exception e){
                Log.e("Exception" , e.getMessage());
            }
        }
    }

    private void sendPushNotification(JSONObject jsonObject) {
        Log.e(TAG , "Notification Json " + jsonObject.toString());
        try {
            JSONObject data = jsonObject.getJSONObject("data");
            String title = data.getString("title");
            String message = data.getString("message");
            String imageUrl = data.getString("image");
            MyNotificationManager myNotificationManager = new MyNotificationManager(getApplicationContext());
            Intent intent = new Intent(getApplicationContext() , SplashScreenActivity.class);
            if(imageUrl.equals("null")){
                //displaying small notification
                myNotificationManager.showSmallNotification(title, message, intent);
            }else{
                //if there is an image
                //displaying a big notification
                myNotificationManager.showBigNotification(title, message, imageUrl, intent);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
