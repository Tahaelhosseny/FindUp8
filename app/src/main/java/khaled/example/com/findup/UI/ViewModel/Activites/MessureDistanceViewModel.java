package khaled.example.com.findup.UI.ViewModel.Activites;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import khaled.example.com.findup.Helper.Remote.ApiClient;
import khaled.example.com.findup.Helper.Remote.ApiInterface;
import khaled.example.com.findup.Helper.Remote.ResponseModel.EditProfileResponse;
import khaled.example.com.findup.Helper.Remote.ResponseModel.MeasureDistanceResponse;
import khaled.example.com.findup.Helper.SharedPrefManger;
import khaled.example.com.findup.R;
import khaled.example.com.findup.models.Distance;
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
    public void getAllDistance(RadioGroup radio){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MeasureDistanceResponse> getMessureDistance = apiService.getAllMeasureDistance();
        getMessureDistance.enqueue(new Callback<MeasureDistanceResponse>() {
            @Override
            public void onResponse(Call<MeasureDistanceResponse> call, Response<MeasureDistanceResponse> response) {
                if(response.body().getSuccess() == 1){
                    List<Distance> distance= response.body().getUser_data();
                    check(distance , radio);
                }else{
                    Toast.makeText(mContext, "Error For Loading Distance", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MeasureDistanceResponse> call, Throwable t) {
                Toast.makeText(mContext, "Something went to error "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void check(List<Distance> distances , RadioGroup radioGroup) {
        final RadioButton[] rb = new RadioButton[distances.size()];
        radioGroup.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL
        Drawable drawable = mContext.getResources().getDrawable( R.drawable.radio_button_selector );
        for (int i = 0; i < distances.size(); i++) {
            rb[i] = new RadioButton(mContext);
            rb[i].setButtonDrawable(new StateListDrawable());
            rb[i].setLayoutParams(new RadioGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            rb[i].setGravity(Gravity.RIGHT);
            rb[i].setText(distances.get(i).getDistance_name());
            rb[i].setId(i);
            rb[i].setChecked(false);
            rb[i].setTextColor(mContext.getResources().getColor(R.color.colorBlack));
            rb[i].setTextSize(24);
            rb[i].setCompoundDrawablesWithIntrinsicBounds(null , null , drawable , null);
            Toast.makeText(mContext, ""+distances.get(i).getDistance_name(), Toast.LENGTH_SHORT).show();
            radioGroup.addView(rb[i]);

        }
    }


}
