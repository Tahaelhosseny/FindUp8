package khaled.example.com.findup.Helper.Remote;

import khaled.example.com.findup.CONST;
import khaled.example.com.findup.Helper.Remote.ResponseModel.EventResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.LoginResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.RegisterResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.StoresResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    String HASH = CONST.API_HASH;

    @GET(ApiClient.PATH_URL + "reg_login?tag=login&HashSecure=" + HASH)
    Call<LoginResponse> LoginRequest(@Query("mobile") String mobile, @Query("password") String password);

    @GET(ApiClient.PATH_URL + "stores?tag=get_all_stores&HashSecure=" + HASH)
    Call<StoresResponse> GetAllStores(@Query("account_id") int account_id);

    @GET(ApiClient.PATH_URL + "stores?tag=get_all_events&HashSecure=" + HASH)
    Call<EventResponse> GetAllEvents(@Query("account_id") int account_id);

    @POST(ApiClient.PATH_URL+"reg_login?tag=signup&HashSecure=FindUpSecure_@@01072018")
    @FormUrlEncoded
    Call<RegisterResponse> registerNewUser(@Field("user_type") String user_type , @Field("name") String user_name
                , @Field("password") String password , @Field("mobile") String user_mob , @Field("logged_type") String logged_type
                , @Field("email") String user_mail);

    @POST(ApiClient.PATH_URL+"reg_login?tag=signup&HashSecure=FindUpSecure_@@01072018")
    @FormUrlEncoded
    Call<RegisterResponse> registerNewUser(@Field("user_type") String user_type , @Field("name") String user_name
                , @Field("password") String password , @Field("mobile") String user_mob , @Field("logged_type") String logged_type
                , @Field("email") String user_mail);

}