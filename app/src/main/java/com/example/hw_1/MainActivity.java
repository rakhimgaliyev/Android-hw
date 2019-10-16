package com.example.hw_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private final static String FRAGMENT1_KEY = "fragment1";

    Fragment fragment1;
    Fragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            fragment1 = new Fragment1();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, fragment1).commit();
        } else {
            fragment1 = getSupportFragmentManager().getFragment(savedInstanceState, FRAGMENT1_KEY);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, FRAGMENT1_KEY, fragment1);
    }

    public void callFragment2(int pressedNumber) {
        if (fragment2 == null) {
            fragment2 = new Fragment2();
        }

        Bundle bundle = new Bundle();
        bundle.putInt("pressedNumber", pressedNumber);
        fragment2.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, fragment2).addToBackStack(null).commit();
    }
}


