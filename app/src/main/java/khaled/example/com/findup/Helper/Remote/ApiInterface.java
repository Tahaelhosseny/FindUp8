package khaled.example.com.findup.Helper.Remote;

import java.util.List;

import khaled.example.com.findup.CONST;
import khaled.example.com.findup.Helper.Remote.ResponseModel.LoginResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.StoresResponse;
import khaled.example.com.findup.models.Category;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    String HASH = CONST.API_HASH;
    @GET(ApiClient.PATH_URL+"reg_login?tag=login&HashSecure="+HASH)
    Call<LoginResponse> LoginRequest(@Query("mobile") String mobile,@Query("password") String password);

    @GET(ApiClient.PATH_URL+"stores?tag=get_all_stores&HashSecure="+HASH+"&account_id=1")
    Call<StoresResponse> GetAllStores();

    @GET(ApiClient.PATH_URL+"public_pgs?tag=home&HashSecure="+HASH+"&account_id=1")
    Call<StoresResponse> GetAllEvents();

}