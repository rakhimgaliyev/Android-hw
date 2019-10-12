package com.example.hw_1;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.content.Context;
import android.graphics.Color;

import androidx.fragment.app.Fragment;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Fragment1 extends Fragment implements OnClickListener {
    private TableLayout tableLayout;
    private int lastNum;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int colNum = getColNum();
        if (savedInstanceState != null) {
            lastNum = savedInstanceState.getInt("lastNum");
        } else {
            lastNum = 30;
        }
        Bundle arguments = getArguments();
        if (arguments != null) {
            lastNum = arguments.getInt("lastNum");
        }

        View view = inflater.inflate(R.layout.fragment1, container, false);
        tableLayout = view.findViewById(R.id.table);
        tableLayout.setVerticalScrollBarEnabled(true);

        for (int i = 0; i <= lastNum / colNum - 1; i++) {
            addRow(tableLayout, colNum, i * colNum + 1, (i + 1) * colNum);
        }
        addRow(tableLayout, colNum,(lastNum / colNum) * colNum + 1, lastNum);

        setButton(tableLayout);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("lastNum", lastNum);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            lastNum = savedInstanceState.getInt("lastNum");
        }
    }

    @Override
    public void onClick(View v) {
        String text = ((Button) v).getText().toString();
        if (text.equals("Add number")) {
            addNumber(tableLayout);
            setButton(tableLayout);
        } else {
            int pressedNumber = Integer.parseInt(text);
            ((MainActivity)getActivity()).callFragment2(pressedNumber, lastNum);
        }
    }

    private int getColNum() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            return 3;
        }
        return 4;
    }

    private int getColWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels / getColNum();
    }

    private int getColHeigth() {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (48 * scale + 0.5f);
    }

    private LayoutParams getColParams() {
        int width = getColWidth();
        int height = getColHeigth();
        return new LayoutParams(width, height);
    }

    private void addNumber(TableLayout tableLayout) {
        TableRow lastRow = (TableRow) tableLayout.getChildAt(tableLayout.getChildCount() - 1);
        Button theButton = (Button) lastRow.getChildAt(lastRow.getChildCount() - 1);
        theButton.setText(String.valueOf(lastNum + 1));
        if (lastNum % 2 == 0) {
            theButton.setTextColor(Color.RED);
        } else {
            theButton.setTextColor(Color.BLUE);
        }
        lastNum++;
    }

    private void setButton(TableLayout tableLayout) {
        Context context = getContext();

        Button button = new Button(context);
        button.setText("Add number");
        button.setLayoutParams(getColParams());
        button.setGravity(Gravity.CENTER);
        button.setTextColor(Color.BLACK);

        if (lastNum % getColNum() == 0) {
            TableRow tableRow = new TableRow(context);
            tableRow.addView(button);
            tableLayout.addView(tableRow);
        } else {
            TableRow lastRow = (TableRow) tableLayout.getChildAt(tableLayout.getChildCount() - 1);
            lastRow.addView(button);
        }
        button.setOnClickListener(this);
    }

    private void addRow(TableLayout tableLayout, int colNum, int first_num, int last_num) {
        Context context = getContext();
        TableRow tableRow = new TableRow(context);

        LayoutParams params = getColParams();

        for (int i = first_num; i <= last_num; i++) {
            Button button = new Button(context);
            button.setText(String.valueOf(i));
            button.setLayoutParams(params);
            button.setGravity(Gravity.CENTER);
            if (i % 2 == 0) {
                button.setTextColor(Color.RED);
            } else {
                button.setTextColor(Color.BLUE);
            }
            button.setOnClickListener(this);
            tableRow.addView(button);
        }
        tableLayout.addView(tableRow);
    }
}
