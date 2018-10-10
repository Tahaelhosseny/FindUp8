package khaled.example.com.findup.Helper.Remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import khaled.example.com.findup.CONST;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
/*
    public static final String BASE_URL = "http://souqbluewater.com/";
    public static final String PATH_URL ="/app/";//*/

    public static final String BASE_URL = CONST.API_FILE_DOMAIN;
    public static final String PATH_URL = CONST.API_PATH_URL;

    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}