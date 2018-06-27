package com.pic.hub.pichub.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pic.hub.pichub.IViewModelListener;
import com.pic.hub.pichub.MainActivity;
import com.pic.hub.pichub.R;
import com.pic.hub.pichub.SearchResultActivity;
import com.pic.hub.pichub.adapter.CategoryRecyclerViewAdapter;
import com.pic.hub.pichub.adapter.RecyclerViewAdapter;
import com.pic.hub.pichub.onRecyclerViewClickListener;
import com.pic.hub.pichub.viewmodels.RecentViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements onRecyclerViewClickListener {
    private RecyclerView recyclerView;
    private CategoryRecyclerViewAdapter adapter;

    List<Integer> pic_list;
    String[] pic_list_names;
    String query;

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView = view.findViewById(R.id.recyclerView_category);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pic_list = new ArrayList<>();


        TypedArray array = getResources().obtainTypedArray(R.array.pic_list);
        for (int i = 0; i < array.length(); i++) {
            int id = array.getResourceId(i, -1);
            pic_list.add(id);
        }

        pic_list_names = getResources().getStringArray(R.array.pic_list_names);

        adapter = new CategoryRecyclerViewAdapter(pic_list, getContext(), this);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClick(int clickedItemIndex) {
        query = pic_list_names[clickedItemIndex];
        Toast.makeText(getContext(), query, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(),SearchResultActivity.class);
        intent.putExtra("search_query",query);
        intent.putExtra("is_category",true);
        startActivity(intent);
    }
}
