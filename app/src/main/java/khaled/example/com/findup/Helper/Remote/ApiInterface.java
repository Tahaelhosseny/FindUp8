package khaled.example.com.findup.Helper.Remote;

import java.util.List;

import khaled.example.com.findup.CONST;
import khaled.example.com.findup.Helper.Remote.ResponseModel.AddCommentStoreResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.CreateStoreEventResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.LoginResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.RegisterResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.StoresResponse;
import khaled.example.com.findup.models.Category;
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

    @GET(ApiClient.PATH_URL+"stores?tag=get_all_stores&HashSecure="+HASH+"&account_id=1")
    Call<StoresResponse> GetAllStores();

    @GET(ApiClient.PATH_URL+"public_pgs?tag=home&HashSecure="+HASH+"&account_id=1")
    Call<StoresResponse> GetAllEvents();

    @POST(ApiClient.PATH_URL+"reg_login?tag=signup&HashSecure=FindUpSecure_@@01072018")
    @FormUrlEncoded
    Call<RegisterResponse> registerNewUser(@Field("user_type") String user_type , @Field("name") String user_name
                , @Field("password") String password , @Field("mobile") String user_mob , @Field("logged_type") String logged_type
                , @Field("email") String user_mail);


    @POST(ApiClient.PATH_URL+"user_actions?tag=add_store_comment&HashSecure=FindUpSecure_@@01072018")
    @FormUrlEncoded
    Call<AddCommentStoreResponse> addNewStoreComment(@Field("account_id") String account_id , @Field("store_id") int store_id
     , @Field("comment") String comment);


    //Create New Event
    @POST(ApiClient.PATH_URL+"stores?tag=create_event&HashSecure=FindUpSecure_@@01072018")
    @FormUrlEncoded
    Call<CreateStoreEventResponse> addNewStoreEvent(@Field("event_name") String event_name , @Field("event_start_date") String event_start_date
            , @Field("event_days") String event_days , @Field("event_time") String event_time
            , @Field("event_description") String event_description , @Field("event_address") String event_address
            , @Field("store_id") int store_id , @Field("event_longitude") double event_longitude
            , @Field("event_latitude") double event_latitude , @Field("event_photo") String event_photo);

}