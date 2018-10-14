package khaled.example.com.findup.UI.ViewModel.Activites;

import android.content.Context;
import android.widget.Toast;

import java.util.Observable;

import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.EditProfileResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.MeasureDistanceResponse;
import khaled.example.com.findup.Helper.SharedPrefManger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessureDistanceViewModel extends Observable {
    private Context mContext;
    public MessureDistanceViewModel(Context mContext){this.mContext = mContext;}
    public void setUserMessureDistance(int account_id , int distance_id){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MeasureDistanceResponse> setMessureDistance = apiService.setUserDistance(distance_id , account_id);
        setMessureDistance.enqueue(new Callback<MeasureDistanceResponse>() {
            @Override
            public void onResponse(Call<MeasureDistanceResponse> call, Response<MeasureDistanceResponse> response) {
                if(response.body().getSuccess() == 1){
                    SharedPrefManger.setDistanceTypeId(distance_id);
                    SharedPrefManger.setDistanceText(response.body().getUser_data().get(0).getDistance_name());
                }else {
                    Toast.makeText(mContext, ""+response.body().getError_msg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MeasureDistanceResponse> call, Throwable t) {
                Toast.makeText(mContext, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getAllDistance(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MeasureDistanceResponse> getMessureDistance = apiService.getAllMeasureDistance();
        getMessureDistance.enqueue(new Callback<MeasureDistanceResponse>() {
            @Override
            public void onResponse(Call<MeasureDistanceResponse> call, Response<MeasureDistanceResponse> response) {

            }

            @Override
            public void onFailure(Call<MeasureDistanceResponse> call, Throwable t) {

            }
        });
    }
}
