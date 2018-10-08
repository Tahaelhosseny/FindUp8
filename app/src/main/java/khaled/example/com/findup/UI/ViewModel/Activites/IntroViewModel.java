package khaled.example.com.findup.UI.ViewModel.Activites;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Observable;

import khaled.example.com.findup.UI.activities.SignupActivity;

public class IntroViewModel extends Observable {
    private Context mContext;
    public IntroViewModel(Context mContext) {
        this.mContext = mContext;
    }
    public void fbLogin(CallbackManager callbackManager) {
        LoginManager.getInstance().logInWithReadPermissions((Activity) mContext, Arrays.asList( "email", "public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>()
                {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

//                        Toast.makeText(mContext , "Login Success :" + loginResult.getAccessToken().getUserId() + " ---- "
//                                + loginResult.getAccessToken().getToken() , Toast.LENGTH_LONG).show();

                        GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                                        try {
                                            Profile profile = Profile.getCurrentProfile();
                                            String id = jsonObject.getString("id");
                                            String name = jsonObject.getString("name");
                                            String email = jsonObject.getString("email");
                                            String photo = profile.getProfilePictureUri(100,100).toString();
                                            Intent toRegister = new Intent(mContext , SignupActivity.class);
                                            toRegister.putExtra("Name" , name);
                                            toRegister.putExtra("Email" , email);
                                            toRegister.putExtra("Photo" , photo);
                                            toRegister.putExtra("id" , id);
                                            mContext.startActivity(toRegister);
                                            ((Activity) mContext).finish();
                                        }
                                        catch (JSONException e) {
                                            e.printStackTrace();
                                            toast(e.getMessage());
                                        }
                                    }
                                });
                        Bundle bundle = new Bundle();
                        bundle.putString(
                                "fields",
                                "id,name,email,gender"
                        );
                        graphRequest.setParameters(bundle);
                        graphRequest.executeAsync();

                    }

                    @Override
                    public void onCancel()
                    {
                        Toast.makeText(mContext , "Login Cancelled" , Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception)
                    {
                        toast(exception.getMessage().toString());
                    }
                });
    }
    public void twitterLogin(){}

    private void toast(String msg){
        Toast.makeText(mContext, ""+msg, Toast.LENGTH_SHORT).show();
    }
}
