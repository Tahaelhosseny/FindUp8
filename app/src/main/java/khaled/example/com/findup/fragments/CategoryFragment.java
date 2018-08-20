package khaled.example.com.findup.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;
import khaled.example.com.findup.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    private SectionedRecyclerViewAdapter sectionAdapter;
    List<ExpandableSection> expandableSections;

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

    private class ExpandableSection extends StatelessSection {

        String title;
        String content;
        public boolean expanded = false;

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
        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            final HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

            headerHolder.titleText.setText(title);
            headerHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0;i<expandableSections.size();i++){
                        expandableSections.get(i).expanded = false;
                    }
                    expanded = !expanded;
                    sectionAdapter.notifyDataSetChanged();
                }
            });
        }
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

    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private View rootView;
        private TextView contentText;

        ItemViewHolder(View view) {
            super(view);

            rootView = view;
            contentText = view.findViewById(R.id.contentText);
        }
    }
}
