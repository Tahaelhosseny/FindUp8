package findupproducts.example.com.findup.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.f2prateek.rx.preferences2.Preference;
import com.f2prateek.rx.preferences2.RxSharedPreferences;

import findupproducts.example.com.findup.models.CurrentLocation;

public class SharedPrefManger {

    private static final String TAG_TOKEN = "tagtoken";
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
    public static void setLoginType(String type){mSharedPref.edit().putString("login_type", type).apply();
    }
    public static String getLogin_type() {
        return mSharedPref.getString("login_type", "");
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
    public static void setStoreID(int id){
        mSharedPref.edit().putInt("store_id" , id).apply();
    }
    public static int getStore_ID() {
        return mSharedPref.getInt("store_id", 0);
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
    public static void setStore_namee(String value) {
        mSharedPref.edit().putString("store_name", value).apply();
    }
    public static String getStore_namee() {
        return mSharedPref.getString("store_name", "");
    }
    public static void setStore_logo(String value) {
        mSharedPref.edit().putString("store_logo", value).apply();
    }
    public static String getStore_logo() {
        return mSharedPref.getString("store_logo", "");
    }
    public static void setStore_banner(String value) {
        mSharedPref.edit().putString("store_banner", value).apply();
    }
    public static String getStore_banner() {
        return mSharedPref.getString("store_banner", "");
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
    //----------------------------------------------------------------------------------------------------------------
    public static void setStoreSettingsId(int value){
        mSharedPref.edit().putInt("store_settings_id" , value).apply();
    }
    public static int getStoreSettingsId() {
        return mSharedPref.getInt("store_settings_id", 0);
    }
    public static void setPushNotiFlagStore(int value){
        mSharedPref.edit().putInt("store_push_noti_flags" , value).apply();
    }
    public static int getPushNotiFlagStore() {
        return mSharedPref.getInt("store_push_noti_flags", 0);
    }
    public static void setChatNotiFlagStore(int value){
        mSharedPref.edit().putInt("store_chat_noti_flags" , value).apply();
    }
    public static int getChatNotiFlagStore() {
        return mSharedPref.getInt("store_chat_noti_flags", 0);
    }
    public static void setCurrencyIdStore(int value){
        mSharedPref.edit().putInt("store_currency_id" , value).apply();
    }
    public static int getCurrencyIdStore() {
        return mSharedPref.getInt("store_currency_id", 0);
    }
    public static void setLanguageStore(String value){
        mSharedPref.edit().putString("store_language" , value).apply();
    }
    public static String getLanguageStore() {
        return mSharedPref.getString("store_language", "");
    }
    public static void setStoreCommentsNoti(int value){
        mSharedPref.edit().putInt("store_comments" , value).apply();
    }
    public static int getStoreCommentsNoti() {
        return mSharedPref.getInt("store_comments", 0);
    }
    public static void setLikesStoreNoti(int value){
        mSharedPref.edit().putInt("store_likes" , value).apply();
    }
    public static int getLikeStoreNoti() {
        return mSharedPref.getInt("store_likes", 0);
    }
    public static void setPermentCurrency(int id){
        mSharedPref.edit().putInt("permant_currency" , id).apply();
    }
    public static int getPermantCurrency(){
        return mSharedPref.getInt("permant_currency" , 0);
    }
    //-----------------------------------------Notification -----------------------------------------------------------

     public static void saveToken(String token){
        mSharedPref.edit().putString("token" , token).apply();
     }
     public static String getToken(){
        return mSharedPref.getString("token" , "");
     }

}