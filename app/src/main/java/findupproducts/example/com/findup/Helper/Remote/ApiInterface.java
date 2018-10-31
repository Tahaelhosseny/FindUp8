package findupproducts.example.com.findup.Helper.Remote;
import android.arch.persistence.room.RawQuery;
import android.graphics.Bitmap;
import android.webkit.HttpAuthHandler;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.gson.JsonObject;
import com.mopub.common.util.Json;

import java.io.File;

import findupproducts.example.com.findup.CONST;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.AddCommentProductResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.AddCommentStoreResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.AskCodeResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.CreateProductResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.CreateStoreEventResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.CreateStoreResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.CurrencyResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.DeleteSavedResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.DeleteStoreProductResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.EditProfileResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.EventResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.GetAllSavedResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.LoginResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.MeasureDistanceResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.NotificationFlagResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.NotificationResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.RateResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.RegisterResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.ResetPasswordResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.SaveModelResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.SearchStoreResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.StoreAddressResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.StoreEditResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.StoreNotificationResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.StoreSettingsGetResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.StoresResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.UserSettingsResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.VerifyCodeResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    String HASH = CONST.API_HASH;
    @GET(ApiClient.PATH_URL+"reg_login?tag=login&HashSecure="+HASH)
    Call<LoginResponse> LoginRequest(@Query("mobile") String mobile,@Query("password") String password);

    @GET(ApiClient.PATH_URL+"stores?tag=get_all_stores&HashSecure="+HASH)
    Call<StoresResponse> GetAllStores(@Query("account_id") int account_id);

    @GET(ApiClient.PATH_URL+"stores?tag=get_all_events&HashSecure="+HASH)
    Call<EventResponse> GetAllEvents(@Query("account_id") int account_id);

    @GET(ApiClient.PATH_URL+"user_profile?tag=get_all_currency&HashSecure="+HASH)
    Call<CurrencyResponse> getAllCurrency();


    @GET(ApiClient.PATH_URL+"user_profile?tag=get_user_notifications&HashSecure="+HASH)
    Call<NotificationResponse> getUserNotification(@Query("account_id") String account_id);

    @GET(ApiClient.PATH_URL+"user_profile?tag=get_user_saved&HashSecure="+HASH)
    Call<GetAllSavedResponse> getUserSaved(@Query("account_id") int account_id);

    @GET(ApiClient.PATH_URL+"reg_login?tag=verify_code&HashSecure="+ HASH)
    Call<VerifyCodeResponse> checkVerifyCode(@Query("mobile") String mobile , @Query("code") String code);

    @GET(ApiClient.PATH_URL+"reg_login?tag=forget_pass&HashSecure="+HASH)
    Call<AskCodeResponse> getResetPasswordCode(@Query("mobile") String mobile);

    @GET(ApiClient.PATH_URL+"user_profile?tag=get_user_setting&HashSecure="+HASH)
    Call<UserSettingsResponse> getUserSetting(@Query("account_id") int account_id);

    @GET(ApiClient.PATH_URL+"stores?tag=get_store_setting&HashSecure="+HASH)
    Call<StoreSettingsGetResponse> getStoreSetting(@Query("store_id") int store_id);

    @GET(ApiClient.PATH_URL+"stores?tag=get_store_notifications&HashSecure="+HASH)
    Call<StoreNotificationResponse> getStoreNotification(@Query("store_id") int store_id);

    @GET(ApiClient.PATH_URL+"stores?tag=get_store_address&HashSecure="+HASH)
    Call<StoreAddressResponse> getStoreAddress(@Query("store_id") int store_id);

    @GET(ApiClient.PATH_URL+"strsearch?tag=search_stores&HashSecure="+HASH)
    Call<SearchStoreResponse> getFilteredStores(
            @Query("account_id") int account_id ,
            @Query("search_text") String search_text ,
            @Query("filter_price") String filter_price,
            @Query("filter_rate") String filter_rate,
            @Query("filter_opennow") String filter_opennow,
            @Query("filter_distance") String filter_distance,
            @Query("search_from") String search_from,
            @Query("longitude") String longitude,
            @Query("latitude") String latitude,
            @Query("filter_by") String filter_by,
            @Query("filter_byid") String filter_byid);
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


    @POST(ApiClient.PATH_URL+"user_actions?tag=add_product_comment&HashSecure="+HASH)
    @FormUrlEncoded
    Call<AddCommentProductResponse> addNewProductComment(@Field("account_id") int account_id , @Field("product_id") int product_id
            , @Field("comment") String comment);
    //Create New Event



    @POST(ApiClient.PATH_URL+"reg_login?tag=edit_profile&HashSecure="+HASH)
    @FormUrlEncoded
    Call<EditProfileResponse> editProfileData(@Field("account_id") int account_id , @Field("username") String username
            ,@Field("old_password") String old_password , @Field("new_password") String new_password , @Field("mobile") String mobile );


    @POST(ApiClient.PATH_URL+"stores?tag=set_store_currency&HashSecure="+HASH)
    @FormUrlEncoded
    Call<CurrencyResponse> setUserCurrency(@Field("currency_id") int currency_id , @Query("store_id") int store_id);


    @POST(ApiClient.PATH_URL+"user_profile?tag=set_user_distance&account_id=&HashSecure="+HASH)
    @FormUrlEncoded
    Call<MeasureDistanceResponse> setUserDistance(@Field("distance_id") int distance_id , @Query("account_id") int account_id);


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
    Call<RateResponse> rateProduct(@Query("account_id") int account_id , @Field("rate") float rate , @Field("product_id") int product_id);


    @POST(ApiClient.PATH_URL+"user_actions?tag=add_store_rate&HashSecure="+HASH)
    @FormUrlEncoded
    Call<String> rateStore(@Field("account_id") int account_id , @Field("rate") float rate , @Field("store_id") int store_id);

    @Multipart
    @POST(ApiClient.PATH_URL+"stores?tag=create_store_account&HashSecure="+HASH)
    Call<CreateStoreResponse> createNewStore(
            @Part MultipartBody.Part store_name,
            @Part MultipartBody.Part store_desc ,
            @Part MultipartBody.Part country_id ,
            @Part MultipartBody.Part city_id ,
            @Part MultipartBody.Part location_type ,
            @Part MultipartBody.Part mobile ,
            @Part MultipartBody.Part password ,
            @Part MultipartBody.Part website_link ,
            @Part MultipartBody.Part twitter_link ,
            @Part MultipartBody.Part instegram_link ,
            @Part MultipartBody.Part facebook_link,
            @Part MultipartBody.Part cat_id,
            @Part MultipartBody.Part store_logo,
            @Part MultipartBody.Part store_banner,
            @Part MultipartBody.Part store_otherlang,
            @Part MultipartBody.Part store_tags,
            @Part MultipartBody.Part user_id);

    @Multipart
    @POST(ApiClient.PATH_URL+"stores?tag=create_event&HashSecure="+HASH)
    Call<CreateStoreEventResponse> addNewStoreEvent(
            @Part MultipartBody.Part event_name,
            @Part MultipartBody.Part event_photo,
            @Part MultipartBody.Part event_start_date,
            @Part MultipartBody.Part event_days,
            @Part MultipartBody.Part event_time,
            @Part MultipartBody.Part event_description,
            @Part MultipartBody.Part event_address,
            @Part MultipartBody.Part store_id,
            @Part MultipartBody.Part event_longitude,
            @Part MultipartBody.Part event_latitude,
            @Part MultipartBody.Part event_photo_base64
    );

    @POST(ApiClient.PATH_URL+"reg_login?tag=delete_account&HashSecure="+HASH)
    @FormUrlEncoded
    Call<VerifyCodeResponse> deleAccount(@Field("mobile") String mobile);

    @POST(ApiClient.PATH_URL+"reg_login?tag=confirm_delete_account&HashSecure="+HASH)
    @FormUrlEncoded
    Call<VerifyCodeResponse> confirmDeleteAccount(@Field("mobile") String mobile , @Field("verify_code") String verify_code);

    @Multipart
    @POST(ApiClient.PATH_URL+"stores?tag=add_store_products&HashSecure="+HASH)
    Call<CreateProductResponse>  createStoreProduct(
            @Part MultipartBody.Part store_id,
            @Part MultipartBody.Part product_name,
            @Part MultipartBody.Part description,
            @Part MultipartBody.Part product_price,
            @Part MultipartBody.Part product_img);

    @POST(ApiClient.PATH_URL+"stores?tag=change_store_address&HashSecure="+HASH)
    @FormUrlEncoded
    Call<StoreAddressResponse> setStoreAddress(
            @Field("store_id") int store_id,
            @Field("longitude") double longitude,
            @Field("latitude") double latitude,
            @Field("days") String days,
            @Field("from_time") String from_time,
            @Field("to_time") String to_time
    );
    @POST(ApiClient.PATH_URL+"stores?tag=edit_store_profile&HashSecure="+HASH)
    @FormUrlEncoded
    Call<StoreEditResponse> editStoreInfo(
            @Field("store_id") int store_id,
            @Field("store_name") String store_name,
            @Field("store_mobile") String mobile,
            @Field("old_password") String old_password,
            @Field("new_password") String new_password
    );

    @POST(ApiClient.PATH_URL+"stores?tag=set_store_noti_setting&HashSecure="+HASH)
    @FormUrlEncoded
    Call<NotificationFlagResponse> setStoreNotificationFlag(
            @Field("push_noti_flag") int push,
            @Field("chat_noti_flag") int chat,
            @Field("like_noti_flag") int like,
            @Field("comment_noti_flag") int comment,
            @Query("store_id") int store_id
    );

    @POST(ApiClient.PATH_URL+"user_actions?tag=delete_save&HashSecure="+HASH)
    @FormUrlEncoded
    Call<DeleteSavedResponse> deleteSavedItem(@Field("account_id") int account_id , @Field("saved_id") int saved_id);

    @POST(ApiClient.PATH_URL+"stores?tag=delete_store_products&HashSecure="+HASH)
    @FormUrlEncoded
    Call<DeleteStoreProductResponse> deleteStoreProduct(@Field("product_id") int product_id , @Field("store_id") int store_id);


}
