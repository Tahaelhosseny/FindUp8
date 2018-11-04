package findupproducts.example.com.findup.Helper.FirebaseMessaging;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import findupproducts.example.com.findup.Helper.SharedPrefManger;

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    private String TAG = "MyFirebaseIDService";


    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {
        SharedPrefManger.saveToken(refreshedToken);
    }

}
