package com.example.hw_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private final static String FRAGMENT1_KEY = "fragment1";

    Fragment fragment1;

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
}


