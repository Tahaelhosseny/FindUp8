package khaled.example.com.findup.Helper.Remote;
import com.google.android.gms.common.internal.ShowFirstParty;

import java.io.File;

import khaled.example.com.findup.CONST;
import khaled.example.com.findup.Helper.Remote.ResponseModel.AddCommentStoreResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.AskCodeResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.CreateStoreEventResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.CreateStoreResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.CurrencyResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.EditProfileResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.EventResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.LoginResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.MeasureDistanceResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.NotificationFlagResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.NotificationResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.RateResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.RegisterResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.ResetPasswordResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.SaveModelResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.StoresResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.UserSettingsResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.VerifyCodeResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    String HASH = CONST.API_HASH;
    @GET(ApiClient.PATH_URL+"reg_login?tag=login&HashSecure="+HASH)
    Call<LoginResponse> LoginRequest(@Query("mobile") String mobile,@Query("password") String password);

    @GET(ApiClient.PATH_URL+"stores?tag=get_all_stores&HashSecure="+HASH)
    Call<StoresResponse> GetAllStores(@Query("account_id") int account_id);

    @GET(ApiClient.PATH_URL+"stores?tag=get_all_events&HashSecure="+HASH)
    Call<EventResponse> GetAllEvents(@Query("account_id") int account_id);

    @GET(ApiClient.PATH_URL+"user_profile?tag=get_user_currency&HashSecure="+HASH)
    Call<CurrencyResponse> getUserCurrency(@Query("account_id") String account_id);

    @GET(ApiClient.PATH_URL+"user_profile?tag=get_user_measure_distance&HashSecure="+HASH)
    Call<MeasureDistanceResponse> getUserDistance(@Query("account_id") String account_id);

    @GET(ApiClient.PATH_URL+"user_profile?tag=get_all_currency&HashSecure="+HASH)
    Call<CurrencyResponse> getAllCurrency();

    @GET(ApiClient.PATH_URL+"user_profile?tag=get_measure_distance&HashSecure="+HASH)
    Call<MeasureDistanceResponse> getAllMeasureDistance();

    @GET(ApiClient.PATH_URL+"user_profile?tag=get_user_notifications&HashSecure="+HASH)
    Call<NotificationResponse> getUserNotification(@Query("account_id") String account_id);

    @GET(ApiClient.PATH_URL+"user_profile?tag=get_user_saved&HashSecure="+HASH)
    Call<SaveModelResponse> getUserSaved(@Query("account_id") int account_id);


    @GET(ApiClient.PATH_URL+"reg_login?tag=verify_code&HashSecure="+ HASH)
    Call<VerifyCodeResponse> checkVerifyCode(@Query("mobile") String mobile , @Query("code") String code);


    @GET(ApiClient.PATH_URL+"reg_login?tag=forget_pass&HashSecure="+HASH)
    Call<AskCodeResponse> getResetPasswordCode(@Query("mobile") String mobile);

    @GET(ApiClient.PATH_URL+"user_profile?tag=get_user_setting&HashSecure="+HASH)
    Call<UserSettingsResponse> getUserSetting(@Query("account_id") int account_id);


    //----------------------------------------------- Post Methods -------------------------------------------------

    
    @POST(ApiClient.PATH_URL+"reg_login?tag=signup&HashSecure="+HASH)
    @FormUrlEncoded
    Call<RegisterResponse> registerNewUser(@Field("user_type") String user_type , @Field("name") String user_name
                , @Field("password") String password , @Field("mobile") String user_mob , @Field("logged_type") String logged_type
                , @Field("email") String user_mail);


    @POST(ApiClient.PATH_URL+"user_actions?tag=add_store_comment&HashSecure="+HASH)
    @FormUrlEncoded
    Call<AddCommentStoreResponse> addNewStoreComment(@Field("account_id") int account_id , @Field("store_id") int store_id
     , @Field("comment") String comment);


    //Create New Event
    @POST(ApiClient.PATH_URL+"stores?tag=create_event&HashSecure="+HASH)
    @FormUrlEncoded
    Call<CreateStoreEventResponse> addNewStoreEvent(@Field("event_name") String event_name , @Field("event_start_date") String event_start_date
            , @Field("event_days") String event_days , @Field("event_time") String event_time
            , @Field("event_description") String event_description , @Field("event_address") String event_address
            , @Field("store_id") int store_id , @Field("event_longitude") double event_longitude
            , @Field("event_latitude") double event_latitude , @Field("event_photo") String event_photo);


    @POST(ApiClient.PATH_URL+"reg_login?tag=edit_profile&HashSecure="+HASH)
    @FormUrlEncoded
    Call<EditProfileResponse> editProfileData(@Field("account_id") int account_id , @Field("username") String username
     ,@Field("old_password") String old_password , @Field("new_password") String new_password , @Field("mobile") String mobile );


    @POST(ApiClient.PATH_URL+"user_profile?tag=set_user_currency&HashSecure="+HASH)
    @FormUrlEncoded
    Call<CurrencyResponse> setUserCurrency(@Field("currency_id") int currency_id , @Field("account_id") String account_id);


    @POST(ApiClient.PATH_URL+"user_profile?tag=set_user_distance&HashSecure="+HASH)
    @FormUrlEncoded
    Call<MeasureDistanceResponse> setUserDistance(@Field("distance_id") int distance_id , @Field("account_id") int account_id);


    @POST(ApiClient.PATH_URL+"user_profile?tag=set_user_noti_setting&HashSecure="+HASH)
    @FormUrlEncoded
    Call<NotificationFlagResponse> setNotificationFlags(@Query("account_id") int account_id , @Field("push_noti_flag") int push_noti_flag
            , @Field("chat_noti_flag") int chat_noti_flag);


    @POST(ApiClient.PATH_URL+"user_actions?tag=add_to_save&HashSecure="+HASH)
    @FormUrlEncoded
    Call<SaveModelResponse> addToSaved(@Field("account_id") int account_id , @Field("saved_id") int saved_id , @Field("saved_type") String saved_type);


    @POST(ApiClient.PATH_URL+"reg_login?tag=update_new_pass&HashSecure="+HASH)
    @FormUrlEncoded
    Call<ResetPasswordResponse> resetNewPassword(@Field("mobile") String mobile , @Field("new_password") String new_password);


    @POST(ApiClient.PATH_URL+"user_actions?tag=add_product_rate&HashSecure="+HASH)
    @FormUrlEncoded
    Call<RateResponse> rateProduct(@Field("account_id") int account_id , @Field("rate") float rate , @Field("product_id") int product_id);


    @POST(ApiClient.PATH_URL+"user_actions?tag=add_store_rate&HashSecure="+HASH)
    @FormUrlEncoded
    Call<RateResponse> rateStore(@Field("account_id") int account_id , @Field("rate") float rate , @Field("store_id") int store_id);

    @POST(ApiClient.PATH_URL+"stores?tag=create_store_account&HashSecure="+HASH)
    @FormUrlEncoded
    Call<CreateStoreResponse> createNewStore(
            @Field("store_desc") String store_desc ,
            @Field("country_id") int country_id ,
            @Field("city_id") int city_id ,
            @Field("location_type") String location_type ,
            @Field("mobile") String mobile ,
            @Field("password") String password ,
            @Field("twitter_link") String twitter_link ,
            @Field("instegram_link") String instegram_link ,
            @Field("facebook_link") String facebook_link,
            @Field("cat_id") int cat_id,
            @Field("store_logo")File store_logo,
            @Field("store_banner")File store_banner,
            @Field("store_otherlang")String store_otherlang,
            @Field("store_tags") String store_tags
            );
}
