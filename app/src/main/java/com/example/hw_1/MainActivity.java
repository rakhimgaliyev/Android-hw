package com.example.hw_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    Fragment1 fragment1;
    Fragment2 fragment2;
    int lastNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            fragment1 = new Fragment1();
            getSupportFragmentManager().beginTransaction().replace(R.id.your_placeholder, fragment1).commit();
        }
        if (savedInstanceState != null) {
            lastNum = savedInstanceState.getInt("lastNum");
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("lastNum", lastNum);
    }

    public void callFragment1() {
        if (fragment1 == null) {
            fragment1 = new Fragment1();
        }

        Bundle bundle = new Bundle();
        bundle.putInt("lastNum", lastNum);
        Log.d("-----", String.valueOf(lastNum));
        fragment1.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.your_placeholder, fragment1).commit();
    }

    public void callFragment2(int pressedNumber, int lastNum) {
        this.lastNum = lastNum;

        if (fragment2 == null) {
            fragment2 = new Fragment2();
        }

        Bundle bundle = new Bundle();
        bundle.putInt("pressedNumber", pressedNumber);
        fragment2.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.your_placeholder, fragment2).commit();
    }
}


