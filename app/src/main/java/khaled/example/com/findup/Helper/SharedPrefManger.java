package khaled.example.com.findup.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefManger {

    private static SharedPreferences mSharedPref;

    public SharedPrefManger(Context mContext) {
        init(mContext);
    }

    public static void init(Context context) {
        if (mSharedPref == null)
            mSharedPref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setLogin_phone(String value) {
        mSharedPref.edit().putString("login_phone", value).apply();
    }

    public static String getLogin_phone() {
        return mSharedPref.getString("login_phone", "");
    }

    public static void setLogin_password(String value) {
        mSharedPref.edit().putString("login_password", value).apply();
    }

    public static String getLogin_password() {
        return mSharedPref.getString("login_password", "");
    }

    public static void setIsLoggedInAsCustomer(boolean value) {
        mSharedPref.edit().putBoolean("isLoggedInAsCustomer", value).apply();
    }

    public static boolean isIsLoggedInAsCustomer() {
        return mSharedPref.getBoolean("isLoggedInAsCustomer", false);
    }

    public static void setIsLoggedIn(boolean value) {
        mSharedPref.edit().putBoolean("isLoggedIn", value).apply();
    }

    public static boolean isIsLoggedIn() {
        return mSharedPref.getBoolean("isLoggedIn", false);
    }
}
