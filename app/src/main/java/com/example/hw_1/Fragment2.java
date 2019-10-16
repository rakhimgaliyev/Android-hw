package com.example.hw_1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment {
    private int pressedNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            pressedNumber = getArguments().getInt("pressedNumber", 0);
        } else {
            pressedNumber = 0;
        }

        View view = inflater.inflate(R.layout.fragment2, container, false);
        TextView textView = view.findViewById(R.id.textView1);
        printNumber(textView);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("pressedNumber", pressedNumber);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            pressedNumber = savedInstanceState.getInt("pressedNumber");
        }
    }

    private void printNumber(TextView textView) {
        if (pressedNumber % 2 == 0) {
            textView.setTextColor(Color.RED);
        } else {
            textView.setTextColor(Color.BLUE);
        }
        textView.setText(String.valueOf(pressedNumber));
    }
}