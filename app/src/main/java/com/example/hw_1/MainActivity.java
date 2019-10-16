package com.example.hw_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Fragment1 fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            fragment1 = new Fragment1();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment1, fragment1).commit();
        }
    }
}


