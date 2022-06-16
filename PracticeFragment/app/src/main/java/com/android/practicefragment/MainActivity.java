package com.android.practicefragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OneFragment oneFragment = new OneFragment();
        // androidx.fragment.app.FragmentManager
        FragmentManager manager = getSupportFragmentManager();
        // androidx.fragment.app.FragmentTransaction
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(R.id.main_content, oneFragment);
        ft.commit();

    }
}