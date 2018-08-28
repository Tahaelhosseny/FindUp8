package khaled.example.com.findup.Helper.Remote;

import java.util.List;

import khaled.example.com.findup.CONST;
import khaled.example.com.findup.Helper.Remote.ResponseModel.LoginResponse;
import khaled.example.com.findup.models.Category;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    String HASH = CONST.API_HASH;
    @GET(ApiClient.PATH_URL+"reg_login?tag=login&HashSecure="+HASH)
    Call<LoginResponse> LoginRequest(@Query("mobile") String mobile,@Query("password") String password);

    @GET(ApiClient.PATH_URL+"Api.php")
    Call<List<Category>> getAllCatSelect(@Query("function") String function);

}