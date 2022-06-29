package com.android.practicetablayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.android.practicetablayout.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        OneFragment oneFragment = new OneFragment();
        TwoFragment twoFragment = new TwoFragment();
        ThreeFragment threeFragment = new ThreeFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.contentLayout, oneFragment);
        transaction.commit();

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            // 탭 버튼을 선택할 때 이벤트
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (tab.getPosition() == 0) {
                    transaction.replace(R.id.contentLayout, oneFragment);
                } else if (tab.getPosition() == 1) {
                    transaction.replace(R.id.contentLayout, twoFragment);
                } else if (tab.getPosition() == 2) {
                    transaction.replace(R.id.contentLayout, threeFragment);
                }
                transaction.commit();
            }

            // 다른 탭 버튼을 눌러 선택된 탭 버튼이 해제될 때 이벤트
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            // 선택된 탭 버튼을 다시 선택할 때 이벤트
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}