package com.example.hw_1;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Fragment1 extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {
    private final static String LAST_NUM_KEY = "lastNum";
    private int lastNum = 100;
    private MyRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            lastNum = savedInstanceState.getInt(LAST_NUM_KEY, 100);
        }

        View view = inflater.inflate(R.layout.fragment1, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < lastNum; i++) {
            data.add(Integer.toString(i + 1));
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), getColNum());
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new MyRecyclerViewAdapter(getContext(), data);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        Button button = view.findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.AddNumber();
                lastNum++;
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(LAST_NUM_KEY, lastNum);
    }

    @Override
    public void onItemClick(View view, int position) {
        ((MainActivity)getActivity()).callFragment2(position + 1);
    }

    private int getColNum() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            return 3;
        }
        return 4;
    }
}