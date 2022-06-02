package com.example.tabpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    MainFragment oneFragment;
    TwoFragment twoFragment;
    ThreeFragment threeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        oneFragment = new MainFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();

        // oneFragment부터 화면에 나타나기
        getSupportFragmentManager().beginTransaction().add(R.id.container, oneFragment).commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            // 탭 버튼을 선택할 때 이벤트
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (tab.getPosition() == 0) {
                    transaction.replace(R.id.container, oneFragment);
                 }else if (tab.getPosition() == 1) {
                    transaction.replace(R.id.container, twoFragment);
                } else if(tab.getPosition() == 2) {
                    transaction.replace(R.id.container, threeFragment);
                }
                transaction.commit();
            }

            // 다음 탭 버튼을 눌러 선택된 탭 버튼이 해체될 때 이벤트
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