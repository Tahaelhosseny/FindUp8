package khaled.example.com.findup.UI.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.warkiz.tickseekbar.OnSeekChangeListener;
import com.warkiz.tickseekbar.SeekParams;
import com.warkiz.tickseekbar.TickSeekBar;

import co.ceryle.radiorealbutton.RadioRealButton;
import co.ceryle.radiorealbutton.RadioRealButtonGroup;
import khaled.example.com.findup.R;
import khaled.example.com.findup.models.FilterQueries;

import static khaled.example.com.findup.UI.activities.MainActivity.filterData;

/**
 * Created by khaled on 8/1/18.
 */

public class FilterFragment extends Fragment {
    RadioRealButtonGroup priceRadio , timeRadio;
    RadioGroup rateGroup;
    TickSeekBar distanceSeekBar;
    public FilterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button applyBtn = getActivity().findViewById(R.id.applyBtn);
        priceRadio = getActivity().findViewById(R.id.priceRadioGroup);
        timeRadio = getActivity().findViewById(R.id.timeRadioGroup);
        rateGroup = getActivity().findViewById(R.id.ratingBar);
        distanceSeekBar = getActivity().findViewById(R.id.distance_seekbar);
        getRatingSelected(rateGroup);getRadioPriceSelected(priceRadio);getTimeRadio(timeRadio);
        getDistanceSeekBarSelected(distanceSeekBar);
        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Price : "+ filterData.getFilter_price(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Time : "+ filterData.getFilter_opennow(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Rate : "+ filterData.getFilter_rate(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Distance : "+ filterData.getFilter_distance(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Sort " + (filterData.getFilter_by() + " " + filterData.getFilter_byid()), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getRadioPriceSelected(RadioRealButtonGroup priceRadio) {
        priceRadio.setOnClickedButtonListener(new RadioRealButtonGroup.OnClickedButtonListener() {
            @Override
            public void onClickedButton(RadioRealButton button, int position) {
                filterData.setFilter_price(String.valueOf(position+1));
            }
        });
    }
    private void getTimeRadio(RadioRealButtonGroup timeRadio){
        timeRadio.setOnClickedButtonListener(new RadioRealButtonGroup.OnClickedButtonListener() {
            @Override
            public void onClickedButton(RadioRealButton button, int position) {
                if (position == 0){
                    filterData.setFilter_opennow("Open Now");
                }else{
                    filterData.setFilter_opennow("Open at");
                }
            }
        });
    }
    private void getRatingSelected(RadioGroup rateGroup){
        rateGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rate1:
                        filterData.setFilter_rate("1");break;
                    case R.id.rate2:
                        filterData.setFilter_rate("2");break;
                    case R.id.rate3:
                        filterData.setFilter_rate("3");break;
                    case R.id.rate4:
                        filterData.setFilter_rate("4");break;
                    case R.id.rate5:
                        filterData.setFilter_rate("5");break;
                    default:
                        filterData.setFilter_rate("");
                        break;
                }
            }
        });

    }
    private void getDistanceSeekBarSelected(TickSeekBar distanceSeekBar){
        distanceSeekBar.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                if (seekParams.thumbPosition == 3){
                    filterData.setFilter_distance("50000 KM");
                }else {
                    filterData.setFilter_distance(seekParams.tickText);
                }
                }

            @Override
            public void onStartTrackingTouch(TickSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(TickSeekBar seekBar) {

            }
        });
    }
}
