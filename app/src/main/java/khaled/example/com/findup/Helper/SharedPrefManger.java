package khaled.example.com.findup.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.f2prateek.rx.preferences2.Preference;
import com.f2prateek.rx.preferences2.RxSharedPreferences;

import khaled.example.com.findup.models.CurrentLocation;

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

    //----------------------------------------------------------------------------------
    public static void setIsLoggedIn(boolean value) {
        mSharedPref.edit().putBoolean("isLoggedIn", value).apply();
    }
    public static boolean isIsLoggedIn() {
        return mSharedPref.getBoolean("isLoggedIn", false);
    }

    public static void setUserID(int id){
        mSharedPref.edit().putInt("user_id" , id).apply();
    }
    public static int getUser_ID() {
        return mSharedPref.getInt("user_id", 0);
    }
    //-----------------------------------------------------------------------------------
    public static void setCurrentLocation(CurrentLocation currentLocation){
        mSharedPref.edit().putBoolean("HaveLocation", currentLocation.isEnabled()).apply();
        mSharedPref.edit().putFloat("latitude", (float) currentLocation.getLocation().latitude).apply();
        mSharedPref.edit().putFloat("longitude", (float) currentLocation.getLocation().longitude).apply();
    }
    public static CurrentLocation getCurrentLocation() {
        float latitude =  mSharedPref.getFloat("latitude", 0);
        float longitude =  mSharedPref.getFloat("longitude", 0);
        CurrentLocation currentLocation = new CurrentLocation(latitude,longitude);
        return currentLocation;
    }
    public static Preference<Float> getLatitude() {
        RxSharedPreferences rxPreferences = RxSharedPreferences.create(mSharedPref);
        return rxPreferences.getFloat("latitude");
    }
    public static Preference<Float> getLongitude() {
        RxSharedPreferences rxPreferences = RxSharedPreferences.create(mSharedPref);
        return rxPreferences.getFloat("longitude");
    }
    public static Float getLongitudeF() {
        return mSharedPref.getFloat("longitude", 0);
    }
    public static Float getLatitudeF() {
        return mSharedPref.getFloat("latitude", 0);
    }

    public static void setUSer_name(String value) {
        mSharedPref.edit().putString("user_name", value).apply();
    }

    public static String getUser_name() {
        return mSharedPref.getString("user_name", "");
    }


    //-------------------------------------- User Setting ---------------------------------------------

    public static void setUserSettingsId(int value){
        mSharedPref.edit().putInt("user_settings_id" , value).apply();
    }
    public static int getUserSettingsId() {
        return mSharedPref.getInt("user_settings_id", 0);
    }

    public static void setPushNotiFlag(int value){
        mSharedPref.edit().putInt("push_noti_flags" , value).apply();
    }
    public static int getPushNotiFlag() {
        return mSharedPref.getInt("push_noti_flags", 0);
    }

    public static void setChatNotiFlag(int value){
        mSharedPref.edit().putInt("chat_noti_flags" , value).apply();
    }
    public static int getChatNotiFlag() {
        return mSharedPref.getInt("chat_noti_flags", 0);
    }

    public static void setDistanceTypeId(int value){
        mSharedPref.edit().putInt("distance_type_id" , value).apply();
    }
    public static int getDistanceTypeId() {
        return mSharedPref.getInt("distance_type_id", 0);
    }

    public static void setCurrencyId(int value){
        mSharedPref.edit().putInt("currency_id" , value).apply();
    }
    public static int getCurrencyId() {
        return mSharedPref.getInt("currency_id", 0);
    }

    public static void setUserLanguage(String value){
        mSharedPref.edit().putString("language" , value).apply();
    }
    public static String getUserLanguage() {
        return mSharedPref.getString("language", "");
    }


    public static void setDistanceText(String value){
        mSharedPref.edit().putString("distance_text" , value).apply();
    }
    public static String getDistanceText() {
        return mSharedPref.getString("distance_text", "");
    }
}