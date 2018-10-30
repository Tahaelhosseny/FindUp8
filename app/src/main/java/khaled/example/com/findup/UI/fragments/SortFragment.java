package khaled.example.com.findup.UI.fragments;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.ViewModel.Fragments.SortFilterViewModel;
import khaled.example.com.findup.UI.ViewModel.Fragments.UserNotificatonViewModel;
import khaled.example.com.findup.databinding.FragmentSortBinding;

import static khaled.example.com.findup.UI.activities.MainActivity.filterData;

public class SortFragment extends Fragment {

    FragmentSortBinding binding;
    SortFilterViewModel viewModel;

    public SortFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sort, container, false);
        View view = binding.getRoot();
        viewModel = new SortFilterViewModel(view.getContext());
        binding.setSortFrag(viewModel);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.InitCatRecycler(binding.categoryFilterRecycler);
        binding.byLikedId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterData.setFilter_byid(""); filterData.setFilter_by("liked");
                binding.byLikedId.setTextColor(Color.parseColor("#F24E8E"));
                binding.bySavedId.setTextColor(getResources().getColor(R.color.tw__composer_deep_gray));
                binding.byProductId.setTextColor(getResources().getColor(R.color.tw__composer_deep_gray));
            }
        });
        binding.byProductId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterData.setFilter_byid(""); filterData.setFilter_by("product");
                binding.byProductId.setTextColor(Color.parseColor("#F24E8E"));
                binding.bySavedId.setTextColor(getResources().getColor(R.color.tw__composer_deep_gray));
                binding.byLikedId.setTextColor(getResources().getColor(R.color.tw__composer_deep_gray));
            }
        });
        binding.bySavedId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterData.setFilter_byid(""); filterData.setFilter_by("saved");
                binding.bySavedId.setTextColor(Color.parseColor("#F24E8E"));
                binding.byLikedId.setTextColor(getResources().getColor(R.color.tw__composer_deep_gray));
                binding.byProductId.setTextColor(getResources().getColor(R.color.tw__composer_deep_gray));

            }
        });
        binding.applySort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(filterData.getFilter_distance())){
                    Toast.makeText(getActivity(), "Please Specify Distance", Toast.LENGTH_SHORT).show();
                    return;
                } if(TextUtils.isEmpty(filterData.getFilter_rate())){
                    Toast.makeText(getActivity(), "Please Specify Rate", Toast.LENGTH_SHORT).show();
                    return;
                } if(TextUtils.isEmpty(filterData.getFilter_opennow())){
                    Toast.makeText(getActivity(), "Please Specify Time", Toast.LENGTH_SHORT).show();
                    return;
                } if(TextUtils.isEmpty(filterData.getFilter_price())){
                    Toast.makeText(getActivity(), "Please Specify Price", Toast.LENGTH_SHORT).show();
                    return;
                } if(TextUtils.isEmpty(filterData.getFilter_by())){
                    Toast.makeText(getActivity(), "Please Specify Filter By", Toast.LENGTH_SHORT).show();
                    return;
                } if(filterData.getFilter_by().equals("Category") && TextUtils.isEmpty(filterData.getFilter_byid())){
                    Toast.makeText(getActivity(), "Please Specify the type of category", Toast.LENGTH_SHORT).show();
                    return;
                }
                viewModel.getFilteredData();
            }
        });
    }
}