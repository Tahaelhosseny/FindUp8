package findupproducts.example.com.findup.UI.ViewModel.Activites;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import findupproducts.example.com.findup.Helper.Remote.ApiClient;
import findupproducts.example.com.findup.Helper.Remote.ApiInterface;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.EditProfileResponse;
import findupproducts.example.com.findup.Helper.Remote.ResponseModel.MeasureDistanceResponse;
import findupproducts.example.com.findup.Helper.SharedPrefManger;
import findupproducts.example.com.findup.R;
import findupproducts.example.com.findup.models.Distance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessureDistanceViewModel extends Observable {
    private Context mContext;
    public MessureDistanceViewModel(Context mContext){this.mContext = mContext;}
    public void setUserMessureDistance(int account_id , int distance_id) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MeasureDistanceResponse> setMessureDistance = apiService.setUserDistance(distance_id, account_id);
        setMessureDistance.enqueue(new Callback<MeasureDistanceResponse>() {
            @Override
            public void onResponse(Call<MeasureDistanceResponse> call, Response<MeasureDistanceResponse> response) {
                if (response.body().getSuccess() == 1) {
                    SharedPrefManger.setDistanceTypeId(distance_id);
                    SharedPrefManger.setDistanceText(response.body().getUser_data().get(0).getDistance_name());
                    Toast.makeText(mContext, "Your Measure Updated Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "" + response.body().getError_msg(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<MeasureDistanceResponse> call, Throwable t) {
                Toast.makeText(mContext, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
