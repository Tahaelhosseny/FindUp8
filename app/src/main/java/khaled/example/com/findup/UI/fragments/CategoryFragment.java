package khaled.example.com.findup.UI.fragments;


import android.animation.ValueAnimator;
import android.content.Intent;
import android.databinding.adapters.ExpandableListViewBindingAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;
import khaled.example.com.findup.Helper.UI_Utility;
import khaled.example.com.findup.R;
import khaled.example.com.findup.UI.activities.EventDetailsActivity;
import khaled.example.com.findup.UI.activities.MainActivity;
import khaled.example.com.findup.UI.activities.ProductsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    private SectionedRecyclerViewAdapter sectionAdapter;
    static List<ExpandableSection> expandableSections;

    public static ExpandableSection getExpanddedSection(){
        for (int i = 0 ; i < expandableSections.size(); i++){
            if (expandableSections.get(i).expanded)
                return expandableSections.get(i);
        }
        return null;
    }

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        expandableSections = new ArrayList<>();
        expandableSections.add(new ExpandableSection(getString(R.string.events), getString(R.string.truck_description)));
        expandableSections.add(new ExpandableSection(getString(R.string.home_business), getString(R.string.truck_description)));
        expandableSections.add(new ExpandableSection(getString(R.string.craft), getString(R.string.craft_description)));
        expandableSections.add(new ExpandableSection(getString(R.string.food_truck), getString(R.string.truck_description)));
        expandableSections.add(new ExpandableSection(getString(R.string.stores), getString(R.string.truck_description)));
        expandableSections.add(new ExpandableSection(getString(R.string.booth), getString(R.string.truck_description)));
        expandableSections.add(new ExpandableSection(getString(R.string.others), getString(R.string.truck_description)));

        sectionAdapter = new SectionedRecyclerViewAdapter();

        for (int i = 0;i<expandableSections.size();i++){
            sectionAdapter.addSection(expandableSections.get(i));
        }

        RecyclerView recyclerView = getActivity().findViewById(R.id.categoryRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(sectionAdapter);

    }

    public class ExpandableSection extends StatelessSection {

        String title;
        String content;
        public boolean expanded = false;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        ExpandableSection(String title, String content) {
            super(SectionParameters.builder()
                    .itemResourceId(R.layout.cat_sec_content)
                    .headerResourceId(R.layout.cat_sec_header)
                    .build());

            this.title = title;
            this.content = content;
        }

        @Override
        public int getContentItemsTotal() {
            return expanded ? 1 : 0;
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
            final ItemViewHolder itemHolder = (ItemViewHolder) holder;
            itemHolder.contentText.setText(content);
            itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), ProductsActivity.class));
                }
            });
            //changeTextSize(itemHolder.contentText,itemHolder.contentText.getTextSize(),((ItemViewHolder) holder).defalut_text_size);
        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(final RecyclerView.ViewHolder holder) {
            final HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

            headerHolder.titleText.setText(title);
            if (headerHolder.titleText.getTextSize() > 50)
                changeTextSize(headerHolder.titleText, 30,19);

            headerHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0;i<expandableSections.size();i++){
                        if (!expandableSections.get(i).expanded)
                            changeTextSize(headerHolder.titleText, 19,30);
                        else
                            changeTextSize(headerHolder.titleText, 19,19);
                        expandableSections.get(i).expanded = false;
                    }
                    expanded = !expanded;
                    sectionAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    public void changeTextSize(final TextView textView, float PstartSize, float PendSize) {
        final float startSize = PstartSize; // Size in pixels
        final float endSize = PendSize;
        long animationDuration = 350; // Animation duration in ms
        ValueAnimator animator = ValueAnimator.ofFloat(startSize, endSize);
        animator.setDuration(animationDuration);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                textView.setTextSize(animatedValue);
            }
        });
        animator.start();
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        private View rootView;
        private TextView titleText;

        HeaderViewHolder(View view) {
            super(view);
            rootView = view;
            titleText = view.findViewById(R.id.titleText);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public View rootView;
        public TextView contentText;
        private int defalut_text_size;

        ItemViewHolder(View view) {
            super(view);

            rootView = view;
            contentText = view.findViewById(R.id.contentText);
            defalut_text_size=19;
        }
    }
}
