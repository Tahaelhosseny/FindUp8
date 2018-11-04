package findupproducts.example.com.findup.Helper.FirebaseMessaging;
import android.content.Intent;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import org.json.JSONException;
import org.json.JSONObject;

import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.UI.activities.MainActivity;
import findupproducts.example.com.findup.UI.activities.MainStoreActivity;
import findupproducts.example.com.findup.UI.activities.SplashScreenActivity;
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                sendPushNotification(json);
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }
    }

    //this method will display the notification
    //We are passing the JSONObject that is received from
    //firebase cloud messaging
    private void sendPushNotification(JSONObject json) {
        //optionally we can display the json into log
        Log.e(TAG, "Notification JSON " + json.toString());
        try {
            //getting the json data
            JSONObject data = json.getJSONObject("data");

            //parsing json data
            String title = data.getString("title");
            String message = data.getString("message");
            String imageUrl = data.getString("image");

            //creating MyNotificationManager object
            MyNotificationManager mNotificationManager = new MyNotificationManager(getApplicationContext());

            //creating an intent for the notification
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            Intent intent1 = new Intent(getApplicationContext() , MainStoreActivity.class);
            //if there is no image
            if(imageUrl.equals("null")){
                //displaying small notification
                if(SharedPrefManger.getUser_ID() != 0){
                    mNotificationManager.showSmallNotification(title, message, intent);
                }else{
                    mNotificationManager.showSmallNotification(title, message, intent1);
                }
            }else{
                if(SharedPrefManger.getUser_ID() != 0){
                    mNotificationManager.showBigNotification(title, message, imageUrl, intent);
                }else{
                    mNotificationManager.showBigNotification(title, message, imageUrl, intent1);
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

}
